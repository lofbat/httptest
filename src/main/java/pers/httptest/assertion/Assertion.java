package pers.httptest.assertion;

import pers.httptest.core.threads.SampleResult;

import java.util.Map;

public interface Assertion {
    void setUp(String name,String type, Map<String, Object> var);
    AssertionResult getAssertionResult(SampleResult sampleResult);
    void tearDown();
}
