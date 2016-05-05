import org.junit.Assert;
import org.junit.Test;

import application.login;
import junit.framework.TestCase;

public class loginTest extends TestCase 
{
	@Test
	public void testAdd() 
	{
		login testLogin = new login();
		
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
