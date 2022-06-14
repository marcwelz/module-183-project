package ch.bbw.pr.sospri.security;

import lombok.Getter;w
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@ToString
public class MemberGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 5L;
    private String authority;

    public MemberGrantedAuthority(String authority) {
        super();
        this.authority = authority;
    }
}
