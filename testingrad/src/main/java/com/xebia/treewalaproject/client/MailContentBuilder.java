package com.xebia.treewalaproject.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

	@Autowired
	private TemplateEngine templateEngine;

	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(String email) {
		String template = "MailTemplate";
		Context context = new Context();
		context.setVariable("EMPLOYEE_EMAIL", email);
		String content = templateEngine.process(template, context).replace("${EMPLOYEE_EMAIL}", email);
		return content;
	}

}
