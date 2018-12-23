
package com.ptit.util;


public class DataRespone {
  public Integer pageCurrent;
  public Integer totalPage;
  public Integer numberRecordPerPage;
  public Integer totalElement;
  public Object data;

 
  public DataRespone() {
  }

 
  public DataRespone(Integer pageCurrent, Integer totalPage, Integer numberRecordPerPage,
      Object data) {
    this.pageCurrent = pageCurrent;
    this.totalPage = totalPage;
    this.numberRecordPerPage = numberRecordPerPage;
    this.data = data;
  }


  public DataRespone(Integer pageCurrent, Integer totalPage, Integer numberRecordPerPage,
      Integer totalElement, Object data) {
    this.pageCurrent = pageCurrent;
    this.totalPage = totalPage;
    this.numberRecordPerPage = numberRecordPerPage;
    this.totalElement = totalElement;
    this.data = data;
  }

  public Integer getPageCurrent() {
    return pageCurrent;
  }

  public Integer getTotalPage() {
    return totalPage == 0 ? 1 : totalPage;
  }

  public Integer getNumberRecordPerPage() {
    return numberRecordPerPage;
  }

  public Integer getTotalElement() {
    return totalElement;
  }

  public Object getData() {
    return data;
  }

}
