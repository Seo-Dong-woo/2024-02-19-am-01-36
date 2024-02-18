package com.sist.commons;

import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
 *   공통 => 모든 화면이나 기능에 적용 
 *   1) Aspect : 중복이 많이 코드에서 (제거) => 자동 
 *   2) 공통 예외처리 
 *   3) 인터셉터 => 필요시마다 처리(화면마다 처리가 가능)
 */
@RestControllerAdvice
public class CommonsRestControllerException {

}
