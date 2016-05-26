import org.junit.Assert;
import org.junit.Test;

import application.Login;
import junit.framework.TestCase;

public class PlusTest extends TestCase 
{
	@Test
	public void testPlus() 
	{
		Login testLogin = new Login();
		Assert.assertEquals(testLogin.checkUser("shengwei","zxcvzxcv") , "");
	}
}
