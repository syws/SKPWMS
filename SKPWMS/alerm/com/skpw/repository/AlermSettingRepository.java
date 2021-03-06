package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.AlermSetting;

public interface AlermSettingRepository extends JpaRepository<AlermSetting, String>,JpaSpecificationExecutor<AlermSetting> {

}
