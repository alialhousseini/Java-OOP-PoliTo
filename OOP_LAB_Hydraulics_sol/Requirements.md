# Hydraulic System Simulation

Design and implement a program to manage the simulation of an hydraulic
system.\
All the classes must belong to the package **hydraulic**.

## R1. Elements and pipes

A hydraulic system is composed of elements (of different types)
connected to each other (through pipes that are not explicitly modeled
in the system).

A system is represented by an object of class **HSystem** that allows
adding new elements through the method **addElement()** that accepts as
argument an object of class **Element** and adds it to the system
components.

By means of method **getElements()** it is possible to get an array
containing all and only the elements that compose the system.

## R2. Simple elements

The simple elements are represented by the classes **Source**, **Tap**,
and **Sink**.

All elements have a name that can be read through the getter method
**getName()**.\
It is possible to connect the output of an element to the input of
another element by means of the method **connect()** that accepts as
argument the element whose input should be connected to the output of
the subject: *a.connect(b);* connects the output of element *a* to the
input of element *b*.

The invocation of method connect on a *Sink* object has no effect.

Given an element, it is possible to know to which other output element
it is connected by means of the method **getOutput()** that returns an
*Element* object.

## R3. Complex elements

In addition to the simple elements described above, there are some
complex elements. The \"T\" element, represented by class **Split**,
allows splitting the input flow into two equal output flows. For this
class, the **connect()** method accepts an additional argument
specifying which output is being connected, such parameter may assume
the values *0* for the first output and *1* for the second output.\
For this class, it is possible to know which elements are connected to
the outputs, by means of the method **getOutputs()** that returns an
array with the two connected elements.

## R4. Simulation

Given a valid system, i.e. a tree with a source as the root and where
each path ends with a sink, it is possible to compute flow and how it is
split among the distinct paths.

The computation takes place into two phases: in the first phase the
parameters of the elements belonging to the system are defined, in the
second phase the actual simulation starts.

During the first setup phase it is possible to:

-   define the flow of a Source through the method **setFlow()** that
    accepts a number representing the cubic meters per hour.
-   set the opening of *Tap*s using the method **setOpen()** that
    accepts a boolean argument. When a tap is open the output flow is
    equal to the input flow, otherwise the output is zero.

For the T split the input flow is divided into two equal output flows
(each a half of the input flow).

The **simulate()** method of class *HSystem*, performs the flow
computations for each element in the system starting from each source
(for the source, only the output flow, and for the sink just the input
flow). This method requires as argument an object implementing the
**SimulationObserver** interface, that provides a single method.

When, during simulation, the input and output flows are known for an
element method **notifyFlow()** must be called on the observer passing
the element type, the name, and the input and output flows; if any of
the flows is not defined (e.g. for the *Source* and *Sink*), the
constant *NO_FLOW* can be used.

-   Hint: given an object, to find out if it is an instance of a
    specific class the operator *instanceof* can be used.\
    E.g. *if( element instanceof Source )*.
-   Warning: you are not required to implement the interface
    *SimulationObserver*, you only need to use it; an example of
    implementation that simply prints to the console the notifications,
    is given in class *PrintingObserver*.

Version 2.0 - 2020-04-16
