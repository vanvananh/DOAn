package com.ptit.util;


public class Paging {

  private int pageNumber;
  private int numberRecordPerPage;

  public Paging(int pageNumber, int numberRecordPerPage) {
    this.pageNumber = pageNumber;
    this.numberRecordPerPage = numberRecordPerPage;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getNumberRecordPerPage() {
    return numberRecordPerPage;
  }

}
