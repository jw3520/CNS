package community.controller;

import java.util.Map;

import community.annotation.Component;

@Component("/auth/terms")
public class TermsController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		return "auth_terms.jsp";
	}
}