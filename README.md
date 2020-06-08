# aws-ses-sdk-service
This is simple email service api to send emails using AWS SDK.

user requests to send email by passing below request

http://localhost:8091/send?toEmail=<toEmail>&fromEmail=<fromEmail>&subject=<subject>&emailBody=<emailBody>

The fromEmail and toEmail needs to be verified for sandbox.

Config set is created to capture all SES events and these events are passed to SNS topic.
SNS topic is subscribed by SQS queue which receives all the events.