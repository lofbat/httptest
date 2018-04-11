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

    private long timeStamp = 0;

    private long startTime = 0;

    private long endTime = 0;

    private List<AssertionResult> assertionResults;

    private List<SampleResult> subResults;

    public SampleResult(){
        this(null);
    }

    public SampleResult(SampleResult sampleResult) {
        this.parent=sampleResult;
    }

    public SampleResult getParent() {
        return parent;
    }

    public void setParent(SampleResult parent) {
        this.parent = parent;
    }

    public byte[] getResponseData() {
        return responseData;
    }

    public void setResponseData(byte[] responseData) {
        this.responseData = responseData;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public String getSamplerData() {
        return samplerData;
    }

    public void setSamplerData(String samplerData) {
        this.samplerData = samplerData;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<AssertionResult> getAssertionResults() {
        return assertionResults;
    }

    public void setAssertionResults(List<AssertionResult> assertionResults) {
        this.assertionResults = assertionResults;
    }

    public List<SampleResult> getSubResults() {
        return subResults;
    }

    public void setSubResults(List<SampleResult> subResults) {
        this.subResults = subResults;
    }
}
