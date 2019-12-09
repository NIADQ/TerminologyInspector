package application.util;

import java.util.HashMap;
import java.util.Map;

public class ChangeBizNo {
	String bulkNum;
	String orgNum;
	String cngMsg;
	String errMsg;
	
	Map <String, String> resultMap = null ; 
	public ChangeBizNo(String _bulkNum) {
		this.orgNum = _bulkNum;
		
		this.bulkNum = _bulkNum.replaceAll("\\)", "");
		this.bulkNum = _bulkNum.replaceAll(",", "");
		this.bulkNum = _bulkNum.replaceAll(" ", "");
		this.bulkNum = _bulkNum.replaceAll("()", "");
		this.bulkNum = _bulkNum.replaceAll("-", "");
		
		
		
		resultMap = FindBizNo(this.bulkNum);
	}
	public Map<String, String>  getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, String>  resultMap) {
		this.resultMap = resultMap;
	}
	
	public Map<String, String>  FindBizNo(String bizNo) {
		errMsg = "";
		cngMsg ="";
		if (bizNo.matches("\\b[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
			bulkNum =  bizNo.replaceAll("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3");
			
			if(this.orgNum.equals(bulkNum)) {
				cngMsg = "";
				
			}else {
				cngMsg = "사업자번호변경::"+this.orgNum+"::"+this.bulkNum;
				
			} 
		}
		else {
			bulkNum = orgNum;
			errMsg =  "사업자번호포맷아님"; 
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("org", orgNum);
		map.put("cng", bulkNum);
		map.put("errMsg", errMsg);
		map.put("cngMsg", cngMsg);
		
		return map;
		
	}
}
