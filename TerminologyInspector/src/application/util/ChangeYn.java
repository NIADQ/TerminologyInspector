package application.util;

import java.util.HashMap;
import java.util.Map;

public class ChangeYn {
	String bulkYn;
	String orgYn;
	String cngMsg;
	String errMsg;
	
	Map <String, String> resultMap = null ; 
	public ChangeYn(String _bulkYn, String userType) {
		
		this.orgYn = _bulkYn;
		 this.bulkYn = _bulkYn.replaceAll("\\)", "");
		 this.bulkYn = this.bulkYn.replaceAll(" ", "");
		 this.bulkYn = this.bulkYn.replace("_", "");
		 this.bulkYn = this.bulkYn.replace("-", "");
		 this.bulkYn = this.bulkYn.replaceAll("\\(", "");
		 this.bulkYn = this.bulkYn.replace(".", "");
		 this.bulkYn = this.bulkYn.replace("[", "");
		 this.bulkYn = this.bulkYn.replace("]", "");
		 this.bulkYn = this.bulkYn.replace("=", "");
		 
		 
	
		 
		 resultMap = FindYn(this.bulkYn, userType);
		 
	} 
	
	
	public Map<String, String>  getResultMap() {
		return resultMap;
	}


	public void setResultMap(Map<String, String>  resultMap) {
		this.resultMap = resultMap;
	}

	
	

	public Map<String, String>  FindYn(String yn, String userType) {
		
		errMsg = "";
		cngMsg ="";
		
		String[] ynStr = userType.split(":");
		
		String[] yesType = null;  //ynStr[0].split(",");
		String[] noType = null;   //ynStr[1].split(",");
	 
		
		String YesString = "";
		String NoString = "";
		
		String seperate = "|";
		
		
		if (ynStr.length > 1 ) {
			
			yesType = ynStr[0].split(",");
			noType = ynStr[1].split(",");
			for(String yes : yesType) {
				
				 
				YesString += seperate + "\\b" + yes + "\\b" ; 
			}
			

			for(String no : noType) {
				
	 
				NoString += seperate + "\\b" + no + "\\b" ; 
			}
			
		}
	
		
		
		if ( yn.matches("\\b가.*|\\b여.*|\\b유.*|.*있.*|\\by|\\bY|\\b0|\\bO|\\b○|\\bo|\\bo|\\b◦|\\b◯|합격|\\b졸업|\\bTRUE|\\btrue" +  YesString)) {
			
			bulkYn = "Y";
			if(this.orgYn.equals(bulkYn)) {
				cngMsg = "";
				
			}else {
				cngMsg = "Y값변경::"+this.orgYn+"::"+this.bulkYn;
			}
				
		} 
		else if ( yn.matches("\\b부.*|\\b아니오|\\b불.*|\\b무.*|.*없.*|\\bn|\\bN|\\bX|\\bx|\\b×|\\b☓|\\b✗|\\b✕|\\b1|\\b비.*|\\b불합격|\\b결시\\b평락|\b졸업예정|\\b기권" +  NoString)) {
			bulkYn ="N";
			if(this.orgYn.equals(bulkYn)) {
				cngMsg = "";
				
			}else {
				cngMsg = "N값변경::"+this.orgYn+"::"+this.bulkYn;
			}
		}
		else {
			bulkYn =orgYn;
			errMsg = "여부판단불가";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgYn);
		map.put("cng", bulkYn);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
	}
	 
}
