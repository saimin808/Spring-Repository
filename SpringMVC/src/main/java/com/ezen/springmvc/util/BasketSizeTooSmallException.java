package com.ezen.springmvc.util;

// Exception을 상속받는 경우 : 반드시 try-catch로 처리해야 하는 예외가 됨
// RuntimeException을 상속받는 경우 : 굳이 처리하지 않아도 되는 예외 (원하면 처리 가능)
public class BasketSizeTooSmallException extends RuntimeException {

	public BasketSizeTooSmallException(String message) {
		super(message);
	}
	
	
	
}
