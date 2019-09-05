package cn.jlhd.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import cn.jlhd.dtalk.login.AuthHelper;
import cn.jlhd.utils.JsonUtil;
import cn.jlhd.utils.RandomStringUtil;
import cn.jlhd.utils.SYSConfig;

@Controller
public class DataController {
	static Logger logger = LogManager.getLogger(DataController.class);

	static String CORP_ID = SYSConfig.getProp().getProperty("corp_id");
	static String CORP_SECRET = SYSConfig.getProp().getProperty("corp_secret");

	@RequestMapping(value = "/config", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonNode getConfig(@RequestBody String key) {
		JsonNode result = null;
		if (key.equals(SYSConfig.getCurrentConfig().VALIDATE_KEY)) {
			result = JsonUtil.getJsonNode(getDTalkConfig());
		} else {
			result = JsonUtil.getJsonNode("{ret:-1,msg:validate failure!}");
		}
		System.out.println("getConfig==result = " + result);
		return result;
	}

	@GetMapping("/userinfo")
	@ResponseBody
	public String getUserinfo(@RequestParam("code") String code) {
		String accessToken = AuthHelper.getAccessToken(CORP_ID, CORP_SECRET);
		logger.info("code:{},accessToken:{}", code, accessToken);
		String user = AuthHelper.getUserInfo(code, accessToken);
		System.out.println(code + "getUserinfo==user = " + user);

		return user;
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String getUser(@RequestParam("userid") String userid) {
		String accessToken = AuthHelper.getAccessToken(CORP_ID, CORP_SECRET);
		logger.info("userid:{},accessToken:{}", userid, accessToken);
		String user = AuthHelper.getUser(userid, accessToken);
		System.out.println(userid + "getUser==user = " + user);
		return user;
	}

	private Config getDTalkConfig() {
		long curtime = System.currentTimeMillis();
		String nonceStr = RandomStringUtil.getRandomCode(10, 6);
		String url = SYSConfig.getProp().getProperty("auth_url");

		String accessToken = AuthHelper.getAccessToken(CORP_ID, CORP_SECRET);

		String jsapiTicket = AuthHelper.getJsapiTicket(accessToken);
		String sign = AuthHelper.sign(jsapiTicket, nonceStr, curtime, url);

		SYSConfig c = SYSConfig.getCurrentConfig();
		Config conf = new Config();
		conf.setAgentId(c.AGENT_ID);
		conf.setCorpId(c.CORP_ID);
		conf.setSign(sign);
		conf.setNonceStr(nonceStr);
		conf.setTimestamp(String.valueOf(curtime));
		conf.setAccessToken(accessToken);
		return conf;
	}

	private class Config {
		String agentId;
		String corpId;
		String timestamp;
		String nonceStr;
		String sign;
		String accessToken;

		public String getAgentId() {
			return agentId;
		}

		public void setAgentId(String agentId) {
			this.agentId = agentId;
		}

		public String getCorpId() {
			return corpId;
		}

		public void setCorpId(String corpId) {
			this.corpId = corpId;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

	}
}
