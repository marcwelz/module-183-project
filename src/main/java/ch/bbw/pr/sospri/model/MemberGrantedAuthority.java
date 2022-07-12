package ch.bbw.pr.sospri.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Marc Welu
 * @version juni 2022
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberGrantedAuthority implements GrantedAuthority {
    private String authority;
}
