//package com.codesoom.assignment.interceptors;
//
//import com.codesoom.assignment.application.AuthenticationService;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AuthenticationInterceptor implements HandlerInterceptor {
//    private AuthenticationService authenticationService;
//
//    public AuthenticationInterceptor(
//            AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws Exception {
//        return filterWithPathAndMethod(request) ||
//                doAuthentication(request, response);
//    }
//
//    private boolean filterWithPathAndMethod(HttpServletRequest request) {
//        String path = request.getRequestURI();
//        if (!path.startsWith("/products")) {
//            return true;
//        }
//
//        String method = request.getMethod();
//        if (method.equals("GET")) {
//            return true;
//        }
//
//        if (method.equals("OPTIONS")) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean doAuthentication(HttpServletRequest request,
//                                     HttpServletResponse response)
//            throws IOException {
//        String authorization = request.getHeader("Authorization");
//
//        if (authorization == null) {
//            response.sendError(HttpStatus.UNAUTHORIZED.value());
//            return false;
//        }
//
//        String accessToken = authorization.substring("Bearer ".length());
//        Long userId = authenticationService.parseToken(accessToken);
//
//        request.setAttribute("userId", userId);
//
//        return true;
//    }
//}
