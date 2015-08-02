package com.hxtech.offer.service.misc;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public interface PersistentJsonStore
{

  public abstract Object getValue(String s, TypeReference typereference)
      throws IOException;

  public abstract Object getValue(String s, Class class1)
      throws IOException;

  public abstract void putValue(String s, Object obj)
      throws JsonProcessingException;
}
