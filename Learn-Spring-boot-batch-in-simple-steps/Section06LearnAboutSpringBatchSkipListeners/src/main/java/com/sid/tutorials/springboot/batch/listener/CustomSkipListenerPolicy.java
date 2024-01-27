/**
 * 
 */
package com.sid.tutorials.springboot.batch.listener;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

import com.sid.tutorials.springboot.batch.customException.MyCustomException;

/**
 * @author kunmu
 *
 */
@Component
public class CustomSkipListenerPolicy implements SkipPolicy {

	private static Integer MAX_SKIP_VALUE = Integer.MAX_VALUE;

	@Override
	public boolean shouldSkip(Throwable throwable, int skipCount) throws SkipLimitExceededException {
		if (throwable instanceof MyCustomException && skipCount < MAX_SKIP_VALUE) {
			return true;
		}
		return false;
	}

}
