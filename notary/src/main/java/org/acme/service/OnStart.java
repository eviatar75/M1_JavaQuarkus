package org.acme.service;

import org.apache.camel.util.FileUtil;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;

@ApplicationScoped
public class OnStart {
    public  OnStart() {
        File directory = new File("data/pdf");

        if(directory.exists()){
            File[] listFiles = directory.listFiles();
            assert listFiles != null;
            for(File listFile : listFiles) {
                    FileUtil.deleteFile(new File(listFile.getPath()));

            }
        }

        directory = new File("saveContratPost/queue");

        if(directory.exists()){
            File[] listFiles = directory.listFiles();
            assert listFiles != null;
            for(File listFile : listFiles) {
                FileUtil.deleteFile(new File(listFile.getPath()));

            }
        }



    }
}

