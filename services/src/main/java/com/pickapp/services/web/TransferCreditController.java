package com.pickapp.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pickapp.dto.ResultDTO;
import com.pickapp.dto.TransferDTO;
import com.pickapp.services.model.TransferCredit;
import com.pickapp.services.model.User;
import com.pickapp.services.service.TransferCreditService;
import com.pickapp.services.service.UserService;
import com.pickapp.util.Constants;
import com.pickapp.util.TokenUtil;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("transferCredit")
public class TransferCreditController {

	@Autowired
	private TransferCreditService transferCreditService;
	@Autowired
	private UserService userService;

	@PostMapping()
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResultDTO pay(@RequestBody TransferDTO transferDTO, @RequestHeader(value = "Authorization") String token) {
		ResultDTO result = new ResultDTO();
		try {
			Claims claims = TokenUtil.decodeJWT(token);
			String email = claims.get(Constants.EMAIL).toString();
			User userFrom = userService.findByEmail(email);
			if (userFrom.getCredit().doubleValue() >= transferDTO.getCredit().doubleValue()) {
				User userTo = userService.findByEmail(transferDTO.getEmailUserTo());
				if(userTo != null) {
					TransferCredit transferCredit = new TransferCredit();
					transferCredit.setCredit(transferDTO.getCredit());
					transferCredit.setUserFrom(userFrom);
					transferCredit.setUserTo(userTo);
					transferCreditService.save(transferCredit);
					userFrom.setCredit(userFrom.getCredit().subtract(transferCredit.getCredit()));
					userService.saveNewUser(userFrom);
					userTo.setCredit(userTo.getCredit().add(transferCredit.getCredit()));
					userService.saveNewUser(userTo);
					result.setCode(Constants.SUCCESS_CODE);
					result.setMessage("SUCCESS");
				} else {
					result.setCode(Constants.ERROR_CODE);
					result.setMessage("User not exist");
				}
				
			} else {
				result.setCode(Constants.ERROR_CODE);
				result.setMessage("Insufficient Credit");
			}

		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
