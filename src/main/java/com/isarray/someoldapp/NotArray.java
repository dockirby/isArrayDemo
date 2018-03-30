package com.isarray.someoldapp;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * This class is a stand in for a domain object called
 * Array in a hypothetical legacy system
 */
public class NotArray extends ScriptableObject {
	
	private static final long serialVersionUID = 1L;
	
	private final static String[] KIRBY_DANCE_POSES = {"(>'-')>", "<('-'<)", "^('-')^", "v('-')v"};
	private final static String KIRBY_DANCE_END = "^(^-^)^";
		
	public NotArray(){}
	
	public static void init(Scriptable scope) throws Exception {
		NotArray arrayObj = new NotArray();
        arrayObj.setPrototype(getObjectPrototype(scope));
        arrayObj.setParentScope(scope);
        ScriptableObject.defineProperty(scope, "Array", arrayObj,
                                        ScriptableObject.DONTENUM);
        arrayObj.defineFunctionProperties(new String[] {"kirbyDance"}, NotArray.class, ScriptableObject.DONTENUM);
    }
	
	public static void kirbyDance(int length) {
		if (length < 1) length = 1;
		length -= 1;
		for(int i = 0; i < length; i++) {
			System.out.print(KIRBY_DANCE_POSES[i%KIRBY_DANCE_POSES.length] + " ");
		}
		System.out.print(KIRBY_DANCE_END + System.lineSeparator());
	}

	@Override
	public String getClassName() {
		return "Array";
	}		
}