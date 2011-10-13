package pl.pietraszek;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {
	
    private static final Integer DEFAULT_MAX = 1000;
    
    
	private ExampleClass example;

	public MainClass(ExampleClass example) {
    	this.example = example;
	}

	public static void main(String ... args) throws Exception {
        ExampleClass example = new ExampleClass();
        
        MainClass mainClass = new MainClass(example);
        mainClass.checkPrivateFieldValue();
        mainClass.invokeMethod(parseMax(args[0]));
    }

	private static Integer parseMax(String arg) {
		if(arg != null) {
			return Integer.valueOf(arg);
		}
		return DEFAULT_MAX;
	}

	private void invokeMethod(Integer max)
			throws 
			NoSuchMethodException, 
			IllegalAccessException,
			InvocationTargetException {
		
		Method method = example.getClass().getMethod("exampleMethod", Integer.class);
		
        for(int i = 0; i < max; i++) {
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
