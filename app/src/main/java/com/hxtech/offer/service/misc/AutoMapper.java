package com.hxtech.offer.service.misc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class AutoMapper extends ObjectMapper
{

  private static AutoMapper sInstance;

  private AutoMapper() {
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    setAnnotationIntrospector(new JacksonAnnotationIntrospector());
  }


  public static AutoMapper getInstance()
  {
    if (sInstance == null)
    {
      sInstance = new AutoMapper();
    }
    return sInstance;
  }
}
