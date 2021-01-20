package com.cos.person.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.person.domain.CommonDto;

@Component
@Aspect
public class BindingAdvice {
//	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);
	
	@Around("execution(* com.cos.person.web..*Controller.*(..))")	//함수의 앞뒤를 제어할 것인데(@Before:전 @After:후)
	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {	//메서드를 가져온다 그리고 내부가 실행된다.
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();

		System.out.println("type : "+type);
		System.out.println("method : "+method);

		Object[] args = proceedingJoinPoint.getArgs();	//save를 호출했으면 JoinReqDto, bindingResult 2개다.

		for (Object arg : args) {	//그 매개변수 중에 
			if(arg instanceof BindingResult) {	//BindingResult 타입이면
				BindingResult bindingResult = (BindingResult) arg;	//bindingResult에 담는다.

				// 서비스 : 정상적인 화면 -> 사용자요청
				if(bindingResult.hasErrors()) { 
					Map<String, String> errorMap = new HashMap<>();

					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						// 로그 레벨 error, warn, info, debug
//						log.warn(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());
//						log.debug(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());
//						Sentry.captureMessage(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());

						//DB연결 -> DB남기기
						//File file = new File();
					}

					return new CommonDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라
	}
}
