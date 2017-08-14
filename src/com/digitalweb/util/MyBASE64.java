package com.digitalweb.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MyBASE64     
{   
	public static final String myKey = "digital";
    
    /**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String decryptBASE64(String key) {               
        String code="";
		try {
			code = new String(new BASE64Decoder().decodeBuffer(key));
		} catch (IOException e) {
			e.printStackTrace();
		}     
        return code.substring(0,code.indexOf(myKey));
    }               
                  
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(String key) { 
        return (new BASE64Encoder()).encodeBuffer(key.concat(myKey).getBytes());               
    }       
         
    public static void main(String[] args) throws Exception     
    {     
        String data = MyBASE64.encryptBASE64("badboy");     
        System.out.println("加密后："+data);     
             
        String dataDecrype= MyBASE64.decryptBASE64(data);     
        System.out.println("解密后："+dataDecrype);     
    }     
} 
