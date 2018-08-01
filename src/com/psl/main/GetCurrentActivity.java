package com.psl.main;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.psl.utilities.RESTClient;

public class GetCurrentActivity {
	
	public static class ProcessDetails{
		long processOid;
		String processName;
		String processState;
		String activityName;
		String activityStatus;
	    
		
		public long getProcessOid() {
			return processOid;
		}


		public void setProcessOid(long processOid) {
			this.processOid = processOid;
		}


		public String getProcessName() {
			return processName;
		}


		public void setProcessName(String processName) {
			this.processName = processName;
		}


		public String getProcessState() {
			return processState;
		}


		public void setProcessState(String processState) {
			this.processState = processState;
		}


		public String getActivityName() {
			return activityName;
		}


		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}


		public String getActivityStatus() {
			return activityStatus;
		}


		public void setActivityStatus(String activityStatus) {
			this.activityStatus = activityStatus;
		}


		@Override
		public String toString() {
			return "ProcessDetails [processOid=" + processOid + ", processName=" + processName + ", processState="
					+ processState + ", activityName=" + activityName + ", activityStatus=" + activityStatus + "]";
		}
	}

	public static void main(String[] args) throws ParseException {
		
		List<ProcessDetails> pdList = new ArrayList<ProcessDetails>();
		
		String processOids= "1122804, 1122906, 1123801, 1124328, 1766961, 2129228, 2328224, 2678978, 3057701, 3058110, 3058293, 3676833, 3854095, 4340604, 4493735, 4497268, 4519375, 4563895, 4714358, 4719365, 4751831, 4798165, 4799726, 4799896, 5129807, 5382124, 6113083, 6148447, 6172985, 6263061, 6271578, 6290406, 6294129, 6309501, 6311586, 6311859, 6330116, 6331055, 6332702, 6344369, 6369273";
		//6085792
		String[] arrPoids = processOids.split(",");
		String URL = "http://10.122.150.35/ipp-portal/services/rest/psl-liberty/processDetailsServices/getProcessHistory";
		String httpMethod = "POST";
		
		for(String poid: arrPoids){
			System.out.println("Process OID: "+ poid);
			try{
			String body = "{\"processOid\":"+Long.parseLong(poid.trim())+",\"onlyInteractive\":false}";
			JSONObject jObj = RESTClient.restServiceCall(URL, httpMethod, body);
			
			if(jObj != null){
				
				JSONObject processDetails = (JSONObject) jObj.get("processDetails");
				String processState = (String)processDetails.get("processState");
				if(processDetails != null && processState.equalsIgnoreCase("active")|| processState.equalsIgnoreCase("interrupted")){
					
					ProcessDetails pd = new ProcessDetails();
					pd.setProcessOid(Long.parseLong((String)processDetails.get("rootProcessOid")));
					pd.setProcessName((String)processDetails.get("processName"));
					pd.setProcessState(processState);
					
					JSONArray activitiesDetails = (JSONArray)processDetails.get("activitiesDetails");
					
					
					//Get data for Results array
					for(int i=0;i<activitiesDetails.size();i++)
					{
					//Store the JSON objects in an array
					//Get the index of the JSON object and print the values as per the index
					JSONObject jsonobj_1 = (JSONObject)activitiesDetails.get(i);
					String state = (String)jsonobj_1.get("state");
					if(state.equalsIgnoreCase("suspended") || state.equalsIgnoreCase("interrupted") || state.equalsIgnoreCase("application")){
						pd.setActivityStatus(state);
						pd.setActivityName((String)jsonobj_1.get("activityName"));
					}
					}
					pdList.add(pd);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		for(ProcessDetails pd: pdList){
			System.out.println(pd);
		}
		
		

	}

	
	public static void writeToExcel(List<ProcessDetails> pd){
		
	}
}
