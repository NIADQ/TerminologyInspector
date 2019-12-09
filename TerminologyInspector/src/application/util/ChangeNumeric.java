package application.util;

import java.util.HashMap;
import java.util.Map;

public class ChangeNumeric {
	
	String bulkNum;
	String orgNum;
	String cngMsg;
	String errMsg;
	
	Map<String, String> resultMap = null ;
	
 	
	public ChangeNumeric(String _bulkNum) {
		
		// 공백 제거 공백괄호제거, 쉼표 제거 
		this.orgNum = _bulkNum;
		System.out.println("처음꺼 : "+ _bulkNum);
		//this.bulkNum = _bulkNum.replaceAll("\\)", "");
		this.bulkNum = _bulkNum.replaceAll(",", "");
		this.bulkNum = bulkNum.replaceAll(" ", "");
		this.bulkNum = bulkNum.replaceAll("()", "");
		this.bulkNum = bulkNum.replaceAll("'", "");
		this.bulkNum = bulkNum.replaceAll("\\?", "");
		this.bulkNum = bulkNum.replaceAll("\\\'\\\' ", "");
		this.bulkNum = bulkNum.replaceAll("\\\'\\\'", "");
		
		System.out.println("뒤에꺼 : "+ this.bulkNum);
		resultMap = FindNumeric(this.bulkNum);
		//-*\d{1,3},*{1,}*\d{1,}.\d{1,}*%
		
		//\b[0-9,]*?\d{1,}.*?\d{1,}%
		//\b\-*\d{1,}.\d{1,}%
		// 숫자 
		//-\d{1,}.\d{1,}|\d{1,}.\d{1,}
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}
	
	public Map<String, String> FindNumeric(String num) {
		errMsg = "";
		cngMsg ="";
		float bfn = (float) 0.0;
		System.out.println(bulkNum);
		// 퍼센트 
		//.+?\d+%|.+?\d+\(%\)|\d%
		// 일단 퍼센트를 포함하고 있는거 부터 ... 
		if( bulkNum.matches(".*\\%.*")) {
			// 퍼센트 포함했어...
			
			// 퍼센트 포함된거는 괄호를 지우는거야. 
			bulkNum=bulkNum.replaceAll("\\(%\\)", "");
		//	bulkNum=bulkNum.replaceAll("%", "");
			
			// -13.5% , 13.5%, 13%, -13% 
			if( bulkNum.matches("(^-*\\d{1,}\\.\\d{1,}|^-*\\d{1,})%")) {
				bulkNum = bulkNum.replaceAll("(^-*\\d{1,}\\.\\d{1,}|^-*\\d{1,})%", "$1");
				
				 
				if(this.orgNum.equals(bulkNum)) {
					cngMsg = "";
					
				}else {
					cngMsg = "숫자_퍼센트:괄호제거_공백제거변경::"+this.orgNum+"::"+this.bulkNum;
					
				} 
				
			} 
			else {
				bulkNum = orgNum;
				errMsg = "숫자_퍼센트는_있지만_작업불가_항목";
				
			}
			
		}
		
		// %는 없어 근데 괄호 존재 ....
		else if( num.matches(".*\\(.*\\).*")) {
			
			// 괄호가 있다면 앞뒤로 ( )
			if( bulkNum.matches("^(\\(-*\\d{1,}\\.\\d{1,}\\)|\\(-*\\d{1,}\\))$")) {
				
				//="괄호안에 있는 문";
				 bulkNum = bulkNum.replaceAll("^\\((-*\\d{1,}\\.\\d{1,}|-*\\d{1,})\\)$", "$1");
			
				if(this.orgNum.equals(bulkNum)) {
					cngMsg = "";
					
				}else {
					cngMsg = "숫자_괄호제거변경::"+this.orgNum+"::"+this.bulkNum;
					
					
				}
				 
				
			}
//			
//			// 괄호가 중간에 한개 끝에 한개 
    		else if( bulkNum.matches("(-*\\d{1,}\\.\\d{1,}|-*\\d{1,})\\((-*\\d{1,}\\.\\d{1,}|-*\\d{1,})\\)")) {
//			 
    			
    			bulkNum = orgNum;
				errMsg = "::숫자_괄호_있지만_작업불가_항목";
//				bulkNum = bulkNum.replaceAll("^(-*\\d{1,}\\.\\d{1,}|-*\\d{1,})\\((-*\\d{1,}\\.\\d{1,}|-*\\d{1,})\\)$", "$1");
//				if(this.orgNum.equals(bulkNum)) {
//					cngMsg = "";
//					
//				}else {
//					cngMsg = "숫자_숫자이외에_별도로_뒤에_괄호안의_숫자_있음_변경::"+this.orgNum+"::"+this.bulkNum;
//					
//				}
//			
//			}
//			  else {
//				bulkNum = orgNum;
//				errMsg = "::숫자_괄호_있지만_작업불가_항목";
//				
 			}
		}
		
		// 괄호없음 숫자인지 아닌지만 판단하면 됨. 
	    else if( bulkNum.matches("^(-*)(\\d{1,}\\.\\d{1,}|\\d{1,})(개|건|원)*")) {
			bulkNum = bulkNum.replaceAll("^(-*)(\\d{1,}\\.\\d{1,}|\\d{1,}).*", "$1$2");
			if(this.orgNum.equals(bulkNum)) {
				cngMsg = "";
				
			}else {
				cngMsg = "숫자_공백제거변경::"+this.orgNum+"::"+this.bulkNum;
				
			} 
		}
		
		
		
	    else if( bulkNum.matches("^(\\.\\d{1,})")) {
			bulkNum = bulkNum.replaceAll("^(\\.\\d{1,})", "0$1");
			if(this.orgNum.equals(bulkNum)) {
				cngMsg = "";
				
			}else {
				cngMsg = "소숫점_변경::"+this.orgNum+"::"+this.bulkNum;
				
			} 
		}
		
	    else if (bulkNum.length()==0) {
	    	cngMsg = "홑따옴표_제거::"+this.orgNum+"::"+this.bulkNum;
	    }
		
	    else if (bulkNum.length()==1) {
	    	//별
	    	if(bulkNum.equals("*")) {
	    		bulkNum = bulkNum.replaceAll("\\*", "");
	    		cngMsg = "별표_제거::"+this.orgNum+"::"+this.bulkNum;
	    	}
	    	
	    	//하이픈
	    	if(bulkNum.equals("-")) {
	    		bulkNum = bulkNum.replaceAll("\\-", "");
	    		System.out.println("하이픈");
	    		cngMsg = "하이픈_제거::"+this.orgNum+"::"+this.bulkNum;
	    	}
	    	
	    	
	    }

		
		
	    else {
	    	bulkNum = orgNum;
			errMsg = "숫자_작업불가_항목";
	    }
			
			 
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgNum);
		map.put("cng", bulkNum);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}
	
	
}
