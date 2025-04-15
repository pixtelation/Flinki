package com.flinki.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Data {
   

   
   public static String randomEmail;
   
   
   static {

      randomEmail = "Auto" + RandomStringUtils.randomAlphabetic(3) + "@yopmail.com";

   }
   
   public static String getRandomEmail() {
      return randomEmail;
  }
   
    
}
