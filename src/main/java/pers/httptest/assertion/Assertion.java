package pers.httptest.assertion;

import pers.httptest.core.threads.SampleResult;

public interface Assertion {
    AssertionResult getAssertionResult(SampleResult sampleResult);
}
