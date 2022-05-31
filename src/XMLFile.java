import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLFile implements StudentsStorer{
    public XMLFile(){}
    public ArrayList<Student> readStudents(){
        File file = new File("students.xml");
        ArrayList<Student>students=new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("student");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    int nr_matricol = Integer.parseInt(eElement.getElementsByTagName("nr_matricol").item(0).getTextContent());
                    String nume = eElement.getElementsByTagName("nume").item(0).getTextContent();
                    double medie = Double.parseDouble(eElement.getElementsByTagName("medie").item(0).getTextContent());
                    Student student = new Student(nr_matricol, medie, nume);
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
