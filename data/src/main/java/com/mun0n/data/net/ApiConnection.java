package com.mun0n.data.net;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection implements Callable<String> {
    
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    
    private URL url;
    private String response;
    
    public ApiConnection(final String url) throws MalformedURLException {
        this.url = new URL(url);
    }
    
    static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }
    
    @Nullable
    String requestSyncCall() {
        connectToApi();
        return response;
    }
    
    private void connectToApi() {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder().url(this.url).addHeader(CONTENT_TYPE_LABEL,
                CONTENT_TYPE_VALUE_JSON).get().build();
        
        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        //TODO: timeouts wrong!
        okHttpClient.readTimeoutMillis();
        okHttpClient.connectTimeoutMillis();
        
        return okHttpClient;
    }
    
    @Override
    public String call() throws Exception {
        return null;
    }
}
