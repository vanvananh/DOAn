package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ptit.entity.Status;

public interface StatusRepository extends JpaRepository< Status, Integer>, JpaSpecificationExecutor<Status>{

}
