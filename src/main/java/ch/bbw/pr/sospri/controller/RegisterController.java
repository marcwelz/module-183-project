package ch.bbw.pr.sospri.controller;

import ch.bbw.pr.sospri.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.service.MemberService;
import ch.bbw.pr.sospri.model.RegisterMember;

import javax.validation.Valid;

/**
 * @author marc.welz
 * @version 31.05.2022
 */
@Controller
@Slf4j
public class RegisterController {

	@Autowired
	MemberService memberservice;

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		log.info("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult bindingResult, Model model) {
		log.info("postRequestRegistMembers");

		if(bindingResult.hasErrors()) {
			log.info("binding result has an error " + bindingResult.toString());
			return "register";
		}

		if(registrationValidate(registerMember)) {
			log.info("registration failed");
			model.addAttribute("message", registerMember.getMessage());
			return "register";
		}

		String username = registerMember.getPrename()+"."+registerMember.getLastname();
		Member tmpMember = new Member(registerMember, username, "member");
		memberservice.add(tmpMember);
		model.addAttribute("member", tmpMember);

		return "registerconfirmed";
	}

	private boolean registrationValidate(RegisterMember registerMember) {


		if(!registerMember.getPassword().equals(registerMember.getConfirmation())) {
			registerMember.setMessage("Password and password confirmation is not equal");
			return true;
		}
		if(memberservice.getByUserName(registerMember.getPrename().toLowerCase() +"."+registerMember.getLastname().toLowerCase()) != null) {
			log.info("User allready exists, choose other first- or lastname.");
			registerMember.setMessage("Username " + registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase() + " allready exists");

			return true;
		}

		//TODO deleted for easy testing

		/*int passwordStrength = calculatePasswordStrength(registerMember.getPassword());
		if(passwordStrength < 10) {
			log.info("User allready exists, choose other first- or lastname.");
			registerMember.setMessage("The Password is to weak. Password strength: " + passwordStrength + " but the strength should be at least 10");

			return true;
		}*/

		return false;
	}

	private static int calculatePasswordStrength(String password){

		int iPasswordScore = 0;
		if( password.length() < 8 ) return 0;
		else if( password.length() >= 10 ) iPasswordScore += 2;
		else iPasswordScore += 1;

		if( password.matches("(?=.*[0-9]).*") ) iPasswordScore += 2;
		if( password.matches("(?=.*[a-z]).*") ) iPasswordScore += 2;
		if( password.matches("(?=.*[A-Z]).*") ) iPasswordScore += 2;
		if( password.matches("(?=.*[~!@#$%^&*()_-]).*") ) iPasswordScore += 2;

		return iPasswordScore;

	}
}