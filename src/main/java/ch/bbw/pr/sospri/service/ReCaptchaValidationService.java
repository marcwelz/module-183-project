package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.controller.MembersController;
import ch.bbw.pr.sospri.model.ReCaptchResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaValidationService {

    Logger logger = LoggerFactory.getLogger(MembersController.class);
    @Autowired
    private ch.bbw.pr.sospri.service.CaptchaSettings captchaSettings;

    private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    public boolean validateCaptcha(String captchaResponse) {
        logger.debug("ReCaptchaValidationService:validateCaptcha(), called");
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", captchaSettings.getSecret());
        requestMap.add("response", captchaResponse);

        ReCaptchResponseType apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptchResponseType.class);

        if (apiResponse == null) {
            logger.error("ReCaptchaValidationService:validateCaptcha(), apiResponse is null");
            return false;
        }
        logger.info("ReCaptchaValidationService:validateCaptcha(), captcha was successful");
        return Boolean.TRUE.equals(apiResponse.isSuccess());
    }
}
