package community.controller;

import java.util.Map;

import community.annotation.Component;

@Component("/test")
public class TestController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		return "test.jsp";
	}

}
