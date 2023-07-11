package com.webapp.boot.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	private List<PageItem> paginas;
	
	private int totalPaginas;
	private int elementosPaginas;
	private int paginaActual;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		elementosPaginas = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber();
		
		int desde, hasta;
		if(totalPaginas <= elementosPaginas) {
			desde = 0;
			hasta = totalPaginas;
		}else {
			if(paginaActual <= elementosPaginas/2) {
				desde = 0;
				hasta = elementosPaginas;
			}else if (paginaActual >= totalPaginas - elementosPaginas/2) {
				desde = totalPaginas - elementosPaginas + 1;
				hasta = elementosPaginas;
			}else {
				desde = paginaActual - elementosPaginas / 2;
				hasta = elementosPaginas;
			}
		}
		
		for(int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i ));
		}
		
		
	}

		public String getUrl() {
			return url;
		}
	
		public List<PageItem> getPaginas() {
			return paginas;
		}
	
		public int getTotalPaginas() {
			return totalPaginas;
		}
	
		public int getPaginaActual() {
			return paginaActual;
		}
		
		public boolean isFirst() {
			return page.isFirst();
		}
		
		public boolean isLast() {
			return page.isLast();
		}
		
		public boolean isHasNext() {
			return page.hasNext();
		}
		
		public boolean isHasPrevious() {
			return page.hasPrevious();
		}
		
}
