package pers.httptest.core.sample;

import pers.httptest.core.exception.CaseIlleagalException;
import pers.httptest.core.threads.SampleResult;

import java.util.Map;

public interface Sample {
    SampleResult getSampleResult(Map<String,Object> var) throws CaseIlleagalException;
}
