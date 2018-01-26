package com.asean.gril;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class HelloController {

    @Value("${girl.cupSize}")
    private String cupSize;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return "Hello Spring Boot!";
    }

    @RequestMapping(value = "/getCup" ,method = RequestMethod.GET)
    public String getCupSize(){
        return content;
    }

    @RequestMapping(value = "/getGirl" ,method = RequestMethod.GET)
    public String getGirl(){
        return girlProperties.getCupSize() + girlProperties.getAge();
    }

}
