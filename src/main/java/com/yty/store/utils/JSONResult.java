package com.yty.store.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yang
 * @create 2023-05-30 16:16
 * @Description: JSON 格式的返回结果
 */
@Data
public class JSONResult<T> implements Serializable {
   private Integer state; // 状态码
   private String message; // 状态信息
   private T data; // 返回的结果

   /**
    * 将异常信息交给 message
    * @param e 异常
    */
   public JSONResult(Throwable e){
      this.message = e.getMessage();
   }
   public JSONResult(Integer state, String message){
      this.state = state;
      this.message = message;
   }

   public JSONResult(Integer state, String message, T data) {
      this.state = state;
      this.message = message;
      this.data = data;
   }
}
