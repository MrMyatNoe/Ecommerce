package com.demo.ecom.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class LimitResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int minimum;
	private int maximum;

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String toString() {
		return "LimitResponse [minimum=" + minimum + ", maximum=" + maximum + "]";
	}

}
