package com.bhansali.service.consignment;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.util.Lots;

@Service
public class Edit extends ConsignmentProcessor {

	@Autowired
	Delete delete;

	@Autowired
	Purchase purchase;

	@Autowired
	Sell sell;

	/**
	 * 
	 * @param lots
	 * @param consignment
	 * @param login
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Consignment editConsignment(Lots lots, Consignment consignment, Login login) throws Exception {

		Consignment clonnedConsignment = consignment.getClone();

		Lots clonnedLots = lots.getClone(clonnedConsignment);

		Consignment persistedConsignment = service.getConsignment(consignment.getId());

		Consignment.Type persistedType = persistedConsignment.getType();
		persistedConsignment.setType(Consignment.Type.EDIT);
		delete.process(lots, persistedConsignment, login);

		clonnedConsignment.setPreviousConsignment(persistedConsignment);
		clonnedConsignment.setType(persistedType);

		if (login.getOrganization().getId() == clonnedConsignment.getReceiver().getId())
			return purchase.process(clonnedLots, clonnedConsignment, login);

		return sell.process(clonnedLots, clonnedConsignment, login);
	}

	@Override
	public Consignment process(Lots lots, Consignment consignment, Login login) throws Exception {

		// Need More Changes in Later Part
		// If We Edit some other Type of Consignment Like Sales_Return, Process,
		// Purchase_Return, Processed etc.
		return editConsignment(lots, consignment, login);
	}

}
