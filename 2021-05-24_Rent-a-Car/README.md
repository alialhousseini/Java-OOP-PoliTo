::: {#title-block-header}
# Rent a car {#rent-a-car .title}
:::

The program simulates a Rent a Car agency. The agency offers cars and
vans for rent to their customers (users). Each rent reservation is for a
vehicle category and it brings certain amount of points to the
customers.

All the classes have to be in **rentacar** package. The main class is
**Agency**. The class **TestApp** in the package **example** contains an
example. The exceptions are thrown using the class **AgencyException**.

It is possible to access [Java API
documentation](http://softeng.polito.it/courses/docs/api/index.html).

## R1: Categories and vehicles

The method **definePoints (double\... points)** defines points that are
being awarded to the customers for each of the vehicle categories.
Number of categories is supposed to be equal to the number of points
provided as arguments; categories are enumerated alphabetically (A, B,
C, etc.) The points are given in an ascending manner (more points are
assigned to category B than to category A, and so on); the method should
launch an exception if the points are not provided in an ascending
order.

The method **getPointsOfCategory(char category)** returns the number of
points for the given category. If the given category does not exist it
throws an **AgencyException**.

To any vehicle, regardless of whether it is a car or a van, an internal
identification number is assigned, in a progressive manner, starting
from 0.

The method **addCar(String manufacturer, String model, int year, String
gear, String color, char category, int seats)** adds a car, with its
manufacturer, model, production year, gear type, color, rent category
and number of seats. It returns the car's id number. An exception is
thrown if the indicated category has not been previously defined.

The method **addVan(String manufacturer, String model, int year, String
gear, String color, char category, int seats, int limit)** adds a van,
with its manufacturer, model, production year, gear type, color, rent
category, number of seats and its maximum weight limit. It returns the
van's id number. An exception is thrown if the indicated category has
not been previously defined.

The method **getVehiclesOfAManufacturer(String manufacturer)** generates
a list of Strings containing the information for vehicles of the given
manufacturer in the following format **\[model\]:\[year\]:\[color\]**.
The list is sorted in an ascending manner based on the vehicle ID. An
empty list is returned if for a given manufacturer no vehicle has been
defined.

## R2: Users

The method **registerUser(String name, String city)** adds a user with
its name and city. It returns the unique ID number that is assigned to
each of the users, progressively, starting from 0. The method throws an
exception if a user with both the same name and city has already been
defined.

The method **getUserInfo()** returns a map that associates a city with
the list of users living in that city. The city names are sorted
alphabetically while the user names in the list are sorted in a reversed
alphabetical order.

The method **countUsers()** returns a number of registered users.

## R3: Reservations

The method **makeReservation(int uid, char category, int seats, int
duration)** adds a vehicle reservation, for the user id, vehicle
category, minimum seat number that is required and a number of rent
days. If a user with the given id does not exist, an exception is
thrown. If the category does not exist, an exception is thrown. The
reservation is made for the first vehicle that satisfies the criteria,
belonging to the desired category and having the number of seats higher
or equal to the given one. If such vehicle exists it is immediately set
to occupied, and the method returns unique id of that vehicle. If more
vehicles satisfy such criteria, the one that has been registered first
is taken. On the other hand, if no such vehicle is available, no
reservation is made, and the method returns **NO_VEHICLE** constant. For
each successful reservation, the user is given a number of points
associated to the category of the rented vehicle. Note: more than one
rent can be associated with a user.

The method **getUserNamesForTakenCars(char category)** produces a list
of alphabetically sorted user names for the occupied cars belonging to
the given category. If there are no occupied cars for the given
category, an empty list is returned.

The method **getAvailableVehicles(int seats)** produces a list of
Strings with vehicle information, of those vehicles that are still
available and have a number of seats higher or equal to the given one.
The list is alphabetically sorted based on the vehicle category and then
bases on the vehicle id in ascending manner. Strings are in the
following format **\### \[category\]:\[manufacturer\]:\[model\]**, where
**\###** corresponds to the vehicle Id (printed on 3 characters). If
there are no vehicles satisfying such conditions, an empty list is
returned.

## R4: Evaluations

The method **getUsersPerPoints()** returns a map that associates number
of points with names of the users having that number of points. Number
of points for one user is equal to the sum of points for all of the
reservations the user made. Users with zero points are discarded. The
points are sorted in descending manner.

The method **evaluateUsers()** returns a map that associates the user id
and name (in format **\[id\]: \[name\]**) with the average number of
rent days for that user. The map is sorted based on the average number
of rent days in descending order and then based on the user names
alphabetically.

## R5: Statistics

The method **getCarInfoForYears()** returns a map that associates a year
with the list of cars being produced in that particular year. The years
are sorted in descending order. Each car in the list is represented in
the following manner **\[manufacturer\]:\[model\]:\[color\]**.

The method **getCategoriesForVehicleNumber()** returns a map that
associates the number of vehicles with the categories of those vehicles.
The number of vehicles is sorted in ascending manner, while the
categories are sorted alphabetically. The categories with 0 vehicles
should also be considered.
