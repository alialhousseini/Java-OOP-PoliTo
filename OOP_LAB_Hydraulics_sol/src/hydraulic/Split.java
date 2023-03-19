package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
	}
    
//	/**
//	 * returns the downstream elements
//	 * @return array containing the two downstream elements
//	 */
//    public Element[] getOutputs(){
//    		//TODO: complete
//        return null;
//    }
//
//	public void connect(Element elem, int noutput){
//		//TODO: complete
//	}

	@Override
	void simulate(double inFlow, SimulationObserver observer) {
		double outFlow = inFlow/2;

		observer.notifyFlow("Split", getName(), inFlow, outFlow, outFlow);

		for(Element e : getOutputs()){
			e.simulate(outFlow, observer);
		}


	}
}
