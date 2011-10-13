package pl.pietraszek;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {
	
    private static final int DEFAULT_MAX = 1000;
    
	private ExampleClass example;
	private int repetitions;
	private long executionStartTime;
	

	public static void main(String ... args) throws Throwable {
        ExampleClass example = new ExampleClass();
        int repetitions = parseMax(args);
        
        MainClass mainClass = new MainClass(example, repetitions);

		mainClass.invokeMethodWithReflection();
        mainClass.invokeWithInvokeDynamic();
        mainClass.invokeStandardMethod();
    }

	private static int parseMax(String[] args) {
		if(args != null && args.length > 0) {
			return Integer.parseInt(args[0]);
		}
		return DEFAULT_MAX;
	}
	
	
	public MainClass(ExampleClass example, int repetitions) {
    	this.example = example;
    	this.repetitions = repetitions;
	}


	public void invokeStandardMethod() {
		startCountingTime();
		
		for(int i = 0; i < repetitions; i++) {
			example.exampleMethod(i);
		}
		
		printExecutionTime("standard: ");
	}

	public void invokeWithInvokeDynamic() 
			throws Throwable {
		startCountingTime();
		
		Lookup lookup = MethodHandles.lookup();
		
        MethodType methodType = MethodType.methodType(Double.class, int.class);
        MethodHandle methodHandle = lookup.findVirtual(
        		ExampleClass.class, 
        		"exampleMethod", 
        		methodType);
        
        for(int i = 0; i < repetitions; i++) {
        	methodHandle.invoke(example, i);
        }
        
        printExecutionTime("invokeDynamic: ");
	}

	public void invokeMethodWithReflection()
			throws NoSuchMethodException, 
					IllegalAccessException,
					InvocationTargetException {
		startCountingTime();
		
		Method method = example.getClass().getMethod("exampleMethod", int.class);
		
        for(int i = 0; i < repetitions; i++) {
        	method.invoke(example, i);
        }
        
        printExecutionTime("reflection:");
	}

	private void printExecutionTime(String methodName) {
		long executionTime = System.currentTimeMillis() - executionStartTime;
		System.out.println(methodName + executionTime);
	}

	private void startCountingTime() {
		executionStartTime = System.currentTimeMillis();
	}
	
}
