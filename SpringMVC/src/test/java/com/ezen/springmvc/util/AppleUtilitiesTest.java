package com.ezen.springmvc.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AppleUtilitiesTest {

	// �ٸ� ������ ������ ���� ��Ű�� ���ο� �ֱ� ������ import�� �ʿ� ����.
	AppleUtilities util;
	
	@Before
	public void beforeTest() {
		util = new AppleUtilities();
		System.out.println("Create new apple utils.");
	}
	
//	@Ignore
	@Test
	public void test1() {
		util.setBasketSize(10);
		
		//����ϴ� ���� 6�Դϴ�.
		assertEquals("�����ߴ�", 6, util.getBasketCount(55));
	}
	
	@Test
	public void test2() {
		// assertThrows() : ���� ���ܰ� �߻��� ���� ����ϴ� ���Դϴ�.
		// () -> : ����
		assertThrows("�����ߴ�.",BasketSizeTooSmallException.class, () -> {
			util.setBasketSize(-5);
		});
	}

	@Test
	public void test3() {
		// �ٱ��� ũ�� ������ ���� �ʰ� ����ϴ� ��쿡 ���� ó���� �� �Ǵ��� Ȯ���ϰ� ����
		assertThrows("�����ߴ�",BasketSizeUnsetException.class, () -> {
			util.getBasketCount(99);
		});
	}
}
