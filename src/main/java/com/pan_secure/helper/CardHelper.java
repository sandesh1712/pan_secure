package com.pan_secure.helper;


import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

@Component
public class CardHelper {
	@Value("${pan.secure.secret.key}")
    private String key;
    
    
    public String CardTokenize(String pan) {
       HashFunction function = Hashing.hmacMd5(key.getBytes());
	   HashCode token = function.hashString(pan,Charset.defaultCharset());
	   return token.toString();
    }
}
