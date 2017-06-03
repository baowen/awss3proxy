package com.benaowen.aws.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ben on 02/06/17.
 */
public class S3Tester {

    public S3Tester() {
    }

    private void log(String str) {
        System.out.println(str);
    }

    public void uploadFile() {

        log("STARTED: uploadFile");


        try {
            log("Uploading a new object to S3 from a file");

            Attachment attachment = new Attachment();
            //update attachment properties
            String filename = "test.txt";
            //          Boolean s3Enabled = Boolean.valueOf((String)config.get(FilestoreS3Config.S3_ENABLE));
            attachment.setS3Region(Regions.EU_WEST_1.getName());
            attachment.setS3Path("test/");
            attachment.setS3Filename(filename);
            attachment.setBase64file("EWSH");


            AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

            builder.setRegion(Regions.EU_WEST_1.getName());

            builder.setClientConfiguration(
                    new ClientConfiguration()
                            .withConnectionTimeout(3000)
                            .withRequestTimeout(3000)
                                 .withProxyPort(3128)
                                  .withProxyHost("172.31.9.34")

            );

            AmazonS3 client = builder.build();


            // create the put request
            PutObjectRequest putRequest = getPutObjectRequest(attachment);

            // upload the file to amazon S3
            PutObjectResult result = client.putObject(putRequest);


            log("S3 object added!");

        } catch (AmazonServiceException ase) {

            ase.printStackTrace();

            log("AmazonServiceException, "
                    + "Error Message:    "+ase.getMessage()
                    + "HTTP Status Code: " + ase.getStatusCode()
                    + "AWS Error Code:   " + ase.getErrorCode()
                    + "Error Type:       " + ase.getErrorType()
                    + "Request ID:       " + ase.getRequestId()
            );


        } catch (AmazonClientException ace) {
            log("AmazonClientException, "
                    +"Error Message: " + ace.getMessage());


        }

        log("COMPLETED: uploadFile");



    }

    /**
     *
     * Create the put request for amazon S3.
     * use the attachment. decode the base64 image string
     * set the filename and application_id tags for the request
     *
     * @param attachment
     * @return
     */
    private PutObjectRequest getPutObjectRequest(Attachment attachment) {
        //       String filename = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(attachment.getFilename());
        String str = attachment.getBase64file();
        byte[] decoded = Base64.decodeBase64(str);
        //   InputStream is = new ByteArrayInputStream( decoded);


        InputStream is = new ByteArrayInputStream( "EWSH".getBytes());

        ObjectMetadata meta = new ObjectMetadata();
        PutObjectRequest putRequest = new PutObjectRequest( "ben-olev-dev", attachment.getS3Path()+attachment.getS3Filename(), is, meta );
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("filename", attachment.getFilename()));
        tags.add(new Tag("application_id", Long.toString(attachment.getApplicationId())));
        putRequest.setTagging(new ObjectTagging(tags));
        return putRequest;
    }

    public static void main(String[] args){

        S3Tester tester = new S3Tester();
        tester.uploadFile();

    }

}
