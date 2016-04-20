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
	 * 更新xml文件
	 * @param path：xml文件url
	 * @param ElementName:目标节点名称
	 * @param NodePropertyList：目标节点属性值列表
	 * @param type：addNode为添加新节点，removeNode为删除节点
	 */
	public static void UpdateXML(String path, String ElementName, List<String> NodePropertyList, String type){
		try{
			//==================打开xml文件begin=========================//
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			Document doc = docB.parse(path);
			//==================打开xml文件end=========================//
			
			int operateTimes = 0;//操作成功次数
			if("addNode".equals(type)){//添加节点
				if(NodePropertyList != null && NodePropertyList.size() > 0){
					for(String NodeProperty : NodePropertyList){
						Element elem = getElementByProperty(doc, ElementName, NodeProperty);
						if(elem == null){//说明该节点不存在
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
			}else if("removeNode".equals(type)){//删除节点
				if(NodePropertyList != null && NodePropertyList.size() > 0){
					for(String NodeProperty : NodePropertyList){
						Element elem = getElementByProperty(doc, ElementName, NodeProperty);
						if(elem != null){//说明该节点存在
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
//			newElem.setAttribute("excelTitleName", "来电号码");
//			newElem.setAttribute("property", "callRecord.callNumber");
//			testElem.getParentNode().insertBefore(newElem, testElem);
			
			if(operateTimes == NodePropertyList.size()){//所有操作都成功才会保存
				//==================保存xml文件begin=========================//
				TransformerFactory transformFac =TransformerFactory.newInstance();
				Transformer transformer = transformFac.newTransformer();
				DOMSource domSource = new DOMSource(doc);
				//设置编码类型
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				StreamResult result = new StreamResult(new FileOutputStream(path));
				transformer.transform(domSource, result);
				//==================保存xml文件end=======================//
				System.out.println("UpdateXML success");
			}else{
				System.out.println("UpdateXML fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加新节点
	 * @param doc：xml文件
	 * @param elem:目标节点的前一节点
	 * @param ElementName:节点名称
	 * @param position：目标节点位置
	 */
	public static void AddXMLNode(Document doc, Element elem, String ElementName,int position){
		if(position > 0){
			Element newElem = doc.createElement(ElementName);
			newElem = setAttribute(newElem,position);
			elem.getParentNode().insertBefore(newElem, elem);
		}
	}
	
	/**
	 * 删除节点
	 * @param elem:目标节点
	 */
	public static void removeXMLNode(Element elem){
		if(elem != null){
			elem.getParentNode().removeChild(elem);
		}
	}
	
	/**
	 * 设置节点位置
	 * 注意：这几个字段顺序是固定的，要与前端传过来的顺序保持一致，否则会出错
	 * @param NodeProperty：节点属性值
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
	 * 根据节点属性值获取节点
	 * @param doc：xml文档
	 * @param ElementName:节点名称
	 * @param NodeProperty：节点属性值
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
	 * 设置节点属性
	 * @param elem：目标节点
	 * @param position：目标节点所在位置
	 * @return
	 */
	public static Element setAttribute(Element elem, int position){
		if(elem != null){
			switch(position){
			case 3:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "来电号码");
				elem.setAttribute("property", "callRecord.callNumber");
				break;
			case 4:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "来电对象");
				elem.setAttribute("property", "callRecord.caller");
				break;
			case 9:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "收件地址");
				elem.setAttribute("property", "recipientAddress");
				break;
			case 10:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "发件地址");
				elem.setAttribute("property", "sentAddress");
				break;
			case 29:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "回电号码");
				elem.setAttribute("property", "callBackPhoneNumber");
				break;
			case 30:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "回电对象");
				elem.setAttribute("property", "callBackPerson");
				break;
			default:
				elem.setAttribute("dataType", "string");
				elem.setAttribute("excelTitleName", "问题编码");
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
				if(brandName.equals("一年级")){
					elem.setAttribute("id", "一班");
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
			//设置编码类型
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
