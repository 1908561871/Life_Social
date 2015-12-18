package cn.com.elex.social_life.support.config;

import java.util.HashMap;

/**
 * Created by zhangweibo on 2015/8/2.
 */
public class NetCode {

   public static HashMap<Integer ,String> RESPONSE_CODE=new HashMap<Integer ,String>(){

       @Override
       public String put(Integer key, String value) {

           return super.put(key, value);
       }
   };


   public static String getErrorMsg(int code){

       return RESPONSE_CODE.get(code);


   }



}
