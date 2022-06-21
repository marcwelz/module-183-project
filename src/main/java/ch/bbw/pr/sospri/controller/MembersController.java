package ch.bbw.pr.sospri.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.bbw.pr.sospri.model.Member;
import ch.bbw.pr.sospri.service.MemberService;

import javax.validation.constraints.Size;

/**
 * @author marc.welz
 * @version 31.05.2022
 */
@Controller
@Slf4j
public class MembersController {
	@Autowired
	MemberService memberservice;

	@GetMapping("/get-members")
	@PreAuthorize("hasRole('admin')")
	public String getRequestMembers(Model model) {
		log.info("getRequestMembers");
		model.addAttribute("members", memberservice.getAll());
		return "members";
	}
	
	@GetMapping("/edit-member")
	@PreAuthorize("hasRole('admin')")
	public String editMember(@RequestParam(name="id", required = true) long id, Model model) {
		Member member = memberservice.getById(id);
		log.info("editMember get: " + member);
		model.addAttribute("member", member);
		return "editmember";
	}

	@PostMapping("/edit-member")
	@PreAuthorize("hasRole('admin')")
	public String editMember(Member member, Model model) {
		log.info("editMember post: edit member" + member);
		Member value = memberservice.getById(member.getId());
		value.setAuthority(member.getAuthority());
		log.info("editMember post: update member" + value);
		memberservice.update(member.getId(), value);
		return "redirect:/get-members";
	}

	@GetMapping("/delete-member")
	@PreAuthorize("hasRole('admin')")
	public String deleteMember(@RequestParam(name="id", required = true) long id, Model model) {
		log.info("deleteMember: " + id);
		memberservice.deleteById(id);
		return "redirect:/get-members";
	}
}
