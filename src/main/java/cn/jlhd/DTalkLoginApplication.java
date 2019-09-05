package cn.jlhd;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DTalkLoginApplication {
	static  Logger logger=	LogManager.getLogger(DTalkLoginApplication.class);

	public static void main(String[] args) {
		DateTime nowDateTime = DateTime.now().withMillisOfSecond(0).withSecondOfMinute(0);
		System.out.println("nowDateTime = " + nowDateTime);
//		SpringApplication.run(DTalkLoginApplication.class, args);
//		//connectAuthCenter();
//		logger.info("------------------------------------------OperationtoolApplication is start complete!-----------------------------------------------!");
	}

//	private static void connectAuthCenter(){
//		try {
//			SYSConfig conf = SYSConfig.getCurrentConfig();
//			ConnectToUserCenter.getInstance().initContext(conf.AUTH_CENTER_URL,conf.SYS_ID,conf.SYS_KEY);
//			logger.info("user-auth-center is connected.");
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
//			logger.error("connect to user-auth-center failure! {}");
//		}
//	}
}
