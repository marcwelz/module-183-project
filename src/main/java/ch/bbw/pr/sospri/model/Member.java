package ch.bbw.pr.sospri.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.jboss.aerogear.security.otp.api.Base32;

/**
 * @author marc.welz
 * @version 31.05.2022
 */

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
	@Id
	@GeneratedValue(generator = "generatorMember", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "generatorMember", initialValue = 20)
	private Long id;

	@NotEmpty(message = "prename may not be empty")
	@Size(min = 2, max = 25, message = "The length of the first name must be 2 to 25 characters!")
	private String prename;

	@NotEmpty(message = "lastname may not be empty")
	@Size(min = 2, max = 25, message = "The length of the last name must be 2 to 25 characters!")
	private String lastname;

	@NotEmpty(message = "password may not be empty")
	private String password;

	private String username;
	private String authority;

	private boolean isUsing2FA = true;
	private String secret;

	public Member() {
		super();
		this.secret = Base32.random();
	}

	public Member(RegisterMember registerMember,String username, String authority) {
		this.prename = registerMember.getPrename();
		this.lastname = registerMember.getLastname();
		this.password = registerMember.getPassword();
		this.username = username;
		this.authority = authority;
	}
}
