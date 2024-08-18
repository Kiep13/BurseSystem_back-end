package com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories;

import com.ns.BurseXmlSystem.BurseXmlSystem.Models.Security;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface SecurityRepository extends CrudRepository<Security, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT s FROM Security AS s WHERE s.secid = :secid")
    ArrayList<Security> findBySecid(@Param("secid") String secid);
}
