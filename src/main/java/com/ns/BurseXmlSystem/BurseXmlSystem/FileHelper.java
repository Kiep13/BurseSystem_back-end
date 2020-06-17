package com.ns.BurseXmlSystem.BurseXmlSystem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileHelper {

    public File readFile(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return convertFile;
    }

    public File createFile(String fileName, StringBuilder stringBuilder) throws IOException {
        String name = fileName.toUpperCase() +  ".xml";
        File convertFile = new File(name);
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(stringBuilder.toString().getBytes());
        fout.close();
        return convertFile;
    }
}
