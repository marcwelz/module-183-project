package ch.bbw.pr.sospri.google2fa;

import ch.bbw.pr.sospri.model.Member;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Marc Welu
 * @version juni 2022
 */

public class QrCodeGenerator {
    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    public static String generateQRUrl(Member member) {
        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", "SoSpri", member.getUsername(), member.getSecret(), "SoSpri"), StandardCharsets.UTF_8);
    }
}
