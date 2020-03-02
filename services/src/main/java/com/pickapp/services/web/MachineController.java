package com.pickapp.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pickapp.dto.QrCodeKeyDTO;
import com.pickapp.dto.ResultDTO;
import com.pickapp.enums.StatusEnum;
import com.pickapp.services.model.Machine;
import com.pickapp.services.model.Transaction;
import com.pickapp.services.service.MachineService;
import com.pickapp.util.Constants;
import com.pickapp.util.Util;

@Controller
@RequestMapping("machine")
public class MachineController {

	@Autowired
	private MachineService machineService;

	@PostMapping()
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResultDTO save(@RequestBody Machine machine) {
		ResultDTO result = new ResultDTO();
		try {
			machine.setNumber(Util.genMachineCode(machineService));
			machineService.save(machine);
			result.setCode(Constants.SUCCESS_CODE);
			result.setMessage("Success");

		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@PostMapping(value = "/validate")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResultDTO validate(@RequestBody QrCodeKeyDTO qrCodeKeyDTO) {
		ResultDTO result = new ResultDTO();
		try {
			Integer transactionId = Integer.parseInt(qrCodeKeyDTO.getQrCodeKey().split("/")[5]);
			Transaction transaction = machineService.findTransactionById(transactionId);
			if ((transaction.getQrKeyOne() + "/" + transaction.getId()).equals(qrCodeKeyDTO.getQrCodeKey())) {
				transaction.setStatus(StatusEnum.CONFIRM.getValue());
				machineService.saveTransaction(transaction);
				result.setCode(Constants.SUCCESS_CODE);
				result.setMessage("SUCCESS");
			} else {
				result.setCode(Constants.ERROR_CODE);
				result.setMessage("Invalid Key");
			}

		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
