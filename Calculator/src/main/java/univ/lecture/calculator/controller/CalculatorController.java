package univ.lecture.calculator.controller;

import lombok.extern.log4j.Log4j;
import univ.lecture.calculator.Calculator;
import univ.lecture.calculator.model.Cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/calc")
@Log4j
public class CalculatorController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryCal(@PathVariable("exp") String exp) throws UnsupportedEncodingException {
        final String url = "https://demo2446904.mockable.io/api/v1/answer";
        final int teamId = 10;
        long now = System.currentTimeMillis();
        double result;
        
        Calculator calculator = new Calculator();
        result = calculator.calculate(exp);
        
        String response = restTemplate.getForObject(url, String.class);
        Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);

        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));
        
        Map<String, Object> cal = (Map<String, Object>) parsedMap.values().toArray()[0];;
        cal.put("teamId", teamId);
        cal.put("now", now);
        cal.put("result", result);
        String msg = restTemplate.postForObject(url, cal, String.class);

        return msg;
    }
}
