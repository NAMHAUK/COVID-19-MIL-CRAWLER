import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crwal_3 {
	   //3철도대 지역별 URL
    public static String URL_Daejeon = "https://www.donggu.go.kr/dg/kor/corona";
    public static String URL_Gyeryong ="https://www.gyeryong.go.kr/corona.html";
    public static String URL_Sejong="http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
    public static String URL_SeoDaejeon = "https://www.djjunggu.go.kr/corona.jsp";
    public static String URL_Nonsan="https://www.nonsan.go.kr/corona/index.html";
    public static String URL_Iksan="http://www.iksan.go.kr/board/list.iksan?boardId=BBS_0000227&menuCd=DOM_000002004016005000&contentsSid=4104&cpath=";
    public static String URL_Jangseong="https://www.jangseong.go.kr/home/apply/info/info_05";
    public static String URL_Gwangju="https://www.gwangjin.go.kr/index1.html";
    public static String URL_Mokpo="http://152.99.135.117/COVID.html?build=bf3b91b35b10946fea31496f1b9385e3";
    public static String URL_Asan="https://www.asan.go.kr/main/corona/";
    public static String URL_Yeongdong="https://www1.chungbuk.go.kr/covid-19/index.do";
    public static String URL_Jeonju="http://www.jeonju.go.kr/corona/";
    public static String URL_Cheonan="http://www.chungnam.go.kr/coronaStatus.do?tab=1#";
    public static String URL_Jeungpyeong="https://www1.chungbuk.go.kr/covid-19/index.do";
    public static String URL_Hongseong="http://www.chungnam.go.kr/coronaStatus.do?tab=1#";
    public static String URL_Cheongju="https://www1.chungbuk.go.kr/covid-19/index.do";
    
    //3철도대 지역별 
    public static String contents_Daejeon="em.first";
    public static String contents_Gyeryong ="div.item.top_t01 ul li b";
    public static String contents_SeoDaejeon = "b";
    public static String contents_Sejong="#map_city8 .mapview .cityinfo li div";
    public static String contents_Nonsan=".top_wrap ul.top_t.tt01 li p";
    public static String contents_Iksan=".iksan span strong";
    public static String contents_Jangseong="#covid_submit li";
    public static String contents_Gwangju="th";
    public static String contents_Mokpo=".total_counter.counter";
    public static String contents_Asan="tbody tr td";
    public static String contents_Yeongdong="div .mp_ab11";
    public static String contents_Jeonju="div .tnum dd";
    public static String contents_Cheonan="div.overflow_x table.new_tbl_board.mb20 tbody tr td ";
    public static String contents_Jeungpyeong="div .mp_ab6";
    public static String contents_Hongseong="div.overflow_x table.new_tbl_board.mb20 tbody tr td ";
    public static String contents_Cheongju="div .mp_ab8";
    
    //Type1: 대전,계룡,세종,부강(세종),논산,장성,광주,목포
    //Type2: 익산
    //Type3: 영동,증평,청주->충북 13 14 16
    //Type4: 아산,전주
    //Type5: 천안,홍성->살려두기->충남 12 15
    
    public static String SearchTag_Match(String to_string, int cheaking) {
    	//Type별로 tag찾기
    	String to_string_temp = null;
    			if(cheaking == 0) { //대전
    				to_string_temp = SearchTag_Type_Daejeon(to_string,cheaking);
    			}
    			else if(cheaking == 1) {
    				to_string_temp = SearchTag_Type_Gyeryong(to_string, cheaking);
    			}
    			else if (cheaking == 2) {
    				to_string_temp = SearchTag_Type_SeoDaejeon(to_string, cheaking);
    			}
    			else if (cheaking == 3) {
    				to_string_temp = SearchTag_Type_Sejong(to_string, cheaking);
    			}
    			else if (cheaking == 4) {
    				to_string_temp = SearchTag_Type_Sejong(to_string, cheaking);
    			}
    			else if (cheaking == 5) {
    				to_string_temp = SearchTag_Type_Nonsan(to_string, cheaking);
    			}
    			else if(cheaking==6) {                                       //익산
    				to_string_temp=to_string;
    			}
    			else if (cheaking == 7) {									// 장성
    				to_string_temp = SearchTag_Type_Jangseong(to_string, cheaking);
    			}
    			else if (cheaking == 8) {
    				to_string_temp = SearchTag_Type_Gwangju(to_string, cheaking);
    			}
    			else if (cheaking == 9) {
    				to_string_temp = SearchTag_Type_Mokpo(to_string, cheaking);
    			}
    			else if (cheaking == 10) {
    				to_string_temp = SearchTag_Type_Asan(to_string, cheaking);
    			}
    			else if (cheaking == 11) {
    				to_string_temp = SearchTag_Type_Jeonju(to_string, cheaking);
    			}
    			else if (cheaking == 12 || cheaking == 15) { //충남
    				to_string_temp = SearchTag_Type_chungnam(to_string,cheaking);
    			}
    			else if(cheaking==13||cheaking==14||cheaking==16) {          //충북
    				to_string_temp = SearchTag_Type_chungbuk(to_string,cheaking);
    			}
    			return to_string_temp;
    }
    
    public static int crawling(String URL,String contents_route,int cheaking) {
    	Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.은 class 스페이스바는 태그 #는 id
		Elements contents = doc.select(contents_route);
		String to_string = contents.toString();
		String to_string_temp=null;
		
		to_string_temp = SearchTag_Match(to_string, cheaking );
		
		to_string = to_string_temp.replaceAll("[^0-9]", "");
		
		int today_Infected_person = Integer.parseInt(to_string);
		return today_Infected_person;
    }
    
    public static String SearchTag_Type_Jeonju(String to_string, int cheaking) {
    	String to_string_temp = null;
		
		int temp = 0;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '명')
			{
				temp++;
				if (temp == 3)
				i = to_string.length();
			}
			
			else
			{
				if(temp == 2)
				to_string_temp += to_string.charAt(i);
			}
		}
    	return to_string_temp;
	}

    public static String SearchTag_Type_Asan(String to_string, int cheaking) {
		String to_string_temp = null;
		int temp = 0;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '명')
			{
				temp++;
				if (temp == 2)
				i = to_string.length();
			}
			else
			{
				if(temp == 1)
				to_string_temp += to_string.charAt(i);
			}
		}
		return to_string_temp;
	}

	public static String SearchTag_Type_Mokpo(String to_string, int cheaking) {
		String to_string_temp = null;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '명')
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

	public static String SearchTag_Type_Gwangju(String to_string, int cheaking) {
		String to_string_temp = null;
		int temp = 0;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '확' && to_string.charAt(i+1) == '진' && to_string.charAt(i+2) == '자')
			{
				temp++;
			}
			if (temp == 1) {
				to_string_temp += to_string.charAt(i);
			}
			if(to_string.charAt(i) == '\n')
			{
				i = to_string.length();
			}
		}
		return to_string_temp;
	}

	public static String SearchTag_Type_Jangseong(String to_string, int cheaking) {
		String to_string_temp = null;
		int var = 0;
		
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '확' && to_string.charAt(i+1) == '진')
				var =1;
			if(var ==1)
			{
				if(to_string.charAt(i) == '검')
				{
					i = to_string.length();
				}
				else
				{
					to_string_temp += to_string.charAt(i);
				}
			}
			
		}
		return to_string_temp;
	}

	public static String SearchTag_Type_Nonsan(String to_string, int cheaking) {
		String to_string_temp = null;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '명')
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

	public static String SearchTag_Type_Sejong(String to_string, int cheaking) {
		String to_string_temp = null;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '명')
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

	public static String SearchTag_Type_SeoDaejeon(String to_string, int cheaking) {
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

	public static String SearchTag_Type_Gyeryong(String to_string, int cheaking) {
		String to_string_temp = null;
		
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '\n')
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

	public static String SearchTag_Type_Daejeon(String to_string, int cheacking) {
	   	String to_string_temp = null;
    	int temp = 0;
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
   
	public static String SearchTag_Type_chungbuk(String to_string,int cheaking) {
    	//영동,증평,청주
    	String to_string_temp = null;
    	int temp = 0;
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '>')
			{
				temp++;
			}
			if(temp == 3) {
				to_string_temp += to_string.charAt(i);
				if(to_string.charAt(i) == '(')
					temp = 4;
			}
			else if(temp == 4)
				i = to_string.length();
		}
		return to_string_temp;
    }

    public static String SearchTag_Type_chungnam(String to_string,int cheaking) {
    	//천안,홍성
    	String to_string_temp = null;
		int temp = 0;
		int var = 0;
		int temp_chk=3;   //기본값 천안
		if(cheaking==15) {//홍성이면 변경
			temp_chk=15;
		}
		for(int i = 0; i<to_string.length(); i++)
		{
			if(to_string.charAt(i) == '합' && to_string.charAt(i+1) == '계')
			{
				var++;
			}
			if(var == 1)
			{
				if(to_string.charAt(i) == '\n')
				{
					temp++;
					if (temp ==temp_chk)
					i = to_string.length();
				}
				else
				{
					if(temp == temp_chk-1)
					{
						if(to_string.charAt(i) == '(')
						{
							i = to_string.length();
						}
						else
							to_string_temp += to_string.charAt(i);
					}
				}
			}
		}
		return to_string_temp;
    }
    
    public static void Crwaling_3(int City[])
    {
		
		//크롤링해서 오늘 확진자 수 저장
		City[0] = crawling(URL_Daejeon,contents_Daejeon,0);//대전
		City[1] = crawling(URL_Gyeryong,contents_Gyeryong,1);//계룡
		City[2] = crawling(URL_SeoDaejeon,contents_SeoDaejeon,2);//서대전
		City[3] = crawling(URL_Sejong,contents_Sejong,3);//조치원
		City[4] = crawling(URL_Sejong,contents_Sejong,4);//부강
		City[5] = crawling(URL_Nonsan,contents_Nonsan,5);//논산
		City[6] = crawling(URL_Iksan,contents_Iksan,6);//익산
		City[7] = crawling(URL_Jangseong,contents_Jangseong,7);//장성
		City[8] = crawling(URL_Gwangju,contents_Gwangju,8);//광주
		City[9] = crawling(URL_Mokpo,contents_Mokpo,9);//목포
		City[10] = crawling(URL_Asan,contents_Asan,10);//아산
		City[11] = crawling(URL_Jeonju,contents_Jeonju,11);//전주
		City[12] = crawling(URL_Cheonan,contents_Cheonan,12);//천안
		City[13] = crawling(URL_Yeongdong,contents_Yeongdong,13);//영동
		City[14] = crawling(URL_Jeungpyeong,contents_Jeungpyeong,14);//증평
		City[15] = crawling(URL_Hongseong,contents_Hongseong,15);//홍성
		City[16] = crawling(URL_Cheongju,contents_Cheongju,16);//청주
    }
    
}
