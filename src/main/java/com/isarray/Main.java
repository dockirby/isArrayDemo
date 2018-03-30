package com.isarray;

import java.io.InputStream;
import java.util.Scanner;

import org.mozilla.javascript.*;

import com.isarray.someoldapp.SomeOldApplication;

public class Main {
	
	private static final String IS_ARRAY_MODULE = "isArray.js";
	private static final String MAIN_SCRIPT = "mainscript.js";
	
	public static void main(String arg[]) throws Exception {
		/*
		 * The isArray method is put in its own file to imitate how it
		 * is in NPM. Sorry I did not go though the extra effort
		 * to rig up this thing actually use NPM to get it.
		 */
		String arrayMod = convertStreamToString(Main.class.getResourceAsStream(IS_ARRAY_MODULE));
		String mainScript = convertStreamToString(Main.class.getResourceAsStream(MAIN_SCRIPT));
		
		//Set up Rhino JS Engine for execution
		Context context = Context.enter();
		Scriptable scope = context.initStandardObjects(null);
		ScriptableObject.defineClass(scope, Print.class);
		SomeOldApplication.init(scope);
		
		//Run the two scripts against the execution context.
		System.out.println("JS Engine Implementation Version: " + context.getImplementationVersion());
		context.evaluateString(scope, arrayMod, IS_ARRAY_MODULE, 1, null);
		context.evaluateString(scope, mainScript, MAIN_SCRIPT, 1, null);
	}
	
	
	public static String convertStreamToString(InputStream is) {
	    @SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}