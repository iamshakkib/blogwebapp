package com.shakkib.bloggingwebapp.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class Blacklist {

     private HashSet<String> blacklist = new HashSet<String>();

     private static Blacklist instance = null;

     private Blacklist() {
     }

     public static Blacklist getInstance(){
          if(instance == null){
               instance = new Blacklist();
          }
          return instance;
     }

     public void addToBlackList(String token){
          blacklist.add(token);
     }

     public boolean isBlackListed(String token){
          return blacklist.contains(token);
     }
}
