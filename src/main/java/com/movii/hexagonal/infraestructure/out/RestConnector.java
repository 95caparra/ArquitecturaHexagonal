package com.movii.hexagonal.infraestructure.out;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

@ComponentScan
public class RestConnector extends RestGatewaySupport implements Serializable {

    private static final long serialVersionUID = 1535390408458475868L;
    private final String uri;


    public RestConnector(@NotNull String puri, @NotNull Integer connectionTimeoutMillis, @NotNull Integer readTimeoutMillis) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeoutMillis).setConnectionRequestTimeout(readTimeoutMillis).setSocketTimeout(readTimeoutMillis).setCookieSpec("standard").build();
        PoolingHttpClientConnectionManager connectionPool = new PoolingHttpClientConnectionManager();
        connectionPool.setMaxTotal(200);
        connectionPool.setDefaultMaxPerRoute(30);
        connectionPool.setValidateAfterInactivity(500);
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionPool).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory paramsFactory = new HttpComponentsClientHttpRequestFactory();
        paramsFactory.setHttpClient(httpClient);
        this.getRestTemplate().setRequestFactory(paramsFactory);
        this.uri = puri;
    }

    private static HttpHeaders createHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null && !headers.isEmpty()) {
            Iterator var2 = headers.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, String> header = (Map.Entry) var2.next();
                httpHeaders.set(header.getKey(), header.getValue());
            }
        }

        return httpHeaders;
    }

    public String getUri() {
        return this.uri;
    }

    public final <T> T post(Object body, Class<T> responseClass, MediaType mediaType) {
        return this.post(this.uri, body, responseClass, mediaType, (Map<String, String>) null);
    }

    public final <T> T post(String url, Object body, Class<T> responseClass, MediaType mediaType, Map<String, String> headers) {
        RestTemplate restTemplate = this.getRestTemplate();
        HttpHeaders httpHeaders = createHeaders(headers);
        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
        }

        HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
        return restTemplate.postForEntity(url, httpEntity, responseClass, new Object[0]).getBody();
    }

    public final <T> T get(String url, Class<T> responseClass) {
        return (new RestTemplate()).getForEntity(url, responseClass, new Object[0]).getBody();
    }

    public final <T> T get(Class<T> responseClass, MediaType mediaType, Map<String, String> headers) {
        return this.get(this.uri, responseClass, mediaType, headers);
    }

    public final <T> T get(String uri, Class<T> responseClass, MediaType mediaType, Map<String, String> headers) {
        HttpHeaders httpHeaders = createHeaders(headers);
        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
        }

        return this.exchange(HttpMethod.GET, uri, responseClass, mediaType, headers, (Object[]) null);
    }

    public final <T> T exchange(HttpMethod method, Class<T> responseClass, MediaType mediaType, Map<String, String> headers, Object... params) {
        return this.exchange(method, this.uri, responseClass, mediaType, headers, params);
    }

    public final <T> T exchange(HttpMethod method, String url, Class<T> responseClass, MediaType mediaType, Map<String, String> headers, Object... param) {
        HttpHeaders httpHeaders = createHeaders(headers);
        if (mediaType != null) {
            httpHeaders.setContentType(mediaType);
        }

        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return (T) this.getRestTemplate().exchange(url, method, httpEntity, responseClass, param);
    }
}
