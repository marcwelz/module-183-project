package ch.bbw.pr.sospri.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author marc.welz
 * @version 31.05.2022
 */

@ToString
@Getter
@Setter
public class RegisterMember {
	private String prename;
	private String lastname;
	private String password;
	private String confirmation;

	private String message;
}
