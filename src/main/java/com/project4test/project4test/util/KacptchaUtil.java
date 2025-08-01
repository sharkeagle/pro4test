package com.project4test.project4test.util;

import com.google.code.kaptcha.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class KacptchaUtil {
    private final Producer producer;
    private final RedisUtil redisUtil;

    public  void createKacpcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store");
        response.setContentType("image/jpeg");
        // 文字验证码
        String text = producer.createText();
        // 图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存验证码到redis
        String KacptchaKey = "Kacptcha:"+ UUID.randomUUID();
        log.info("KacptchaKey:{}",KacptchaKey);
        redisUtil.set(KacptchaKey,text,60, TimeUnit.SECONDS);
        response.setHeader("KacptchaKey",KacptchaKey);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }
    public boolean checkKacptcha(String KacptchaKey,String checkCode) {
        String text = redisUtil.get(KacptchaKey);
        if(text == null) {
            return false;
        }
        redisUtil.delete(KacptchaKey);
        return text.equalsIgnoreCase(checkCode);
    }
}
