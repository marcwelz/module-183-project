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
		System.out.println("postRequestRegistMembers");

		if(registrationValidate(registerMember)) {
			System.out.println("registration failed");
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
			System.out.println("User allready exists, choose other first- or lastname.");
			registerMember.setMessage("Username " + registerMember.getPrename().toLowerCase() + "." + registerMember.getLastname().toLowerCase() + " allready exists");

			return true;
		}

		return false;
	}
}