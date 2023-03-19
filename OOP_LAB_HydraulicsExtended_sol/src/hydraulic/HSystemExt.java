package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		// Funziona se:
		// 1. il primo elemento aggiunto è la sorgente
		// 2. c'è una sola sorgente
//		return getElements()[0].layout("").toString();
			
		StringBuffer res = new StringBuffer();
		for(Element e : elements){
			if( e != null && e instanceof Source){
				res.append(e.layout(""));
			}
		}
		return res.toString();
	}

	private static int numConnectedOut(Element e) {
		int count=0;
		for(Element o : e.getOutputs()) {
			count+= o!=null?1:0;
		}
		return count;
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public void deleteElement(String name) {
		boolean found = false;
		int i;
		for(i=0; i<next; i++){
			Element current = elements[i];
			if(!found && current.getName().equals(name)){
				found = true;
				if(( current instanceof Split || current instanceof Multisplit ) 
					&& numConnectedOut(current)>1)
					//return false;
					return;
				
				Element input = current.getInput();
				Element output= null;
				for(Element o : current.getOutputs()) {
					if(o!=null) {
						output = o;
						break;
					}
				}
				if(input!=null) {
					input.replaceWith(current, output);
				} else {
					if( output != null ) output.setInput(null);
				}
			}
			if(found)
				elements[i]=elements[i+1];
		}
		elements[i] = null;
		next--;
		
		//return true;
	}

	/**
	 * starts the simulation of the system; if {@code enableMaxFlowCheck} is {@code true},
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		for(Element e : elements){
			if( e != null && e instanceof Source){
				e.simulate(-1,observer,enableMaxFlowCheck);
			}
		}
	}
	
}
