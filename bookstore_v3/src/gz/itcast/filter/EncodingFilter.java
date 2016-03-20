package gz.itcast.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//请求参数编码过滤器
public class EncodingFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//POST
		req.setCharacterEncoding("utf-8");
		//创建装饰类
		MyHttpRequest myRequest = new MyHttpRequest((HttpServletRequest) req);
		//放行，装饰后的reqeust
		chain.doFilter(myRequest, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

//对request对象进行装饰
class MyHttpRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		try {
			String value = request.getParameter(name);
			if(value!=null){
				if("GET".equals(request.getMethod())){
					//手动解码
					value = new String(value.getBytes("iso-8859-1"),"utf-8");
				}
			}
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String[] getParameterValues(String name) {
		try {
			String[] values = request.getParameterValues(name);
			if(values!=null){
				if("GET".equals(request.getMethod())){
					for(int i=0;i<values.length;i++){
						//手动解码
						values[i] = new String(values[i].getBytes("iso-8859-1"),"utf-8");
					}
				}
			}
			return values;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}


