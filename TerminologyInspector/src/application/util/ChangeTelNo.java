package application.util;

import java.util.HashMap;
import java.util.Map;

public class ChangeTelNo {
	
	String bulkTelNo;
	String orgTelNo;
	String cngMsg;
	String errMsg;
	 
 


	Map <String, String>  resultMap ; 
	
	public ChangeTelNo(String _bulkTelNo) {
	     this.orgTelNo = _bulkTelNo;	 
		 this.bulkTelNo = _bulkTelNo.replaceAll("\\)", "");
		 this.bulkTelNo = this.bulkTelNo.replaceAll(" ", "");
		 this.bulkTelNo = this.bulkTelNo.replace("_", "");
		 this.bulkTelNo = this.bulkTelNo.replace("-", "");
		 this.bulkTelNo = this.bulkTelNo.replaceAll("^\\(", "");
		//  this.bulkTelNo = this.bulkTelNo.replace("----", "");
		 this.bulkTelNo = this.bulkTelNo.replace(".", "");
		 this.bulkTelNo = this.bulkTelNo.replace("[", "");
		 this.bulkTelNo = this.bulkTelNo.replace("]", "");
		 this.bulkTelNo = this.bulkTelNo.replace("=", "");
		 this.bulkTelNo = this.bulkTelNo.replace(".", "");
		 //this.bulkTelNo = this.bulkTelNo.replaceAll("[^0-9]", "");
		 

		 
		 resultMap = FindLocalArea(this.bulkTelNo);
		 
	}
 
	
	
	
	public Map<String, String> getResultMap() {
		return resultMap;
	}




	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}




	public Map <String, String>  FindLocalArea(String telNo) {
		errMsg = "";
		cngMsg =""; 
		if (telNo.length() == 5) {
			if (telNo.matches("014\\d{2}")) {
				bulkTelNo = telNo;

				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			
		    }
			else {
				errMsg ="전화번호_형식아님";
				bulkTelNo = this.orgTelNo;
			}
		}
		else if (telNo.length() == 7) {
			if (telNo.matches("(\\d{3})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(\\d{3})(\\d{4})", "$1-$2");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			else {
				errMsg ="전화번호_형식아님";
				bulkTelNo = this.orgTelNo;
			}
		}
		else if (telNo.length() == 8) {
			if(telNo.matches("(02)(\\d{6})")){
				cngMsg = "";
				errMsg = "전화번호_형식아님";
				bulkTelNo = orgTelNo;
			}
			else if(telNo.matches("(031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|010|011|012|016|017|018|019|030|050|060|070|080)(\\d{5})")){
				cngMsg = "";
				errMsg = "전화번호_형식아님";
				bulkTelNo = orgTelNo;
			}
			else if (telNo.matches("(\\d{4})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			else {
				errMsg ="전화번호_형식아님";
				bulkTelNo = this.orgTelNo;
			}
		}
		else if (telNo.length() == 9) {
			if (telNo.matches("(02)(\\d{3})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(02)(\\d{3})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			else {
				errMsg ="전화번호_형식아님";
				bulkTelNo = this.orgTelNo;
			}
		}
		else if (telNo.length() == 10) {
			// 서울 02-1234-5678 형
			if (telNo.matches("(02)(\\d{4})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(02)(\\d{4})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			// 031-123-4567  
			else if (telNo.matches("(031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|010|011|012|016|017|018|019|030|050|060|070|080)(\\d{3})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|010|011|012|016|017|018|019|030|050|060|070|080)(\\d{3})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			// 050-222-2222
			else if (telNo.matches("(050)(\\d{3})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(050)(\\d{3})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			
			
			else {
				bulkTelNo = orgTelNo;
				errMsg ="전화번호_형식아님";
			}
			
		}
		else if (telNo.length() == 11) {
			 // 031-1234-5678
			if (telNo.matches("(031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|010|011|012|016|017|018|019|030|050|060|070|080)(\\d{4})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|010|011|012|016|017|018|019|030|050|060|070|080)(\\d{4})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			// 050-222-2222
			else if (telNo.matches("(050\\d|013\\d)(\\d{3})(\\d{4})")) {
				bulkTelNo = telNo.replaceAll("(050\\d|013\\d)(\\d{3})(\\d{4})", "$1-$2-$3");
				if( this.orgTelNo.equals(bulkTelNo)) {
					cngMsg = "";
				}else {
					cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
				}
			}
			
			else {
				bulkTelNo = orgTelNo;
				errMsg ="전화번호_형식아님";
			}
		}
		else if (telNo.length() == 12) {
			 if (telNo.matches("(050\\d|013\\d)(\\d{4})(\\d{4})")) {
					bulkTelNo = telNo.replaceAll("(050\\d|013\\d)(\\d{4})(\\d{4})", "$1-$2-$3");
					if( this.orgTelNo.equals(bulkTelNo)) {
						cngMsg = "";
					}else {
						cngMsg = "전화번호변경::" +this.orgTelNo+"::"+this.bulkTelNo;	
					}
			}
			 else {
				 bulkTelNo = orgTelNo;
				 errMsg ="전화번호_형식아님";
			 }
		}
		
		else if (telNo.length()==1) {
	    	//별
	    	if(telNo.equals("*")) {
	    		bulkTelNo = telNo.replaceAll("\\*", "");
	    		cngMsg = "별표_제거::"+this.orgTelNo+"::"+this.bulkTelNo;
	    	}
	    	
	    	//하이픈
	    	if(telNo.equals("-")) {
	    		bulkTelNo = telNo.replaceAll("\\-", "");
	    		System.out.println("하이픈");
	    		cngMsg = "하이픈_제거::"+this.orgTelNo+"::"+this.bulkTelNo;
	    	}
	    	
	    	
	    }
		
		else {
			bulkTelNo = "";
			errMsg ="전화번호_형식아님";
			bulkTelNo = this.orgTelNo;
		}
		 
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgTelNo);
		map.put("cng", bulkTelNo);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		
		return  map;
	}
	
}
