package com.ptit.service;

import java.util.List;

import com.ptit.entity.Menu;

/**
 * This class is public to controller using business of Menu.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface MenuService {

  /**
   * This method is get list Menu.
   * 
   * @return List Menu .
   * @author: NNDuy
   */
  List<Menu> getAllMenu();

}
