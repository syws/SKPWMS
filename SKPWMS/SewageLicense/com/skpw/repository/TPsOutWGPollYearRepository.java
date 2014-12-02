package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutWGPollYear;

@Repository
public interface TPsOutWGPollYearRepository extends JpaRepository<TPsOutWGPollYear, String>,JpaSpecificationExecutor<TPsOutWGPollYear>{

}
