package com.ns.BurseXmlSystem.BurseXmlSystem.Controllers;

import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.SecurityAlereadyExistsException;
import com.ns.BurseXmlSystem.BurseXmlSystem.Models.Security;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.HistoryRepository;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:4200")
public class SecurityController {

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    HistoryRepository historyRepository;

    @RequestMapping(value={"/securities"}, method = RequestMethod.GET)
    public @ResponseBody Iterable<Security> getAllSecurities() {
        return securityRepository.findAll();
    }

    @RequestMapping(value = { "/getSecurity/{id}"}, method = RequestMethod.GET)
    public @ResponseBody Optional<Security> getSecurityById(@PathVariable("id") Integer id){
        return securityRepository.findById(id);
    }

    @RequestMapping(value = {"/addSecurity"}, method =  RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSecurity(@RequestBody Security security) throws SecurityAlereadyExistsException {
        String secid = security.getSecid();
        List<Security> securityList = securityRepository.findBySecid(secid);
        if(securityList.size() == 0) {
            securityRepository.save(security);
        } else {
            throw new SecurityAlereadyExistsException();
        }
    }

    @RequestMapping(value = {"/editSecurity"}, method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editSecurity(@RequestBody Security security) throws SecurityAlereadyExistsException {
        System.out.println(security);
        String secid = security.getSecid();
        List<Security> securityList = securityRepository.findBySecid(secid);
        if(securityList.size() == 0 ||
                (securityList.size() == 1 && securityList.get(0).getId().equals(security.getId()))) {
            securityRepository.save(security);
        } else {
            throw new SecurityAlereadyExistsException();
        }
    }

    @RequestMapping(value = { "/deleteSecurity/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSecurity(@PathVariable("id") Integer id){
        Optional<Security> optional = securityRepository.findById(id);
        Security security = optional.get();
        historyRepository.deleteBySecurity(security);
        securityRepository.deleteById(id);
    }
}
