package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.entity.Menu;

/**
 * This class is Repository of Menu.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
