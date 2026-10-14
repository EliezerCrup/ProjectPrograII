package com.supermercado.pos.util;

import java.security.*;import java.util.*;import javax.crypto.*;import javax.crypto.spec.*;
public final class Seguridad {
 private Seguridad(){} private static final int ITERACIONES=120000;
 private static byte[] pbkdf2(char[] clave,byte[] sal,int it){PBEKeySpec spec=new PBEKeySpec(clave,sal,it,256);try{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}catch(GeneralSecurityException e){throw new IllegalStateException(e);}finally{spec.clearPassword();}}
 public static String hash(char[] clave){byte[] sal=new byte[16];new SecureRandom().nextBytes(sal);return ITERACIONES+":"+Base64.getEncoder().encodeToString(sal)+":"+Base64.getEncoder().encodeToString(pbkdf2(clave,sal,ITERACIONES));}
 public static boolean verificar(char[] clave,String almacenado){try{String[] p=almacenado.split(":");return p.length==3&&MessageDigest.isEqual(Base64.getDecoder().decode(p[2]),pbkdf2(clave,Base64.getDecoder().decode(p[1]),Integer.parseInt(p[0])));}catch(RuntimeException e){return false;}}
}