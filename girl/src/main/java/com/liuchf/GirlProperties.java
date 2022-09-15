package com.liuchf;

//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;

//@Component
//@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

   private String cupSize;

   private Integer age;

   public String getCupSize() {
       return cupSize;
   }
}
