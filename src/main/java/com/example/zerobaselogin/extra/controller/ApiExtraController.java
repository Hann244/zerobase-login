package com.example.zerobaselogin.extra.controller;

import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.extra.model.OpenApiResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/extra")
public class ApiExtraController {

    // 공공 API 활용
    /*
    @GetMapping("/pharmacy")
    public String pharmacy() {

        String apiKey = "nqObNjQeEw60Ri9U%2B9Yzcs0TCPYtUQfj1OVRom6hKGqxDuM0V39BAxNhcZX6FBcPgkhvXkts6Fv%2Bt%2FgC%2F0SJRA%3D%3D";
        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=%s&pageNo=1&numOfRows=10";

        String apiResult = "";

        // url이 깨지는 경우가 있어서 URI로 받아줄 필요가 있음
        try {
            URI uri = new URI(String.format(url, apiKey));
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            String result = restTemplate.getForObject(uri, String.class);

            apiResult = result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResult;
    }
     */

    // 공공 API 활용 -> 모델로 매핑
    @GetMapping("/pharmacy")
    public ResponseEntity<?> pharmacy() {

        String apiKey = "nqObNjQeEw60Ri9U%2B9Yzcs0TCPYtUQfj1OVRom6hKGqxDuM0V39BAxNhcZX6FBcPgkhvXkts6Fv%2Bt%2FgC%2F0SJRA%3D%3D";
        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=%s&pageNo=1&numOfRows=10";

        String apiResult = "";

        // url이 깨지는 경우가 있어서 URI로 받아줄 필요가 있음
        try {
            URI uri = new URI(String.format(url, apiKey));
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            String result = restTemplate.getForObject(uri, String.class);

            apiResult = result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        OpenApiResult jsonResult = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonResult = objectMapper.readValue(apiResult, OpenApiResult.class);

        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return ResponseResult.succeess(jsonResult);
    }
}
