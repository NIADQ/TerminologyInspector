package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import application.util.ChangeBizNo;
import application.util.ChangeDate;
import application.util.ChangeNumeric;
import application.util.ChangeTelNo;
import application.util.ChangeYn;
import application.util.ChangeZipNo;

public class MainController {
	
	 static String changeFileNm;
	 static String errFileNm;
	 
	public List<String[]> readerCSV(String filePath) { 

		List<String[]> content = new ArrayList<String[]>();

		CSVReader reader = null;

		try {
			reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath),"EUC-KR"));
			//reader = new CSVReader(new FileReader(filePath));
			String[] s;
			
			
			int count =1;
			while((s = reader.readNext()) != null) {
				content.add(s);
				count++;
				if(count>3001)break;
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return content;

	}
	
	
	
	
	
	
	
	
	public static List<String[]> readerCSV2(String filePath) { 

		List<String[]> content = new ArrayList<String[]>();

		CSVReader reader = null;

		try {

			reader = new CSVReader(new FileReader(filePath));
			String[] s;
			
			
			content = reader.readAll(); 
			
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return content;

	}
	

	

	static String FileInspection(String filePath, String fileNm, String str2, int startHeaderCnt, List<Map<String,String>> list)
			throws Exception {
		
		String errrrr ="";
		String itrerr = "";
		
		int result = 0;
		try {
			
			
 
			
			String[] oriArrays = null;
			String[] cngArrays = null;
			String[] msgerrArrays = null;
			
			String[] errArrays = { "�����","�÷�����", "�÷���",  "������Ÿ��",  "����" , "����" , "���泻��" , "��������" };
			String[] errArraysh = { "�����","�÷�����", "�÷���",  "������Ÿ��",  "����" , "����" , "���泻��" , "��������" };
			ArrayList<Map<String,String>> rsltList = new ArrayList<Map<String,String>>();
			
			int headerCnt = startHeaderCnt;
			int itr = 0;
			
			
			String filepath2 = filePath.replace(str2, "");
			filepath2 = filepath2.replace("\\", "/");
			
			File f = new File(filepath2+"temp");
			
			if(f.mkdir()) {
			}else {
			}
			filepath2 = filepath2 + "temp";
			
			String path = filepath2;
			
 
			changeFileNm = path + "/" +fileNm + "_temp.csv"; 
			errFileNm = path + "/" +fileNm + "_msg.csv";  


			
			String orgins ="";
			String changes ="";
			String errmsgs ="";
			String cngmsgs = "";
			String seperate = "";
			

			List<String []> csvdata = new ArrayList<String[]>();
			List<String []> errdata = new ArrayList<String[]>();
			
			errdata.add(errArraysh);
			
			List<String[]> content = readerCSV2(filePath);
			int insertCount =0;
            int hisSize = content.size();
            int hisCount =0;
            
           int len1 = content.get(0).length;
           
            
            itrerr = hisCount  +"  || " ;
			for (String[] data : content) {
				oriArrays = new String[data.length]; 
				msgerrArrays = new String[data.length]; 
				cngArrays = new String[data.length]; 
			    
				
				
				
				if (itr <= headerCnt) {
					
					if( headerCnt == itr) {
						csvdata.add(data);
					}
					
					
					itr++;
					hisCount ++;
					continue;
 
				}
				else {
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
					Date currentTime = new Date();
					String inspectime = formatter.format(currentTime);
					
					
					for(int i=0; i<data.length; i++) {
						errArrays = new String [8];
						 itrerr  =  hisCount  +"  || " + i;
						 
						
						 
						if ( i== 0 ) {
							seperate = "";
							
							 orgins ="";
							 changes ="";
							 errmsgs ="";
							 cngmsgs = "";
						}
						else {
							seperate = ", ";
						}
						String node = (data[i].trim()).isEmpty() ? "" : data[i].trim(); 
						Map<String, String> map = null;
						
						if( node.length() > 0) {
							
							// ��ȣ > 6 : ����ڹ�ȣ
							if (list.get(i).get(i+"datatype").equals("6")) {
								errrrr = "����ڹ�ȣ";
								map = new ChangeBizNo(node).getResultMap();
							} 
							// ��ȣ > 5 : �����ȣ
							else if (list.get(i).get(i+"datatype").equals("5")) {
								errrrr = "�����ȣ";
								ChangeZipNo czn = new ChangeZipNo(node);
								map = czn.getResultMap();
							} 
							// ��ȣ > 4 : ��ȭ��ȣ
							
							else if (list.get(i).get(i+"datatype").equals("4")) {
								errrrr = "��ȭ ��ȣ";
								ChangeTelNo ctn = new ChangeTelNo(node);
								map = ctn.getResultMap();
							} 
							// ��ȣ > 1 : ����
							else if (list.get(i).get(i+"datatype").equals("1")) {
								errrrr = "���� " + node  + "  " + i ;
								 
								ChangeNumeric cnr = new ChangeNumeric(node);
								map = cnr.getResultMap();
							} 
							// ��ȣ > 2 : ����
							else if (list.get(i).get(i+"datatype").equals("2")) {
								errrrr = "���� ";
								ChangeYn cyn = new ChangeYn(node, "Y|N");
								map = cyn.getResultMap();
							} 
							// ��ȣ > 3 : ��¥
							else if (list.get(i).get(i+"datatype").length()>1) {
								errrrr ="��¥ ";
								String dateType = list.get(i).get(i+"datatype").substring(0,1);
								String dtlOpt = "";
								if(list.get(i).get(i+"datatype").length()==3) {
									dtlOpt = list.get(i).get(i+"datatype").substring(2, 3);
								} else if(list.get(i).get(i+"datatype").length() ==4 ) {
									dtlOpt = list.get(i).get(i+"datatype").substring(2, 4);
								}
								
								ChangeDate cdt = new ChangeDate(node, dtlOpt);
								map = cdt.getResultMap();
							} 

							// ���ڿ�
							else if (list.get(i).get(i+"datatype").equals("0")) {
								errrrr = "����" + itr + "   i ="+i;
							
								map = new HashMap<String, String>();
								map.put("org", node);
								map.put("cng", node);
								map.put("errMsg", "");
								map.put("cngMsg", "");	 					
							}
							// ��Ÿ
							else {
								map = new HashMap<String, String>();
								errrrr = "��";
								map.put("org", node);
								map.put("cng", node);
								map.put("errMsg", "");
								map.put("cngMsg", "");

							}
							
						
						}
						else {
							map = new HashMap<String, String>();
							map.put("org", "");
							map.put("cng", "");
							map.put("errMsg", "");
							map.put("cngMsg", "");
						}
						String colId = i +1 +"";
						String colNm = list.get(i).get(i+"header");
						oriArrays[i] = map.get("org");
						cngArrays[i] = map.get("cng");
						msgerrArrays[i] = map.get("errMsg");
						
						orgins +=  seperate +  data[i] ; 
						changes += seperate + cngArrays[i];
						cngmsgs += seperate + map.get("cngMsg");
						errmsgs += seperate + map.get("errMsg");
						
						if (  map.get("errMsg").length() > 0  || map.get("cngMsg").length() > 0) {
							
					 		
							
							String dataTp = list.get(i).get(i+"datatype");
							String dp ="";
							String dtlOpt = "";
							if(dataTp.length()==3) {
								dtlOpt = dataTp.substring(2, 3);
							} else if(dataTp.length() ==4 ) {
								dtlOpt = dataTp.substring(2, 4);
							}
							
							
							
							
							if ( dataTp.equals("1")){
								dp = "����" ;
							}
							else if ( dataTp.equals("2")){
								dp = "����" ;
							}
							else if ( dataTp.length()>1){
								dp = "��¥" ;
								String dtlStr = dtlOpt;
								
								if ( dtlStr.equals("1")) {
									dp = "��¥ : YYYY-MM-DD HH24:MI:SS";
								}
								else if ( dtlStr.equals("1")) {
									dp = "��¥ : YYYY-MM-DD HH24:MI:SS";
								}
								else if ( dtlStr.equals("1")) {
									dp = "��¥ : YYYY-MM-DD HH24:MI:SS";
								}else if ( dtlStr.equals("2")) {
									dp = "��¥ : YYYY-MM-DD HH24:MI";
								}else if ( dtlStr.equals("3")) {
									dp = "��¥ : YYYY-MM-DD HH24";
								}else if ( dtlStr.equals("4")) {
									dp = "��¥ : MM-DD HH24:MI";
								}else if ( dtlStr.equals("5")) {
									dp = "��¥ : HH24:MI:SS";
								}else if ( dtlStr.equals("6")) {
									dp = "��¥ : YYYYY-MM-DD";
								}else if ( dtlStr.equals("7")) {
									dp = "��¥ : HH24:MI";
								}else if ( dtlStr.equals("8")) {
									dp = "��¥ : YYYY-MM";
								}else if ( dtlStr.equals("9")) {
									dp = "��¥ : MM-DD";
								}else if ( dtlStr.equals("10")) {
									dp = "��¥ : HH24";
								}else if ( dtlStr.equals("11")) {
									dp = "��¥ : YYYY";
								}else if ( dtlStr.equals("12")) {
									dp = "��¥ : DD";
								}else if ( dtlStr.equals("13")) {
									dp = "��¥ : MI";
								}else if ( dtlStr.equals("14")) {
									dp = "��¥ : MM";
								}else if ( dtlStr.equals("15")) {
									dp = "��¥ : SS";
								}
								
							}
							else if ( dataTp.equals("4")){
								dp = "��ȭ��ȣ" ;
							}
							else if ( dataTp.equals("5")){
								dp = "�����ȣ" ;
							}
							else if ( dataTp.equals("6")){
								dp = "����ڹ�ȣ" ;
							}
							
							
							errArrays[0] = itr+""; 
							errArrays[1] = colId;
							errArrays[2] = colNm;
							errArrays[3] = dp;
							errArrays[4] = map.get("org");
							errArrays[5] = map.get("cng");
							errArrays[6] = map.get("cngMsg");
							errArrays[7] = map.get("errMsg");
							errdata.add(errArrays);
							
						}
						
						
					} 
					
					csvdata.add(oriArrays);
					csvdata.add(cngArrays);
					csvdata.add(msgerrArrays);
		 
					
					
					
					
					
					
					
					itr ++;
				}
				

				
				
			}	


			result = 0;
			writeChangeRow(changeFileNm, csvdata);
			writeChangeRow(errFileNm, errdata);
		}catch(Exception e) {
			System.out.println("errrrrrrrr========"+itrerr );
		    
			System.out.println("errrrrrrrr========"+e);
			result = 1;
			
		    
		}finally {
			return changeFileNm;
		}
	}
	

	
	public static void writeChangeRow(String changeFileNm, List<String[]> data) {

		if (data.size() > 0 ) {
			FileWriter filewriter = null;
			CSVWriter writer = null;
			File filefc = new File(changeFileNm);
			try {
				filefc.createNewFile();
				filewriter = new FileWriter(filefc);
				writer = new CSVWriter(filewriter);
				
				String [] dataRow = new String[data.get(0).length];
				
				for (int i=0; i<data.size();i++) {
					writer.writeNext(data.get(i));
				}
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != writer)
						writer.close();
					if (null != filewriter)
						filewriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} 
		
	}
	
	
}
