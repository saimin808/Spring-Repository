package com.ezen.springmvc.util;

public class AppleUtilities {

	private Integer basketSize;
	
	public void setBasketSize(int basketSize) throws BasketSizeTooSmallException {
		if(basketSize <= 0) {
			throw new BasketSizeTooSmallException("Basket size is too small");
		}
		
		this.basketSize = basketSize;
	}	
	
	public int getBasketCount(int apple) {
		if (basketSize == null) {
			throw new BasketSizeUnsetException("basket size not initialized");
		}
		
		return apple % basketSize == 0 ? 
				apple / basketSize : apple / basketSize + 1;
	}
	
	public static void main(String[] args) {
		AppleUtilities util = new AppleUtilities();
		
		util.setBasketSize(10);
		
		System.out.println(util.getBasketCount(33));
	}
}
