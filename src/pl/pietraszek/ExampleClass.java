package pl.pietraszek;

/**
 * @author Marcin Pietraszek
 */
public class ExampleClass {

    private String examplePrivateField;
	protected String exampleProtectedField;
    /* package */ String exampleDefaultField;
    public String examplePublicField;

    public ExampleClass() {
        this.examplePrivateField = "private field";
        this.exampleProtectedField = "protected field";
        this.exampleDefaultField = "default field";
        this.examplePublicField = "public field";
    }
    
    public String getExamplePrivateField() {
		return examplePrivateField;
	}
    
    public Integer exampleMethod(Integer arg) {
    	return arg * arg;
    }

}