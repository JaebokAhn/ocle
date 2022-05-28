package com.yeol.ocle.service.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    /**
     * AWS 파일 업로드 샘플
     * @param file
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();   //파일명
        InputStream inputStream = file.getInputStream();    //파일 inputStream
        ObjectMetadata objectMetadata = new ObjectMetadata();

        byte[] bytes = IOUtils.toByteArray(inputStream);

        //파일명에 따라 Content type을 설정한다.
        objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(fileName));

        //Content length를 추가한다.
        objectMetadata.setContentLength(bytes.length);

        //ByteArrayInptStream으로 변환 한다.
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        s3Client.putObject(new PutObjectRequest(bucket, fileName, byteArrayInputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }
}
