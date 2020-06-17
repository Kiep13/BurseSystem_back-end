package com.ns.BurseXmlSystem.BurseXmlSystem.Controllers;
import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.WrongXmlFileException;
import com.ns.BurseXmlSystem.BurseXmlSystem.FileHelper;
import com.ns.BurseXmlSystem.BurseXmlSystem.Parsers.HistoryParser;
import com.ns.BurseXmlSystem.BurseXmlSystem.Parsers.SecurityParser;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.HistoryRepository;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
@CrossOrigin("http://localhost:4200")
public class FileUploadController {

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    HistoryParser historyParser;

    @Autowired
    SecurityParser securityParser;

    @RequestMapping(value={"/uploadHistory"}, method= RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void uploadHistory(@RequestParam MultipartFile file) throws Exception {

        long start = System.currentTimeMillis();

        File convertFile = fileHelper.readFile(file);

        historyParser.setFile(convertFile);
        historyParser.parse();

        convertFile.delete();

        System.out.println(System.currentTimeMillis() - start);
    }

    @RequestMapping(value={"/uploadSecurity"}, method= RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void uploadSecurity(@RequestParam MultipartFile file) throws IOException, WrongXmlFileException {

        long start = System.currentTimeMillis();

        File convertFile = fileHelper.readFile(file);

        securityParser.setFile(convertFile);
        securityParser.parse();

        convertFile.delete();

        System.out.println(System.currentTimeMillis() - start);
    }
}
