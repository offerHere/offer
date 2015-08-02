package com.hxtech.offer.service.data;

import java.util.ArrayList;

public class Repo {

  public String name;
  public String description;

  @Override
  public String toString() {
    return "Repo [name=" + name + ", description=" + description + "]";
  }

  public static class List extends ArrayList<Repo> {}
}
