public class Crwal_HO {

	
	public static void Crwaling_HO(int City[])
    {
		//�� ����ֱ�
		City[0] = Crwal_3.crawling(Crwal_3.URL_Daejeon, Crwal_3.contents_Daejeon,0);  //1�ߴ�
		City[1] = Crwal_2.crawling(Crwal_2.URL_Changwon, Crwal_2.contents_Changwon,2); //2�ߴ�
		City[2] = Crwal_1.crawling(Crwal_1.URL_Yongsan, Crwal_1.contents_Yongsan,0);         //3�ߴ�
		
    }
}
