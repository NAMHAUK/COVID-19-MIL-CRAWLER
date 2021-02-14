import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crwal_2 {
	
	 
    //2ö���� ������ URL
    static String URL_DongDaegu = "https://www.dong.daegu.kr/corona19/index.php";
    static String URL_Busan ="https://www.busan.go.kr/covid19/Corona19.do";
    static String URL_Changwon="http://xn--19-q81ii1knc140d892b.kr/main/main.do";
    static String URL_Pohang="https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";
    static String URL_Gyeongju="https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";
    static String URL_Gimcheon="https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";
    static String URL_Gyeongsan="https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";
    static String URL_Ulsan="http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
    static String URL_Jinju="http://xn--19-q81ii1knc140d892b.kr/main/main.do";
    static String URL_Yeongcheon="https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";
    static String URL_Gupo = "https://www.busan.go.kr/covid19/Corona19.do";
   
    //2ö���� ������ 
    static String contents_DongDaegu="p.txt02";
    static String contents_Busan ="td";
    static String contents_Changwon="td.point";
    static String contents_Pohang="dl";
    static String contents_Gyeongju="dl";
    static String contents_Gimcheon="dl";
    static String contents_Gyeongsan="dl";
    static String contents_Ulsan="#map_city7 .mapview .cityinfo li div";
    static String contents_Jinju="td.point";
    static String contents_Yeongcheon="dl";
    static String contents_Gupo = "td";
    
    //Type1: ���뱸,�λ�,���
    //Type2: â��,����
    //Type3: ����,����,��õ,���,��õ
    
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
		if(cheaking==7) {                  //Type1
			to_string_temp = SearchTag_Type_Ulsan(to_string,cheaking);
		}//���
		else if(cheaking == 0) {
			to_string_temp = SearchTag_Type_Dongdaegu(to_string,cheaking);
		}//���뱸
		else if(cheaking == 1 || cheaking == 10 ||cheaking == -1) {
			to_string_temp = SearchTag_Type_BusanGupo(to_string,cheaking);
		}//�λ걸�� //�׸��ܵ� ����(-1�� �������̽�)
		else if(cheaking==2||cheaking==8 ) {                         
			to_string_temp = SearchTag_Type_Gyeongnam(to_string,cheaking);
		}//�泲
		else if((cheaking>=3&&cheaking<=6)||cheaking==9) {           //Type3
			to_string_temp = SearchTag_Type_Gyeongbuk(to_string,cheaking);
		}//���
		
		to_string = to_string_temp.replaceAll("[^0-9]", "");
		int today_Infected_person = Integer.parseInt(to_string);
		return today_Infected_person;
    }
   
    public static String SearchTag_Type_BusanGupo(String to_string, int cheaking) {
    	int temp = 0;
    	int var = 0;
    	int num  = 0;
    	if (cheaking == 1)//�λ�
    		num = 3;
    	else if (cheaking == 10)//����
    		num = 8;
    	else if (cheaking == -1)//����(�׸���)
    		num = 7;
    	String to_string_temp = null;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '��' &&to_string.charAt(i+1) == '��')
			{
				temp = 1;
			}
			if (temp == 1) {
				
				if(to_string.charAt(i) == '\n')
				{
					var++;
				}
				if(var == num)
				{
					to_string_temp += to_string.charAt(i);
				}
				else if (var == num+1)
					i = to_string.length();
			}
			
		}
		return to_string_temp;
	}

	public static String SearchTag_Type_Dongdaegu(String to_string, int cheaking) {
		int temp = 0;
		String to_string_temp = null;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '>')
			{
				temp = 1;
			}
			if (temp == 1) {
				to_string_temp += to_string.charAt(i);
				if(to_string.charAt(i) == '\n')
				{
					i = to_string.length();
				}
			}
			
		}
		return to_string_temp;
	}

	public static String SearchTag_Type_Ulsan(String to_string,int cheaking) {
    	String to_string_temp = null;
    	for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '��')
			{
				i = to_string.length();
			}
			else
			{
				to_string_temp += to_string.charAt(i);
			}
		}
		return to_string_temp;
    }
    
    public static String SearchTag_Type_Gyeongnam(String to_string,int cheaking) {
    	String to_string_temp = null;
    	int var = 0;
    	int var_chk=2; //â���̸� 2
    	if(cheaking==8)//�����̸� 3
    		var_chk=3;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '\n')
			{
				var++;
			}
			if(var ==var_chk)
			{
				to_string_temp += to_string.charAt(i);
			}
			if (var == var_chk+1)
			{
				i = to_string.length();
			}
		}
		return to_string_temp;
    }
    public static String SearchTag_Type_Gyeongbuk(String to_string,int cheaking) {
    	String to_string_temp = null;
    	int var = 0;
		char ch1 = 0,ch2 = 0;
		if(cheaking==3) {
			ch1='��';
			ch2='��';
		}
		else if(cheaking==4) {
			ch1='��';
			ch2='��';
		}
		else if(cheaking==5) {
			ch1='��';
			ch2='õ';
		}
		else if(cheaking==6) {
			ch1='��';
			ch2='��';
		}
		else if(cheaking==9) {
			ch1='��';
			ch2='õ';
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
	
	
	
	public static void Crwaling_2(int City[])
    {

		//�� ����ֱ�
		City[0] = crawling(URL_DongDaegu, contents_DongDaegu, 0);//���뱸
		City[1] = crawling(URL_Busan, contents_Busan, 1);//�λ�,����
		City[2] = crawling(URL_Changwon, contents_Changwon,2);//â��,����,����
		City[3] = crawling(URL_Pohang, contents_Pohang, 3);//����
		City[4] = crawling(URL_Gyeongju, contents_Gyeongju, 4);//�Ű���
		City[5] = crawling(URL_Gimcheon, contents_Gimcheon, 5);//��õ����(��õ)
		City[6] = crawling(URL_Gyeongsan, contents_Gyeongsan, 6);//���
		City[7] = crawling(URL_Ulsan, contents_Ulsan, 7);//���
		City[8] = crawling(URL_Jinju, contents_Jinju, 8);//����
		City[9] = crawling(URL_Yeongcheon, contents_Yeongcheon, 9);   //��õ
		City[10] = crawling(URL_Gupo, contents_Gupo, 10);   //��õ
    }
}
