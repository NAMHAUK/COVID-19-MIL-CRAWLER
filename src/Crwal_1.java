import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crwal_1 {
	
    //1ö���� ������ URL
	public static String URL_Yongsan = "https://www.seoul.go.kr/coronaV/coronaStatus.do";
    public static String URL_Gwangmyeong="https://www.gg.go.kr/contents/contents.do?ciIdx=1150&menuId=2909";
    public static String URL_Pyeongtaek="https://www.gg.go.kr/contents/contents.do?ciIdx=1150&menuId=2909";
    public static String URL_Suwon="https://www.gg.go.kr/contents/contents.do?ciIdx=1150&menuId=2909";
    public static String URL_Suseo ="https://www.seoul.go.kr/coronaV/coronaStatus.do";
    public static String URL_DongSeoul="https://www.seoul.go.kr/coronaV/coronaStatus.do";
    public static String URL_Chouncheon="https://www.provin.gangwon.kr/covid-19.html";
    public static String URL_Wonju="https://www.provin.gangwon.kr/covid-19.html";
    public static String URL_Gangneung="https://www.provin.gangwon.kr/covid-19.html";
    
    //1ö���� ������ 
    public static String contents_Yongsan="td";
    public static String contents_Gwangmyeong="dl";
    public static String contents_Pyeongtaek="dl";
    public static String contents_Suwon="dl";
    public static String contents_Suseo="td";
    public static String contents_DongSeoul="td";
    public static String contents_Chouncheon="td.txt-c";
    public static String contents_Wonju="td.txt-c";
    public static String contents_Gangneung="td.txt-c";
	
    //Type1: ���,����,������
    //Type2: ����,����,����
    //Type3: ��õ,����,����
    public static int crawling(String URL,String contents_route,int cheaking) {
    	Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.�� class �����̽��ٴ� �±� #�� id
		Elements contents = doc.select(contents_route);
		String to_string = contents.toString();
		String to_string_temp=null;
		
		//Type���� tagã��
		if(cheaking==0||cheaking==4||cheaking==5) {               //Type1
			to_string_temp = SearchTag_Type_Seoul(to_string,cheaking);
		}//����
		else if(cheaking==1||cheaking==2||cheaking==3) {          //Type2
			to_string_temp = SearchTag_Type_Gyeonggi(to_string,cheaking);
		}//���
		else if(cheaking==6||cheaking==7||cheaking==8) {          //Type3
			to_string_temp = SearchTag_Type_Gangwon(to_string,cheaking);
		}//����
		
		to_string = to_string_temp.replaceAll("[^0-9]", "");
		int today_Infected_person = Integer.parseInt(to_string);
		return today_Infected_person;
    }
   
    private static String SearchTag_Type_Seoul(String to_string,int cheaking) {
    	String to_string_temp = null;
		int var = 0;
		int var_chk=0;
		if(cheaking==0)     //����̸� 3
			var_chk=3;
		else if(cheaking==4)//�����̸� 36
			var_chk=36;
		else if(cheaking==5)//�������̸� 5
			var_chk=5;
		
		for(int i = 0; i<to_string.length(); i++)
		{
			if(var == var_chk)
			{
				i = to_string.length();
			}
			else
			{
				if(to_string.charAt(i) == '\n')
				{
					var++;
				}
				if(var == var_chk-1)
				{
				to_string_temp += to_string.charAt(i);
				}
			}	
		}
		return to_string_temp;
    }
    private static String SearchTag_Type_Gyeonggi(String to_string,int cheaking) {
    	String to_string_temp = null;
    	int var = 0;
		char ch1 = 0,ch2 = 0;
		if(cheaking==1) {
			ch1='��';
			ch2='��';
		}
		else if(cheaking==3) {
			ch1='��';
			ch2='��';
		}
		else if(cheaking==2) {
			ch1='��';
			ch2='��';
		}
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == ch1 && to_string.charAt(i+1) == ch2)
			{
				var = 1;
			}
			if( var == 1 && to_string.charAt(i) == '/' && to_string.charAt(i+1) == 's')
			{	
				var = 0;
				i = to_string.length();
			}
			if(var == 1)
			{
				to_string_temp += to_string.charAt(i);
			}
		}
		return to_string_temp;
    }
    private static String SearchTag_Type_Gangwon(String to_string,int cheaking) {
    	String to_string_temp = null;
    	int var = 0;
    	int var_chk=0;
    	if(cheaking==6)     //��õ�̸� 1
    		var_chk=1;
    	else if(cheaking==7)//�����̸� 2
    		var_chk=2;
    	else if(cheaking==8)//�����̸� 3
    		var_chk=3;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '\n')
				var++;
			if(var == var_chk)
			{
				to_string_temp += to_string.charAt(i);
			}
			if(var == var_chk+1)
			{	
				i = to_string.length();
			}
		}
		return to_string_temp;
    }
	
	
	public static void Crwaling_1(int City[])
    {
		//�� ����ֱ�
		City[0] = crawling(URL_Yongsan, contents_Yongsan,0);         //����,���
		City[1] = crawling(URL_Gwangmyeong, contents_Gwangmyeong, 1);//����
		City[2] = crawling(URL_Pyeongtaek, contents_Pyeongtaek, 2);  //����
		City[3] = crawling(URL_Suwon, contents_Suwon, 3);            //����
		City[4] = crawling(URL_Suseo, contents_Suseo, 4);            //����
		City[5] = crawling(URL_DongSeoul, contents_DongSeoul, 5);    //������(������)
		City[6] = crawling(URL_Chouncheon, contents_Chouncheon, 6);  //��õ
		City[7] = crawling(URL_Wonju, contents_Wonju, 7);            //����,����(����)
		City[8] = crawling(URL_Gangneung, contents_Gangneung, 8);    //����
		
    }
}
