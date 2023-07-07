package com.webapp.boot.util.paginator;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int elementosPaginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		
		elementosPaginas = page.getSize();
	}
	
	
}
