package cn.info.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.info.model.SystemContext;

public class ThreadlocalFilter implements Filter{
	int pageSize;
	@Override
	public void destroy() {
		
	}
//每个页面都可以装进dofilter
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			int pageOffset=1;
			//init-param可以初始化参数变量pagesize5
			try{
			pageOffset=Integer.parseInt(req.getParameter("pager.offset"));
			}catch(NumberFormatException e){
				
			}
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(req, resp);
			} finally {
				SystemContext.removepageIndex();
				SystemContext.removePageSize();
		}
		
	}
	
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize=15;
		}
		
	}
	
}
