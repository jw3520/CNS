package community.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {
	public static Object bind(ServletRequest request, Class<?> dataType, String dataName) throws Exception {
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		Set<String> paramNames = request.getParameterMap().keySet();//request의 Parameter값의 Key값 모음
		Object dataObject = dataType.newInstance();
		Method m = null;
		
		for(String paramName : paramNames) {
			m = findSetter(dataType, paramName); // findSetter(vo.spms.Member-클래스타입, mname-멤버변수)
			if(m != null) {
				//invoke는 해당 메소드를 실행하라는 명령
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));
			}
		}
		return dataObject;
	}
	
	//기본 타입이면 True
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type == Integer.class ||
	       type.getName().equals("long") || type == Long.class ||
	       type.getName().equals("float") || type == Float.class ||
	       type.getName().equals("double") || type == Double.class ||
	       type.getName().equals("boolean") || type == Boolean.class ||
	       type == Date.class || type == String.class) {
			return true;
		}
		return false;
	}
	
	private static Object createValueObject(Class<?> type, String value) {
		if (type.getName().equals("int") || type == Integer.class) {
			return new Integer(value);
		} else if (type.getName().equals("float") || type == Float.class) {
			return new Float(value);
		} else if (type.getName().equals("double") || type == Double.class) {
			return new Double(value);
		} else if (type.getName().equals("long") || type == Long.class) {
			return new Long(value);
		} else if (type.getName().equals("boolean") || type == Boolean.class) {
			return new Boolean(value);
		} else if (type == Date.class) {
			return java.sql.Date.valueOf(value);
		} else {
			return value;
		}
	}
	
	private static Method findSetter(Class<?> type, String name) {
		Method[] methods = type.getMethods();
		//멤버변수(속성, 프로퍼티)
		String propName = null;
		for(Method m : methods) {
			//세터 메소드가 아니면 next
			if(!m.getName().startsWith("set")) continue;
			propName = m.getName().substring(3);
			//name과 멤버변수를 비교한 뒤 해당 세터를 반환
			if(propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}
}