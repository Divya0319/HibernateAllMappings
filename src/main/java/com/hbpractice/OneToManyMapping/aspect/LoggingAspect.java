package com.hbpractice.OneToManyMapping.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hbpractice.OneToManyMapping.entity.BookReferred;
import com.hbpractice.OneToManyMapping.entity.Coder;
import com.hbpractice.OneToManyMapping.entity.CoderDetail;

@Aspect
@Component
public class LoggingAspect {
	
	Logger theLogger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.hbpractice.OneToManyMapping.rest.CoderRestController.*(..))")
	private void forControllerPackage() {}
	
	@AfterReturning(pointcut="forControllerPackage()", returning="result")
	public void afterReturningMethod(JoinPoint theJoinPoint, Object result) {
		
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		
		theLogger.info("----->>>>> Calling method -> " + methodSig.toString());
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg : args) {
			
			if(tempArg instanceof BookReferred) {
				
				BookReferred bRef = (BookReferred)tempArg;
				
				bRef.setCoder(null);
				
				theLogger.info("---->>> "+ bRef.toString());
				
			} else if(tempArg instanceof Coder) {
				
				Coder tCoder = (Coder)tempArg;
				
				tCoder.setBooksReferred(null);
				tCoder.setCoderDetail(null);
				
				theLogger.info("---->>> "+ tCoder.toString());
				
			} else if(tempArg instanceof CoderDetail) {
				
				CoderDetail cDet = (CoderDetail) tempArg;
				
				cDet.setCoder(null);
				
				theLogger.info("---->>> "+ cDet.toString());
			}
			
			else {
				theLogger.info("---->>> "+ tempArg.toString());
			}

		}
		
		theLogger.info("---->>> Response :\n" + result);
		
	}

}
