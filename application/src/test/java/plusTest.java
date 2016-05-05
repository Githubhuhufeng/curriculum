

import org.junit.Test;

import application.login;
import junit.framework.TestCase;

public class loginTest extends TestCase 
{
	@Test
	public void testPlus() 
	{
		login testLogin = new login();
		assertEquals(testLogin.checkUser("shengwei","zxcvzxcv") , "");
	}
}
