package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

	private double[] proportions;
	private int numOutput;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Multisplit(String name, int numOutput) {
		super(name, numOutput);
		this.numOutput = numOutput;
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		this.proportions = proportions;
	}
	
	@Override
	protected StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Multisplit ");
		
		String subPad = pad + blanks(res.length()) ;
		
		for(int i=0; i<this.numOutput; ++i) {
			if(i>0) {
				res.append("\n");
				res.append(subPad).append("|\n");
				res.append(subPad);
			}
			res.append("+-> ");
			if(getOutputs()[i]!=null) {
				if(i<this.numOutput-1) {
				    res.append( getOutputs()[i].layout(subPad+"|   ") );
				}else {
					res.append( getOutputs()[i].layout(subPad+"    ") );
				}
			}
		}
		return res;
	}
	
	@Override
	protected void simulate(double inFlow, SimulationObserverExt observer, boolean enableMaxFlowChecks) {
		double[] outFlow = new double[numOutput];
		for(int i=0; i<outFlow.length; ++i) {
			outFlow[i] = inFlow * proportions[i];
		}
		observer.notifyFlow("MultiSplit", getName(), inFlow, outFlow);
		if(enableMaxFlowChecks && inFlow>maxFlow)
			observer.notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		for(int i=0; i<outFlow.length; ++i) {
		    Element e = getOutputs()[i];
			e.simulate(outFlow[i],observer,enableMaxFlowChecks);
		}
	}


}
