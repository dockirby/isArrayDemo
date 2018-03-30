package com.isarray;

import org.mozilla.javascript.ScriptableObject;

/**
 * Wrapper for standard out
 */
public class Print extends ScriptableObject {
	
	private static final long serialVersionUID = 1L;
	
	public static void jsStaticFunction_out(String out) {
		System.out.println(out);
	}

	@Override
	public String getClassName() {
		return "Print";
	}

}
