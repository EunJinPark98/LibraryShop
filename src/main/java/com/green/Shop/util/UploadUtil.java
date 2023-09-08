package com.green.Shop.util;

import com.green.Shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    //파일 첨부 기능(단일 파일 업로드)
    public static ImgVO uploadFile(MultipartFile img){
        ImgVO imgVO = null;

        if(!img.isEmpty()){
            imgVO = new ImgVO();
            //첨부파일
            String originFileName = img.getOriginalFilename();
            //첨부될 파일 명 설정
            String uuid = UUID.randomUUID().toString(); //랜덤
            String extension = originFileName.substring(originFileName.lastIndexOf(".")); //확장자
            String attachedFileName = uuid + extension;
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
                img.transferTo(file);   //첨부

                imgVO.setOriginFileName(originFileName);
                imgVO.setAttachedFileName(attachedFileName);
                imgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imgVO;
    }

    //다중 파일 업로드
    public static void multiFileUpload(MultipartFile[] imgs){
        for(MultipartFile img : imgs){
            uploadFile(img);
        }
    }
}
