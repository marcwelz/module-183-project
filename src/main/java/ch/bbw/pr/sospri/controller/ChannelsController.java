package ch.bbw.pr.sospri.controller;

import java.util.Date;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.model.Member;
import ch.bbw.pr.sospri.service.MemberService;
import ch.bbw.pr.sospri.model.message.Message;
import ch.bbw.pr.sospri.service.MessageService;
/**
 * @author marc.welz
 * @version 31.05.2022
 */
@Controller
@Slf4j
public class ChannelsController {
	@Autowired
	MessageService messageservice;
	@Autowired
	MemberService memberservice;

	@GetMapping("/get-channel")
	public String getRequestChannel(Model model) {
		log.info("getRequestChannel");
		model.addAttribute("messages", messageservice.getAll());
		
		Message message = new Message();
		message.setContent("Der zweite Pfeil trifft immer.");
		log.info("message: " + message);
		model.addAttribute("message", message);
		return "channel";
	}

	@PostMapping("/add-message")
	public String postRequestChannel(Model model, @ModelAttribute @Valid Message message, BindingResult bindingResult) {
		log.info("postRequestChannel(): message: " + message.toString());
		if(bindingResult.hasErrors()) {
			log.info("postRequestChannel(): has Error(s): " + bindingResult.getErrorCount());
			model.addAttribute("messages", messageservice.getAll());
			return "channel";
		}
		// Hack solange es kein authenticated member hat
		Member tmpMember = memberservice.getById(4L);
		message.setAuthor(tmpMember.getPrename() + " " + tmpMember.getLastname());
		message.setOrigin(new Date());
		log.info("message: " + message);
		messageservice.add(message);
		
		return "redirect:/get-channel";
	}
}
