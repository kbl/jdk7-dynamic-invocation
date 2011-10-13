package pl.pietraszek;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {
	
    private static final int DEFAULT_MAX = 1000;
    
    
	private ExampleClass example;

	public MainClass(ExampleClass example) {
    	this.example = example;
	}

	public static void main(String ... args) throws Throwable {
        ExampleClass example = new ExampleClass();
        int repetitions = parseMax(args[0]);
        
        MainClass mainClass = new MainClass(example);
        mainClass.checkPrivateFieldValue();
		mainClass.invokeMethodWithReflection(repetitions);
        mainClass.invokeWithInvokeDynamic(repetitions);
    }

	private static int parseMax(String arg) {
		if(arg != null) {
			return Integer.parseInt(arg);
		}
		return DEFAULT_MAX;
	}
	
	

	private void invokeWithInvokeDynamic(int repetitions) 
			throws Throwable {
		Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(Integer.class, Double.class);
        
        MethodHandle methodHandle = lookup.findVirtual(
        		ExampleClass.class, 
        		"exampleMethod", 
        		methodType);
        
        for(int i = 0; i < repetitions; i++) {
        	System.out.println(methodHandle.invokeExact(123));
        }
	}

	private void invokeMethodWithReflection(int repetitions)
			throws 
			NoSuchMethodException, 
			IllegalAccessException,
			InvocationTargetException {
		
		Method method = example.getClass().getMethod("exampleMethod", Integer.class);
		
        for(int i = 0; i < repetitions; i++) {
        	method.invoke(example, Integer.valueOf(i));
        }
	}
	
	private String checkPrivateFieldValue() {
		try {
			Field field = example.getClass().getDeclaredField("examplePrivateField");
			
			field.setAccessible(true);
			String privateFieldValue = (String) field.get(example);
			field.setAccessible(false);
			
			return privateFieldValue;
		} catch (IllegalArgumentException | IllegalAccessException | 
				 NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
