package com.hxtech.offer.service.misc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class CustomJacksonConverter implements Converter
{

  private static final String MIME_TYPE = "application/json; charset=UTF-8";
  private final ObjectMapper objectMapper;

  public CustomJacksonConverter(ObjectMapper objectmapper)
  {
    objectMapper = objectmapper;
  }

  @Override
  public Object fromBody(TypedInput typedinput, Type type)
      throws ConversionException {
    if (typedinput.length() == 0L)
    {
      return null;
    }
    try
    {
      JavaType javaType = objectMapper.getTypeFactory().constructType(type);
      if (type.getClass().equals(Void.TYPE)) {
        return null;
      }
      return objectMapper.readValue(typedinput.in(), javaType);
    } catch (IOException e) {
      throw new ConversionException(e);
    }
  }

  @Override
  public TypedOutput toBody(Object obj)
  {
    try {
      obj =
          new TypedByteArray("application/json; charset=UTF-8", objectMapper
              .writeValueAsString(obj)
              .getBytes("UTF-8"));
      return ((TypedOutput) (obj));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new AssertionError(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new AssertionError(obj);
    }
  }
}
