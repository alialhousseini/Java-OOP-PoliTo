package hydraulic;

import java.util.Arrays;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	
	private String name;
	
	private Element input;
	
	//private Element output;
	private Element[] outputs;
	
	
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		this.name=name;
		outputs = new Element[1];
	}

	Element(String name, int numOutputs){
		this.name=name;
		outputs = new Element[numOutputs];
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		outputs[0] = elem;
		if(elem!=null)
			elem.setInput(this);
	}
	
	public void connect(Element elem, int index){
		outputs[index] = elem;
		if(elem!=null)
			elem.setInput(this);
	}
	
	public Element getInput()
	{
		return input;
	}
	
	public void setInput(Element elem)
	{
		input = elem;
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		return outputs[0];
	}

	public Element[] getOutputs(){
		return outputs;
	}
	
	/**
	 * Replace and output element with a new one.
	 * 
	 * Returns {@code true} when element {@code e} was
	 * found an thus replacement was possible; 
	 * {@code false} otherwise. 
	 * 
	 * @param e the element to be replaced
	 * @param r the replacement element
	 * @return success of replacement operation.
	 */
	boolean replaceWith(Element e, Element r) {
		for(int i=0; i<outputs.length; ++i) {
			if(outputs[i] == e) {
				outputs[i] = r;
				if( r != null ) {
					r.setInput(this);
				}
				return true;
			}
		}
		return false;
	}
	
	protected void simulate(double inFlow, SimulationObserver observer) {
		simulate(inFlow,new SimulationObserverExt() {
			@Override
			public void notifyFlow(String type, String name, double inFlow, double... outFlow) {
				observer.notifyFlow(type, name, inFlow, outFlow);
			}
			@Override
			public void notifyFlowError(String type, String name, double inFlow, double maxFlow) {
				// ignore notification
			}
		},false);
	}

	protected abstract void simulate(double inFlow, SimulationObserverExt observer, boolean enableMaxFlowChecks);

	
	protected abstract StringBuffer layout(String padding);
	
	/**
	 * Returns a string consisting of 'n' blanks
	 * 
	 * @param n number of blanks to generate
	 * @return
	 */
	protected static String blanks(int n) {
		char[] res = new char[n];
		Arrays.fill(res, ' ');
		return new String(res);
	}

}
