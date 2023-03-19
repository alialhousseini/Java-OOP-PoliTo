package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {

	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
	}

	
	protected Split(String name, int numOutput) {
		super(name,numOutput);
	}

	
	@Override
	protected StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Split ");
		
		
		String subPad = pad + blanks(res.length()) ;
		
		res.append("+-> ").
		    append( getOutputs()[0]==null?"*":getOutputs()[0].layout(subPad+"|   ") ).
		    append("\n");
		
		res.append(subPad).append("|\n");
		
		res.append(subPad + "+-> ").
		    append( getOutputs()[1]==null?"*":getOutputs()[1].layout( subPad + "    ") );
		return res;
	}

	@Override
	protected void simulate(double inFlow, SimulationObserverExt observer, boolean enableMaxFlowChecks) {
		double outFlow = inFlow/2;

		observer.notifyFlow("Split", getName(), inFlow, outFlow, outFlow);
		if(enableMaxFlowChecks && inFlow>maxFlow)
			observer.notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		for(Element e : getOutputs()){
			e.simulate(outFlow,observer,enableMaxFlowChecks);
		}
	}
}
