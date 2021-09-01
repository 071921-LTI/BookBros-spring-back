package com.bookbros.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookbros.annotations.Secure;
import com.bookbros.exceptions.AuthenticationException;
import com.bookbros.exceptions.AuthorizationException;
import com.bookbros.models.User;
import com.bookbros.services.UserService;

@Component
@Aspect
public class SecurityAspect {
	
	private HttpServletRequest request;
	private UserService us;
	
	@Autowired
	public SecurityAspect(HttpServletRequest request, UserService us) {
		this.request = request;
		this.us = us;
	}

	// @Around("@annotation(com.bookbros.annotations.Secured)")
	// public Object secureEndpoint(ProceedingJoinPoint pjp) throws Throwable {
		
	// 	/*
	// 	 * Retrieve the method with @Secured
	// 	 * Retrieve the annotation itself
	// 	 */
	// 	Method method =((MethodSignature) pjp.getSignature()).getMethod();
	// 	Secure securedAnnotation = method.getAnnotation(Secure.class);
		
	// 	/*
	// 	 * Retrieving allowed roles for that controller method
	// 	 */
	// 	List<String> allowedRoles = Arrays.asList(securedAnnotation.allowedRoles());
		
	// 	/*
	// 	 * Verify user role
	// 	 * 	token: id:role
	// 	 */
	// 	String token = request.getHeader("Authorization");
	// 	if(token == null) {
	// 		throw new AuthenticationException();
	// 	}
		
	// 	String[] tokens = token.split(":");
		
	// 	User authUser = us.getById(Integer.valueOf(tokens[0]));
		
	// 	if(authUser == null || !authUser.getRole().toString().equals(tokens[1]) || !allowedRoles.contains(authUser.getRole().toString())) {
	// 		throw new AuthorizationException();
	// 	} 
		
	// 	Object target = pjp.proceed();
		
	// 	return target;
	// }
}
