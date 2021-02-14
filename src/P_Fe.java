public class P_Fe{
	
	public static void routine(String Which_Fe, int How_Many_TMO) {
		int[] City = new int[How_Many_TMO];//크롤링한 확진자 수를 저장할 배열
		for(int i = 0; i<How_Many_TMO; i++) {
			City[i] = 0;
		}//초기화
		
		if(Which_Fe == "1") {
			Crwal_1.Crwaling_1(City);
			File_Write.File_Update(Which_Fe, How_Many_TMO, City);
			}
		else if(Which_Fe == "2") {
			Crwal_2.Crwaling_2(City);
			File_Write.File_Update(Which_Fe, How_Many_TMO, City);
		}
		else if(Which_Fe == "3") {
			Crwal_3.Crwaling_3(City);
			File_Write.File_Update(Which_Fe, How_Many_TMO, City);
		}
		else if(Which_Fe == "HO") {
			Crwal_HO.Crwaling_HO(City);
			File_Write.File_Update(Which_Fe, How_Many_TMO, City);
		}
		else if(Which_Fe == "HA") {
			Crwal_HA.Crwaling_HA(City);
			File_Write.File_Update(Which_Fe, How_Many_TMO, City);
		}
		
	}
	
	public static void main(String[] args){
		routine("1", 9);
		routine("2", 11);
		routine("3", 17);
		routine("HO", 3);
		routine("HA", 2);
		
	}
}