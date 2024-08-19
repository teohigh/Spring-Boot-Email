### Spring Boot Email

## Mô tả

Dự án này sử dụng `Spring Boot Email` để thử nghiệm gửi email cho người dùng khi đặt phòng thành công. Dưới đây là một số thông tin bổ sung.

## Spring Boot Email là gì?

Spring Boot Email là một dự án mẫu minh họa cách gửi email bằng cách sử dụng Spring Boot. Dự án này bao gồm các tính năng như:

- Cấu hình máy chủ SMTP
- Gửi email văn bản đơn giản
- Gửi email HTML
- Đính kèm tệp vào email
- Sử dụng mẫu Thymeleaf cho nội dung email
- Xử lý lỗi khi gửi email
- Gửi email không đồng bộ

## Làm sao để sử dụng trong dự án Spring Boot

1. **Thêm dependency vào `pom.xml`**:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-mail</artifactId>
   </dependency>
   ```

2. **Cấu hình SMTP trong `application.properties`:**

```properties
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

3. **Tạo một service để gửi email:**

```java
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Attachment", file);

        mailSender.send(message);
    }
}
```
