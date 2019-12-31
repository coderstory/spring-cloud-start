package cn.coderstory.springboot.security.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/app/api")
public class CaptchaController {
    private Producer producer;

    public CaptchaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String capText = producer.createText();
        request.getSession().setAttribute("captcha", capText);
        BufferedImage image = producer.createImage(capText);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
