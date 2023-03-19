# TravelPortal

The program simulate a travel website. All classes are in the package
**travelPortal**. The main class is **TravelPortal**. The class
**TestApp** in package **example** contains an example, it shows some
test cases (not all). Exceptions are thrown through class
**TPException**; only the checks that are explicitly required should be
implemented.

It is possible to access to the full [Java API
documentation](https://oop.polito.it/api/).

## Activity types and travel agencies

The method **addActivityTypes (String\... names)** is used to define the
names of the types of the activities and returns a list with all the
names in alphabetic order.

The method **addTravelAgency (String name, String\... actityTypes)**
defines a travel agency by its name, and indicates the names of the
activity types that it offers; the method returns the number of the
types of activity that are offered. It throws an exception if the agency
is already defined, or if any of the activity types is undefined.

The method **getAgenciesForActivityTypes ()** returns a map with the
names of the activity types as key, and the list of travel agencies that
offer it. Both keys and values are sorted alphabetically. Activity types
that are not offered by any agency should be ignored.

## Travel proposal

The method **addProposal (String code, String agency, String
destination, String period, int minNP, int maxNP, int price)** adds a
travel proposal with the following parameters: unique code of the
proposal, name of the travel agency that proposed the travel,
destination, travel period, minimum (minNP) and maximum (maxNP) number
of participants, price of the travel. The period has format \"m:i-f\",
where **m** is the number of the month, **i** is the number of the
departure day and **f** is the number of the return day; for instance,
8:10-20 denotes a travel from August, 10th to August, 20th. The day
**i** and **f** are in the same month. The method returns the length of
the travel (the difference between **f** and **i**) and throws an
exception if code of the proposal has already been defined, or if the
agency is undefined.

The method **addActivity (String code, String activityType, String
description, int price)** adds to the proposal with the given code an
activity with the following parameters: activity type, description,
price. It throws an exception if the travel agency does not offer the
selected type of activity. The method returns the total of the price of
the activities added to the proposal.

The method **getProposalPrice (String code)** returns the total price of
the proposal including travel and the activities added to the proposal.
Activities are not mandatory. the method throws an exception if the code
does not correspond to any proposal.

## Participants

The method **addParticipants (String code, String\... names)** adds the
names of the participants to the proposal selected through the parameter
code. The parameter names list the names of the applicants: applicants
are accepted only if they do not non participate to another proposal
that overlaps the month and some of the days. For instance, if the
applicant *Alice* participate to proposal *P1* that is held in period
*8:6-12*, Alice\'s next request fo proposal *P2* that i held in period
*8:10-10* is rejected because there is an overlapping. Accepted
applicants become participants. The method returns the list of the
participants in the same order as specified in parameter *names*. It
throws an exception if the number of participants is not in the range
minNP-maxNP (extremes included) set in the proposal.

The method **getIncome (String code)** returns the economic result of
the proposal selected by the code. Such economic result is calculated by
multiplying the total price of the proposal by the number of
participants.

## Evaluations

The method **addRatings (String code, int\... evaluations)** returns the
evaluations (integer values) to the proposal selected by the code. The
number of the evaluations must be equal to the number of participants,
otherwise an exception is thrown. It is easy to determine the authors of
the evaluations: evaluation with index j has been provided by
participant with index j (in the list of the participants). The method
returns for each participant, the name followed by *\":\"* and by the
evaluation. The tokens \"name:evaluation\"are joined by commas *\", \"*.
For example: *\"pt2:10, pt4:12, pt6:14, pt8:16\"* (see TestApp).

The method **getTotalRatingsForParticipants ()** returns for each
participant the sum of the values of the participant\'s evaluations. The
keys are the names of the participants ands must be sorted
alphabetically. The participants that provided at least one evaluation
are considered.

## Statistics

the method **incomeForActivityTypes()** returns for each types of
activity the sum of the prices of the connectedactivities. The types of
activities without any connected activity are ignored. The keys are the
names of the types of activities and are sorted alphabetically.

The method **participantsWithSameNofProposals ()** returns the list of
the names of the participants that participate to the same number of
proposals. The keys are the number of the proposals in increasing order,
the list of names are sorted alphabetically.
