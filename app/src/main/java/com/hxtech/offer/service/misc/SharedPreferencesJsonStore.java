package com.hxtech.offer.service.misc;

import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Referenced classes of package com.rjfittime.app.service.misc:
// PersistentJsonStore

public class SharedPreferencesJsonStore
    implements PersistentJsonStore
{

  private static final String TAG = SharedPreferencesJsonStore.class.getSimpleName();
  private ObjectMapper mObjectMapper;
  private SharedPreferences mSharedPreferences;

  public SharedPreferencesJsonStore(Context context, ObjectMapper objectmapper, String s)
  {
    mSharedPreferences = context.getSharedPreferences(s, 0);
    mObjectMapper = objectmapper;
  }

  public SharedPreferencesJsonStore(Context context, String s)
  {
    this(context, new ObjectMapper(), s);
  }

  public String getRawText(String s)
  {
    return mSharedPreferences.getString(s, "null");
  }

  public Object getValue(String s, TypeReference typereference)
      throws IOException
  {
    return mObjectMapper.readValue(getRawText(s), typereference);
  }

  public Object getValue(String s, Class class1)
      throws IOException
  {
    return mObjectMapper.readValue(getRawText(s), class1);
  }

  public void putRawText(String s, String s1)
  {
    mSharedPreferences.edit().putString(s, s1).apply();
  }

  public void putValue(String s, Object obj)
      throws JsonProcessingException
  {
    putRawText(s, mObjectMapper.writeValueAsString(obj));
  }

}
