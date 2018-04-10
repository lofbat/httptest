package pers.httptest.core.threads;

import pers.httptest.assertion.AssertionResult;

import java.util.List;

public class SampleResult {
    private SampleResult parent;

    private byte[] responseData = new byte[0];

    private String responseCode = "";

    private String label = "";

    private String resultFileName = "";

    private String samplerData;

    private String threadName = "";

    private String responseMessage = "";

    private String responseHeaders = "";

    private String requestHeaders = "";

    private long timeStamp = 0;

    private long startTime = 0;

    private long endTime = 0;

    private List<AssertionResult> assertionResults;

    private List<SampleResult> subResults;

}
