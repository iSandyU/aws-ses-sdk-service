package com.sandeepmane.awssessdkservice.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.sandeepmane.awssessdkservice.model.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.config.set-name}")
    private String configSetName;

    public boolean sendEmail(Email email) {

        AmazonSimpleEmailServiceClientBuilder builder = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region);

        AmazonSimpleEmailService client = builder.build();

        SendEmailRequest request = new SendEmailRequest().withConfigurationSetName(configSetName)
                .withDestination(new Destination().withToAddresses(email.getToEmail())).withSource(email.getFromEmail())
                .withSource(email.getFromEmail()).withReplyToAddresses(
                        email.getReplyTo())
                .withMessage(new Message()
                        .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(email.getEmailBody()))
                                .withText(new Content().withCharset("UTF-8").withData(email.getEmailBody())))
                        .withSubject(new Content().withData(email.getSubject())));

        /**
         * AWS credentials are stored in the /.aws/credentials file, if not then set the
         * credentials in the builder
         */

        client.sendEmail(request);

        return true;
    }

}