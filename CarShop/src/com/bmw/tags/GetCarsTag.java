package com.bmw.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GetCarsTag extends SimpleTagSupport {
	
	public void doTag() throws JspException, IOException {
	    JspWriter out = getJspContext().getOut();
	    out.println("Hello Custom Tag!");
	}
}