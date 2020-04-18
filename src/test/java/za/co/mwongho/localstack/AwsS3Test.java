package za.co.mwongho.localstack;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import za.co.mwongho.aws.s3.service.AmazonS3ClientService;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AwsS3Test extends LocalStackTest {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Test
    @Order(1)
    public void uploadFile() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Test text".getBytes());
        PutObjectResult putObjectResult = this.amazonS3ClientService.uploadFile(mockMultipartFile, true);
        Assertions.assertNotNull(putObjectResult);
    }

    @Test
    @Order(2)
    public void listAllTest() {
        List<S3ObjectSummary> s3ObjectSummaries = this.amazonS3ClientService.listAll();
        Assertions.assertNotNull(s3ObjectSummaries);
        Assertions.assertFalse(s3ObjectSummaries.isEmpty());
    }
}
