package com.ns.BurseXmlSystem.BurseXmlSystem.Controllers;

import com.ns.BurseXmlSystem.BurseXmlSystem.Models.History;
import com.ns.BurseXmlSystem.BurseXmlSystem.Options;
import com.ns.BurseXmlSystem.BurseXmlSystem.Parsers.HistoryParser;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.HistoryRepository;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("http://localhost:4200")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryParser historyParser;

    @RequestMapping(value = {"/histories"}, method = RequestMethod.GET)
    public @ResponseBody Iterable<History> getAllHistories() {
        return historyRepository.findAll();
    }

    @RequestMapping(value = {"/sortedHistories"}, method = RequestMethod.POST)
    public @ResponseBody Iterable<History> getSortedHistories(@RequestBody Options options) {
        Sort.Direction direction = options.getDirection().compareTo("inc") == 0 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        return historyRepository.findAll(Sort.by(direction, options.getSortField()));
    }

    @RequestMapping(value = {"/filteredByDataHistories"}, method = RequestMethod.POST)
    public @ResponseBody Iterable<History> getFilteredByDataHistories(@RequestBody Options options) {
        Sort.Direction direction = options.getDirection().compareTo("inc") == 0 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        return historyRepository.findByTradedate(options.getFilterDate(), Sort.by(direction, options.getSortField()));
    }

    @RequestMapping(value = {"/filteredByTitleHistories"}, method = RequestMethod.POST)
    public @ResponseBody Iterable<History> getFilteredByTitleHistories(@RequestBody Options options) {
        Sort.Direction direction = options.getDirection().compareTo("inc") == 0 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        return historyRepository.findBySecurityEmitentTitleContaining(options.getFilterTitle(), Sort.by(direction, options.getSortField()));
    }

    @RequestMapping(value = { "/getHistory/{id}"}, method = RequestMethod.GET)
    public @ResponseBody Optional<History> getHistoryById(@PathVariable("id") Long id){
        return historyRepository.findById(id);
    }

    @RequestMapping(value = {"/addHistory"}, method =  RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addHistory(@RequestBody History history){
        historyParser.setSecid(history.getSecid());
        try {
            history = historyParser.connectWithSecurity(history);
            historyRepository.save(history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/editHistory"}, method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editHistory(@RequestBody History history){
        HistoryParser historyParser = new HistoryParser();
        historyParser.setSecid(history.getSecid());
        try {
            history = historyParser.connectWithSecurity(history);
            historyRepository.save(history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = { "/deleteHistory/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSecurity(@PathVariable("id") Long id){
        historyRepository.deleteById(id);
    }

}
