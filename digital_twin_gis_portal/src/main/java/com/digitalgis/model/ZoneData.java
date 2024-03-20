package com.digitalgis.model;

public class ZoneData {

		private int zone_no;
		private String type_data;
		
		public ZoneData() {
			
		}

		public ZoneData(int zone_no,String type_data) {
			super();
			this.zone_no = zone_no;
			this.type_data=type_data;
		}

		
		
		public String getType_data() {
			return type_data;
		}

		public void setType_data(String type_data) {
			this.type_data = type_data;
		}

		public int getZone_no() {
			return zone_no;
		}

		public void setZone_no(int zone_no) {
			this.zone_no = zone_no;
		}
		
}
