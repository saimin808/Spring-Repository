package com.ezen.springmvc.util;

// Exception�� ��ӹ޴� ��� : �ݵ�� try-catch�� ó���ؾ� �ϴ� ���ܰ� ��
// RuntimeException�� ��ӹ޴� ��� : ���� ó������ �ʾƵ� �Ǵ� ���� (���ϸ� ó�� ����)
public class BasketSizeTooSmallException extends RuntimeException {

	public BasketSizeTooSmallException(String message) {
		super(message);
	}
	
	
	
}
