package community.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.bind.DataBinding;
import community.bind.ServletMultiDataBinder;
import community.bind.ServletRequestDataBinder;
import community.context.ApplicationContext;
import community.controller.AjaxController;
import community.controller.Controller;
import community.dto.PageInfo;
import community.listener.ContextLoaderListener;
import community.vo.Reply;

// 페이지 컨트롤러를 찾을 때 ApplicationContext의 사용
@SuppressWarnings("serial")
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();

			// 페이지 컨트롤러에게 전달할 Map 객체를 준비한다.
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			model.put("request", request);
			
			//AjaxController
			if(ctx.getBean(servletPath) instanceof AjaxController) {
				AjaxController ajax = (AjaxController) ctx.getBean(servletPath);
				model.put("response", response);
				
				if(request.getParameter("email") != null) {
					model.put("email", request.getParameter("email"));
				} else if (request.getParameter("nickname") != null) {
					model.put("nickname", request.getParameter("nickname"));
				} else if (request.getParameter("info") != null) {
					model.put("info", request.getParameter("info"));
				} else if (request.getParameter("send_email") != null) {
					model.put("send_email", request.getParameter("send_email"));
				} else if (request.getParameter("vote") != null) {
					model.put("vote", request.getParameter("vote"));
				} else if (request.getParameter("history") != null) {
					model.put("history", request.getParameter("history"));
				} else if (request.getParameter("pageNum") != null) {
					model.put("pageInfo", new PageInfo(Integer.parseInt(request.getParameter("pageNum")), Integer.parseInt(request.getParameter("amount")))
													   .setSort(request.getParameter("sort"))
													   .setType(request.getParameter("type"))
													   .setKeyword(request.getParameter("keyword")));
				} else if (request.getParameter("master") != null) {
					model.put("reply", new Reply().setBno(Integer.parseInt((String) request.getParameter("bno")))
												  .setMaster(Integer.parseInt((String) request.getParameter("master")))
												  .setMno(Integer.parseInt((String) request.getParameter("mno")))
												  .setWriter((String) request.getParameter("writer"))
												  .setContent((String) request.getParameter("content")));
				} else if (request.getParameter("rno") != null) {
					model.put("rno", request.getParameter("rno"));
				} else if (request.getParameter("rno2") != null) {
					model.put("rno2", request.getParameter("rno2"));
				} else if (request.getParameter("bno") != null) {
					model.put("bno", request.getParameter("bno"));
				}
					
				ajax.execute(model);
				return;
			}

			Controller pageController = (Controller) ctx.getBean(servletPath);
			if (pageController == null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			}

			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding) pageController);
			}

			// 페이지 컨트롤러를 실행한다.
			String viewUrl = pageController.execute(model);

			// Map 객체에 저장된 값을 ServletRequest에 복사한다.
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}

			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		
		for (int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i + 1];
			boolean isMulti = dataType.getName().equals("com.oreilly.servlet.MultipartRequest");
			boolean isPost = request.getMethod().equals("POST");
			
			if(isMulti) {
				if(isPost) {
					dataObj = ServletMultiDataBinder.bind(request, dataType, dataName);
					i += 2;
				} else {
					dataObj = null;
				}
			} else {
				dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			}
			model.put(dataName, dataObj);
		}
	}
}