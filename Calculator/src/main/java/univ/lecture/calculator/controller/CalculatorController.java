package univ.lecture.calculator.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

import lombok.extern.log4j.Log4j;
import univ.lecture.calculator.Calculator;
import univ.lecture.calculator.model.Cal;;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/calc")
@Log4j
public class CalculatorController {

    final int teamId = 10;
    long now = System.currentTimeMillis();
    double result = 0.0;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${calc.endpoint}")
    private String endpoint;
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Cal queryCal(@PathVariable("exp") String exp) throws UnsupportedEncodingException {
        final int teamId = 10;
        long now = System.currentTimeMillis();
        double result;
        
        Calculator calculator = new Calculator();
        result = calculator.calculate(exp);
        
        Cal cal = new Cal();
        cal.setTeamId(teamId);
        cal.setNow(now);
        cal.setResult(result);

        return cal;
    }
}
