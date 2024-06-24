 package com.demo.littlehelper.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

     public static String getJsonString(Object obj) {
         String jsonString = "";
         try {
             jsonString = OBJECT_MAPPER.writeValueAsString(obj);
         } catch (JsonProcessingException e) {
             log.error("物件轉換Json有誤: ", e.getMessage());
         }
         return jsonString;
     }

     public static <T> T readFromJson(String jsonString, Class<T> type) throws IllegalAccessException, InstantiationException {
         try {
             return OBJECT_MAPPER.readValue(jsonString, type);
         } catch (JsonProcessingException e) {
             log.error("Json轉換物件有誤:", e.getMessage());
         }
         return type.newInstance() ;
     }
}
