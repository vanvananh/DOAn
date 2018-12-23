package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.entity.Config;



/**
 * This class is Repository of Config.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface ConfigRepository extends JpaRepository<Config, Integer> {

}
