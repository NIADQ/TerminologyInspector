package application.util;
 
 import java.util.HashMap;
import java.util.Map;

public class ChangeDate {
	String bulkDate;
	String orgDate;
	 
	Map resultMap ;
	
	int monthLastDay[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    int hourNum[] = {12, 24, 60, 60};
    
    
	
	public ChangeDate(String _bulkDate, String dateType) {
		 

		 this.orgDate = _bulkDate;
		 this.bulkDate = _bulkDate;
		 
		 resultMap = FindDate(this.bulkDate, dateType);
 
	}
	
	
	public Map<String, String>  getResultMap() {
		return resultMap;
	}


	public void setResultMap(Map<String, String>  resultMap) {
		this.resultMap = resultMap;
	}

	
	public Map<String, String> FindDate(String StrDate, String dateType) {
		    Map<String, String> map = null;
			
			if( StrDate.length() == 1) {
				map = checkLength_01(StrDate, dateType);
			}
			else if( StrDate.length() == 2) {
				map = checkLength_02(StrDate, dateType);
			}
			else if( StrDate.length() == 3) {
				map = checkLength_03(StrDate, dateType);
			}
			else if( StrDate.length() == 4) {
				map = checkLength_04(StrDate, dateType);
			}
			else if( StrDate.length() == 5) {
				map = checkLength_05(StrDate, dateType);
			}
			else if( StrDate.length() == 6) {
				map = checkLength_06(StrDate, dateType);
			}
			else if( StrDate.length() == 7) {
				map = checkLength_07(StrDate, dateType);
			}
			else if( StrDate.length() == 8) {
				map = checkLength_08(StrDate, dateType);
			}
			else if( StrDate.length() == 9) {
				map = checkLength_09(StrDate, dateType);
			}
			else if( StrDate.length() == 10) {
				map = checkLength_10(StrDate, dateType);
			}
			else if( StrDate.length() == 11) {
				map = checkLength_11(StrDate, dateType);
			}
			else if( StrDate.length() >  11) {
				map = checkLength_12_over(StrDate, dateType);
			} 	
		 
		
		return map;
	}

	//윤년 판단. 
	public int is_leap_year(int yearNum) {
		int result = 0; 
		if( yearNum % 4 ==0) {
			if ( yearNum % 100 == 0 ) {
				if( yearNum % 400 == 0 ) {
					result = 1;
				}
				result = 0;
			}
			return 1;
			
		}
		else {
			result = 0;
		}
		return result;
	}
	
	//
	
	
	
	
	
	public Map<String, String>  checkLength_01 (String dt , String type) {
		String bulkDt;
		String orgDt;
		
		String cngMsg;
 
		String errMsg;
		 
		// 일반적인 14개 사이즈 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
	 
		
	   if (dt.length()==1) {
	    	//별
	    	if(dt.equals("*")) {
	    		bulkDt = dt.replaceAll("\\*", "");
	    		cngMsg = "별표_제거::"+orgDt+"::"+bulkDt;
	    	}
	    	
	    	//하이픈
	    	if(dt.equals("-")) {
	    		bulkDt = dt.replaceAll("\\-", "");
	    		System.out.println("하이픈");
	    		cngMsg = "하이픈_제거::"+orgDt+"::"+bulkDt;
	    	}
	    	
	    	
	    }
		
	   else if ( type.equals("6")) {   //YYYY-MM-DD
			errMsg ="YYYY-MM-DD로_변경불가";
			bulkDt = orgDt;
				
		}
		else if ( type.equals("9")) {  //MM-DD
			errMsg ="MM-DD로_변경불가";
			bulkDt = orgDt;
			
		}

		else if ( type.equals("10")) {  //HH24
			bulkDt = "0" + orgDt;
			cngMsg ="HH24로_변경::" + orgDt + "::" + bulkDt;
		}

		else if ( type.equals("11")) {  //YYYY
			bulkDt = orgDt;
			errMsg ="YYYY로_변경불가";
		}
		else if ( type.equals("12")) { //DD
			
			bulkDt = "0" + orgDt;
			cngMsg ="DD로 변경::" + orgDt + "::" + bulkDt;
			
		}
		else if ( type.equals("13")) {//MI
			bulkDt = "0" + orgDt;
			cngMsg ="MI로 변경::" + orgDt + "::" + bulkDt;
			
		}
		else if ( type.equals("14")) {//MM
			bulkDt = "0" + orgDt;
			cngMsg ="MM로 변경::" + orgDt + "::" + bulkDt;
			
		}
		else if ( type.equals("15")) {//SS
			bulkDt = "0" + orgDt;
			cngMsg ="SS로 변경변경::" + orgDt + "::" + bulkDt;
			
		}
		else   {
			errMsg ="날짜판단불가";
			
		}
			
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	

	public Map<String, String>  checkLength_02 (String dt , String type) {
		String bulkDt;
		String orgDt;
		
		String cngMsg;
 
		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
	 

 
	   
	   if (dt.matches("^([1-9])일$")) {
		   
			
			bulkDt =  dt.replaceAll("^([1-9])일$",  0+"$1");

			cngMsg = "DD_변경" +"::" + orgDt + "::" + bulkDt;
	   }
	   else if (dt.matches("^([1-9])월$")) {
		   
			bulkDt =   dt.replaceAll("^([1-9])월$",  0+"$1");
			cngMsg = "MM_변경" +"::" + orgDt + "::" + bulkDt;
			
	   }
	   else if(dt.matches("^([0-9][0-9])$")) {
		    
		   if ( type.equals("6") || type.equals("8") || type.equals("9")) {  //YYYY-MM-DD  || YYYY-MM || MM-DD

				errMsg ="변경_불가";
				bulkDt = orgDt;
	       }
		   else if ( type.equals("10")) {  //HH24
			   if (dt.matches("^(0[0-9]|1[0-9]|2[0-3])$")) {
				   bulkDt = dt;
				   cngMsg = "";
			   }
			   else {
				   errMsg ="HH24아님";
			   }
			    bulkDt = dt; 
			    cngMsg = "" ;
		   }
		   else if ( type.equals("11")) {  //YYYY
			   	
			    int dty = Integer.parseInt(dt);
			    if (dty > 25)  bulkDt = "19" + dt;
			    else bulkDt = "20" + dt;
			      
			    cngMsg = "YYYY_변경"  +"::" + orgDt + "::" + bulkDt;
		   }
		   else if ( type.equals("12") ) { //DD
			   if (dt.matches("^(0[1-9]|[12][0-9]|3[01])$")) {
				   bulkDt = dt;
				   cngMsg = "";
			   }
			   else {
				   errMsg ="DD일자아님";
			   }
			}
		   else if ( type.equals("13") ) { //MI
			   if (dt.matches("^(0[1-9]|[1-5][0-9])$")) {
				   bulkDt = dt;
				   cngMsg = "";
			   }
			   else {
				   errMsg ="MI아님";
			   }
			}

		   else if ( type.equals("14") ) { //MM
			   if (dt.matches("^(0[1-9]|1[0-2])$")) {
				   bulkDt = dt;
				   cngMsg = "";
			   }
			   else {
				   errMsg ="MM아님";
			   }
			}

		   else if ( type.equals("15") ) { //SS
			   if (dt.matches("^(0[1-9]|[1-5][0-9])$")) {
				   bulkDt = dt;
				   cngMsg = "";
			   }
			   else {
				   errMsg ="SS아님";
			   }
			}
	       else   {
	    		errMsg ="날짜판단불가";
	    		
		    }
	   }
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	public Map<String, String>  checkLength_03 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";

	   
	   if (dt.matches("^([1-9][0-9][0-9])$")) {
		   if ( type.equals("6")){
			   
			   bulkDt =   dt.replaceAll("^([1-9][0-9][0-9])$",  "0" + "$1");
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	       } 
		   else if ( type.equals("7")){
			   bulkDt =   dt.replaceAll("^([1-9])([0-9][0-9])$",  "0" + "$1:$2");
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
	       } 		   
		   else if ( type.equals("11")){
			   bulkDt =   dt.replaceAll("^([1-9][0-9][0-9])$",  "0" + "$1");
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	       } 
		   else if ( type.equals("13")){
			   errMsg = "MI_변경_불가" ;
			   bulkDt =   orgDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가" ;
			   bulkDt =   orgDt;
		   }
	   }
	   else if (dt.matches("^('|‘|’)*(\\d{2})(\\.|\\|)*$")) {
		   
			
			bulkDt =   dt.replaceAll("('|‘|’)*(\\d{2})(\\.|\\|)*",  "$2");
			
			   int dty = Integer.parseInt(bulkDt);
			    if (dty > 25)  bulkDt = "19" + bulkDt;
			    else bulkDt = "20" + bulkDt;
			    cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			
	   }
	   else if (dt.matches("^([1-9])\\.([1-9])$")) {
		     
		   if ( type.equals("6") || type.equals("9") ){
			  
			   bulkDt =   dt.replaceAll("^([1-9])\\.([1-9])$",  "0" + "$1" + "-" + "0" + "$2");
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }

	   else if (dt.matches("^([0-1][0-9]|[2][0-3])시$")) {
		     
		   if ( type.equals("7")   ){
			   
			   bulkDt =  dt.replaceAll("^([0-1][0-9]|[2][0-3])시$",   "$1" + ":00");
			   cngMsg = "HH24MI_변경"  +"::" + orgDt + "::" + bulkDt;
	       }   
		   else if ( type.equals("10")   ){
			   
			   bulkDt =  dt.replaceAll("^([0-1][0-9]|[2][0-3])시$",   "$1" );
			   cngMsg = "HH24_변경"  +"::" + orgDt + "::" + bulkDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
		
	   }
	   
	   else if (dt.matches("^([0][1-9]|[1][0-2])월$")) {
		       
			   bulkDt =  dt.replaceAll("^([0][1-9]|[1][0-2])월$",   "$1" );
			   cngMsg = "MM_변경"  +"::" + orgDt + "::" + bulkDt;
	   }
	  
	   else if (dt.matches("^([0][1-9]|[12][0-9]|[3][01])일$")) {
	           
			   bulkDt =  dt.replaceAll("^([0][1-9]|[12][0-9]|[3][01])일$",   "$1" );
			   cngMsg = "DD_변경" +"::" + orgDt + "::" + bulkDt;
	   }
	   
	   else if (dt.matches("^([1-9])\\-([1-9])$")) {
           
		   bulkDt =  dt.replaceAll("^([1-9])\\-([1-9])$",  "0$1:0$2"  );
		   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
   }
	   	   
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	    
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	
	public Map<String, String>  checkLength_04 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";

 
	   // 98년
	   if (dt.matches("^([0-9][0-9])년도$")) {
		   	   
			   bulkDt =   dt.replaceAll("^([0-9][0-9])년도$",    "$1");
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	   } 
	   // '05년
	   else if(dt.matches("^(‘|’|'|`)([0-9][0-9])년$")) {
	   	   
		   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9])년$",    "$2");
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   // 456년 
	   else if(dt.matches("^([1-9][0-9][0-9])년$")) {
	   	   
		   bulkDt =   dt.replaceAll("^([1-9][0-9][0-9])년$",    "0$1");
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	  // 2023
	   else if (dt.matches("^([0-9][0-9][0-9][0-9])$")) {
		   if ( type.equals("7")){
			   
			   if(dt.matches("^([01][0-9]|[2][0-3])([0-5][0-9])$")) {
			   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3])([0-5][0-9])$",   "$1:$2");
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   } else {
				 errMsg = "HH24MI아님";  
			   }
	       } 		   
		   else if ( type.equals("11")   ){
			
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])$",   "$1");
	
			   if( bulkDt.equals(orgDt)) {
				   cngMsg ="";
			   }
			   else {
				   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   }
	       } else if ( type.equals("9")){
	    	   if ( dt.matches("^([0][1-9]|[1][0-2])([0-2][0-9]|3[0-1])$")){
	    		   bulkDt =   dt.replaceAll("^([0][1-9]|[1][0-2])([0-2][0-9]|3[0-1])$",   "$1-$2");
	    		   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
	    	   }
	    	   else {
	    		   errMsg = "날짜타입_변경_불가" ;
				   bulkDt =   orgDt;
	    	   }
			   
	       } else if ( type.equals("8")){
	    	   if ( dt.matches("^([0-9][0-9])([0][1-9]|1[0-2])$")){
	    		   bulkDt =   dt.replaceAll("^([0-9][0-9])([0][1-9]|1[0-2])$",   "$1-$2");
	    		   
	    		   String[] tmp = bulkDt.split("-");
	    		   
	    		   int dty = Integer.parseInt(tmp[0]);
	    		   if (dty > 25)  bulkDt = "19" + tmp[0];
	    		   else bulkDt = "20" + tmp[0] + "-" + tmp[1]; 
	    		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	    	   }
	    	   else {
	    		   errMsg = "날짜타입_변경_불가" ;
				   bulkDt =   orgDt;
	    	   }
			   
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가" ;
			   bulkDt =   orgDt;
		   }
	   }
	   
	   
	   // '23.
	   else if (dt.matches("^(‘|’|'|`)(\\d{2})\\.$")) {
		   
			
			bulkDt =   dt.replaceAll("^(‘|’|'|`)(\\d{2})\\.$",  "$2");
			
			   int dty = Integer.parseInt(bulkDt);
			    if (dty > 25)  bulkDt = "19" + bulkDt;
			    else bulkDt = "20" + bulkDt;
			    cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			
	   }
	   // 1.12
	   else if (dt.matches("^([1-9])\\.([0-9][0-9])$")) {
		     
		   if ( type.equals("6") || type.equals("8") ){
			  
			   bulkDt =   dt.replaceAll("^([1-9])\\.([0-9][0-9])$",  "0" + "$1" + "-" +  "$2");
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }
	   // 1.12
	   else if (dt.matches("^([0-9])[\\.|\\:]([0-9][0-9])$")) {
		     
		   if ( type.equals("7")  ){
			  
			   bulkDt =   dt.replaceAll("^([0-9])[\\.|\\:]([0-9][0-9])$",   "0$1:$2" );
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }

	   // 21.2
	   else if (dt.matches("^([1-9][0-9])\\.([0-9])$")) {
		     
		   if ( type.equals("6") || type.equals("8") ){  
			   
			   bulkDt =  dt.replaceAll("^([1-9][0-9])\\.([0-9])$",   "$1" );
			   String tmpM = dt.replaceAll("^([1-9][0-9])\\.([0-9])$",   "0$2");
			    
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmpM;
			   cngMsg = "YYYYMM_변경"  +"::" + orgDt + "::" + bulkDt;
	       }   
		   else if ( type.equals("9")   ){ //MMDD
			   if( dt.matches("^([0][0-9]|[1][0-2])\\.([0-9])$")) {
				   bulkDt =  dt.replaceAll("^([0][0-9]|[1][0-2])\\.([0-9])$",   "$1-0$2" );
				   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
			   }
			   else {
				   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
				   bulkDt =   orgDt;
			   }
	       } 
		   else {  
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
		
	   }
	   
	   // 9:23
	   else if (dt.matches("^([0-9]):([0][1-9]|[1-5][0-9])$")) {
		       
			   bulkDt =  dt.replaceAll("^([0-9]):([0][1-9]|[1-5][0-9])$",   "0$1:$2" );
			   cngMsg = "HH24:MI_변경"  +"::" + orgDt + "::" + bulkDt;
	   }
	  
	   
	   // 1-23
	   else if (dt.matches("^([0-9])-([0][1-9]|[1-2][0-9]|[3][01])$")) {
	           
		   if( type.equals("9")) {
			   bulkDt =  dt.replaceAll("^([0-9])-([0][1-9]|[1-2][0-9]|[3][01])$",   "0$1-$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
	   }
	   
	   //12-3
	   else if (dt.matches("^([0][1-9]|[1][0-2])-([1-9])$")) {
           
		   if( type.equals("9")) {
			   bulkDt =  dt.replaceAll("^([0][1-9]|[1][0-2])-([1-9])$",   "$1-0$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
	   }
	   
	   
	   //2.2.
	   else if (dt.matches("^([1-9])\\.([1-9])\\.$")) {
           
		   if( type.equals("9")) {
			   bulkDt =  dt.replaceAll("^([1-9])\\.([1-9])\\.$",   "0$1-0$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
	   }
	   
	   
	   //7월1일
	   else if (dt.matches("^([1-9])월([1-9])일$")) {
           
		   if( type.equals("9")) {
			   bulkDt =  dt.replaceAll("^([1-9])월([1-9])일$",   "0$1-0$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
	   }
	   	   
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	    
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	

	public Map<String, String>  checkLength_05 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
	 
 
	   // '98년도 
	   if (dt.matches("^(‘|’|'|`)([0-9][0-9])년도$")) { 
		   	   
			   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9])년도$",    "$2");
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	   } 
	   // 2005년
	   else if(dt.matches("^([12][0-9][0-9][0-9])년$")) {
	   	   
		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])년$", "$1");
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	   // 2013. ] , ...
	   else if(dt.matches("^([12][0-9][0-9][0-9])(\\(|\\+|-|\\.|,|\\?|(‘|’|'|`)|/|\\]|;|\\)|▲)$")) {
	   	   
		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])(\\(|\\+|-|\\.|,|\\?|(‘|’|'|`)|/|\\]|;|\\)|▲)$",    "$1");
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	  // '2023
	   else if (dt.matches("^(c|-|;|‘|’|'|`|\\]|ⓒ)([12][0-9][0-9][0-9])$")) {
		  
			   bulkDt =   dt.replaceAll("^(c|-|;|‘|’|'|`|\\]|ⓒ)([12][0-9][0-9][0-9])$",   "$2");
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	  } 		   
	   //2,222
	   else if (dt.matches("^([12]),([0-9][0-9][0-9])$")) {
		   if ( type.equals("11")) {
			   bulkDt =   dt.replaceAll("^([12]),([0-9][0-9][0-9])$",   "$1$2");
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
	       }   
		   else if ( type.equals("13")) {
			   bulkDt =   dt.replaceAll("^([12]),([0-9])([0-9][0-9])$",   "$1$2:$3");
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
	       }
      } 	
	   

	   
	   // '19.2
	   else if (dt.matches("^(‘|’|'|`)([0-9][0-9])\\.[1-9]$")) {
		   if ( type.equals("6") || type.equals("8") ){
				  
			   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9])\\.[1-9]$",   "$2");
			   
			   String tmp = dt.replaceAll("^(‘|’|'|`)([0-9][0-9])\\.([1-9])$",   "0$3");
			   
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       } 
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
	   }
	
	   
	   // 87.6.
	   else if (dt.matches("^([0-9][0-9])\\.([1-9])\\.$")) {
		     
		   if ( type.equals("6") || type.equals("8") ){
			  
			   bulkDt =   dt.replaceAll("^([0-9][0-9])\\.([1-9])\\.$",   "$1");
			   String tmp =  dt.replaceAll("^([0-9][0-9])\\.([1-9])\\.$",   "0$2");
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       } 
		   else if ( type.equals("9")) {
			   if (dt.matches("^([0][0-9]|[1][0-2])\\.([1-9])\\.$")) {
				   bulkDt =   dt.replaceAll("^([0][0-9]|[1][0-2])\\.([1-9])\\.$",   "$1:0$2");
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt; 
			   }
			   else {
				   errMsg = "MMDD_변경 불가";
				   bulkDt = orgDt;
			   }
		   }
		   
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }
 
	   // 87.12
	   else if (dt.matches("^([0-9][0-9])\\.([0-9][0-9])$")) {
		     
		   if ( type.equals("6") || type.equals("8") ){
			  
			   bulkDt =   dt.replaceAll("^([0-9][0-9])\\.([0-9][0-9])$",   "$1");
			   String tmp =  dt.replaceAll("^([0-9][0-9])\\.([1-9][0-9])$",   "$2");
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       } 
		   else if ( type.equals("9")) {
			   if (dt.matches("^([0][0-9]|[1][0-2])\\.([0-2][0-9]|[3][01])$")) {
				   
			   
				   bulkDt =   dt.replaceAll("^([0][0-9]|[1][0-2])\\.([0-2][0-9]|[3][01])$",   "$1:$2");
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt; 
			   }
			   else {
				   errMsg = "MMDD_변경 불가";
				   bulkDt = orgDt;
			   }
		   }
		   
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }
	   
	   
	   
	   // 15.3월
	   else if (dt.matches("^([0-9][0-9])\\.([0-9])월$")) {
		     
		   if (  type.equals("8") ){
			  
			   bulkDt =   dt.replaceAll( "^([0-9][0-9])\\.([0-9])월$",   "$1");
			   String tmp =  dt.replaceAll("^([0-9][0-9])\\.([0-9])월$",   "0$2");
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       }  
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }
	   
	   
	   
	   // 5.21.
	   else if (dt.matches("^([0-9])\\.([0-9][0-9])\\.$")) {
		     
		   if (  type.equals("9") ){
			  
			   bulkDt =   dt.replaceAll( "^([0-9])\\.([0-9][0-9])\\.$",   "0$1:$2"); 
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       }  
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   } 
	   
       //02-01
	   else if (dt.matches("^(0[1-9]|1[0-2])\\-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
		     
		   if ( type.equals("9")) {
			   
			   bulkDt =  dt;
			   
			   if( bulkDt.equals(orgDt)) {
				   cngMsg = "";
			   }
			   else {
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   }
		   }
		   
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   }

	     
	   // 9월23일
	   else if (dt.matches("^([1-9])월([012][0-9]|[3][01])일$")) {
		       
			   bulkDt =  dt.replaceAll("^([1-9])월([012][0-9]|[3][01])일$",   "0$1-$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
	   }
	  
	   
	   // 10월2일
	   else if (dt.matches("^([0][1-9]|[1][012])월([0-9])일$")) {
		       
			   bulkDt =  dt.replaceAll("^([0][1-9]|[1][012])월([0-9])일$",   "$1-0$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
	   }
	   
	   
	   
	   
	   //12:02
	   else if (dt.matches("^([01][0-9]|[2][0-3]):([0-5][0-9])$")) {
           
		   if( type.equals("5") || type.equals("6") ||  type.equals("7")) {
			   bulkDt =  dt.replaceAll("^([01][0-9]|[2][0-3])(:|;)([0-5][0-9])$",   "$1:$3" );
			  
			   if (bulkDt.equals(orgDt) && type.equals("7"))  {
				   cngMsg = "";
			   }
			   else {
				   cngMsg =  "HH24MI_변경"  +"::" + orgDt + "::" + bulkDt;
				   
			   }
		   }
		   else {
			   errMsg = "날짜타입_변경_불가"  +"::" + orgDt + "::" + bulkDt;
			   bulkDt =   orgDt;
		   }
	   }
	   
	   
	   // 5.21.
	   else if (dt.matches("^([0-9])시([0-9][0-9])분$")) {
		     
		   if (  type.equals("7") ){
			  
			   bulkDt =   dt.replaceAll( "^([0-9])시([0-9][0-9])분$",   "0$1:$2"); 
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       }  
		   else {
			   errMsg = "날짜타입_변경_불가";
			   bulkDt =   orgDt;
		   }
		
	   } 

	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	    
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
			
	
	//////////////////
   // 스페이스 포함  
	public Map<String, String>  checkLength_06 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
		/*
		 *             
		 *  print( '         YYYY-MM-DD HH24:MI:SS: 1')
            print( '         YYYY-MM-DD HH24:MI: 2')
            print( '         YYYY-MM-DD HH24: 3')
            print( '         MM-DD HH24:MI: 4')
            print( '         HH24:MI:SS: 5')
            print( '         YYYY-MM-DD: 6')
            print( '         HH24:MI: 7')
            print( '         YYYY-MM: 8')
            print( '         MM-DD: 9')
            print( '         HH24: 10')
            print( '         YYYY: 11')
            print( '         DD: 12')
            print( '         MI: 13')
            print( '         MM: 14')
            print( '         SS: 15')
		 */
 
	   // *2019* 
	   if (dt.matches("^(‘|’|'|`|\\[|c|\\.)([12][0-9][0-9][0-9])(년|\\.|\\])$")) { 
		   	   
			   bulkDt =   dt.replaceAll("^(‘|’|'|`|\\[|c|\\.)([12][0-9][0-9][0-9])(년|\\.|\\])$",    "$2");
			   System.out.println(dt.substring(0,4));
			   bulkDt = bulkDt.substring(0,4); 
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
			   
	   } 
	   // 2012**
	   else if (dt.matches("^([12][0-9][0-9][0-9])(년도|년\\.|\\.\\.|\\.[0])$")) { 
	   	   
		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])(년도|년\\.|\\.\\.|\\.[0])$", "$1");
		    
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	   
	   // 200508
	   else if(dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])$")) {
	   	   
		   if ( type.equals("8")){
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])$", "$1-$2");
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   
       }
	   
	   // 200508
	   else if(dt.matches("^([01][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])$")) {
	   	   
		   if ( type.equals("5")){
			   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])$", "$1:$2:$3");
			   cngMsg = "HH24MISS_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   
       }
	   

	   // 2013.2
	   else if(dt.matches("^([12][0-9][0-9][0-9])[\\.|\\-]([1-9])$")) {
	   	   
		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\-]([1-9])$",    "$1-0$2");
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	  // '20.23
	   else if (dt.matches("^(‘|’|'|`)([0-9][0-9])\\.([0][0-9]|[1][12])$")) {
		  
		   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9])\\.([0-9][0-9])$",   "$2");
		   String tmp =  dt.replaceAll("^(‘|’|'|`)([0-9][0-9])\\.([0-9][0-9])$",   "$2");
		   
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   bulkDt = bulkDt + "-" + tmp;
		   
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
	  } 		   
	   //‘83.2월
	   else if (dt.matches("^(‘|’|'|`)([0-9][0-9]).([1-9])월$")) {
		   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9]).([1-9])월$",   "$2");
		   String tmp = dt.replaceAll("^(‘|’|'|`)([0-9][0-9]).([1-9])월$",   "0$3");
			
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   bulkDt = bulkDt + "-" + tmp;
		   
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
      } 	
	   
	  // 84.4.-
	   else if (dt.matches("^([0-9][0-9]).([1-9])\\.\\-$")) {
		   bulkDt =   dt.replaceAll("^([0-9][0-9]).([1-9])\\.\\-$",   "$1");
		   String tmp = dt.replaceAll("^([0-9][0-9]).([1-9])\\.\\-$",   "0$2");
			
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   bulkDt = bulkDt + "-" + tmp;
		   
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
      }    
	   
	// 2008 8
	   else if (dt.matches("^([0-9][0-9][0-9][0-9]) ([1-9])$")) {
		   bulkDt =   dt.replaceAll("^([0-9][0-9][0-9][0-9]) ([1-9])$",   "$1-0$2");
			
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
      }  
		// 15. 07
	   else if (dt.matches("^([0-9][0-9])\\. ([0-9][0-9])$")) {
		   bulkDt =   dt.replaceAll("^([0-9][0-9])\\. ([0-9][0-9])$",   "$1");
		   String tmp =  dt.replaceAll("^([0-9][0-9])\\. ([0-9][0-9])$",   "$2");
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   bulkDt = bulkDt + "-" + tmp;
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
      }  

	   
		// 15.12월
	   else if (dt.matches("^([0-9][0-9])\\.([0][1-9]|[1][0-2])월$")) {
		   bulkDt =   dt.replaceAll("^([0-9][0-9])\\.([0][1-9]|[1][0-2])월$",   "$1");
		   String tmp =  dt.replaceAll("^([0-9][0-9])\\.([0][1-9]|[1][0-2])월$",   "$2");
		   int dty = Integer.parseInt(bulkDt);
		   if (dty > 25)  bulkDt = "19" + bulkDt;
		   else bulkDt = "20" + bulkDt;
		   bulkDt = bulkDt + "-" + tmp;
		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
      }  	 
//	   
//
	   
	   // '19.2.
	   else if (dt.matches("^(‘|’|'|`)([0-9][0-9])[\\.|년]|\\-][1-9][월|\\.]$")) {
		   
				  
			   bulkDt =   dt.replaceAll("^(‘|’|'|`)([0-9][0-9])[\\.|년|\\-][1-9][\\.|월|\\-]$",   "$2");
			   
			   String tmp = dt.replaceAll("^(‘|’|'|`)([0-9][0-9])[\\.|년|\\-]([1-9])[\\.|월|\\-]$",   "0$3");
			   
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
	   }
	   
	   // 19년 2
	   else if (dt.matches("^([0-9][0-9])[\\.|년]|\\-] [1-9][월|\\.]$")) {
		   
				  
			   bulkDt =   dt.replaceAll("^([0-9][0-9])[\\.|년|\\-] [1-9][\\.|월|\\-]$",   "$2");
			   
			   String tmp = dt.replaceAll("^([0-9][0-9])[\\.|년|\\-] ([1-9])[\\.|월|\\-]$",   "0$3");
			   
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	       
	   }
//	
//	   
	   // 1987-6
	   else if (dt.matches("^([12][0-9][0-9][0-9])\\-([1-9])$")) {
		     
		   	  
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\-([1-9])$",   "$1-0$2");
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
	   }   
	   
	   // 19. 12
	   else if (dt.matches("^([0-9][0-9])\\. ([0][1-9]|[1][0-2])$")) {
		     
		   	  
			   bulkDt =   dt.replaceAll("^([0-9][0-9])\\. ([0][1-9]|[1][0-2])$",   "$1");
			   String tmp = dt.replaceAll("^([0-9][0-9])\\. ([0][1-9]|[1][0-2])$",   "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	   }  
	   // 19. 1.
	   else if (dt.matches("^([0-9][0-9])\\. ([1-9])\\.$")) {
		     
		   if (type.equals("6")||type.equals("8")){
			   bulkDt =   dt.replaceAll("^([0-9][0-9])\\. ([1-9])\\.$",   "$1");
			   String tmp = dt.replaceAll("^([0-9][0-9])\\. ([1-9])\\.$",   "0$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else if (type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][0-2])\\. ([1-9])\\.$",   "$1-0$2"); 
			    
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt; 
		   }
		   else {
			   errMsg ="날짜변경불가";
		   }
	   }  
	   // 19. 1월 
	   else if (dt.matches("^([0-9][0-9])[\\.|년|\\-] ([1-9])월$")) {
		     
		   	  
			   bulkDt =   dt.replaceAll("^([0-9][0-9])[\\.|년|\\-] ([1-9])월$",   "$1");
			   String tmp = dt.replaceAll("^([0-9][0-9])[\\.|년|\\-] ([1-9])월$",   "0$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
	   }    
	   
	   // 19.11. 
	   else if (dt.matches("^([0-9][0-9])\\.([0-9][0-9])\\.$")) {
		   if ( type.equals("6")){
			   bulkDt =   dt.replaceAll("^([0-9][0-9])\\.([0-9][0-9])\\.$",   "$1");
			   String tmp = dt.replaceAll("^([0-9][0-9])\\.([0-9][0-9])\\.$",   "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   }
		   else if (type.equals("9")){
			   if (dt.matches("^([01][0-9]|[1][0-2])\\.([0-5][0-9])\\.$") ) {
				   bulkDt =   dt.replaceAll("^([01][0-9]|[1][0-2])\\.([0-5][0-9])\\.$",   "$1-$2");
				     
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   }
			   else {
				    bulkDt = orgDt;
				    errMsg = "MMDD_변경_불가";
			   }
			   
		   }
		   	  
			   
	   }    
	   // 1. 11. 
	   else if (dt.matches("^([1-9])\\. ([0-9][0-9])\\.$")) {
		  if (type.equals("9")){
			   if (dt.matches("^([1-9])\\. ([0-9][0-9])\\.$") ) {
				   bulkDt =   dt.replaceAll("^([1-9])\\. ([0-9][0-9])\\.$",   "0$1-$2");
				     
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   }
			   else {
				    bulkDt = orgDt;
				    errMsg = "MMDD_변경_불가";
			   }
			   
		   }
		   	  
			   
	   }    
	   
	   // 89.3.6 
	   else if (dt.matches("^([0-9][0-9])\\.([1-9])\\.([1-9])$")) {
		  if (type.equals("6")){
			   if (dt.matches("^([0-9][0-9])\\.([1-9])\\.([1-9])$") ) {
				   bulkDt =   dt.replaceAll("^([0-9][0-9])\\.([1-9])\\.([1-9])$",   "$1");
				     
				   String tmp = dt.replaceAll("^([0-9][0-9])\\.([1-9])\\.([1-9])$",   "0$2-0$3"); 
				   int dty = Integer.parseInt(bulkDt);
				   if (dty > 25)  bulkDt = "19" + bulkDt;
				   else bulkDt = "20" + bulkDt;
				   
				   bulkDt = bulkDt + "-" + tmp;
				   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
				   
				   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   }
			   else {
				    bulkDt = orgDt;
				    errMsg = "MMDD_변경_불가";
			   }
			   
		   }
		   
	   }     
 
// 
//	   // 11시30분 
	   else if (dt.matches("^([01][0-9]|[2][0-3])시([0-5][0-9])분$")) {
		     
		   if ( type.equals("7")  ){
			  
			   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3])시([0-5][0-9])분$",   "$1:$2");
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       } 	
	   }
	   
	   //16.:50
	   else if (dt.matches("^([01][0-9]|[2][0-3])\\.:([0-5][0-9])$" ) ) {
		     
		   if ( type.equals("7")  ){
			  
			   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3])\\.:([0-5][0-9])$",   "$1:$2");
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       } 	
	   }   
	   //*16:50
	   else if (dt.matches("^\\*([01][0-9]|[2][0-3]):([0-5][0-9])$" ) ) {
		     
		   if ( type.equals("7")  ){
			  
			   bulkDt =   dt.replaceAll("^\\*([01][0-9]|[2][0-3]):([0-5][0-9])$",   "$1:$2");
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt; 
			   
	       } 	
	   }   
	     
	   // 10월23일
	   else if (dt.matches("^([1][0-2])월([012][0-9]|[3][01])일$")) {
		       
			   bulkDt =  dt.replaceAll("^([1][0-2])월([012][0-9]|[3][01])일$",   "$1-$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
	   }   
	   
	   // 1월 23일
	   else if (dt.matches("^([1-9])월 ([012][0-9]|[3][01])일$")) {
		       
			   bulkDt =  dt.replaceAll("^([1-9])월 ([012][0-9]|[3][01])일$",   "0$1-$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
	   }   
	   
	   // 3.5(월)
	   else if (dt.matches("^([1-9])[\\.|/]([0-9])\\([일월화수목금토]\\)$")) {
		       
			   bulkDt =  dt.replaceAll("^([1-9])[\\.|/]([0-9])\\([일월화수목금토]\\)$",   "0$1-0$2" );
			   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
	   }  
	   // 10월 3일
	   else if (dt.matches("^([1][0-2])월 ([0-9])일$")) {
	       
		   bulkDt =  dt.replaceAll("^([1][0-2])월 ([0-9])일$",   "$1-$2" );
		   cngMsg = "MMDD_변경"  +"::" + orgDt + "::" + bulkDt;
       }   
 

	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	    
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	
	//
////스페이스 포함 
	
	public Map<String, String>  checkLength_07 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
		/*
		 *             
		 *  print( '         YYYY-MM-DD HH24:MI:SS: 1')
            print( '         YYYY-MM-DD HH24:MI: 2')
            print( '         YYYY-MM-DD HH24: 3')
            print( '         MM-DD HH24:MI: 4')
            print( '         HH24:MI:SS: 5')
            print( '         YYYY-MM-DD: 6')
            print( '         HH24:MI: 7')
            print( '         YYYY-MM: 8')
            print( '         MM-DD: 9')
            print( '         HH24: 10')
            print( '         YYYY: 11')
            print( '         DD: 12')
            print( '         MI: 13')
            print( '         MM: 14')
            print( '         SS: 15')
		 */
 
	   // [2019** 
	   if (dt.matches("^\\[([12][0-9][0-9][0-9])\\W\\W$")) { 
		   	   
		   if(type.equals("6")||type.equals("8")||type.equals("11")) {
			   bulkDt =   dt.replaceAll("^\\[([12][0-9][0-9][0-9])\\W\\W$",    "$1");
			    
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // [c2019* 
	   else if (dt.matches("^\\[.([12][0-9][0-9][0-9])\\W$")) { 
		   	   
		   if(type.equals("6")||type.equals("8")||type.equals("11")) {
			   bulkDt =   dt.replaceAll("^\\[.([12][0-9][0-9][0-9])\\W$",    "$1");
			    
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // 2019*** 
	   else if (dt.matches("^([12][0-9][0-9][0-9])\\W\\W\\W$")) { 
		   	   
		   if(type.equals("6")||type.equals("8")||type.equals("11")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])...$",    "$1");
			    
			   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // 2 0 1 9
	   else if (dt.matches("^([12])\\s([0-9])\\s([0-9])\\s([0-9])$")) { 
	   	   
		   bulkDt =   dt.replaceAll("^([12])\\s([0-9])\\s([0-9])\\s([0-9])$", "$1$2$3$4");
		    
		   cngMsg = "YYYY_변경" +"::" + orgDt + "::" + bulkDt;
		   
       }
	   
	   // 2012*2*
       else if (dt.matches("^([12][0-9][0-9][0-9])[\\(|\\s|년|\\-|\\.]([1-9])[\\)|월|\\-|\\.]$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\(|\\s|년|\\-|\\.]([1-9])[\\)|월|\\-|\\.]$", "$1-0$2");
		    
		   		cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   // '13.12.  '13.12월
       else if (dt.matches("^[‘|’|'|`]([0-9][0-9])\\.([0-9][0-9])[월|\\.]$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.([0-9][0-9])[월|\\.]$", "$1");
		    
    		   String tmp = dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.([0-9][0-9])[월|\\.]$",   "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   
	   // '13. 12   
       else if (dt.matches("^[‘|’|'|`]([0-9][0-9])\\.\\s([0-9][0-9])$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.\\s([0-9][0-9])$", "$1");
		    
    		   String tmp = dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.\\s([0-9][0-9])$", "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   // 2012* 2
       else if (dt.matches("^([12][0-9][0-9][0-9])\\.\\s([1-9])$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\.\\s([1-9])$", "$1-0$2");
		    
		   		cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       } 	   
	    
	// 2012- 12
       else if (dt.matches("^([12][0-9][0-9][0-9])\\-(0[1-9]|1[0-2])$")) { 
    	   if(type.equals("8") ) {
    		   bulkDt =   dt;
		    
		   		cngMsg = "";
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
       else if (dt.matches("^([12][0-9][0-9][0-9])\\.(0[1-9]|1[0-2])$")) { 
    	   if(type.equals("8") ) {
    		   bulkDt =    dt.replaceAll("^([12][0-9][0-9][0-9])\\.(0[1-9]|1[0-2])$", "$1-$2");;
		    
    		   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   // 201310월 
       else if (dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])월$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])월$", "$1-$2");
		    
		   		cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       } 	  

	   
	   // 12. 12월 
       else if (dt.matches("^([0-9][0-9])[\\.|년]\\s([0][1-9]|[1][012])월$")) { 
    	   if(type.equals("6")||type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^([0-9][0-9])[\\.|년]\\s([0][1-9]|[1][012])월$", "$1");
		    
    		   String tmp = dt.replaceAll("^([0-9][0-9])[\\.|년]\\s([0][1-9]|[1][012])월$", "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   
	   // 2.12(월) 
       else if (dt.matches("^([1-9])[\\.|월|/]([0][1-9]|[12][0-9]|[3][01])\\([월|화|수|목|금|토|일]\\)$")) { 
    	   if(type.equals("9") ) {
    		   bulkDt =   dt.replaceAll("^([1-9])[\\.|월|/]([0][1-9]|[12][0-9]|[3][01])\\([월|화|수|목|금|토|일]\\)$", "0$1-$2");
		    
    		  
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   // 12월 12일 
       else if (dt.matches("^([0][1-9]|[1][012])월\\s([0][1-9]|[12][0-9]|[3][01])일$")) { 
    	   if(type.equals("9") ) {
    		   bulkDt =   dt.replaceAll("^([0][1-9]|[1][012])월\\s([0][1-9]|[12][0-9]|[3][01])일$", "$1-$2");
		    
    		  
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
       
	   // 2. 2(월) 
       else if (dt.matches("^([1-9])[\\.|월|/]\\s([1-9])\\([월|화|수|목|금|토|일]\\)$")) { 
    	   if(type.equals("9") ) {
    		   bulkDt =   dt.replaceAll("^([1-9])[\\.|월|/]\\s([1-9])\\([월|화|수|목|금|토|일]\\)$", "0$1-0$2");
		    
    		  
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }   
         
	   // 12. 12. 
       else if (dt.matches("^([0][1-9]|[1][012])\\.\\s([0][1-9]|[12][0-9]|[3][01])\\.$")) { 
    	   if(type.equals("9") ) {
    		   bulkDt =   dt.replaceAll("^([0][1-9]|[1][012])\\.\\s([0][1-9]|[12][0-9]|[3][01])\\.$", "$1-$2");
		    
    		  
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   // 5.2.(화) 
       else if (dt.matches("^([1-9])[\\.|월|/]([1-9])[\\.|/|\\s]\\([월|화|수|목|금|토|일]\\)$")) { 
    	   if(type.equals("9") ) {
    		   bulkDt =   dt.replaceAll("^([1-9])[\\.|월|/]([1-9])[\\.|/|\\s]\\([월|화|수|목|금|토|일]\\)$", "0$1-0$2");
		    
    		  
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	  
       //99.9.8.
       else if (dt.matches("^([0-9][0-9])[\\.|년|/]([1-9])\\.([1-9])\\.$")) { 
    	   if(type.equals("6") ) {
    		   bulkDt =   dt.replaceAll("^([0-9][0-9])[\\.|년|/]([1-9])\\.([1-9])\\.$", "$1");
		    

    		   String tmp = dt.replaceAll("^([0-9][0-9])[\\.|년|/]([1-9])\\.([1-9])\\.$", "0$2-0$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	     
       //99. 9.8
       else if (dt.matches("^([0-9][0-9])[\\.|년|/]\\s([1-9])\\.([1-9])$")) { 
    	   if(type.equals("6") ) {
    		   bulkDt =   dt.replaceAll("^([0-9][0-9])[\\.|년|/]\\s([1-9])\\.([1-9])$", "$1");
		    

    		   String tmp = dt.replaceAll("^([0-9][0-9])[\\.|년|/]\\s([1-9])\\.([1-9])$", "0$2-0$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   cngMsg = "YYYYMMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       } 
       
	   // '13.2.2   
       else if (dt.matches("^[‘|’|'|`]([0-9][0-9])\\.([1-9])\\.([1-9])$")) { 
    	   if(type.equals("6") ) {
    		   bulkDt =   dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.([1-9])\\.([1-9])$", "$1");
		    
    		   String tmp = dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.([1-9])\\.([1-9])$", "0$2-0$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   // 2011629
       else if (dt.matches("^([12][0-9][0-9][0-9])([1-9])([0][1-9]|[12][0-9]|[3][01])$")) { 
    	   if(type.equals("6") ) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([1-9])([0][1-9]|[12][0-9]|[3][01])$", "$1-0$2-$3");
		    
    		  
			   cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   //18시 00분
       else if (dt.matches("^([01][0-9]|[2][0-3])시\\s([0-5][0-9])분$")) { 
    	   if(type.equals("7") ) {
    		   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3])시\\s([0-5][0-9])분$", "$1:$2");
		    
    		  
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	    
	   
       else if (dt.matches("^([0-9]):([0-5][0-9]):([0-5][0-9])$")) { 
    	   if(type.equals("5") ) {
    		   bulkDt =   dt.replaceAll("^([0-9]):([0-5][0-9]):([0-5][0-9])$", "0$1:$2:$3");
		    
    		  
			   cngMsg = "HH24MISS__변경" +"::" + orgDt + "::" + bulkDt;
			   
		   		 
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
       else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	     
	   
	   
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		 
	}
	
	
	public Map<String, String>  checkLength_08 (String dt , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
 		String errMsg;
		 
 
		
	   bulkDt = dt; 
	   orgDt = dt;
	   cngMsg ="";
	   errMsg = "";
	   
	   
	   // 12:21:12
	   
	   if (dt.matches("^([01][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")) { 
	   	   
		   if(type.equals("5")) {
			   bulkDt =   dt.replaceAll("^([01][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$",    "$1:$2:$3");
			    
			   cngMsg = "HH24MISS_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // mmdd
	   else if (dt.matches("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // yyyymm   2020.02. 
	   else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|년|/|:|\\-|\\s|\\(]\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\s|\\-|\\)]*$")) { 
	   	   
		   if(type.equals("6")|| type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|년|/|:|\\-|\\s|\\(]\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\s|\\-|\\)]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   //2012 .01
	   else if (dt.matches("^([12][0-9][0-9][0-9])\\s\\.([0][1-9]|[1][012]|[1-9])$")) { 
	   	   
		   if(type.equals("6")|| type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s\\.([0][1-9]|[1][012]|[1-9])$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   
	   else if (dt.matches("^([12][0-9][0-9][0-9])\\s\\.([0][1-9]|[1][012]|[1-9])$")) { 
	   	   
		   if(type.equals("6")|| type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s\\.([0][1-9]|[1][012]|[1-9])$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   // ‘16. 11월  
       else if (dt.matches("^[‘|’|'|`]([0-9][0-9])\\.\\s([0][1-9]|[1][012]|[1-9])[월|\\.|\\-]$")) { 
    	   if(type.equals("8") ) {
    		   bulkDt =   dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.\\s([0][1-9]|[1][012]|[1-9])[월|\\.|\\-]$", "$1");
		    
    		   String tmp = dt.replaceAll("^[‘|’|'|`]([0-9][0-9])\\.\\s([0][1-9]|[1][012]|[1-9])[월|\\.|\\-]$", "$2"); 
			   int dty = Integer.parseInt(bulkDt);
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   bulkDt = bulkDt + "-" + tmp;
			   
		   		cngMsg = "YYYYMM__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   //
       else if (dt.matches("^[‘|’|'|`]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$")) { 
    	   if(type.equals("2") || type.equals("6")) {
    		   bulkDt =   dt.replaceAll("^[‘|’|'|`]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$", "$1");
		    
    		   String tmp = dt.replaceAll("^[‘|’|'|`]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$", "$2-$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if( bulkDt.length() ==1 ) bulkDt = "0" + bulkDt;
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
       else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$")) { 
    	   if(type.equals("2") || type.equals("6")) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$", "$1");
		    
    		   String tmp = dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.]*$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	   
	   
 
	   
       else if (dt.matches("^([12][0-9][0-9][0-9])([1-9]|[0][1-9]|[1][012])[\\.|\\s]*([012][0-9]|[3][01]|[1-9])$")) { 
    	   if(type.equals("2") || type.equals("6")) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([1-9]|[0][1-9]|[1][012])[\\.|\\s]*([012][0-9]|[3][01]|[1-9])$", "$1");
		    
    		   String tmp = dt.replaceAll("^([12][0-9][0-9][0-9])([1-9]|[0][1-9]|[1][012])[\\.|\\s]*([012][0-9]|[3][01]|[1-9])$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }	
	   
	   
	   
       else if (dt.matches("^([0-9][0-9])(\\s\\s)([0][1-9]|[1][012])([012][0-9]|[3][01])$")) { 
    	   if(type.equals("2") || type.equals("6")) {
    		   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s\\s([0][1-9]|[1][012])([012][0-9]|[3][01])$", "$1");
		    
    		//   String tmp = dt.replaceAll("^([12][0-9][0-9][0-9])\\s\\s([0][1-9]|[1][012])([012][0-9]|[3][01])$", "$3-$3"); 
			  
			   
			   String[] tmp2 = bulkDt.split("  ");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   int dty = Integer.parseInt(tmp2[0]);
			   
			   if (dty > 25)  tmp2[0] = "19" + tmp2[0];
			   else tmp2[0] = "20" + tmp2[0];
			   
			   bulkDt = tmp2[0] + "-" + tmp2[1].substring(0, 2) + "-" + tmp2[1].substring(2, 4);
			   
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   
       else if (dt.matches("^([0-9][0-9])/([0][1-9]|[1][012])/([012][0-9]|[3][01])$")) { 
    	   if(  type.equals("6")) {
    		   bulkDt =   dt.replaceAll("^([0-9][0-9])/([0][1-9]|[1][012])/([012][0-9]|[3][01])$", "$1");
		    
    		   String tmp = dt.replaceAll("^([0-9][0-9])/([0][1-9]|[1][012])/([012][0-9]|[3][01])$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   int dty = Integer.parseInt(bulkDt);
			   
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			    
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
    	   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
       }
	   
	   
	   ////////////////////////////////////////////////////
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	   
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}
	
 
////////////////////////////////////////////////////////////////////////////////////////////////////


	
	public Map<String, String>  checkLength_09 (String dtemp , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
		String errMsg;
		 
	
		
	   bulkDt = dtemp; 
	   orgDt = dtemp;
	   cngMsg ="";
	   errMsg = "";
	   
	   
	   String dt = dtemp.trim().trim();
	   
	   // 야간(13:22)   
	   if (dt.matches("^(야간|주간|오전|오후)\\(([1-9]|[01][0-9]|[2][0-3]):([0-5][0-9]|[1-9])\\)$")) { 
	   	   
		   if(type.equals("7")) {
			   bulkDt =   dt.replaceAll("^(야간|주간|오전|오후)\\(([1-9]|[01][0-9]|[2][0-3]):([0-5][0-9]|[1-9])\\)$",    "$1:$2:$3");
			    
			   String[] tmp = bulkDt.split(":");
			   
			   int cnt = 0;
			   if( tmp[0].equals("야간") || tmp[0].equals("오후")) {
				 cnt = 12; 
	          		   
			   }
			   
			   if ( Integer.parseInt(tmp[1]) < 13 ) {
				  cnt = cnt +    Integer.parseInt(tmp[1]);
				  tmp[1] = cnt +"";
				  
				  if( tmp[1].length() ==1  ) tmp[1] = "0"+ tmp[1];
			   }
			   
			   
			   bulkDt = tmp[1] + ":" + tmp[2];
			   cngMsg = "HH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	
	   else if (dt.matches("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9") | type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/]*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/]*$", "$1");
		    
			   String tmp = dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/]*$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }	
	   
	   
	   
	   else if (dt.matches("^[‘|’|'|`|′|´]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?]*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?]*$", "$2-$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if( bulkDt.length() ==1 ) bulkDt = "0" + bulkDt;
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	   
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	   
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}

	
	
	public Map<String, String>  checkLength_10 (String dtemp , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
		String errMsg;
		 
	
		
	   bulkDt = dtemp; 
	   orgDt = dtemp;
	   cngMsg ="";
	   errMsg = "";
	   
	   
	   String dt = dtemp.trim().trim();
	   
	 if (dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$")) { 
	   	   
		   if(type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$",    "$1-$2-$3 $4");
			    
			   	String[] tmp = bulkDt.split("-");
			   
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];

			    
			    bulkDt = tmp[0] + "-"  + tmp[1] + "-" + tmp[2]   ;
			   
			   cngMsg = "YYYYMMDDHH24_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else if (dt.matches("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
      else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:]*$")) { 
		   if(type.equals("1")||type.equals("2") || type.equals("3") ||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:]*$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
			   if(dt.length()!=10) {
				   cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
			   } else {
				   cngMsg = "";
			   }
		 
			   
		   		
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }	
	  
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|'|\\.|/|\\-|:]*$")) { 
		   if(type.equals("1")||type.equals("2") || type.equals("3") ||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|'|\\.|/|\\-|:]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:]*\\s*([012][0-9]|[3][01]|[1-9])[일|'|\\.|/|\\-|:]*$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
			   if(dt.length()!=10) {
				   cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
			   } else {
				   cngMsg = "";
			   }
		 
			   
		   		
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }	
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[0-9])[\\.|\\s|,|\\-|년|\\:]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년|\\:]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년|\\:]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$2-$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if( bulkDt.length() ==1 ) bulkDt = "0" + bulkDt;
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	 
	 
	   
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	   
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}
	
	

	
	public Map<String, String>  checkLength_11 (String dtemp , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
		String errMsg;
		 
	
		
	   bulkDt = dtemp; 
	   orgDt = dtemp;
	   cngMsg ="";
	   errMsg = "";
	   
	   
	   String dt = dtemp.trim().trim();
	   
	 if (dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$")) { 
	   	   
		   if(type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$",    "$1-$2-$3 $4");
			    
			 
			   
			   cngMsg = "YYYYMMDDHH24_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else if (dt.matches("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	  
	 //4번 매치
	  else if (dt.matches("^(0[1-9]|1[0-2])\\-([0-2][0-9]|3[0-1])\\s([01][0-9]|2[0-3])\\:([0-5][0-9])$")) { 
		  if(type.equals("4")) {
			   bulkDt =   dt.replaceAll("^(0[1-9]|1[1-2])\\-([01][0-9]|3[0-1])\\s([01][0-9]|2[0-3])\\:([0-5][0-9])$",    "$1-$2 $3:$4");
			    
			   cngMsg = "MMDDHH24MI변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	 
      else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$")) { 
		   if(type.equals("1")||type.equals("2") || type.equals("3") ||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$", "$2-$3"); 
			  
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }	
	  
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$1");
		    
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$2-$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if( bulkDt.length() ==1 ) bulkDt = "0" + bulkDt;
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	   
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	   
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}	
	
	// 12 over 
	
	public Map<String, String>  checkLength_12_over (String dtemp , String type) {
		String bulkDt;
		String orgDt;
		String cngMsg;
		String errMsg;
		 
	
		
	   bulkDt = dtemp; 
	   orgDt = dtemp;
	   cngMsg ="";
	   errMsg = "";
	   
	   
	   String dt = dtemp.trim().trim();
	   
	 if (dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$")) { 
	   	   
		   if(type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([1-9]|[01][0-9]|[2][0-3])$",    "$1-$2-$3 $4");
			    
			   
			   cngMsg = "YYYYMMDDHH24_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else if (dt.matches("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$")) { 
	   	   
		   if(type.equals("6") ||type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([0][0-9]|[1][012]|[1-9])[\\.|월|/|:]\\s*([0-2][0-9]|[3][01]|[1-9])[\\.|일|\\s]*\\([일|월|화|수|목|금|토]\\)$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "MMDD_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
      else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("8")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\(][\\[*|\\s*|\\(]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　|\\)]*$")) { 
	   	   
		   if(type.equals("9")||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\(][\\[*|\\s*]*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　|\\)]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   else  if (dt.matches("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$")) { 
	   	   
		   if(type.equals("9")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\s*년\\s*([0][1-9]|[1][012]|[1-9])[\\.|월|\\]|\\s|　]*$",    "$1-$2");
			    
			   String[] tmp = bulkDt.split("-");
			   if(tmp[0].length()==1) tmp[0]= "0"+tmp[0];
			   if(tmp[1].length()==1) tmp[1]= "0"+tmp[1];
			   
			   bulkDt = tmp[0] + "-" + tmp[1];
			   
			   cngMsg = "YYYYMM_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$")) { 
		   if(type.equals("1")||type.equals("2") || type.equals("3") ||type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$", "$1");
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월|/|:|　]*\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|/|\\-|:|　|'|\\)]*$", "$2-$3"); 
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			//   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
			   	if ( tmp2[1].equals("00")) {
			   		bulkDt =  bulkDt + "-" + tmp2[0] ;
			   		cngMsg = "YYYYMM__변경" +"::" + orgDt + "::" + bulkDt;
			   		
			   	}
			   	else {
			   		bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
			   	}
			   
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }	
	  
	   
	   else if (dt.matches("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$1");
			   String tmp = dt.replaceAll("^[‘|’|'|`|′|´|\\.|\\(]*([0-9][0-9]|[1-9])[\\.|\\s|,|\\-|년]\\s*([1-9]|[0][1-9]|[1][012])[\\.|\\s|,|\\-|월]\\s*([012][0-9]|[3][01]|[1-9])[일|\\.|\\?|\\)]*$", "$2-$3"); 
			   int dty = Integer.parseInt(bulkDt);
			   if( bulkDt.length() ==1 ) bulkDt = "0" + bulkDt;
			   if (dty > 25)  bulkDt = "19" + bulkDt;
			   else bulkDt = "20" + bulkDt;
			   
			   String[] tmp2 = tmp.split("-");
			   if(tmp2[0].length()==1) tmp2[0]= "0"+tmp2[0];
			   if(tmp2[1].length()==1) tmp2[1]= "0"+tmp2[1];
			   
			   bulkDt = bulkDt + "-" + tmp2[0] + "-" + tmp2[1];
			   
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	   
	 
	   
	   else if (dt.matches("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([01][0-9]|[2][0-3])([0-5][0-9])$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])([0][1-9]|[1][012])([0-2][0-9]|[3][01]|[1-9])([01][0-9]|[2][0-3])([0-5][0-9])$", "$1-$2-$3 $4:$5");
			 
			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    if( tmp[3].length() ==1 ) tmp[3] = "0" + tmp[3]; 
			    if( tmp[4].length() ==1 ) tmp[4] = "0" + tmp[4]; 
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[3] + ":" + tmp[4];
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	   
	   else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*(\\([월|화|수|목|금|토]\\))*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*(\\([월|화|수|목|금|토]\\))*$", "$1-$2-$3");
			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2];
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	 
	   else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·]*(\\([월|화|수|목|금|토]\\))*$")) { 
		   if(type.equals("2") || type.equals("6")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·]*(\\([월|화|수|목|금|토]\\))*$", "$1-$2-$3");
			    String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2];
			    
			     
		 
			   
		   		cngMsg = "YYYYMMDD__변경" +"::" + orgDt + "::" + bulkDt;
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	 
	 else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·]*$")) { 
	   	   
		   if(type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·]*$",    "$1-$2-$3-$4");
			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    if( tmp[3].length() ==1 ) tmp[3] = "0" + tmp[3]; 
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[3] +":00" ;
			    
			   
			   cngMsg = "YYYYMMDDHH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 //sfsd
	 else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\||\\(]*([0-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\||\\)]*$")) { 
		   if(type.equals("2") ||type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\||\\(]*([0-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\||\\)]*$",    "$1-$2-$3-$4-$5");
			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    if( tmp[3].length() ==1 ) tmp[3] = "0" + tmp[3]; 
			    if( tmp[4].length() ==1 ) tmp[4] = "0" + tmp[4]; 
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[3] + ":" + tmp[4];
//			    System.out.println(bulkDt);
			   
			   cngMsg = "YYYYMMDDHH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 
	 
	 else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\.|\\s|,|\\-|초|_|/|:|　|·|\\|]*([0-9][0-9][0-9])$")) { 
	   	   
		   if(type.equals("2") ||type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\.|\\s|,|\\-|초|_|/|:|　|·|\\|]*([0-9][0-9][0-9])$",    "$1-$2-$3-$4-$5-$6");
			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    if( tmp[3].length() ==1 ) tmp[3] = "0" + tmp[3]; 
			    if( tmp[4].length() ==1 ) tmp[4] = "0" + tmp[4]; 
			    if( tmp[5].length() ==1 ) tmp[5] = "0" + tmp[5]; 
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[3] + ":" + tmp[4]+ ":" + tmp[5];
			    
			   
			   cngMsg = "YYYYMMDDHH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 else 	 if	 (dt.matches("^(‘|’|'|`)([12][0-9][0-9][0-9])([0][1-9]|[1][012]|[1-9])([0-2][0-9]|[3][01]|[1-9])([01][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])(‘|’|'|`)$")) {
		   if(type.equals("1")) {
//			   System.out.println("bulkDt 변경전 : " + bulkDt);
//			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\-([0][1-9]|[1][012])\\-([0-2][0-9]|[3][01]|[1-9])\\s([1-9]|[01][0-9]|[2][0-3])\\:([0-5][0-9])\\:([0-5][0-9])$",    "$1-$2-$3 $4:$5:$6");
			    bulkDt = dt.replaceAll("^(')([12][0-9][0-9][0-9])([0][1-9]|[1][012]|[1-9])([0-2][0-9]|[3][01]|[1-9])([01][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])(')$",    "$2-$3-$4 $5:$6:$7");
//			    System.out.println("bulkDt 변경후 : " + bulkDt);
			   cngMsg = "YYYYMMDD HH24:MI:SS_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 
	 
	 //else 	 if (dt.matches("^([12][0-9][0-9][0-9])\\-([0][1-9]|[1][012])\\-([0-2][0-9]|[3][01]|[1-9])\\s([1-9]|[01][0-9]|[2][0-3])\\:([0-5][0-9])\\:([0-5][0-9])$")) { 
	 else 	 if	 (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$")) {
		   if(type.equals("1")) {
//			   System.out.println("bulkDt 변경전 : " + bulkDt);
//			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\-([0][1-9]|[1][012])\\-([0-2][0-9]|[3][01]|[1-9])\\s([1-9]|[01][0-9]|[2][0-3])\\:([0-5][0-9])\\:([0-5][0-9])$",    "$1-$2-$3 $4:$5:$6");
			    bulkDt = dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$",    "$1-$2-$3 $4:$5:$6");
//			    System.out.println("bulkDt 변경후 : " + bulkDt);
			   
			   cngMsg = "YYYYMMDD HH24:MI:SS_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 
	 else 	 if	 (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$")) {
		   if(type.equals("1")) {
//			   System.out.println("bulkDt 변경전 : " + bulkDt);
//			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\-([0][1-9]|[1][012])\\-([0-2][0-9]|[3][01]|[1-9])\\s([1-9]|[01][0-9]|[2][0-3])\\:([0-5][0-9])\\:([0-5][0-9])$",    "$1-$2-$3 $4:$5:$6");
			    bulkDt = dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$",    "$1-$2-$3 $4:$5:$6");
//			    System.out.println("bulkDt 변경후 : " + bulkDt);
			   System.out.println("여기 : " + bulkDt.substring(0,10));
			   System.out.println(bulkDt.substring(11,12));
			   
			   String date1 = bulkDt.substring(0,10);
			   String date2 = "0"+bulkDt.substring(11);
			   
			   bulkDt = date1 + " " + date2;
			   
			    System.out.println("나오나");
			   cngMsg = "YYYYMMDD HH24:MI:SS_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 
	 
	 else 	 if	 (dt.matches("^([0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$")) {
		   if(type.equals("1")) {
//			   System.out.println("bulkDt 변경전 : " + bulkDt);
//			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])\\-([0][1-9]|[1][012])\\-([0-2][0-9]|[3][01]|[1-9])\\s([1-9]|[01][0-9]|[2][0-3])\\:([0-5][0-9])\\:([0-5][0-9])$",    "$1-$2-$3 $4:$5:$6");
			    bulkDt = dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[\\\\.|\\\\s|,|\\\\-|초|_|/|:|　|·|\\\\|]*$",    "$1-$2-$3 $4:$5:$6");
//			    System.out.println("bulkDt 변경후 : " + bulkDt);
			   
//			    System.out.println(bulkDt.substring(0,2));
//			    System.out.println(bulkDt.substring(3,5));
//			    System.out.println(bulkDt.substring(6,8));
//			    System.out.println(bulkDt.substring(9));
			    
			    
			    
			    String date1 = "20"+bulkDt.substring(0,2);
			    String date2 = bulkDt.substring(3,5);
			    String date3 = bulkDt.substring(6,8);
			    String date4 = bulkDt.substring(9);
			    
//			    System.out.println("날짜 : " + date1 + "-" + date2  + "-" + date3 + " " + date4);
			    
			    bulkDt = date1 + "-" + date2  + "-" + date3 + " " + date4;
			    
			    
			    System.out.println("bulkDt : "+bulkDt);
			    
			   cngMsg = "YYYYMMDD HH24:MI:SS_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	 else if (dt.matches("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*(\\([월|화|수|목|금|토|일]\\))*\\s*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*$")) { 
	   	   
		   if(type.equals("2") ||type.equals("3")) {
			   bulkDt =   dt.replaceAll("^([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*(\\([월|화|수|목|금|토|일]\\))*\\s*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*$",    "$1-$2-$3-$5-$6");

			   String[] tmp = bulkDt.split("-");
			    
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    if( tmp[3].length() ==1 ) tmp[3] = "0" + tmp[3]; 
			    if( tmp[4].length() ==1 ) tmp[4] = "0" + tmp[4]; 
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[3] + ":" + tmp[4];
			    
			   
			   cngMsg = "YYYYMMDDHH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   } 
	 
	 else if (dt.matches("^[‘|’|'|`]([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*(오전|오후)\\s*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[‘|’|'|`]$")) { 
	   	   
		   if(type.equals("1") ||type.equals("2") ||type.equals("3")) {
			   bulkDt =   dt.replaceAll("^[‘|’|'|`]([12][0-9][0-9][0-9])[\\.|\\s|,|\\-|년|_|/|:|　|·]*([0][1-9]|[1][012]|[1-9])[\\.|\\s|,|\\-|월|_|/|:|　|·]*([0-2][0-9]|[3][01]|[1-9])[\\.|\\s|,|\\-|일|_|/|:|　|·|\\|]*(오전|오후)\\s*([1-9]|[01][0-9]|[2][0-3])[\\.|\\s|,|\\-|시|_|/|:|　|·|;]*([0-5][0-9])[\\.|\\s|,|\\-|분|_|/|:|　|·|\\|]*([0-5][0-9])[‘|’|'|`]$",    "$1-$2-$3-$4-$5-$6-$7");

			   String[] tmp = bulkDt.split("-");
			    
			   	int past = 0; 
			    if( tmp[1].length() ==1 ) tmp[1] = "0" + tmp[1];
			    if( tmp[2].length() ==1 ) tmp[2] = "0" + tmp[2];
			    
			    if( tmp[3].equals("오전") ) {
			    	past = 0;  
			    }
			    else {
			    	past = 12;
			    }
			    
			    if( tmp[4].length() ==1 ) {
			    	
			     
			    	
			    	tmp[4] = past + Integer.parseInt(tmp[4]) + "";
			    	
			    }
			    else{
			    	
			    	int tmp1 = Integer.parseInt(tmp[4]);
			    	if( tmp1 < 13) {
			    		tmp[4] = past + Integer.parseInt(tmp[4]) + "";
			    	}
			    }
			   
			    
			    
			    if( tmp[5].length() ==1 ) tmp[5] = "0" + tmp[5]; 
			    if( tmp[6].length() ==1 ) tmp[6] = "0" + tmp[6];  
			    
			    bulkDt = tmp[0] +"-"  + tmp[1] + "-" + tmp[2] + " " + tmp[4] + ":" + tmp[5]+  ":" + tmp[6] ;
			    
			   
			   cngMsg = "YYYYMMDDHH24MI_변경" +"::" + orgDt + "::" + bulkDt;
			   
			   if (bulkDt.equals(orgDt)) {
				   cngMsg= "";
			   }
		   }
		   else {
			   errMsg = "날짜_변경_불가";
		   }
	   }
	 
	   else {
		   errMsg = "날짜타입_변경_불가";
		   bulkDt =   orgDt;
	   }
	   
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgDt);
		map.put("cng", bulkDt);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}	
}

