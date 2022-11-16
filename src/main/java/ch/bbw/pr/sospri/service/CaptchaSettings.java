package ch.bbw.pr.sospri.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SoSpriBasisProjekt_V03 - CaptchaSettings
 *
 * @author Robin Kübler
 * @version 02.07.2022
 */

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {
    private String site;
    private String secret;
}
