import org.junit.Assert;
import org.junit.Test;

import application.login;
import junit.framework.TestCase;

public class plusTest extends TestCase 
{
	@Test
	public void testPlus() 
	{
		login testLogin = new login();
		Assert.assertEquals(testLogin.checkUser("shengwei","zxcvzxcv") , "");
	}
}
