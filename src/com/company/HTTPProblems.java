package com.company;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HTTPProblems {


    //todo:pb250
    //HTTP2 is a very important protocol. It has 2 parts: the framing layer(multiplexing core abalitiy)
    //and the data layer(contains data).

    //todo:pb251
    public static void triggerGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users/2")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }

    //todo:pb252
    public static void selectProxy() {
        HttpClient client = HttpClient.newBuilder().proxy(ProxySelector.getDefault()).build();
    }

    //todo:pb253
    public static void settingHeader() throws IOException, InterruptedException {
        HttpRequest request=HttpRequest.newBuilder().headers("Content-Type","application/json","Referer","https://reqres.in/").uri(URI.create("https://reqres.in/api/users/2")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpHeaders allHeaders=response.headers();
        List<String> allValues=response.headers().allValues("Cache-Control");
        System.out.println(allValues);
    }

    //todo:pb254
        public static void specifyHttpMethod(){
            //get
            HttpRequest requestGet=HttpRequest.newBuilder().GET().uri(URI.create("https://reqres.in/api/users/2")).build();
            //post
            HttpRequest requestPost=HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString("caine1")).uri(URI.create("https://reqres.in/api/users/2")).build();
            //put
            HttpRequest requestPut=HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("caine1")).uri(URI.create("https://reqres.in/api/users/2")).build();
            //delete
            HttpRequest requestDelete=HttpRequest.newBuilder().DELETE().uri(URI.create("https://reqres.in/api/users/2")).build();

        }
}
