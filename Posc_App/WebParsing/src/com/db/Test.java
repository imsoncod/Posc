package com.db;

import java.security.*; 
import javax.crypto.*; 
import javax.crypto.spec.DESKeySpec; 
import javax.crypto.spec.DESedeKeySpec; 
import sun.misc.BASE64Encoder; 

public class Test { 
	
	public static void main(String[] args) 
      throws Exception { 
      } 

// 대칭키 설정하는 코드
public static String key() { return "yoo_jong_hyeok_passwdKey"; } 

public static Key getKey() 
      throws Exception { 
   System.out.println("키의 길이 : "+key().length()); 
   return (key().length() == 24) ? getKey2(key()) : getKey1(key()); } 


public static Key getKey1(String keyValue) 
      throws Exception { System.out.println("DES"); 
      DESKeySpec desKeySpec = new DESKeySpec(keyValue.getBytes()); 
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
      Key key = keyFactory.generateSecret(desKeySpec); 
      return key; } 

public static Key getKey2(String keyValue) 
      throws Exception { System.out.println("Triple DES"); 
      DESedeKeySpec desKeySpec = new DESedeKeySpec(keyValue.getBytes()); 
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede"); 
      Key key = keyFactory.generateSecret(desKeySpec); return key; } 

public static String encrypt(String ID) 
      throws Exception { 
   if (ID == null || ID.length() == 0) 
      return ""; 

   String instance = (key().length() == 24) ? "DESede/ECB/PKCS5Padding" : "DES/ECB/PKCS5Padding"; 
   System.out.println(instance); 

   javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(instance); 

   cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getKey()); 
   String amalgam = ID; 

   byte[] inputBytes1 = amalgam.getBytes("UTF8");

   byte[] outputBytes1 = cipher.doFinal(inputBytes1); 

   BASE64Encoder encoder = new BASE64Encoder(); 
   String outputStr1 = encoder.encode(outputBytes1); 
   return outputStr1; 
   } 


public static String decrypt(String codedID) 
      throws Exception { 
      if (codedID == null || codedID.length() == 0) 
         return ""; 
      String instance = (key().length() == 24) ? "DESede/ECB/PKCS5Padding" : "DES/ECB/PKCS5Padding"; 
         javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(instance); 

         cipher.init(javax.crypto.Cipher.DECRYPT_MODE, getKey()); 
         sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 

         byte[] inputBytes1 = decoder.decodeBuffer(codedID); 

         byte[] outputBytes2 = cipher.doFinal(inputBytes1); 

         String strResult = new String(outputBytes2, "UTF8"); return strResult; 
         } 

      }

