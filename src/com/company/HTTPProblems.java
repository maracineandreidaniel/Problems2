package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

public class HTTPProblems {
    public HTTPProblems() throws IOException, InterruptedException {
    }


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
        HttpRequest request = HttpRequest.newBuilder().headers("Content-Type", "application/json", "Referer", "https://reqres.in/").uri(URI.create("https://reqres.in/api/users/2")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpHeaders allHeaders = response.headers();
        List<String> allValues = response.headers().allValues("Cache-Control");
        System.out.println(allValues);
    }

    //todo:pb254
    public static void specifyHttpMethod() {
        //get
        HttpRequest requestGet = HttpRequest.newBuilder().GET().uri(URI.create("https://reqres.in/api/users/2")).build();
        //post
        HttpRequest requestPost = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString("caine1")).uri(URI.create("https://reqres.in/api/users/2")).build();
        //put
        HttpRequest requestPut = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("caine1")).uri(URI.create("https://reqres.in/api/users/2")).build();
        //delete
        HttpRequest requestDelete = HttpRequest.newBuilder().DELETE().uri(URI.create("https://reqres.in/api/users/2")).build();
    }

    //todo:pb255
    public void creatingBodies() throws FileNotFoundException {
        //from String
        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"nume\":\"rex\"}")).uri(URI.create("https://reqres.in/api/users")).build();
        //from file
        HttpRequest request1 = HttpRequest.newBuilder().header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofFile(Path.of("user.json"))).uri(URI.create("https://reqres.in/api/users")).build();
    }

    //todo:pb256
    public static void setAuthentication() {
        HttpClient client = HttpClient.newBuilder().authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password".toCharArray());
            }
        }).build();

    }

    //todo:pb257
    public static void setTimeout() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users/2")).timeout(Duration.of(5, ChronoUnit.CENTURIES)).build();
    }

    //todo:pb258
    public static void redirectPolicy() {
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
    }

    //todo:pb259
    public static void sendingRequest() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users/2")).build();
        //sync reqest
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //async request
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).exceptionally(e -> "Exception" + e).thenAccept(System.out::println).get(30, TimeUnit.DAYS);


    }

    //todo:pb260
    public void handlingCookies() {
        HttpClient client = HttpClient.newBuilder().cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE)).build();
        CookieManager cm = new CookieManager();
        cm.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        HttpClient client1 = HttpClient.newBuilder().cookieHandler(cm).build();
    }

    //todo:pb261
    public void getResponse() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE)).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users/2")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.version());
        System.out.println(response.uri());
        System.out.println(response.statusCode());
        System.out.println(response.headers());
    }

    //todo:pb262
    public void handlingResponses() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE)).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://reqres.in/api/users/2")).build();
        //handloing response body as a file
        HttpResponse<Path> responseOfFile = client.send(request, HttpResponse.BodyHandlers.ofFile(Path.of("response.json")));
        //as a byte array
        HttpResponse<byte[]> responseBytes = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        //as a stream of strings
        HttpResponse<Stream<String>> responseLines = client.send(request, HttpResponse.BodyHandlers.ofLines());
    }

    //todo:pb264
    public void compression() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().header("Accept=Encoding", "gzip").uri(URI.create("https://maracine.name")).build();
        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        String encoding = response.headers().firstValue("Content-Encoding").orElse("");
        if ("gzip".equals(encoding)) {
            String gzipAsString = gZipToString(response.body());
            System.out.println(gzipAsString);
        } else {
            String isAsString = isToString(response.body());
            System.out.println(isAsString);
        }
    }

    private String isToString(InputStream is) throws IOException {
        byte[] allBytes;
        try(InputStream fromIs=is) {
            allBytes=fromIs.readAllBytes();
        }
        return new String(allBytes,StandardCharsets.UTF_8);
    }

    public static String gZipToString(InputStream gzip) {
        byte[] allBytes = new byte[0];
        try(InputStream fromIs=new GZIPInputStream(gzip)) {
            allBytes=fromIs.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(allBytes, StandardCharsets.UTF_8);
    }


}
