package org.roc.roc.auth.controller;

import org.apache.commons.lang.StringUtils;
import org.roc.roc.common.entity.RocResponse;
import org.roc.roc.common.exception.RocAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public RocResponse signout(HttpServletRequest request) throws RocAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        RocResponse rocResponse = new RocResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new RocAuthException("退出登录失败");
        }
        return rocResponse.message("退出登录成功");
    }
}
