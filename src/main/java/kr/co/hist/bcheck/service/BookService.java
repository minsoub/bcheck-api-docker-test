package kr.co.hist.bcheck.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hist.bcheck.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@XRayEnabled
@Slf4j
@Service
public class BookService {

    @Value("${openapi.naver.url}")
    String naverURL;

    @Value("${openapi.naver.clientID}")
    String clientID;

    @Value("${openapi.naver.clientSecret}")
    String clientSecret;

    /**
     * 외부 연계를 통해서 ISBN 의 책 정보를 가져온다.
     * @param isbn
     * @return
     */
    public IsbnResponse search(String isbn)
    {
        //get /v1/search/book_adv
        //HOST: openapi.naver.com
        //Content-Type: plain/text
        //X-Naver-Client-Id: 8LHwtR6GWztI7XY6_zt1
        //X-Naver-Client-Secret: qJ8C9_odvW
        try {
            String url = naverURL+"?d_isbn="+isbn;
            log.debug(url);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet req = new HttpGet(url);
            req.addHeader("X-Naver-Client-Id", clientID);
            req.addHeader("X-Naver-Client-Secret", clientSecret);

            HttpResponse res = client.execute(req);
            if (res.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(res);
                System.out.println(body);
                //Gson gsonObj = new Gson();

                JSONObject obj = new JSONObject(body);
                if (obj.getInt("total") == 0) {   // 조회 도서가 없다면..
                    return null;
                }else {
                    JSONArray array = obj.getJSONArray("items");

                    JSONObject data = array.getJSONObject(0);  // 첫번째 정보
                    ObjectMapper mapper = new ObjectMapper();

                    log.debug(data.toString());
                    IsbnResponse isbnRes = mapper.readValue(data.toString(), IsbnResponse.class);
                    log.debug(isbnRes.toString());
                    return isbnRes;
                }
            }else {
                System.out.println("response is error => " + res.getStatusLine().getStatusCode());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
