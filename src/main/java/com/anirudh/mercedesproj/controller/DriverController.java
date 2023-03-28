package com.anirudh.mercedesproj.controller;

import com.anirudh.mercedesproj.model.Driver;
import com.anirudh.mercedesproj.service.DriverService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/check")
    public ResponseEntity<String> getResponse(){
        return new ResponseEntity<String>("Got it", HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<Map<String,Integer>> getData(@RequestBody Map<String, Object> driverMap){

        HashMap<String,Integer> output = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        for(String key : driverMap.keySet()){
            if(key.equals("Total Test Cases"))break;
            Driver driver = mapper.convertValue(driverMap.get(key), Driver.class);
            Integer val = driverService.solveDisparity(driver);
            output.put(key,val);
        }

        return new ResponseEntity<>(output,HttpStatus.OK);
    }
}
