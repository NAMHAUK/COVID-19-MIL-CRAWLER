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
	public static String path_1 = "C:\\Users\\��ź�PC\\OneDrive\\���� ȭ��\\ũ�ѷ�\\PR\\1Fe.txt";
	public static String path_2 = "C:\\Users\\��ź�PC\\OneDrive\\���� ȭ��\\ũ�ѷ�\\PR\\2Fe.txt";
	public static String path_3 = "C:\\Users\\��ź�PC\\OneDrive\\���� ȭ��\\ũ�ѷ�\\PR\\3Fe.txt";//�ؽ�Ʈ ���� ���
	public static String path_HO = "C:\\Users\\��ź�PC\\OneDrive\\���� ȭ��\\ũ�ѷ�\\PR\\hosong.txt";
	public static String path_HA = "C:\\Users\\��ź�PC\\OneDrive\\���� ȭ��\\ũ�ѷ�\\PR\\hangman.txt";
	public static String TMO1_index[] = {"����,���(��걸)","����","����","����","����(������)","������(������)","��õ","����,����(���ֽ�)","����"};//9��
	public static String TMO2_index[] = {"���뱸(�뱸 ����)","�λ�(�λ� ����)","â���߾�,����,����(â��)","����","�Ű���","��õ����(��õ)","���","���","����", "��õ", "����(�λ� �ϱ�)"};//11��
	public static String TMO3_index[]={"����(���� ����)","���","������(���� �߱�)","��ġ��(����)","�ΰ�(����)","���","�ͻ�","�强","���ּ���(���� ���걸)","����","õ�Ⱦƻ�(�ƻ�)","����","õ��","����","����","ȫ��","����(û��)"};//17��
	public static String HO_index[] = {"1�ߴ�(���� ����)","2�ߴ�(â��)","3�ߴ�(���� ��걸)"};//3��
	public static String HA_index[] = {"�λ�(�λ� ����)","����(â��)"};
	
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
			//ö���뿡 ���� path �� TMO index ����
			
			path = Choose_path(Which_Fe);
			TMO_index = Choose_index(Which_Fe);
			

			String TDay,LDay;
			int TMO_data[][]=new int[How_Many_TMO][3];//0=���� Ȯ���� ��, 1=���� Ȯ���� ��, 2=�ڷγ� �̹߻� �ϼ�
			int int_TDay,int_LDay;
	
			//���ó�¥,�����ֱ� ���ų�¥ ���
			SimpleDateFormat format = new SimpleDateFormat ("yyMMdd");//ex)201129
			Date time = new Date();
			TDay=format.format(time);
			
			
			//txt���Ͽ� �ִ� ���� LDay, TMO_data�� ����
			File file = new File(path);

			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            int linenum=0;
            LDay=bufReader.readLine();//LDay=���������ų�¥
            while((line = bufReader.readLine()) != null){
            	String[] arr=line.split(" ");
            	for(int i=0;i<3;i++) {
            		TMO_data[linenum][i]=Integer.parseInt(arr[i],10);
            	}
            	linenum++;
            }
            
			//����� TMO_data�� ����ؼ� ���� ����
			int_TDay=Integer.parseInt(TDay,10);
			int_LDay=Integer.parseInt(LDay,10);
			
			if(int_TDay==int_LDay) {//�̹� ���� ������ �� ����-> ���� Ȯ���� ���� �������ָ� ��
				for(int i=0;i<How_Many_TMO;i++) {
					TMO_data[i][0]=Now_data[i];//���� Ȯ���� �� ����
					
					//���� Ȯ���ڰ� ������ ���-> �ڷγ� �̹߻� �ϼ� 0�Ϸ� �ʱ�ȭ
					if(TMO_data[i][0]-TMO_data[i][1]>0) {
						TMO_data[i][2]=0;
					}
				}
			}
			else {//���� ������ �ѹ��� ���� ����-> ���� Ȯ���� ���� ���� Ȯ���ڼ��� ����,���� Ȯ���ڼ� ����
				for(int i=0;i<How_Many_TMO;i++) {
					TMO_data[i][1]=TMO_data[i][0];
					TMO_data[i][0]=Now_data[i];
					
					//���� Ȯ���ڰ� ������ ���-> �ڷγ� �̹߻� �ϼ� 0�Ϸ� �ʱ�ȭ
					if(TMO_data[i][0]-TMO_data[i][1]>0) {
						TMO_data[i][2]=0;
					}
					//���� Ȯ���ڼ��� �������� ���� ���-> �ڷγ� �̹߻� �ϼ� +1
					else {
						TMO_data[i][2]++;
					}
				}
			}
			
			
			//*******TMO_data�� ����ؼ� �޸��忡 �ٽ� ���پ� �ۼ��ؼ� ����� ����
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            String tmpString="";
            if(file.isFile() && file.canWrite()){
            	bufferedWriter.write(TDay);//ù��: ���ų�¥
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
