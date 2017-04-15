package com.intuit.wordprocessor;

import org.junit.Assert;
import org.junit.Test;

public class StatementProcessorTest {
	@Test
	public void testStatementProcessor(){
		StatementProcessor sp = new StatementProcessor("This is some text with %%&*% &^$$   characters");
		Assert.assertEquals(sp.getWordCount(), 8);
		Assert.assertEquals(sp.getAllCharacterCount(), 37);
		BinaryTree bt = new BinaryTree();
		for (char c : "This is some text with %%&*% &^$$   characters".toCharArray()) {
			bt.add(c);
		}
		Assert.assertEquals(bt.get('&').getCount(), 2);
	}
	@Test
	public void testStatementProcessorNull(){
		StatementProcessor sp = new StatementProcessor(null);
		Assert.assertEquals(sp.getWordCount(), 0);
		Assert.assertEquals(sp.getAllCharacterCount(), 0);
	}
}
