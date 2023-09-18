package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config{

	@Key("App.url")
	String appUrl();
	
	@Key("App.User")
	String userName();
	
	@Key("App.Pass")
	String password();
	
	@Key("DB.host")
	String dbhost();
	
	@Key("DB.User")
	String dbuser();
	
	@Key("DB.Pass")
	String dbpass();
}
