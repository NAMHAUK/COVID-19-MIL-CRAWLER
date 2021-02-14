public class Crwal_HO {

	
	public static void Crwaling_HO(int City[])
    {
		//값 집어넣기
		City[0] = Crwal_3.crawling(Crwal_3.URL_Daejeon, Crwal_3.contents_Daejeon,0);  //1중대
		City[1] = Crwal_2.crawling(Crwal_2.URL_Changwon, Crwal_2.contents_Changwon,2); //2중대
		City[2] = Crwal_1.crawling(Crwal_1.URL_Yongsan, Crwal_1.contents_Yongsan,0);         //3중대
		
    }
}
