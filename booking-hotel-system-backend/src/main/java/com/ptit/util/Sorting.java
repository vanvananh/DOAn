package com.ptit.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class Sorting {

  private List<Sort.Direction> sortTypes;
  private List<String> sortFields;

  public Sorting(String sortType, String sortField) {
    this.sortTypes = new ArrayList<>();
    this.sortFields = new ArrayList<>();
    if (sortType.toLowerCase().equals(Sort.Direction.ASC.toString().toLowerCase())) {
      this.sortTypes.add(Sort.Direction.ASC);
    } else {
      this.sortTypes.add(Sort.Direction.DESC);
    }
    this.sortFields.add(sortField);
  }

 
  public Sorting(Direction sortType, String sortField) {
    this.sortTypes = new ArrayList<>();
    this.sortFields = new ArrayList<>();
    this.sortTypes.add(sortType);
    this.sortFields.add(sortField);
  }


  public Sorting and(String sortType, String sortField) {
    if (sortType.toLowerCase().equals(Sort.Direction.ASC.toString().toLowerCase())) {
      this.sortTypes.add(Sort.Direction.ASC);
    } else {
      this.sortTypes.add(Sort.Direction.DESC);
    }
    return this;
  }


  public Sorting and(Direction sortType, String sortField) {
    this.sortTypes.add(sortType);
    this.sortFields.add(sortField);
    return this;
  }

  public void convertSort(String sortFieldOld, String sortFieldNew) {
    for (int i = 0; i < sortFields.size(); i++) {
      if (sortFields.get(i).contains(sortFieldOld)) {
        sortFields.set(i, sortFields.get(i).replace(sortFieldOld, sortFieldNew));
      }
    }
  }

  public List<Sort.Direction> getSortTypes() {
    return sortTypes;
  }

  public List<String> getSortFields() {
    return sortFields;
  }

}
