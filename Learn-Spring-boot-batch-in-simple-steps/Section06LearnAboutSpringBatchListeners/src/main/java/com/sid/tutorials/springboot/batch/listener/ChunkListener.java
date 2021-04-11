/**
 * 
 */
package com.sid.tutorials.springboot.batch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * @author Lenovo
 *
 */
@Component
public class ChunkListener {

	@BeforeChunk
	public void beforeChunkListener(ChunkContext chunkContext) {
		System.out.println("-------> Before Chunk execution <----------");
	}

	@AfterChunk
	public void afterChunkListener(ChunkContext chunkContext) {
		System.out.println("-------> After Chunk execution <----------");
	}
}
