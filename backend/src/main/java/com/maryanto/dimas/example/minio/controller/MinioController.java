package com.maryanto.dimas.example.minio.controller;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/minio")
public class MinioController {

    private MinioClient minio;

    public MinioController(MinioClient minio) {
        this.minio = minio;
    }

    @GetMapping("/check/{bucketName}/exists")
    public ResponseEntity<?> checkBucketIsExists(@PathVariable("bucketName") String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean exists = this.minio.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build());
        if (exists)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}