import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	// Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "jet981217@naver.com";
    static final String FROMNAME = "국수사 크롤러봇";
	
    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    final static String []TO_1 = new String[] {"ljoljo3980@naver.com", "namhoyog@naver.com",//*/ 
    		"jet981217@naver.com"};
    final static String []TO_2 = new String[] {"kaay51022@naver.com", "namhoyog@naver.com", //*/
    		"jet981217@naver.com"};
    final static String []TO_3 = new String[] {"tkth2575@naver.com", "jsj4213@naver.com", "yjr0299@naver.com", "windkwon@naver.com",

"rkggksqlc123@naver.com",

"dbtp2@daum.net",

"ssibalbabe@naver.com",

"hchclgns1116@naver.com",

"tn4207@naver.com",

"ptsas98@naver.com",

"magma1251@naver.com",

"jmg1879@naver.com", "namhoyog@naver.com", //*/
    		"jet981217@naver.com"};
    
    final static String []TO_HO = new String[] {"namhoyog@naver.com",//*/ 
"jet981217@naver.com"};//호송

    final static String []TO_HA = new String[] {"namhoyog@naver.com",//*/ 
    "jet981217@naver.com"};//항만
    
    //보내는 메일 ID
    static final String SMTP_USERNAME = "jet981217@naver.com";
    
    //보내는 메일 비번
    static final String SMTP_PASSWORD = "dlftmdck@98";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "ConfigSet";
    
    // Amazon SES SMTP host name. This example uses the 미국 서부(오레곤) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "smtp.naver.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 587;
    static String SUBJECT = "철도대 코로나 정보";
    
    
 
    public final static String Mail_body(String Which_Fe,String TMO_index[], int How_Many_TMO, int TMO_data[][], String color[]) {
    	String start = null;
    	if (Which_Fe == "HO") {
    		start = "<h1>호송대 코로나 19 정보</h1>";
    	}
    	else if (Which_Fe == "HA") {
    		start = "<h1>항만단 코로나 19 정보</h1>";
    	}
    	else 
    		start = "<h1>" + Which_Fe + "철도대 코로나 19 정보</h1>";
    	String body =
        	    start+
        	    "<table border=\"1\" border=\"1\" width =\"700\" height=\"300\" align = \"center\">"+
        		"<th>지역</th>"+
        		"<th>오늘 확진자 수</th>"+
        		"<th>오늘 누적 확진자 수</th>"+
        		"<th>어제 누적 확진자 수</th>"+
        		"<th>코로나 미발생 일수</th>" ;
        
        for(int i = 0; i<How_Many_TMO; i++) {
        	if(i == How_Many_TMO-1) {
        		body +=
                		"<tr style=color:" + color[i] + ">"+
                		"<td>" +TMO_index[i] +"</td>"    +
                		    "<td>"+  Integer.toString(TMO_data[i][0] - TMO_data[i][1])+"</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][0]) + "</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][1]) + "</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][2]) + "</td>"+
                		"</tr>"+
                	    "</table>"+
                	    "<span style=color:#54be07> 초록색"+
                	    "<span>은 코로나 미발생 7일 이상<br>"+
                	    "<span style=color:#ff7f00> 주황색"+
                	    "<span>은 코로나 미발생 4~6일<br>"+ 
                	    "<span style=color:#FF0000> 빨간색"+
                	    "<span>은 코로나 미발생 1~3일 입니다.<br>"+
                		"<span style=font-size: 12pt; color: rgb(51,51,51);>오류 발견시 jet981217@naver.com으로 제보 부탁드립니다.</span>"
                	    		
        				;
        		//마지막부분
        		
        	}
        	else {
        		body +=
        				"<tr style=color:" + color[i] + ">"+
                		"<td>" +TMO_index[i] +"</td>"    +
                		    "<td>"+  Integer.toString(TMO_data[i][0] - TMO_data[i][1])+"</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][0]) + "</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][1]) + "</td>"+
                		    "<td>"+Integer.toString(TMO_data[i][2]) + "</td>"+
                		"</tr>"
        				;
        		
        	}
        }
    	return body;
    }

    public static void Write_mail(String Which_Fe,String TMO_index[], int How_Many_TMO, int TMO_data[][]) {
		String[] color = new String[How_Many_TMO];
        for(int i = 0; i<How_Many_TMO; i++)
        {
        	if(TMO_data[i][2]>=7) {
				color[i] = "#54be07";
			}
			else if(TMO_data[i][2]<7&&TMO_data[i][2]>3) {
				color[i] = "#ff7f00";
			}
			else {
				color[i] = "#FF0000";
			}
        }
		SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분");
        Date time1 = new Date();
        
        String time2 = format2.format(time1);
        
       
        final String BODY = Mail_body(Which_Fe, TMO_index, How_Many_TMO, TMO_data, color);
        

        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");

    	
    	String TO[] = new String[How_Many_TMO];
        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);
    	if(Which_Fe == "1")
    		TO = TO_1;
    	else if (Which_Fe == "2")
    		TO = TO_2;
    	else if (Which_Fe == "3")
    		TO = TO_3;
    	else if (Which_Fe == "HO")
    		TO = TO_HO;
    	else if (Which_Fe == "HA")
    		TO = TO_HA;
    	
       for(int i = 0; i<TO.length; i++)
       {
    	   if(TO[i] != null)
    	   {
    		   // Create a message with the specified information. 
   	        MimeMessage msg = new MimeMessage(session);
   	        try {
   				msg.setFrom(new InternetAddress(FROM,FROMNAME,"euc-kr"));
   			} catch (UnsupportedEncodingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			} catch (MessagingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	        try {
   				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO[i]));
   			} catch (AddressException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			} catch (MessagingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	        try {
   	        	if(Which_Fe == "1" || Which_Fe == "2" || Which_Fe == "3")
   	        		msg.setSubject(time2+ " "+Which_Fe+ SUBJECT);
   	        	else if (Which_Fe == "HO")
   	        		msg.setSubject(time2+ " 호송대 코로나 정보");
   	        	else
   	        		msg.setSubject(time2+ " 항만단 코로나 정보");
   			} catch (MessagingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	        try {
   				msg.setContent(BODY,"text/html; charset=EUC-KR");
   			} catch (MessagingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	        
   	        // Add a configuration set header. Comment or delete the 
   	        // next line if you are not using a configuration set
   	        try {
   				msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
   			} catch (MessagingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	            
   	        // Create a transport.
   	        Transport transport = null;
   			try {
   				transport = session.getTransport();
   			} catch (NoSuchProviderException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	                    
   	        // Send the message.
   	        try
   	        {
   	            System.out.println("Sending...");
   	            
   	            // Connect to Amazon SES using the SMTP username and password you specified above.
   	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
   	        	
   	            // Send the email.
   	            transport.sendMessage(msg, msg.getAllRecipients());
   	            System.out.println("Email sent!");
   	        }
   	        catch (Exception ex) {
   	            System.out.println("The email was not sent.");
   	            System.out.println("Error message: " + ex.getMessage());
   	        }
   	        finally
   	        {
   	            // Close and terminate the connection.
   	            try {
   					transport.close();
   				} catch (MessagingException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
   	        }
    	   }
       }
     
        
    }
    
	
}
