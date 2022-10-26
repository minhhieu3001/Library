package vn.com.ntqsolution.authenticate;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.exception.TokenException;
import vn.com.ntqsolution.response.JwtResponse;
import vn.com.ntqsolution.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@AllArgsConstructor
public class AuthAspect {

    @Before(value = "execution(* vn.com.ntqsolution.controller.*.*(..))")
    public void authorizeApi(JoinPoint joinPoint) {
        Authorized authorized = getAnnotationAuthorized(joinPoint);
        if (authorized == null) {
            return;
        }

        checkValidRoleAnotation(authorized.role());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new TokenException("TOKEN NOT FOUND", ResponseCode.INVALID_TOKEN);
        }
        HttpServletRequest request = requestAttributes.getRequest();

        String token = request.getHeader("Authorization");

        checkValidToken(token, authorized.role());
    }

    private Authorized getAnnotationAuthorized(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(Authorized.class);
    }

    public void checkValidRoleAnotation(String role) {
        if (role == null) {
            throw new TokenException("NOT FOUND ROLE");
        }
        if (!role.equals(Constant.USER_ROLES.ACCOUNT_ADMIN) && !role.equals(Constant.USER_ROLES.ACCOUNT_ROOT)) {
            throw new TokenException("WRONG ROLE");
        }
    }

    public void checkValidToken(String token, String authorizeRole) {
        if (token == null || token.isEmpty()) {
            throw new TokenException("INVALID TOKEN", ResponseCode.INVALID_TOKEN);
        }
        JwtResponse jwtResponse = JwtUtil.parseToken(token);
        if (jwtResponse == null) {
            throw new TokenException("INVALID TOKEN", ResponseCode.INVALID_TOKEN);
        }
        String role = jwtResponse.getRole();
        if (!role.equals(authorizeRole)) {
            throw new TokenException("WRONG ROLE", ResponseCode.INVALID_TOKEN);
        }
        if (JwtUtil.isTokenExpired(token)) {
            throw new TokenException("TOKEN EXPIRED", ResponseCode.TOKEN_EXPIRED);
        }
    }

}
