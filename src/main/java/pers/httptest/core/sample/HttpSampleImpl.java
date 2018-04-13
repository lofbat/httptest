package pers.httptest.core.sample;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import pers.httptest.core.exception.CaseIlleagalException;
import pers.httptest.core.threads.Context;
import pers.httptest.core.threads.ContextService;
import pers.httptest.core.threads.SampleResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpSampleImpl implements Sample,HttpInterface{

    private static final Logger log=Logger.getLogger(HttpSampleImpl.class);
    private static final CloseableHttpClient httpClient= HttpClients.createDefault();
    private String name;
    private String protocol;
    private String method;
    private String host;
    private int port;
    private String path;
    private Map<String,String> requestHeaders;
    private Map<String,String> cookies;
    private Map<String,String> parameters;
    private String body;

    public HttpSampleImpl(String name) {
        this.name=name;
    }

    private void parser(Map<String,Object> var) throws CaseIlleagalException {

        if(var.isEmpty()){
            return;
        }

        Object temp;
        try {
            if ((temp = var.get(PROTOCOL)) != null) {
                protocol = (String) temp;
            }
            if ((temp = var.get(METHOD)) != null) {
                method = (String) temp;
            }
            if ((temp = var.get(HOST)) != null) {
                host = (String) temp;
            }
            if ((temp = var.get(PORT)) != null) {
                port = (int) temp;
            }
            if ((temp = var.get(PATH)) != null) {
                path = (String) temp;
            }
            if ((temp = var.get(HEADERS)) != null) {
                requestHeaders = (Map<String, String>) temp;
            }
            if ((temp = var.get(COOKIES)) != null) {
                cookies = (Map<String, String>) temp;
            }
            if ((temp = var.get(PARAMETERS)) != null) {
                parameters = (Map<String, String>) temp;
            }
            if((temp = var.get(BODY))!=null){
                if(temp.getClass().getName().equals("java.lang.Integer")) {
                    body = temp.toString();
                }else {
                    body = (String) temp;
                }
            }
        }catch (Exception e){
            throw new CaseIlleagalException("request sample illeagal:"+name+e);
        }
    }

    @Override
    public SampleResult getSampleResult(Map<String,Object> var) throws CaseIlleagalException {
        Context context= ContextService.getContext();
        Map<String, Object> variables=context.getVariables();
        if(variables!=null){
            parser(variables);
        }
        parser(var);
        check();

        HttpRequestBase httpRequest;
        SampleResult res=new SampleResult();
        context.setSampleResult(res);
        URI uri;
        try {
            URIBuilder uriBuilder=new URIBuilder().setScheme(protocol)
                                                    .setHost(host)
                                                    .setPort(port)
                                                    .setPath(path);
            if(parameters!=null){
                for(Map.Entry<String,String> entry:parameters.entrySet()){
                    uriBuilder.setParameter(entry.getKey(),entry.getValue());
                }
            }
            uri=uriBuilder.build();
        } catch (Exception e) {
            throw new CaseIlleagalException("URI build failed:",e);
        }

        if (method.equals(POST)) {
            httpRequest = new HttpPost(uri);
        } else if (method.equals(GET)) {
            httpRequest = new HttpGet(uri);
        } else if (method.equals(PUT)) {
            httpRequest = new HttpPut(uri);
        }else {
            throw new CaseIlleagalException("Unexpected method: '"+method+"'");
        }

        if(requestHeaders!=null) {
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if(cookies!=null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            }
            httpRequest.setHeader(COOKIE, sb.toString());
        }

        if(httpRequest.getMethod().equals(POST)){
            HttpPost hp=(HttpPost)httpRequest;
            BasicHttpEntity entity=new BasicHttpEntity();
            InputStream in=new ByteArrayInputStream(body.getBytes());
            entity.setContent(in);
            hp.setEntity(entity);
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(60000).build();
        httpRequest.setConfig(requestConfig);

        CloseableHttpResponse httpResponse;
        try {
            httpResponse=httpClient.execute(httpRequest);
            log.info(httpResponse);
        } catch (IOException e) {
            res.end();
            res.error(e.getMessage());
            return res;
        }

        try {
            byte[] bytes = new byte[0];
            bytes = new byte[httpResponse.getEntity().getContent().available()];
            httpResponse.getEntity().getContent().read(bytes);
            res.setResponseData(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    private void check() throws CaseIlleagalException {
        if(method==null){
            throw new CaseIlleagalException("checkfailed");
        }
    }
}
