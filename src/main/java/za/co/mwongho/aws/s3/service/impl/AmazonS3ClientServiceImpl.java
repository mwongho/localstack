package za.co.mwongho.aws.s3.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.co.mwongho.aws.s3.service.AmazonS3ClientService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {
    private static final Logger logger = LoggerFactory.getLogger(AmazonS3ClientServiceImpl.class);

    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public AmazonS3ClientServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    private PutObjectResult upload(String key, InputStream inputStream){
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, new ObjectMetadata());

        PutObjectResult putObjectResult = this.amazonS3.putObject(putObjectRequest);

        return putObjectResult;
    }

    @Override
    public PutObjectResult uploadFile(MultipartFile multipartFile, boolean enablePublicReadAccess) throws IOException {
        return this.upload(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
    }

    @Override
    public void deleteFile(String fileName) {
        this.amazonS3.deleteObject(this.bucketName, fileName);
    }

    @Override
    public List<S3ObjectSummary> listAll() {
        ObjectListing objectListing =  this.amazonS3.listObjects(new ListObjectsRequest().withBucketName(this.bucketName));
        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
        return s3ObjectSummaries;
    }
}
