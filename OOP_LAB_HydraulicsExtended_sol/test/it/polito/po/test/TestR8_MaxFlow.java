package it.polito.po.test;

import java.util.HashMap;

import hydraulic.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestR8_MaxFlow {

	@Test
	public void testSimpleElements(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		double flow = 100.0;
		src.setFlow(flow);
		tap.setOpen(true);
		
		assertTrue("Tap should extend ElementExt", tap instanceof ElementExt);
		assertTrue("Sink should extend ElementExt", sink instanceof ElementExt);
		tap.setMaxFlow(90.0);
		sink.setMaxFlow(90.0);

		StoreObserverMF obs = new StoreObserverMF();

		s.simulate(obs,true);
		
		assertEquals("Wrong number of notifications",2,obs.getErrorCount());
		assertTrue("Missing simulation trace for element Tap",obs.containsError("Tap"));

		assertEquals("Wrong max flow of 'Tap'", 90.0, obs.maxFlowOf("Tap"), 0.01);
		assertEquals("Wrong max flow of 'Sink'", 90.0, obs.maxFlowOf("Sink"), 0.01);
	}

	@Test
	public void testSimpleElementsClosed(){
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		double flow = 100.0;
		src.setFlow(flow);
		tap.setOpen(false);

		assertTrue("Tap should extend ElementExt", tap instanceof ElementExt);
		assertTrue("Sink should extend ElementExt", sink instanceof ElementExt);

		tap.setMaxFlow(90.0);
		sink.setMaxFlow(90.0);
		
		StoreObserverMF obs = new StoreObserverMF();

		s.simulate(obs,true);
		
		assertEquals("Expected two error notifications",1,obs.getErrorCount());

		assertTrue("There was no error notification for element Tap",obs.containsError("Tap"));

		assertEquals("Wrong max flow of 'Tap'", 90.0, obs.maxFlowOf("Tap"), 0.01);
	}

	
	//---------- utility class: observer that collects a log of notification events -----------ù
	
	static class StoreObserverMF implements SimulationObserverExt {
		private static class Event {
			String type;
			String name;
			double inFlow;
			double[] outFlow;

			public Event(String type, String name, double inFlow, double... outFlow) {
				super();
				this.type = type;
				this.name = name;
				this.inFlow = inFlow;
				this.outFlow = outFlow;
			}
		}
		private HashMap<String,Event> events = new HashMap<>();
		private HashMap<String,Event> errors = new HashMap<>();

		@Override
		public void notifyFlow(String type, String name, double inFlow, double... outFlow) {
			Event evt = new Event(type.toLowerCase(),name.toLowerCase(),inFlow,outFlow);
			events.put(evt.name.toLowerCase(), evt );
		}

		@Override
		public void notifyFlowError(String type, String name, double inFlow, double maxFlow) {
			Event evt = new Event(type.toLowerCase(),name.toLowerCase(),inFlow,maxFlow);
			errors.put(evt.name.toLowerCase(), evt );
		}

		
		public boolean contains(String name) {
			return events.containsKey(name.toLowerCase());
		}


		public boolean containsError(String name) {
			return errors.containsKey(name.toLowerCase());
		}

		
		public Object getErrorCount() {
			return errors.size();
		}


		double maxFlowOf(String name) {
			if(!errors.containsKey(name.toLowerCase())) 
				fail("Could not find flow error notification for element " + name);
			return errors.get(name.toLowerCase()).outFlow[0];
		}


		void assertHasType(String name, String expectedType) {
			if(!events.containsKey(name.toLowerCase())) 
				fail("There was no simulation notification for element " + name);
			String type = events.get(name.toLowerCase()).type.toLowerCase();
			expectedType = expectedType.toLowerCase();
			assertTrue("Wrong type for element " + name + ": expected '" 
						+ expectedType + "' but was " + type,
						type.endsWith(expectedType));
		}

		double inFlowOf(String name) {
			if(!events.containsKey(name.toLowerCase())) 
				fail("There was no simulation notification for element " + name);
			return events.get(name.toLowerCase()).inFlow;
		}
		double outFlowOf(String name) {
			if(!events.containsKey(name.toLowerCase())) 
				fail("There was no simulation notification for element " + name);
			return events.get(name.toLowerCase()).outFlow[0];
		}
		double[] outFlowsOf(String name) {
			if(!events.containsKey(name.toLowerCase())) 
				fail("There was no simulation notification for element " + name);
			return events.get(name.toLowerCase()).outFlow;
		}
	}

}
