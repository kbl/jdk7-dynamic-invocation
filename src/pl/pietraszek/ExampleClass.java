package pl.pietraszek;

/**
 * @author Marcin Pietraszek
 */
public class ExampleClass {

    private String examplePrivateField = "private field";

    public String getExamplePrivateField() {
		return examplePrivateField;
	}
    
    public Double exampleMethod(Integer arg) {
    	return arg * arg * 1.1;
    }

}
