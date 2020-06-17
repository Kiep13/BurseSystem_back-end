package com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories;

import com.ns.BurseXmlSystem.BurseXmlSystem.Models.History;
import com.ns.BurseXmlSystem.BurseXmlSystem.Models.Security;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM History AS h WHERE h.security = :security")
    void deleteBySecurity(@Param("security") Security security);

    List<History> findAll(Sort sort);

    List<History> findByTradedate(Date date, Sort sort);

    List<History> findBySecurityEmitentTitleContaining(String emitentTitle, Sort sort);
}
