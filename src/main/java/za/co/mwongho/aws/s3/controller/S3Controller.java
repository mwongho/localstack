package za.co.mwongho.aws.s3.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.mwongho.aws.s3.service.AmazonS3ClientService;

import java.util.List;

@RestController
@RequestMapping("/v1/s3")
public class S3Controller {

    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    public S3Controller(AmazonS3ClientService amazonS3ClientService) {
        this.amazonS3ClientService = amazonS3ClientService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<S3ObjectSummary> getAll() {
        return this.amazonS3ClientService.listAll();
    }
}
