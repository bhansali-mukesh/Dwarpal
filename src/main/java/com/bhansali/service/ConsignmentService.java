package com.bhansali.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Login;
import com.bhansali.service.consignment.ConsignmentProcessor;
import com.bhansali.service.consignment.Delete;
import com.bhansali.service.consignment.Edit;
import com.bhansali.service.consignment.Purchase;
import com.bhansali.service.consignment.Sell;
import com.bhansali.util.Lots;

@Service
public class ConsignmentService {

	@Autowired
	Sell sell;

	@Autowired
	Purchase purchase;

	@Autowired
	Delete delete;

	@Autowired
	Edit edit;

	/**
	 * Set Consignment ( Everything is consignment, ir-respective of buy, sell,
	 * process, return etc. )
	 * 
	 * @param consignment
	 * @param lots
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Consignment setConsignment(Consignment consignment, Lots lots, Login login) throws Exception {

		Consignment resultant = consignment;

		ConsignmentProcessor processor = null;

		System.out.println(" LOTS " + lots.getList().size());
		System.out.println("Consignment Type : " + consignment.getType());

		switch (consignment.getType()) {

		case SALES_RETURN:
		case PURCHASE: {
			System.out.println("PURCHASE");
			processor = purchase;
			break;
		}

		case PROCESS: {
			System.out.println("PROCESS");

			break;
		}

		case PROCESSED: {
			System.out.println("PROCESSED");

			break;
		}

		case PURCHASE_RETURN:
		case SELL: {
			System.out.println("SELL");
			processor = sell;
			break;
		}

		case DELETE: {
			System.out.println("DELETE");
			processor = delete;
			break;
		}

		case EDIT: {
			System.out.println("EDIT");
			processor = edit;

			break;
		}
		}

		try {
			resultant = processor.process(lots, consignment, login);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("setConsignment() Mein Exception Hain Ji");
			throw new Exception(e);
		}

		return resultant;
	}

	public static Consignment getBlankConsignment(Consignment older) {
		Consignment consignment = new Consignment();
		consignment.setType(older.getType());
		consignment.setPreviousConsignment(older.getPreviousConsignment());
		return consignment;
	}

}