package com.bhansali.util;

public class PersonUtil {

//	@Autowired
//    MemberService memberService;
//	
//	@Autowired
//	GeneralDao generalDao;
//	  	
//  	public List<Person> getFilteredPersons(Filter filter, List<Person> persons ) {
//	  	
//	  	if(filter != null) {
//	  		
//	  		if(filter.getFirst() != null)
//	  			persons = getPersonsByFirstName(filter.getFirst(), persons);
//	  		if(filter.getMiddle() != null)
//	  			persons = getPersonsByMiddleName(filter.getMiddle(), persons);
//	  		if(filter.getLast() != null)
//	  			persons = getPersonsByLastName(filter.getLast(), persons);
//	  		if(filter.getNick() != null)
//	  			persons = getPersonsByNickName(filter.getNick(), persons);
//	  		
//	  		if(filter.getGender() != null)
//	  			persons = getPersonsByGender(filter.getGender(), persons);
//	  		
//	  		if(filter.getDate() != null)
//	  			persons = getPersonsByDate(filter.getDate(), persons);
//	  		if(filter.getDay() != null)
//	  			persons = getPersonsByDay(filter.getDay(), persons);
//	  		if(filter.getMonth() != null)
//	  			persons = getPersonsByMonth(filter.getMonth(), persons);
//	  		if(filter.getYear() != null)
//	  			persons = getPersonsByYear(filter.getYear(), persons);
//	  		
//	  	}	
//	    return persons;
//	}
//  	
//	public static List<Person> getPersonsByFirstName(String firstName, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getFirstName().equalsIgnoreCase(firstName)) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public static List<Person> getPersonsByMiddleName(String middleName, List<Person> persons) {
//			
//			List<Person> filtered = new ArrayList<>();
//			for(Person person: persons) {
//				if(person.getMiddleName().equalsIgnoreCase(middleName)) {
//					filtered.add(person);
//				}
//			}
//			
//			return filtered;
//		}
//	
//	public static List<Person> getPersonsByLastName(String lastName, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getLastName().equalsIgnoreCase(lastName)) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public static List<Person> getPersonsByNickName(String nickName, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getNickName().equalsIgnoreCase(nickName)) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public static List<Person> getPersonsByGender(int id, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getGender().getId() == id) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Person> getPersonsByGender(String gender, List<Person> persons) {
//		
//		Gender g = generalDao.getGenderRepository().findByName(gender);
//		return g != null ? getPersonsByGender(g.getId(), persons) : persons;
//	}
//	
//	public static List<Person> getPersonsByDate(int date, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getDob().getDate() == date) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public static List<Person> getPersonsByDay(String day, List<Person> persons) {
//		
////		List<Person> filtered = new ArrayList<>();
////		for(Person person: persons) {
////			if(person.getEducation().getCourse().getId() == id) {
////				filtered.add(person);
////			}
////		}
////		
////		return filtered;
//		return persons;
//	}
//	
//	public static List<Person> getPersonsByMonth(int month, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getDob().getMonth() == month) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public static List<Person> getPersonsByYear(int year, List<Person> persons) {
//		
//		List<Person> filtered = new ArrayList<>();
//		for(Person person: persons) {
//			if(person.getDob().getYear() == year) {
//				filtered.add(person);
//			}
//		}
//		
//		return filtered;
//	}
}