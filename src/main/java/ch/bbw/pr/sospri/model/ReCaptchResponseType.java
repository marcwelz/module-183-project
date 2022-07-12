package ch.bbw.pr.sospri.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marc Welu
 * @version juni 2022
 */

@Getter
@Setter
public class ReCaptchResponseType {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}
