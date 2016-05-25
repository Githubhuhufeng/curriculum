import org.junit.Assert;
import org.junit.Test;

import application.Login;
import junit.framework.TestCase;

public class LoginTest extends TestCase 
{
	@Test
	public void testAdd() 
	{
		Login testLogin = new Login();
		
		//check for equality
		Assert.assertEquals(testLogin.checkUser("xxxxx","xxxx") , "");
		//assertEquals(testLogin.checkUser("1","1") , "9");
		//assertEquals(testLogin.checkUser("1","1") , "");
		
		//check for false condition
		//assertFalse(num > 6);
		
		//check for not null value
		//assertNotNull(str);
	}
}
