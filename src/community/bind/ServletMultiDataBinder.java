package community.bind;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServletMultiDataBinder {
	public static Object bind(ServletRequest request, Class<?> dataType, String dataName) throws Exception {
		String saveFolder = "upload";
		String fileurl = request.getServletContext().getRealPath(saveFolder);
		String encType = "UTF-8";
		int Maxsize = 5 * 1024 * 1024; //최대 5MB
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest((HttpServletRequest) request, fileurl, Maxsize, encType, policy);
		return multi;
	}
}