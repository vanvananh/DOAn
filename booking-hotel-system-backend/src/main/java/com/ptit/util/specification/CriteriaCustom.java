/**
 * 
 */
package com.ptit.util.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ptit.util.Constants;
import com.ptit.util.Constants.Operation;




public class CriteriaCustom {
  private List<String> key;
  private Operation operation;
  private Object value;


  public CriteriaCustom(String key, Operation operation, Object value) {

    parseKey(key);
    this.operation = operation;
    this.value = value;
  }


  private void parseKey(String key) {
    if (key.contains(Constants.Common.DOT)) {
      this.key = Arrays.stream(key.split(Constants.Common.DOT_ENCODE)).collect(Collectors.toList());
    } else {
      this.key = new ArrayList<>();
      this.key.add(key);
    }
  }


  public List<String> getKey() {
    return key;
  }


  public Operation getOperation() {
    return operation;
  }

 
  public Object getValue() {
    return value;
  }

}
