package utilities;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.TestNG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static utilities.InitTests.dir_path;

public class XMLCreator {

	public static void xmlCreator(ArrayList<String> testcases, String group) {
		try {
			Properties props = new Properties();
			ClassLoader loader = new XMLCreator().getClass().getClassLoader();
			InputStream input = loader.getResourceAsStream("config/testdata.properties");
			props.load(input);
			dir_path = props.getProperty("userdir");
			Integer threadCnt=Integer.parseInt(props.getProperty("thread-count"));

			System.out.println("dir path in xml creator" + dir_path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element(Suite)
			Element rootElement = doc.createElement("suite");
			Attr attr = doc.createAttribute("name");
			attr.setValue("Mercer.com Suite");
			rootElement.setAttributeNode(attr);
			//parallel
			if(threadCnt>1)
			{
			Attr attrParallel = doc.createAttribute("parallel");
			attrParallel.setValue("true");
			rootElement.setAttributeNode(attrParallel);
			Attr attrThreadCount = doc.createAttribute("thread-count");
			attrThreadCount.setValue(threadCnt.toString());
			rootElement.setAttributeNode(attrThreadCount);
			}
			doc.appendChild(rootElement);
			
		
			// Listeners
			Element listeners = doc.createElement("listeners");
			rootElement.appendChild(listeners);

			// Listener 6
			Element listener6 = doc.createElement("listener");
			Attr listener_attr6 = doc.createAttribute("class-name");
			listener_attr6.setValue("listeners.InitReports");
			listener6.setAttributeNode(listener_attr6);
			listeners.appendChild(listener6);
			// Listener 5
			Element listener5 = doc.createElement("listener");
			Attr listener_attr5 = doc.createAttribute("class-name");
			listener_attr5.setValue("listeners.RetryListener");
			listener5.setAttributeNode(listener_attr5);
			listeners.appendChild(listener5);
			// Listener 4
			Element listener4 = doc.createElement("listener");
			Attr listener_attr4 = doc.createAttribute("class-name");
			listener_attr4.setValue("listeners.DataTransformer");
			listener4.setAttributeNode(listener_attr4);
			listeners.appendChild(listener4);
			// Listener 1
			Element listener1 = doc.createElement("listener");
			Attr listener_attr1 = doc.createAttribute("class-name");
			listener_attr1.setValue("atu.testng.reports.listeners.ATUReportsListener");
			listener1.setAttributeNode(listener_attr1);
			listeners.appendChild(listener1);
			// Listener 2
			Element listener2 = doc.createElement("listener");
			Attr listener_attr2 = doc.createAttribute("class-name");
			listener_attr2.setValue("atu.testng.reports.listeners.ConfigurationListener");
			listener2.setAttributeNode(listener_attr2);
			listeners.appendChild(listener2);
			// Listener 3
			Element listener3 = doc.createElement("listener");
			Attr listener_attr3 = doc.createAttribute("class-name");
			listener_attr3.setValue("atu.testng.reports.listeners.MethodListener");
			listener3.setAttributeNode(listener_attr3);
			listeners.appendChild(listener3);
			for (int i = 0; i < testcases.size(); i++) {
				// test element
				Element test = doc.createElement("test");
				Attr test_attr = doc.createAttribute("name");
				test_attr.setValue("" + testcases.get(i));
				test.setAttributeNode(test_attr);
				rootElement.appendChild(test);

				// Classes element
				Element classes = doc.createElement("classes");
				test.appendChild(classes);

				// class element
				Element Class = doc.createElement("class");
				Attr class_attr = doc.createAttribute("name");
				if(group.contains("rest"))
					class_attr.setValue("services." + group + ".testcases." + testcases.get(i));
				else
				class_attr.setValue("ui." + group + ".testcases." + testcases.get(i));
				Class.setAttributeNode(class_attr);
				classes.appendChild(Class);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			// System.out.println(input);
			StreamResult result = new StreamResult(new File(dir_path + "//Scheduler.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

			// Running the xml file
			// Create object of TestNG Class
			TestNG runner = new TestNG();

			// Create a list of String
			List<String> suitefiles = new ArrayList<String>();

			// Add xml file which you have to execute
			suitefiles.add(dir_path + "//Scheduler.xml");

			// now set xml file for execution
			runner.setTestSuites(suitefiles);

			// finally execute the runner using run method
			runner.run();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
