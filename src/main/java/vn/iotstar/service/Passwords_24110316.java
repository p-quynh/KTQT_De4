package vn.iotstar.service;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.util.*;
public final class Passwords_24110316 {
 private static final int ITERATIONS=210000;
 private Passwords_24110316() {}
 private static byte[] derive(String secret, byte[] salt, int iterations) {
  PBEKeySpec spec=new PBEKeySpec(secret.toCharArray(),salt,iterations,256);
  try {return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}
  catch(Exception e){throw new IllegalStateException(e);} finally {spec.clearPassword();}
 }
 public static String hash(String secret){
  byte[] salt=new byte[16]; new SecureRandom().nextBytes(salt);
  return ITERATIONS+":"+Base64.getEncoder().encodeToString(salt)+":"+Base64.getEncoder().encodeToString(derive(secret,salt,ITERATIONS));
 }
 public static boolean matches(String secret, String encoded){
  try {String[] p=encoded.split(":",3); int n=Integer.parseInt(p[0]); if(n<100000||n>1000000)return false;
   return MessageDigest.isEqual(Base64.getDecoder().decode(p[2]),derive(secret,Base64.getDecoder().decode(p[1]),n));
  } catch(RuntimeException e){return false;}
 }
}
