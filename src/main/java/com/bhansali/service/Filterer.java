package com.bhansali.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


@Service
@Transactional
public class Filterer {

//	@Autowired
//	MemberUtil memberUtil;
//	
//	public List<Member> getFilteredMembers(Filter filter, List<Member> members ) {
//	  	
//	  	if(filter != null) {
//	  	
//	  		if(GeneralUtility.isValid(filter.getFirst()))
//	  			members = memberUtil.getMembersByFirstName(filter.getFirst(), members);
//	  		if(GeneralUtility.isValid(filter.getMiddle()))
//	  			members = memberUtil.getMembersByMiddleName(filter.getMiddle(), members);
//	  		if(GeneralUtility.isValid(filter.getLast()))
//	  			members = memberUtil.getMembersByLastName(filter.getLast(), members);
//	  		if(GeneralUtility.isValid(filter.getNick()))
//	  			members = memberUtil.getMembersByNickName(filter.getNick(), members);
//	  		
//	  		if( GeneralUtility.isValid(filter.getGender()) )
//	  			members = memberUtil.getMembersByGender(filter.getGender(), members);
//	  		
//	  		if(filter.getDate() != null)
//	  			members = memberUtil.getMembersByDate(filter.getDate(), members);
//	  		if( GeneralUtility.isValid(filter.getDay()) )
//	  			members = memberUtil.getMembersByDay(filter.getDay(), members);
//	  		if(filter.getMonth() != null)
//	  			members = memberUtil.getMembersByMonth(filter.getMonth(), members);
//	  		if(filter.getYear() != null)
//	  			members = memberUtil.getMembersByYear(filter.getYear(), members);
//	  		
//	  		if( GeneralUtility.isValid(filter.getGautra()) )
//	  			members = memberUtil.getMembersByGautra(filter.getGautra(), members);
//	  		if( GeneralUtility.isValid(filter.getCaste()) )
//	  			members = memberUtil.getMembersByCaste(filter.getCaste(), members);
//	  		
//	  		if(filter.getZip() != null)
//	  			members = memberUtil.getMembersByZip(filter.getZip(), members);
//	  		if( GeneralUtility.isValid(filter.getSoil()) )
//	  			members = memberUtil.getMembersBySoil(filter.getSoil(), members);
//	  		
//	  		if( GeneralUtility.isValid(filter.getCourse()) )
//	  			members = memberUtil.getMembersByCourse(filter.getCourse(), members);
//	  		if( GeneralUtility.isValid(filter.getInstitute()) )
//	  			members = memberUtil.getMembersByInstitute(filter.getInstitute(), members);
//	  		if(filter.getPassingYear() != null)
//	  			members = memberUtil.getMembersByPassingYear(filter.getPassingYear(), members);
//	  		
//	  		if( GeneralUtility.isValid(filter.getOccupation()) )
//	  			members = memberUtil.getMembersByOccupation(filter.getOccupation(), members);
//	  	}	
//	    return members;
//	}

}
