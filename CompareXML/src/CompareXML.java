import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.xml.sax.SAXException;
import org.custommonkey.xmlunit.*;


public class CompareXML {

	public static void main(String[] args) throws FileNotFoundException, 
    SAXException, IOException {
		// TODO Auto-generated method stub

        FileInputStream file1 = new FileInputStream("C:/task/taskPath/source.xml");
        FileInputStream file2 = new FileInputStream("C:/task/taskPath/target.xml");
     
        BufferedReader source = new BufferedReader(new InputStreamReader(file1));
        BufferedReader target = new BufferedReader(new InputStreamReader(file2));
     
        XMLUnit.setIgnoreWhitespace(true);
     
        List <Difference> differences = compareXML(source, target);
     
        printDifferences(differences);
   
    }    
	
	public static void printLine(int l)
	{
		for(int i=0; i<l; i++)
		System.out.print("_");
		System.out.println();
	}
 
    private static List compareXML(Reader source, Reader target) throws SAXException, IOException {
		
    	Diff xmlDiff = new Diff(source, target);
    	DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
    	return detailXmlDiff.getAllDifferences();
    	
    }

	public static void printDifferences(List <Difference> differences){
        int totalDifferences = differences.size();

        System.out.println("Total differences : " + totalDifferences);
        printLine(10);
        System.out.printf("V1", "V2", "P");
        printLine(10);
        
        
        for(Difference difference: differences){
        	String F1 = difference.getTestNodeDetail().getValue();
        	String F2 = difference.getControlNodeDetail().getValue();
        	String Path = difference.getControlNodeDetail().getXpathLocation();
        	System.out.printf(F1, F2, Path);
            
        }
        printLine(10);
    }
}

