package com.pickapp.services.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.pickapp.dto.ResultDTO;
import com.pickapp.services.model.User;
import com.pickapp.services.service.UserService;
import com.pickapp.util.Constants;
import com.pickapp.util.TokenUtil;
import com.pickapp.util.Util;

@Controller
@RequestMapping("public/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping()
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResultDTO login(@Param("token") String token) {
		ResultDTO result = new ResultDTO();
		try {
			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
			User user = userService.findByEmail(decodedToken.getEmail());
			if (user == null) {
				user = new User(Util.genUserCode(userService), decodedToken.getEmail(), new BigDecimal(0),
						decodedToken.getName());
				userService.saveNewUser(user);
			}
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put(Constants.CODE, user.getCode());
			claims.put(Constants.EMAIL, user.getEmail());
			String customToken = TokenUtil.createJWTWithCustomClaims("" + user.getId(), "pickapp", "pickapp",
					Constants.VALID_TOKEN_TIME, claims);
			result.setCode(Constants.SUCCESS_CODE);
			result.setMessage("Success");
			result.setToken(customToken);

		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
