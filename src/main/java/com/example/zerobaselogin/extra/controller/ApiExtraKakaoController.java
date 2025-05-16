package com.example.zerobaselogin.extra.controller;

import com.example.zerobaselogin.common.model.ResponseResult;
import com.example.zerobaselogin.extra.model.KakaoTranslateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/extra")
public class ApiExtraKakaoController {

    // KAKAO OPEN API 활용 -> 번역 API는 이제 제공하지 않음
    @GetMapping("/kakao/translate")
    public ResponseEntity<?> translate(@RequestBody KakaoTranslateInput kakaoTranslateInput) {

        String restApiKey = "08c5c78c63d9b01355d082cbda0b069f";
        String url = "https://dapi.kakao.com/v2/translation/translate";

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("src_lang", "kr");
        parameters.add("target_lang", "en");
        parameters.add("query", kakaoTranslateInput.getText());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "KakaoAK " + restApiKey);

        HttpEntity formEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, formEntity, String.class);

        return ResponseResult.succeess(responseEntity.getBody());
    }
}
