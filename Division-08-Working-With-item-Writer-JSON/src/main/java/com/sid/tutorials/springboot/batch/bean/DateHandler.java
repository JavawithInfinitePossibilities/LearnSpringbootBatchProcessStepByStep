/**
 * 
 */
package com.sid.tutorials.springboot.batch.bean;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author kunmu
 *
 */
public class DateHandler extends StdDeserializer<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateHandler() {
		this(null);
	}

	public DateHandler(Class<?> vc) {
		super(vc);
	}

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String date = jsonParser.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
