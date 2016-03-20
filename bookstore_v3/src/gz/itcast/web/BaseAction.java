package gz.itcast.web;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {
	//基础通用的Action类，具体的业务action都可以继承此类
	protected Map<String,Object> requestMap;
	protected Map<String,Object> sessionMap;
	protected Map<String,Object> applicationMap;

	public void setApplication(Map<String, Object> arg0) {
		this.applicationMap = applicationMap;
	}

	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = sessionMap;
	}

	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = requestMap;
	}


}
