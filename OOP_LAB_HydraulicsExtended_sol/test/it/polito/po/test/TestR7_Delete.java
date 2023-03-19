package it.polito.po.test;

import hydraulic.*;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import static it.polito.po.test.OOPAssertions.*;
import org.junit.Test;


public class TestR7_Delete {

	@Test
	public void testSimpleElementRemove(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		//boolean done = s.deleteElement("Tap");
		Optional<Boolean> done = callDelete(s,"Tap");

		
		assertEquals("Wrong number of elements after delete",2,s.getElements().length);
		assertTrue("Operation not performed", done.orElse(true));
	}
	
	@Test
	public void testSimpleElementsRelink(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
//		s.deleteElement("Tap");
		callDelete(s,"Tap");
		
		assertSameElement("Output not fixed after delete",sink,src.getOutput());
	}

	@Test
	public void testSinkRelink(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		//s.deleteElement("Sink");
		callDelete(s,"Sink");

		assertSameElement("Output not fixed after delete", null, tap.getOutput());
	}

	@Test
	public void testSinkAfterSplit(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");
		Tap tap = new Tap("Tap");
		Split t = new Split("T");
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(tap);
		tap.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		//boolean done = s.deleteElement("Sink 2");
		Optional<Boolean> done = callDelete(s,"Sink 2");

		
		assertTrue("Operation should be performed", done.orElse(true));
		assertEquals("Wrong number of elements after delete",4,s.getElements().length);
		assertSameElement("Output not fixed after delete", sink1, t.getOutputs()[0]);
		assertSameElement("Output not fixed after delete", null, t.getOutputs()[1]);
	}

	@Test
	public void testWithSplit(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");
		Tap tap = new Tap("Tap");
		Split t = new Split("T");
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(tap);
		tap.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		//boolean done = s.deleteElement("T");
		Optional<Boolean> done = callDelete(s,"T");

		
		assertFalse("Operation should not be performed on a connected Split", done.orElse(false));
		assertEquals("Wrong number of elements after dnied delete", 5, s.getElements().length);
	}
	
	@Test
	public void testWithSplitUnconnected(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");
		Tap tap = new Tap("Tap");
		Split t = new Split("T");
		Element sink1 = new Sink("Sink 1");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(t);
		s.addElement(sink1);
		
		src.connect(tap);
		tap.connect(t);
		t.connect(sink1,0); // only one output connected!
		
		Optional<Boolean> done = callDelete(s,"T"); //s.deleteElement("T");
		
		assertTrue("Operation should be permitted on a single-connected Split", done.orElse(true));
		assertEquals("Wrong number of elements after delete", 3, s.getElements().length);
		assertSameElement("Output not fixed after delete", sink1, tap.getOutput());

	}
	
	@Test
	public void testMultipleDeletes(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");
		Tap tap = new Tap("Tap");
		Split t = new Split("T");
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(tap);
		tap.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		Optional<Boolean> done = callDelete(s,"Sink 1"); //s.deleteElement("Sink 1");
		assertTrue("Operation should be permitted!", done.orElse(true));
		
		
		done = callDelete(s,"Sink 2"); //s.deleteElement("Sink 2");
		assertTrue("Operation should be permitted!", done.orElse(true));
		
		
		done = callDelete(s,"T"); //s.deleteElement("T");

		assertTrue("Operation should be permitted!", done.orElse(true));
		
		assertEquals("Wrong number of elements after multiple deletes", 2, s.getElements().length);
	}

	/**
	 * This method is used to indirectly call the method {@code deleteElement()}
	 * because of an ambiguity between requirements and code.
	 * 
	 * The method was declared as {@code void} in the code but described as {@code boolean}
	 * in the requirements.
	 * This wrapper method calls the method and returns an {@code Optional} containing
	 * the return value if returned or {@code empty} if the original method is {@ void}.
	 * 
	 * 
	 * @param s		the System
	 * @param name  the name of the element to be deleted
	 * @return		an optional with the return values
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private Optional<Boolean> callDelete(HSystemExt s, String name) {
		try {
			Method dm = s.getClass().getMethod("deleteElement", String.class );
			Object result = dm.invoke(s, name);
			return Optional.ofNullable((Boolean)result);
		} catch (NoSuchMethodException e) {
			fail("Cannot call method deleteElement(): NoSuchMethodException - " + e);
		} catch (IllegalAccessException e) {
			fail("Cannot call method deleteElement(): IllegalAccessException - " + e);
		} catch (IllegalArgumentException e) {
			fail("Cannot call method deleteElement(): IllegalArgumentException - " + e);
		} catch (InvocationTargetException e) {
			Throwable te = e.getCause();
			if(te instanceof RuntimeException) {
				throw (RuntimeException)te;
			}
			fail("Unexpected exception in method deleteElement(): " + te);
		}
		return Optional.empty();
	}
}
