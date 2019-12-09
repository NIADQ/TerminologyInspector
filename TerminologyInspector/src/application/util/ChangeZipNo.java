package application.util;

import java.util.HashMap;
import java.util.Map;

public class ChangeZipNo {
	String bulkNum;
	String orgNum;
	String cngMsg;
	String errMsg;
	
	Map <String, String>  resultMap ; 
	public ChangeZipNo(String _bulkNum) {
		
		this.orgNum = _bulkNum;
		
		
		this.bulkNum = _bulkNum.replaceAll("\\)", "");
		this.bulkNum = _bulkNum.replaceAll(",", "");
		this.bulkNum = _bulkNum.replaceAll(" ", "");
		this.bulkNum = _bulkNum.replaceAll("()", "");
		this.bulkNum = _bulkNum.replaceAll("-", "");
		this.bulkNum = _bulkNum.replaceAll("\\'", "");
		this.bulkNum = _bulkNum.replaceAll("\\\'\\\' ", "");
		this.bulkNum = _bulkNum.replaceAll("\\\'\\\'", "");
		
		
		resultMap = FindZipNo(this.bulkNum);
	}
	
	public Map <String, String> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map <String, String>  resultMap) {
		this.resultMap = resultMap;
	}
	
	public Map <String, String>  FindZipNo(String zipNo) {
		errMsg = "";
		cngMsg ="";
		
		zipNo = zipNo.replaceAll("\\'", "");
		
		if (zipNo.matches("\\b[0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
			bulkNum =  zipNo.replaceAll("(\\d{3})(\\d{3})", "$1-$2");
			if( this.orgNum.equals(bulkNum)) {
				cngMsg = "";
			}else {
				cngMsg = "구우편번호변경::" +this.orgNum+"::"+this.bulkNum;	
			}
		}
		else if(zipNo.matches("\\b[0-9][0-9][0-9][0-9][0-9]\\b")) {
			
			bulkNum =  zipNo;
			if( this.orgNum.equals(bulkNum)) {
				cngMsg = "";
			}else {
				cngMsg = "신우편번호변경::" +this.orgNum+"::"+this.bulkNum;	
			}
			 
		}
		//4자리
		else if(zipNo.matches("\\b[0-9][0-9][0-9][0-9]\\b")) {
			
			bulkNum =  "0"+zipNo;
			if( this.orgNum.equals(bulkNum)) {
				cngMsg = "";
			}else {
				cngMsg = "신우편번호변경::" +this.orgNum+"::"+this.bulkNum;	
			}
			 
		}
		else {
			bulkNum = orgNum;
			errMsg = "우편번호포맷아님";
			
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgNum);
		map.put("cng", bulkNum);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		
	}
}
