package com.test.ssm.xmlbuilder;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLBuilderTest {
	
	/**
	 * ����xml�ļ�
	 * @param path��xml�ļ�url
	 * @param ElementName:Ŀ��ڵ�����
	 * @param NodePropertyList��Ŀ��ڵ�����ֵ�б�
	 * @param type��addNodeΪ����½ڵ㣬removeNodeΪɾ���ڵ�
	 */
	public static void UpdateXML(String path, String ElementName, List<String> NodePropertyList, String type){
		try{
			//==================��xml�ļ�begin=========================//
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			Document doc = docB.parse(path);
			//==================��xml�ļ�end=========================//
			
			int operateTimes = 0;//�����ɹ�����
			if("addNode".equals(type)){//��ӽڵ�
				if(NodePropertyList != null && NodePropertyList.size() > 0){
					for(String NodeProperty : NodePropertyList){
						Element elem = getElementByProperty(doc, ElementName, NodeProperty);
						if(elem == null){//˵���ýڵ㲻����
							int position = setPositionByProperty(NodeProperty);
							elem = (Element)doc.getElementsByTagName(ElementName).item(position);
							System.out.println("elem.getAttribute('property')="+elem.getAttribute("property"));
							System.out.println("elem.position="+position);
							AddXMLNode(doc, elem, ElementName, position);
							System.out.println("elem.AddXMLNode success");
							operateTimes++;
						}else{
							System.out.println("node is exist");
						}
					}
				}
			}else if("removeNode".equals(type)){//ɾ���ڵ�
				if(NodePropertyList != null && NodePropertyList.size() > 0){
					for(String NodeProperty : NodePropertyList){
						Element elem = getElementByProperty(doc, ElementName, NodeProperty);
						if(elem != null){//˵���ýڵ����
							System.out.println("elem.getAttribute('property')="+elem.getAttribute("property"));
							removeXMLNode(elem);
							System.out.println("elem.removeXMLNode success");
							operateTimes++;
						}else{
							System.out.println("node is not exist");
						}
					}
				}
			}
			
			
//			Element testElem = (Element)doc.getElementsByTagName("column").item(3);
//			System.out.println("testElem.getNodeName()="+testElem.getNodeName());
//			System.out.println("testElem.getAttribute('property')="+testElem.getAttribute("property"));
//			
//			System.out.println("testElem.getParentNode().getNodeName()="+testElem.getParentNode().getNodeName());
//			
////			testElem.getParentNode().removeChild(testElem);
//			Element newElem = doc.createElement("column");
//			newElem.setAttribute("dataType", "string");
//			newElem.setAttribute("excelTitleName", "�������");
//			newElem.setAttribute("property", "callRecord.callNumber");
//			testElem.getParentNode().insertBefore(newElem, testElem);
			
			if(operateTimes == NodePropertyList.size()){//���в������ɹ��Żᱣ��
				//==================����xml�ļ�begin=========================//
				TransformerFactory transformFac =TransformerFactory.newInstance();
				Transformer transformer = transformFac.newTransformer();
				DOMSource domSource = new DOMSource(doc);
				//���ñ�������
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				StreamResult result = new StreamResult(new FileOutputStream(path));
				transformer.transform(domSource, result);
				//==================����xml�ļ�end=======================//
				System.out.println("UpdateXML success");
			}else{
				System.out.println("UpdateXML fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ����½ڵ�
	 * @param doc��xml�ļ�
	 * @param elem:Ŀ��ڵ��ǰһ�ڵ�
	 * @param ElementName:�ڵ�����
	 * @param position��Ŀ��ڵ�λ��
	 */
	public static void AddXMLNode(Document doc, Element elem, String ElementName,int position){
		if(position > 0){
			Element newElem = doc.createElement(ElementName);
			newElem = setAttribute(newElem,position);
			elem.getParentNode().insertBefore(newElem, elem);
		}
	}
	
	/**
	 * ɾ���ڵ�
	 * @param elem:Ŀ��ڵ�
	 */
	public static void removeXMLNode(Element elem){
		if(elem != null){
			elem.getParentNode().removeChild(elem);
		}
	}
	
	/**
	 * ���ýڵ�λ��
	 * ע�⣺�⼸���ֶ�˳���ǹ̶��ģ�Ҫ��ǰ�˴�������˳�򱣳�һ�£���������
	 * @param NodeProperty���ڵ�����ֵ
	 * @return
	 */
	public static int setPositionByProperty(String NodeProperty){
		if("callRecord.callNumber".equals(NodeProperty)){
			return 3;
		}else if("callRecord.caller".equals(NodeProperty)){
			return 4;
		}else if("recipientAddress".equals(NodeProperty)){
			return 9;
		}else if("sentAddress".equals(NodeProperty)){
			return 10;
		}else if("callBackPhoneNumber".equals(NodeProperty)){
			return 29;
		}else if("callBackPerson".equals(NodeProperty)){
			return 30;
		}
		return 0;
	}
	
	
	/**
	 * ���ݽڵ�����ֵ��ȡ�ڵ�
	 * @param doc��xml�ĵ�
	 * @param ElementName:�ڵ�����
	 * @param NodeProperty���ڵ�����ֵ
	 * @return
	 */
	 
	public static Element getElementByProperty(Document doc, String ElementName, String NodeProperty){
		if(doc != null && NodeProperty != null){
			NodeList nodeList = doc.getElementsByTagName(ElementName);
			for(int i=0;i<nodeList.getLength();i++){
				Element elem = (Element)nodeList.item(i);
				if(NodeProperty.equals(elem.getAttribute("property"))){
					return elem;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * ���ýڵ�����
	 * @param elem��Ŀ��ڵ�
	 * @param position��Ŀ��ڵ�����λ��
	 * @return
	 */
	public static Element setAttribute(Element elem, int position){
		if(elem != null){
			switch(position){
			case 3:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�������");
				elem.setAttribute("property", "callRecord.callNumber");
				break;
			case 4:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�������");
				elem.setAttribute("property", "callRecord.caller");
				break;
			case 9:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�ռ���ַ");
				elem.setAttribute("property", "recipientAddress");
				break;
			case 10:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "������ַ");
				elem.setAttribute("property", "sentAddress");
				break;
			case 29:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�ص����");
				elem.setAttribute("property", "callBackPhoneNumber");
				break;
			case 30:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�ص����");
				elem.setAttribute("property", "callBackPerson");
				break;
			default:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "�������");
				elem.setAttribute("property", "code");
				break;
			}
		}
		return elem;
	}
	
	
	
	
	
	public static void Test(){
		try{
			String path = "src/main/resources/test.xml";
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			Document doc = docB.parse(path);
			
			NodeList nodeList = doc.getElementsByTagName("Class");
			for(int i=0;i<nodeList.getLength();i++){
				Element elem =(Element)nodeList.item(i);
				String brandName = elem.getAttribute("id");
				if(brandName.equals("һ�꼶")){
					elem.setAttribute("id", "һ��");
				}
				
				if(i==nodeList.getLength()-1){
					Element newElem = doc.createElement("Student");
					newElem.setAttribute("name", "Test");
					elem.getParentNode().appendChild(newElem);
				}
			}
			
			
			TransformerFactory transformFac =TransformerFactory.newInstance();
			Transformer transformer = transformFac.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			//���ñ�������
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			StreamResult result = new StreamResult(new FileOutputStream(path));
			transformer.transform(domSource, result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		//Test();
		String path = "src/main/resources/test.xml";
		List<String> NodePropertyList = new ArrayList<String>();
		NodePropertyList.add("callRecord.callNumber");
		NodePropertyList.add("callRecord.caller");
		NodePropertyList.add("recipientAddress");
		NodePropertyList.add("sentAddress");
		NodePropertyList.add("callBackPhoneNumber");
		NodePropertyList.add("callBackPerson");
		UpdateXML(path,"column",NodePropertyList,"removeNode");
	}
}
