package com.shop.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.shop.model.dto.Product;

public class XmlReader {

	public XmlReader() {
		
	}
	
	public List<Product> getProductsList(ExternalContext ec) {
		
		SAXBuilder builder = new SAXBuilder();
		String path = ec.getRealPath("/");
		File file = new File(path + "resources\\xml\\shop.xml");
		
		System.out.println("HEEEEEEEEEEEEHEHE " + path + "resources\\xml\\shop.xml");
		
		List<Product> productList = new ArrayList<Product>();
		
		try {
			Document document = (Document)builder.build(file);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("product");
			
			System.out.println("SIZELIST: " + list.size());
			
			for(int i = 0; i < list.size(); i++) {
				System.out.println("LISTA");
				 Element node = (Element) list.get(i);
				 
				 Product product = new Product();
				 product.setName(node.getChildText("name"));
				 product.setCategory(node.getChildText("category"));
				 product.setPrice(Integer.parseInt(node.getChildText("price")));
				 product.setQuantity(Integer.parseInt(node.getChildText("quantity")));
				 
				 productList.add(product);
			}
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
	    } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		
		return productList;
	}
}
