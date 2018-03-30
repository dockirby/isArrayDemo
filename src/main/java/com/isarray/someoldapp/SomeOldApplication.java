package com.isarray.someoldapp;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Class is a stand in for a representation of an older system.
 * Very common for legacy systems to use generic names for their
 * domain objects. Just imagine this is a giant library with 100s
 * of objects, and someone wants to be able to access said
 * legacy system with a JavaScipt based system. 
 */
public class SomeOldApplication extends ScriptableObject {
	
	private static final long serialVersionUID = 1L;
	
	public static void init(Scriptable scope) throws Exception {
		SomeOldApplication oldApp = new SomeOldApplication();
        oldApp.setPrototype(getObjectPrototype(scope));
        oldApp.setParentScope(scope);
        ScriptableObject.defineProperty(scope, "SomeOldApplication", oldApp,
                                        ScriptableObject.DONTENUM);
        
        NotArray.init(oldApp);        
    }
	
	public SomeOldApplication(){}

	@Override
	public String getClassName() {
		return "SomeOldApplication";
	}

}
