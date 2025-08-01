package com.project4test.project4test.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.qo.UserLoginQo;
import com.project4test.project4test.qo.UserRegisterQo;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final Producer producer;
    @RequestMapping("/login")
    public Result<UserVo> login(UserLoginQo loginQo) {
        return userService.login(loginQo);
    }
    @RequestMapping(value = "/captchat.jpg")
    public void createKacptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control","no-store");
        response.setContentType("image/jpeg");
        // 文字验证码
        String text = producer.createText();
        // 图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存验证码到session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @SaCheckLogin
    @RequestMapping("/logout")
    public Result<String> logout() {
        return userService.logout();
    }

    @RequestMapping("/register")
    @SentinelResource(value = "register")
    public Result<String> register(UserRegisterQo userRegisterQo) {
        return userService.register(userRegisterQo);
    }

    @SaCheckLogin
    @RequestMapping("/unregister")
    public Result<String> unregister() {
        return userService.unregister();
    }


}
