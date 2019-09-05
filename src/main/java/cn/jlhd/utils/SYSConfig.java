package cn.jlhd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class SYSConfig {

	@Value("${validate_key}")
	public String VALIDATE_KEY;

	@Value("${agentid}")
	public String AGENT_ID;

	@Value("${corp_id}")
	public String CORP_ID;

	@Value("${corp_secret}")
	public String CORP_SECRET;


	public static SYSConfig getCurrentConfig() {
		return (SYSConfig) SpringContextUtils.getBeanByClass(SYSConfig.class);
	}

	private static Properties prop;

	static {
		prop = new Properties();
		try {
			InputStream ins = SYSConfig.class.getResourceAsStream("/config.properties");
			prop.load(ins);
		} catch (FileNotFoundException e) {
			System.err.println("config.properties is not found!");
		} catch (IOException e) {
			System.err.println("config.properties loading io exception!" + e);
		}
	}

	public static Properties getProp() {
		return prop;
	}

	public static Properties getPropertisFile(String path) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			System.err.println("config.properties is not found!");
		} catch (IOException e) {
			System.err.println("config.properties loading io exception!" + e);
		}
		return p;
	}

}
