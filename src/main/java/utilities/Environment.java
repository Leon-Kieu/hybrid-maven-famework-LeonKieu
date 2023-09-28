package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config{

	@Key("App.url")
	String appUrl();
	
	@Key("Admin.url")
	String adminUrl();
	
	@Key("admin.user")
	String adminUser();
	
	@Key("admin.pass")
	String adminPass();
}
