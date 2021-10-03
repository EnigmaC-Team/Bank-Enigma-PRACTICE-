package com.enigma.bankenigma.service.file;

import com.enigma.bankenigma.service.account.UserAccountService;
import com.enigma.bankenigma.string.properties.ModeString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FileService {

    @Autowired
    UserAccountService userAccountService;

    public void saveFileTo(MultipartFile multipartFile, String id, String serviceName) throws IOException {
        validateId(id, serviceName);

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String destination = Paths.get("")
                .toAbsolutePath() +
                "/upload/" + serviceName +
                "/" + id +
                "."+ extension;

        File fileTmp = new File(destination);
        multipartFile.transferTo(fileTmp);
    }

    private void validateId(String id, String serviceName) {
        if(serviceName.equals(ModeString.USER_ACCOUNT)){
            userAccountService.checkAccount(id);
        }
    }
}
