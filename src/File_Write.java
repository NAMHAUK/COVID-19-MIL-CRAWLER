import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File_Write {
	public static String path_1 = "C:\\Users\\통신병PC\\OneDrive\\바탕 화면\\크롤러\\PR\\1Fe.txt";
	public static String path_2 = "C:\\Users\\통신병PC\\OneDrive\\바탕 화면\\크롤러\\PR\\2Fe.txt";
	public static String path_3 = "C:\\Users\\통신병PC\\OneDrive\\바탕 화면\\크롤러\\PR\\3Fe.txt";//텍스트 파일 경로
	public static String path_HO = "C:\\Users\\통신병PC\\OneDrive\\바탕 화면\\크롤러\\PR\\hosong.txt";
	public static String path_HA = "C:\\Users\\통신병PC\\OneDrive\\바탕 화면\\크롤러\\PR\\hangman.txt";
	public static String TMO1_index[] = {"서울,용산(용산구)","광명","평택","수원","수서(강남구)","동서울(광진구)","춘천","원주,만종(원주시)","강릉"};//9개
	public static String TMO2_index[] = {"동대구(대구 동구)","부산(부산 동구)","창원중앙,진해,마산(창원)","포항","신경주","김천구미(김천)","경산","울산","진주", "영천", "구포(부산 북구)"};//11개
	public static String TMO3_index[]={"대전(대전 동구)","계룡","서대전(대전 중구)","조치원(세종)","부강(세종)","논산","익산","장성","광주송정(광주 광산구)","목포","천안아산(아산)","전주","천안","영동","증평","홍성","오송(청주)"};//17개
	public static String HO_index[] = {"1중대(대전 동구)","2중대(창원)","3중대(서울 용산구)"};//3개
	public static String HA_index[] = {"부산(부산 남구)","진해(창원)"};
	
	public static String Choose_path(String Which_Fe) {
		if(Which_Fe == "1") {
			return path_1;
		}
		else if (Which_Fe == "2") {
			return path_2;
		}
		else if (Which_Fe == "3") {
			return  path_3;
		}
		else if (Which_Fe == "HO") {
			return  path_HO;
		}
		else if (Which_Fe == "HA") {
			return path_HA;
		}
		else
			return null;
	}
	
	public static String[] Choose_index(String Which_Fe) {
		if(Which_Fe == "1") {
			return TMO1_index;
		}
		else if (Which_Fe == "2") {
			return TMO2_index;
		}
		else if (Which_Fe == "3") {
			return  TMO3_index;
		}
		else if (Which_Fe == "HO") {
			return  HO_index;
		}
		else if (Which_Fe == "HA") {
			return HA_index;
		}
		else
			return null;
	}
	
	public static void File_Update(String Which_Fe, int How_Many_TMO, int City[]) {
		try {
			int Now_data[]=new int[How_Many_TMO];
			for(int i=0;i<How_Many_TMO;i++) {
				Now_data[i]=City[i];
			}
			

			String path = null;
			String TMO_index[] = new String[How_Many_TMO];
			//철도대에 따라 path 및 TMO index 지정
			
			path = Choose_path(Which_Fe);
			TMO_index = Choose_index(Which_Fe);
			

			String TDay,LDay;
			int TMO_data[][]=new int[How_Many_TMO][3];//0=오늘 확진자 수, 1=어제 확진자 수, 2=코로나 미발생 일수
			int int_TDay,int_LDay;
	
			//오늘날짜,가장최근 갱신날짜 얻기
			SimpleDateFormat format = new SimpleDateFormat ("yyMMdd");//ex)201129
			Date time = new Date();
			TDay=format.format(time);
			
			
			//txt파일에 있는 정보 LDay, TMO_data로 저장
			File file = new File(path);

			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            int linenum=0;
            LDay=bufReader.readLine();//LDay=마지막갱신날짜
            while((line = bufReader.readLine()) != null){
            	String[] arr=line.split(" ");
            	for(int i=0;i<3;i++) {
            		TMO_data[linenum][i]=Integer.parseInt(arr[i],10);
            	}
            	linenum++;
            }
            
			//저장된 TMO_data를 사용해서 정보 갱신
			int_TDay=Integer.parseInt(TDay,10);
			int_LDay=Integer.parseInt(LDay,10);
			
			if(int_TDay==int_LDay) {//이미 오늘 갱신을 한 상태-> 오늘 확진자 수만 갱신해주면 됨
				for(int i=0;i<How_Many_TMO;i++) {
					TMO_data[i][0]=Now_data[i];//오늘 확진자 수 갱신
					
					//오늘 확진자가 증가한 경우-> 코로나 미발생 일수 0일로 초기화
					if(TMO_data[i][0]-TMO_data[i][1]>0) {
						TMO_data[i][2]=0;
					}
				}
			}
			else {//오늘 생신을 한번도 안한 상태-> 오늘 확진자 수를 어제 확진자수로 변경,오늘 확진자수 갱신
				for(int i=0;i<How_Many_TMO;i++) {
					TMO_data[i][1]=TMO_data[i][0];
					TMO_data[i][0]=Now_data[i];
					
					//오늘 확진자가 증가한 경우-> 코로나 미발생 일수 0일로 초기화
					if(TMO_data[i][0]-TMO_data[i][1]>0) {
						TMO_data[i][2]=0;
					}
					//오늘 확진자수가 증가하지 않은 경우-> 코로나 미발생 일수 +1
					else {
						TMO_data[i][2]++;
					}
				}
			}
			
			
			//*******TMO_data를 사용해서 메모장에 다시 한줄씩 작성해서 덮어씌워 저장
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            String tmpString="";
            if(file.isFile() && file.canWrite()){
            	bufferedWriter.write(TDay);//첫줄: 갱신날짜
            	bufferedWriter.newLine();
            	for(int i=0;i<How_Many_TMO;i++) {
            		tmpString=TMO_data[i][0]+" "+TMO_data[i][1]+" "+TMO_data[i][2];
            		bufferedWriter.write(tmpString);
            		bufferedWriter.newLine();
            	}
                bufferedWriter.close();
            }
            bufReader.close();
		Mail.Write_mail(Which_Fe, TMO_index, How_Many_TMO, TMO_data);
	        
		}catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
	}
	
}
