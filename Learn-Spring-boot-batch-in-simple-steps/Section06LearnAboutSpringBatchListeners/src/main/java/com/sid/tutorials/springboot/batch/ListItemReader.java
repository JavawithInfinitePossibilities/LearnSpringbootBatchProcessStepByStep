/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author Lenovo
 *
 */
public class ListItemReader<T> implements ItemReader<T> {

	private List<T> list;

	/**
	 * 
	 */
	public ListItemReader() {
		super();
	}

	/**
	 * @param list
	 */
	public ListItemReader(List<T> list) {
		if (AopUtils.isAopProxy(list)) {
			this.list = list;
			System.out.println("Size of the list:" + this.list.size());
		} else {
			System.out.println("Size of the list:" + this.list.size());
			this.list = new ArrayList<T>(list);
		}
	}

	public final List<T> getList() {
		return list;
	}

	public final void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (!list.isEmpty()) {
			System.out.println("-------> Reading the object <-----------");
			return list.remove(0);
		}
		return null;
	}

}
