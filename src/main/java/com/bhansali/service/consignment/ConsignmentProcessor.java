package com.bhansali.service.consignment;

import org.springframework.beans.factory.annotation.Autowired;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.service.GeneralService;
import com.bhansali.util.DBUtil;
import com.bhansali.util.Lots;

public abstract class ConsignmentProcessor {

	@Autowired
	GeneralService service;

	@Autowired
	DBUtil util;

	public abstract Consignment process(Lots lots, Consignment consignment, Login login) throws Exception;
}
