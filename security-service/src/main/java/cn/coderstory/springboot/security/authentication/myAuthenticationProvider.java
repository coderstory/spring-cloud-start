package cn.coderstory.springboot.security.authentication;

import cn.coderstory.springboot.security.exception.VerificationCodeException;
import cn.coderstory.springboot.security.service.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义用户登入验证
 */
@Component
public class myAuthenticationProvider extends DaoAuthenticationProvider {
    private HttpServletRequest httpServletRequest;

    public myAuthenticationProvider(UserDetailsService userDetailsService, HttpServletRequest request, PasswordEncoder passwordEncoder) {
        this.httpServletRequest = request;
        // 注意是使用set
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if ("/login".equals(httpServletRequest.getRequestURI())) {
            verificationCode(httpServletRequest);
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    private void verificationCode(HttpServletRequest httpServletRequest) throws VerificationCodeException {
        String requestCode = httpServletRequest.getParameter("captcha");
        HttpSession session = httpServletRequest.getSession();
        String savedCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(savedCode)) {
            session.removeAttribute("captcha");
        }
        if (StringUtils.isEmpty(savedCode) || StringUtils.isEmpty(requestCode) || !savedCode.equals(requestCode)) {
            throw new VerificationCodeException();
        }

    }

}
