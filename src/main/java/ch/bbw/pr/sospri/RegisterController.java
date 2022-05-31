package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.member.RegisterMember;
/**
 * @author marc.welz
 * @version 31.05.2022
 */
@Controller
public class RegisterController {
	@Autowired
	MemberService memberservice;

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(RegisterMember registerMember, Model model) {
		System.out.println("postRequestRegistMembers: registerMember");
		System.out.println(registerMember);

		memberservice.add(new Member(registerMember,registerMember.getPrename()+"03", "member"));

		return "registerconfirmed";
	}
}