/**
 * 
 */
package com.sid.tutorials.springboot.batch.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.CustomErrorItem;
import com.sid.tutorials.springboot.batch.customException.MyCustomException;

/**
 * @author kunmu
 *
 */
@Component
public class CustomSkipListener /*implements SkipListener<Car, Number>*/ {

	@Autowired
	private CustomErrorItem customErrorItem;

	@OnSkipInRead
	public void onSkipInRead(Throwable t) {
		System.out.println("===============>This is the CustomSkipListener class error section Skip Read !!!!");
	}

	@OnSkipInWrite
	public void onSkipInWrite(Number item, Throwable t) {
		System.out.println("===============>This is the CustomSkipListener class error section Skip Write !!!!");
	}

	@OnSkipInProcess
	public void onSkipInProcess(Car item, Throwable throwable) {
		System.out.println("===============>This is the CustomSkipListener class error section !!!!");
		if (throwable instanceof MyCustomException) {
			MyCustomException customException = (MyCustomException) throwable;
			customErrorItem.setErrorCars(item, customException.getMessage());
			System.out.println(
					"===============>This is the CustomSkipListener class error section number of error cars is : "
							+ customErrorItem.getErrorCars().keySet().size());
		}
	}
}
