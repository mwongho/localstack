package za.co.mwongho.aws.s3.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AmazonS3ClientService {


    PutObjectResult uploadFile(MultipartFile multipartFile, boolean enablePublicReadAccess) throws IOException;

    void deleteFile(String fileName);

    List<S3ObjectSummary> listAll();
}
