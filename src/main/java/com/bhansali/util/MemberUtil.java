package com.bhansali.util;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberUtil {

//	 @Autowired
//	 GeneralService generalService;
//	
//	public List<Person> getPersons(List<Member> members) {
//			
//			List<Person> persons = new ArrayList<>();
//			for(Member member: members) {
//					persons.add(member.getPerson());
//			}
//			
//			return persons;
//		}
//  	
//	public List<Member> getMembersByFirstName(String firstName, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getFirstName().equalsIgnoreCase(firstName)) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByMiddleName(String middleName, List<Member> members) {
//			
//			List<Member> filtered = new ArrayList<>();
//			for(Member member: members) {
//				if(member.getPerson().getMiddleName().equalsIgnoreCase(middleName)) {
//					filtered.add(member);
//				}
//			}
//			
//			return filtered;
//		}
//	
//	public List<Member> getMembersByLastName(String lastName, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getLastName().equalsIgnoreCase(lastName)) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByNickName(String nickName, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getNickName().equalsIgnoreCase(nickName)) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByGender(int id, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getGender().getId() == id) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByGender(String name, List<Member> members) {
//			
//		Gender gender = generalService.getGender(name);
//		return gender != null ? getMembersByGender(gender.getId(), members) : members;
//	}
//
//	public List<Member> getMembersByDate(int date, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getDob().getDate() == date) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByDay(String day, List<Member> members) {
//		
////		List<Member> filtered = new ArrayList<>();
////		for(Member member: members) {
////			if(member.getEducation().getCourse().getId() == id) {
////				filtered.add(member);
////			}
////		}
////		
////		return filtered;
//		return members;
//	}
//	
//	public List<Member> getMembersByMonth(int month, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getDob().getMonth() == month) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByYear(int year, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getPerson().getDob().getYear() == year) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//
//	public List<Member> getMembersByGautra(int id, List<Member> members) {
//			
//			List<Member> filtered = new ArrayList<>();
//			for(Member member: members) {
//				if(member.getGautra().getId() == id) {
//					filtered.add(member);
//				}
//			}
//			
//			return filtered;
//		}
//	
//	public List<Member> getMembersByGautra(String name, List<Member> members) {
//		
//		Gautra gautra = generalService.getGautra(name);
//		return gautra != null ? getMembersByGautra(gautra.getId(), members) : members;
//	}
//	
//	public List<Member> getMembersByCaste(int id, List<Member> members) {
//		
////		List<Gautra> gauytras =
////		List<Member> filtered = new ArrayList<>();
////		for(Member member: members) {
////			if(member.getEducation().getC.getId() == id) {
////				filtered.add(member);
////			}
////		}
////		
////		return filtered;
//		return members;
//	}
//	
//	public List<Member> getMembersByCaste(String name, List<Member> members) {
//		
//		Caste caste = generalService.getCaste(name);
//		return caste != null ? getMembersByCaste(caste.getId(), members) : members;
//	}
//	
//	public List<Member> getMembersByZip(int zip, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		
//		for(Member member: members) {
//			Location location = member.getLocation();
//			
//			if( location != null && location.getCurrentAddress() != null && location.getCurrentAddress().getZip() == zip
//				|| location.getPermanentAddress() != null && location.getPermanentAddress().getZip() == zip
//				|| location.getWorkPlace() != null && location.getWorkPlace().getZip() == zip )
//			{
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersBySoil(int id, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			if(member.getLocation() != null && member.getLocation().getOrigin() != null && member.getLocation().getOrigin().getId() == id) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersBySoil(String name, List<Member> members) {
//			
//			Soil soil = generalService.getSoil(name);
//			return soil != null ? getMembersBySoil(soil.getId(), members) : members;
//		}
//
//	public List<Member> getMembersByCourse(int id, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			Education education = member.getEducation();
//			Course course = education != null ? education.getCourse() : null;
//			
//			if(course != null && course.getId() == id) {
//				filtered.add(member);
//			}
//		}
//		
//		return filtered;
//	}
//	
//	public List<Member> getMembersByCourse(String name, List<Member> members) {
//		
//		Course course = generalService.getCourse(name);
//		return course != null ? getMembersByCourse(course.getId(), members) : members;
//	}
//	
//	public List<Member> getMembersByInstitute(int id, List<Member> members) {
//		
//		List<Member> filtered = new ArrayList<>();
//		for(Member member: members) {
//			Education education = member.getEducation();
//			if(education != null && education.getInstitute().getId() == id) {
//				filtered.add(member);
//			}
//		}
//		System.out.println("Members " + members.size());
//		return filtered;
//	}
//	
//	public List<Member> getMembersByInstitute(String name, List<Member> members) {
//			
//			Institute institute = generalService.getInstitute(name);
//			return institute != null ? getMembersByCaste(institute.getId(), members) : members;
//		}
//
//	public List<Member> getMembersByPassingYear(int passingYear, List<Member> members) {
//			
//			List<Member> filtered = new ArrayList<>();
//			for(Member member: members) {
//				Education education = member.getEducation();
//				
//				if(education != null && education.getPassingYear() == passingYear) {
//					filtered.add(member);
//				}
//			}
//			System.out.println("Members " + members.size());
//			return filtered;
//		}
//	
//	public List<Member> getMembersByOccupation(int id, List<Member> members) {
//			
//			List<Member> filtered = new ArrayList<>();
//			for(Member member: members) {
//				if(member.getOccupation() != null && member.getOccupation().getId() == id) {
//					filtered.add(member);
//				}
//			}
//			System.out.println("Members " + members.size());
//			return filtered;
//		}
//	
//	public List<Member> getMembersByOccupation(String name, List<Member> members) {
//			
//			Occupation occupation = generalService.getOccupation(name);
//			return occupation != null ? getMembersByOccupation(occupation.getId(), members) : members;
//		}
}