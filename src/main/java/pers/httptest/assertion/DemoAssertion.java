package pers.httptest.assertion;

import pers.httptest.core.threads.SampleResult;

public class DemoAssertion implements Assertion{

    private static final String ASSERTION_NAME="DemoAssertion";

    private static final String Contain="contain";

    private String assertionType;

    private String checkString;

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

}
