package com.server.auth.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

import com.server.auth.utility.HashUtil;
import com.server.auth.repositories.UsersRepository;
//import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {
	
	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private String port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;

	@Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
	private String connectiontimeout;

	@Value("${spring.mail.properties.mail.smtp.timeout}")
	private String timeout;

	@Value("${spring.mail.properties.mail.smtp.writetimeout}")
	private String writetimeout;
	
	@Autowired
	UsersRepository userRepo;

	private String newPass = "";
	
	public String generateCommonLangPassword() {
//	    String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
//	    String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
//	    String numbers = RandomStringUtils.randomNumeric(2);
//	    String specialChar = RandomStringUtils.random(2, 35, 127, false, false);
//	    String totalChars = RandomStringUtils.randomAlphanumeric(2);
//	    String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
//	      .concat(numbers)
//	      .concat(specialChar)
//	      .concat(totalChars);
//	    List<Character> pwdChars = combinedChars.chars()
//	      .mapToObj(c -> (char) c)
//	      .collect(Collectors.toList());
//	    Collections.shuffle(pwdChars);
//	    String password = pwdChars.stream()
//	      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
//	      .toString();
		String password = "";
	    return password;
	}

	private void sendmail(long userId) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.connectiontimeout", connectiontimeout);
		props.put("mail.smtp.timeout", timeout);
		props.put("mail.smtp.writetimeout", writetimeout);

		String emailReceiver = "email@xxx.com";
		String contentBody = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
				"    <title>Password Reset</title>\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"        @media screen {\r\n" + 
				"            @font-face {\r\n" + 
				"                font-family: 'Source Sans Pro';\r\n" + 
				"                font-style: normal;\r\n" + 
				"                font-weight: 400;\r\n" + 
				"                src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            @font-face {\r\n" + 
				"                font-family: 'Source Sans Pro';\r\n" + 
				"                font-style: normal;\r\n" + 
				"                font-weight: 700;\r\n" + 
				"                src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"        body,\r\n" + 
				"        table,\r\n" + 
				"        td,\r\n" + 
				"        a {\r\n" + 
				"            -ms-text-size-adjust: 100%;\r\n" + 
				"            /* 1 */\r\n" + 
				"            -webkit-text-size-adjust: 100%;\r\n" + 
				"            /* 2 */\r\n" + 
				"        }\r\n" + 
				"        table,\r\n" + 
				"        td {\r\n" + 
				"            mso-table-rspace: 0pt;\r\n" + 
				"            mso-table-lspace: 0pt;\r\n" + 
				"        }\r\n" + 
				"        img {\r\n" + 
				"            -ms-interpolation-mode: bicubic;\r\n" + 
				"        }\r\n" + 
				"        a[x-apple-data-detectors] {\r\n" + 
				"            font-family: inherit !important;\r\n" + 
				"            font-size: inherit !important;\r\n" + 
				"            font-weight: inherit !important;\r\n" + 
				"            line-height: inherit !important;\r\n" + 
				"            color: inherit !important;\r\n" + 
				"            text-decoration: none !important;\r\n" + 
				"        }div[style*=\"margin: 16px 0;\"] {\r\n" + 
				"            margin: 0 !important;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        body {\r\n" + 
				"            width: 100% !important;\r\n" + 
				"            height: 100% !important;\r\n" + 
				"            padding: 0 !important;\r\n" + 
				"            margin: 0 !important;\r\n" + 
				"        }\r\n" + 
				"        table {\r\n" + 
				"            border-collapse: collapse !important;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        a {\r\n" + 
				"            color: #1a82e2;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        img {\r\n" + 
				"            height: auto;\r\n" + 
				"            line-height: 100%;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"            border: 0;\r\n" + 
				"            outline: none;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"background-color: #e9ecef;\">\r\n" + 
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"        <tr>\r\n" + 
				"            <td align=\"center\" bgcolor=\"#e9ecef\">\r\n" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\">\r\n" + 
				"                         </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align=\"center\" bgcolor=\"#e9ecef\">\r\n" + 
				"                 <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"center\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"padding: 36px 24px 0; font-family: HelveticaNeueLight, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\r\n" + 
				"                            <img src=\"http://45.251.74.163/static/media/bank-bjb-syariah.9211df92.png\" alt=\"Logo\"\r\n" + 
				"                                border=\"0\" style=\"display: block; width: 100px; border-radius: 5px;\">\r\n" + 
				"                            <h1\r\n" + 
				"                                style=\"margin: 0; font-size: 28px; font-weight: 300; letter-spacing: .50px; line-height: 48px; color: #333333;\">\r\n" + 
				"                                PASTItuh BJBS</h1>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align=\"center\" bgcolor=\"#e9ecef\">\r\n" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td colspan=\"3\" align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"padding: 10px 20px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Kami mendapatkan permintaan untuk melakukan reset password pada aplikasi PASTItuh BJBS.\r\n" + 
				"                            Berikut detail informasi beserta password baru Anda :\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 25%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Username\r\n" + 
				"                        </td>\r\n" +
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 25%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Nama Lengkap\r\n" + 
				"                        </td>\r\n" +
				"                    </tr>\r\n" +
				"                    <tr>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 25%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Cabang\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" +
				"                    <tr>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 25%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Role\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" +
				"                    <tr>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 25%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            Password Baru\r\n" + 
				"                        </td>\r\n" + 
				"                        <td align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"width: 75%; padding-left: 40px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            : " + newPass + " \r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td colspan=\"3\" align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"padding: 10px 20px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            <p style=\"margin: 0;\">Klik tautan berikut untuk login ke aplikasi PASTItuh BJBS</p>\r\n" + 
				"                            <p style=\"margin: 0;\"><a href=\"http://45.251.74.163/\" target=\"_blank\">Login PASTItuh BJBS</a></p>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td colspan=\"3\" align=\"left\" bgcolor=\"#ffffff\"\r\n" + 
				"                            style=\"padding: 10px 20px; font-family: HelveticaNeueLight, Arial, sans-serif; font-weight: 300; font-stretch: normal; font-size: 14px; line-height: 28px; letter-spacing: .50px; color: #333333;\">\r\n" + 
				"                            <p style=\"margin: 0;\">Hormat Kami,<br> Bank BJB Syariah</p>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\">\r\n" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"center\" bgcolor=\"#e9ecef\"\r\n" + 
				"                            style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\r\n" + 
				"                            <p style=\"margin: 0;\">To stop receiving these emails, you can <a href=\"#\"\r\n" + 
				"                                    target=\"_blank\">unsubscribe</a> at any time.</p>\r\n" + 
				"                            <p style=\"margin: 0;\">Jl. Braga No.12, Braga, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40111</p>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>\r\n" + 
				"</body>\r\n" + 
				"</html>";
	   
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	   	Message msg = new MimeMessage(session);
	   	msg.setFrom(new InternetAddress(username, false));
	   	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReceiver));
	   	msg.setSubject("Reset Password");
		msg.setContent(contentBody, "text/html");
		msg.setSentDate(new Date());
		Transport.send(msg);   
	}
	
	@Transactional
	@RequestMapping(value = "/sendemail")
   	public String aSendEmail(@RequestHeader("user_id") long userId) throws AddressException, MessagingException, IOException, Exception {
		newPass = generateCommonLangPassword().toString();
		if(userRepo.updatePassword(HashUtil.SHA_256.digestAsHex(newPass), userId)==1) {
			sendmail(userId);
			return "Reset Password Success";
		}else {
			return "Reset Password Failed";
		}  
	} 
}
