package com.hxtech.offer.service.misc;

import android.util.Log;

import com.octo.android.robospice.exception.RequestCancelledException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;

public abstract class ApiListener<T>
    implements RequestListener<T>
{

  public ApiListener() {}

  protected void onBadRequestError()
  {
    Log.e("APIRequestListener", "Unhandled HTTP Bad Request Error");
  }

  protected void onConflictError()
  {
    Log.e("APIRequestListener", "Unhandled HTTP Conflict Error");
  }

  protected void onForbiddenError()
  {
    Log.e("APIRequestListener", "Unhandled HTTP Forbidden Error");
  }

  protected void onNetworkError()
  {
    Log.e("APIRequestListener", "Unhandled Network error");
  }

  protected void onNotFoundError()
  {
    Log.e("APIRequestListener", "Unhandled HTTP Not Found Error");
  }

  public void onRequestFailure(SpiceException spiceexception) {
    Throwable throwable = spiceexception.getCause();
    if (throwable instanceof RetrofitError) {
      RetrofitError retrofitError = (RetrofitError) throwable;
      if (retrofitError.isNetworkError()) {
        onNetworkError();
        return;
      }
      if (retrofitError.getResponse() == null) {
        onUnknownError(throwable);
        return;
      }
      int i = retrofitError.getResponse().getStatus();
      switch (i) {
        default:
          if (i >= 500) {
            onServerError();
            return;
          }
          break;

        case 403:
          onForbiddenError();
          return;

        case 404:
          onNotFoundError();
          return;

        case 409:
          onConflictError();
          return;

        case 400:
          onBadRequestError();
          return;
      }
    }
    if (spiceexception instanceof RequestCancelledException) {
      Log.d("APIRequestListener", "Request cancelled.");
      return;
    } else {
      onUnknownError(throwable);
      return;
    }
  }

  protected void onServerError()
  {
    Log.e("APIRequestListener", "Unhandled HTTP Server Error");
  }

  protected void onUnknownError(Throwable throwable)
  {
    Log.e("APIRequestListener", "Error occurred", throwable);
  }
}
