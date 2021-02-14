public class Crwal_HA {

	public static void Crwaling_HA(int[] City) {
		
		City[0] = Crwal_2.crawling(Crwal_2.URL_Busan, Crwal_2.contents_Busan,-1);  //부산 남구(항만단 부산)
		City[1] = Crwal_2.crawling(Crwal_2.URL_Changwon, Crwal_2.contents_Changwon,2); //창원(항만단 진해)
	}

}
