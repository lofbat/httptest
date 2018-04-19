package pers.httptest.assertion;

import pers.httptest.core.threads.SampleResult;

import java.util.Map;

public class DemoAssertion implements Assertion{

    private static final String ASSERTION_NAME="DemoAssertion";

    private static final String Contain="contain";

    private String assertionType;

    private String checkString;

    public DemoAssertion() {

    }

    @Override
    public void setUp(String name,String type, Map<String, Object> var) {

    }

    @Override
    public AssertionResult getAssertionResult(SampleResult sampleResult) {
        AssertionResult assertionResult=new AssertionResult(ASSERTION_NAME);
        if(sampleResult.getResponseMessage().equals("")){
            assertionResult.setError(true);
            assertionResult.setErrorMessage("Response Body is null");
            return assertionResult;
        }
        if(assertionType.equals(Contain)){
            if(sampleResult.getResponseMessage().contains(checkString)){
                assertionResult.setFailure(false);
                return assertionResult;
            }
            else{
                assertionResult.setFailure(true);
                return assertionResult;
            }
        }
        else{
            assertionResult.setError(true);
            assertionResult.setErrorMessage("unexpected assertionType");
            return assertionResult;
        }
    }

    @Override
    public void tearDown() {

    }


}
