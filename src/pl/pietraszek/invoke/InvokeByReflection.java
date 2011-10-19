package pl.pietraszek.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.pietraszek.ExampleClass;

/**
 * @author Marcin Pietraszek
 */
public class InvokeByReflection extends InvokeBase {

	public InvokeByReflection(ExampleClass instance, int repetitions) {
		super(instance, repetitions);
	}

	@Override
	protected void invokeMethod() {
		try {
			Method method = instance.getClass().getMethod("exampleMethod", int.class);
			for(int i = 0; i < repetitions; i++) {
				method.invoke(instance, i);
			}
		} catch (NoSuchMethodException | 
				 SecurityException | 
				 IllegalAccessException | 
				 IllegalArgumentException | 
				 InvocationTargetException e) {
			throw new RuntimeException(e);
        }
	}

}
