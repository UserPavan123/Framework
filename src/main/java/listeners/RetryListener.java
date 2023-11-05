package listeners;
import java.lang.reflect.Constructor;
/**
 * @author Yugandhar Reddy 
 *         Listener that re try's the failed tests during the same
 * 
 */
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import utilities.RetryAnalyzer;
public class RetryListener implements IAnnotationTransformer
{
	@Override
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
			Method testMethod)
	{
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
		/*if (retry == null)
		{*/
			System.out.println("in retry listener");
			testannotation.setRetryAnalyzer(RetryAnalyzer.class);
		//}
	}
}
