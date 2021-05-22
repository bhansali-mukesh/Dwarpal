package com.bhansali.view;

public class Filter {

	// Personal
	String first;
	String middle;
	String last;
	String nick;
	
	String gender; 
	
	// Date Of Birth
	Integer date;
	Integer month;
	Integer year;
	String day;
		
	// Education
	String course;
	String institute;
	Integer passingYear;
	
	// Jati
	String gautra;
	String caste;
	
	// Address
	Integer zip;
	String soil;
	
	// Occupation
	String occupation;
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public Integer getPassingYear() {
		return passingYear;
	}
	public void setPassingYear(Integer passingYear) {
		this.passingYear = passingYear;
	}
	public String getGautra() {
		return gautra;
	}
	public void setGautra(String gautra) {
		this.gautra = gautra;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getSoil() {
		return soil;
	}
	public void setSoil(String soil) {
		this.soil = soil;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
//	public Filter() {
//		this.first = "First Name";
//		this.middle = "Middle Name";
//		this.last = "Last Name";
//		this.nick = "Nick Name";
//	}
	
	public String toString() {
		return first + " " + middle + " " + last + " (" + nick + ")" + " :\t" + gender + "\n"
				+ date + "/" + month + "/" + year + " (" + day + ")\n"
				+ gautra + " (" + caste + ")\n"
				+ course + " From " + institute + " in " + passingYear + "\n"
				+ occupation + "\n"
				+ "From " + soil;
	}
}