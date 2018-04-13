package pers.httptest.core.threads;

import java.util.Map;

public class Context {

    private Map<String, Object> variables;

    private SampleResult sampleResult;


    public Map<String, Object> getVariables() {
        return variables;
    }

    public SampleResult getSampleResult() {
        return sampleResult;
    }

    public void setSampleResult(SampleResult sampleResult) {
        this.sampleResult = sampleResult;
    }
}
