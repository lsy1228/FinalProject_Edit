package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.MailDto;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service // 스프링에서 비즈니스 로직을 처리하는 클래스임을 표시
@RequiredArgsConstructor // 생성자를 자동으로 생성
public class MailSendService {
    // 의존성 주입을 통해 필요한 객체를 가져옴
    private final JavaMailSender javaMailSender; // 이메일을 보내기 위해 사용되는 인터페이스, 의존성 주인을 통해 이 인터페이스를 사용
    private static final String senderEmail = "aelmusic1234@naver.com"; // 이메일을 발송할 때 사용될 발송자 이메일 주소
    private static int number; // 생성한 랜덤 숫자를 저장할 정적 필드

    public static void createNumber() { // 6자기의 램덤 숫자를 생성해 number필드에 저장
        number = (int)(Math.random() * (90000)) + 100000; // (int) Math.random() * (최댓값-최소값+1)+최소값
    }

    public MimeMessage CreateMail(String mail) { // 인증 이메일을 생성 입력 받은 mail주소로 이메일을 발송하도록 MimeMessage 객체 생성
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try { // 이메일 발신자, 수신자,제목,본문을 HTML형식으로 작성하며 랜덤한 숫자 포함
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    // 이메일 유효성 검사
    public boolean validateCode(int code) {
        return code == number;
    }
    // 이메일로 보내진 코드와 입력창에 적힌 코드가 같아야 true

    public boolean verifyCode(String mail, int code) {
        return validateCode(code);
    }


    public int sendMail(String mail) { // 생성한 인증 이메일을 입력 받은 mail주소로 전송
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return number;
    }

//    // 임시 메일 발송
//    @Autowired
//    UserRepository userRepository;
//
//    public MailDto createMailAndChangePassword(String userEmail, String userName) {
//        String str = getTempPassword();
//        MailDto mailDto = new MailDto();
//        mailDto.setReceiver(userEmail);
//        mailDto.setTitle(userName + "님의 iMMUTABLE 임시비밀번호 안내 이메일 입니다.");
//        mailDto.setContent("\"안녕하세요. iMMUTABLE 임시비밀번호 안내 관련 이메일 입니다.\" + \"[\" + userName + \"]\" +\"님의 임시 비밀번호는 \"\n" +
//                "        + str + \" 입니다.");
//        updatePassword(str, userEmail);
//        return mailDto;
//    }
//    public void updatePassword(String str, String userEmail) {
//        int userId = userRepository.findByUserEmailAndUserPwd(userEmail).getId();
//        userRepository.updateUserPassword(userId, userPassword);
//    }
//
//    public String getTempPassword(){
//        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//
//        String str = "";
//
//        int idx = 0;
//        for (int i = 0; i < 10; i++) {
//            idx = (int) (charSet.length * Math.random());
//            str += charSet[idx];
//        }
//        return str;
//    }
}
