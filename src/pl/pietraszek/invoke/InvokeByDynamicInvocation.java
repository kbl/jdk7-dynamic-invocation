package pl.pietraszek.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;

import pl.pietraszek.ExampleClass;

/**
 * @author Marcin Pietraszek
 */
public class InvokeByDynamicInvocation extends InvokeBase {

	public InvokeByDynamicInvocation(ExampleClass instance, int repetitions) {
		super(instance, repetitions);
	}

	@Override
	protected void invokeMethod() {
		Lookup lookup = MethodHandles.lookup();
		
		try {
			MethodType methodType = MethodType.methodType(Double.class, int.class);
			MethodHandle methodHandle = lookup.findVirtual(
					ExampleClass.class, 
					"exampleMethod", 
					methodType);
			for(int i = 0; i < repetitions; i++) {
				methodHandle.invoke(instance, i);
			}		
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
        
	}

}
