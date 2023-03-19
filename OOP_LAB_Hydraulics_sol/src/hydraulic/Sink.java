package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override
	public void connect(Element elem){
		// no effect!
	}


	@Override
	void simulate(double inFlow, SimulationObserver observer) {
		observer.notifyFlow("Sink", getName(), inFlow, SimulationObserver.NO_FLOW);
	}

}
