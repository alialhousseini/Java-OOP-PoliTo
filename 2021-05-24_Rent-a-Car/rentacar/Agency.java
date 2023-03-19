package rentacar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import rentacar.Vehicle.VehicleStatus;

public class Agency {
	public static final int NO_VEHICLE = -1;
	
	// R1
	/**
	 * Define points for vehicle categories (A, B, C, etc.). Number of categories is
	 * equal to the number of points provided as arguments.
	 * 
	 * @param points
	 * @throws AgencyException is thrown in case points are not given in ascending
	 *                         order
	 */
	private int VehicleN=-1;
	private int userN=-1;
	private ArrayList<Double> points = new ArrayList<>();
	private Map<Integer,Vehicle> vehicles = new TreeMap<>();
	private Map<Integer,User> users = new TreeMap<>();
	private ArrayList<Reservation> reservations = new ArrayList<>();
	
	public void definePoints(double... points) throws AgencyException {
		for(int i=0;i<points.length-1;i++)
			if(points[i]>points[i+1])
				throw new AgencyException("delta");
		for(double point:points)
			this.points.add(point);
				
	}

	/**
	 * Retrieves the number of points for the given category.
	 * 
	 * @param category
	 * @return number of points for the given category
	 * @throws AgencyException thrown in case category has not been defined
	 */
	public double getPointsOfCategory(char category) throws AgencyException {
		int index=category-'A';
		if(index >=points.size() | index<0)
			throw new AgencyException("dss");
		return points.get(index);
	}

	/**
	 * Registers a new car to the agency with the following attributes:
	 * 
	 * @param manufacturer
	 * @param model
	 * @param year
	 * @param gear
	 * @param color
	 * @param category
	 * @param seats
	 * @return a unique vehicle id, that is assigned progressively to each vehicle,
	 *         starting from 0
	 * @throws AgencyException thrown in case the category doesn't exist
	 */
	public int addCar(String manufacturer, String model, int year, String gear, String color, char category, int seats)
			throws AgencyException {
		VehicleN++;
		if(vehicles.containsKey(VehicleN))
			throw new AgencyException("already exists");
		int index = category-'A';
		if(index>=points.size()|index<0)
			throw new AgencyException("already exists");
		Car c = new Car(manufacturer,model,year,gear,color,category,seats);
		c.setId(VehicleN);
		vehicles.put(VehicleN, c);
		return VehicleN;
	}

	/**
	 * Registers a new van to the agency with the following attributes:
	 * 
	 * @param manufacturer
	 * @param model
	 * @param year
	 * @param gear
	 * @param color
	 * @param category
	 * @param seats
	 * @param limit
	 * @return a unique vehicle id, that is assigned progressively to each vehicle,
	 *         starting from 0
	 * @throws AgencyException thrown in case the category doesn't exist
	 */
	public int addVan(String manufacturer, String model, int year, String gear, String color, char category, int seats,
			int limit) throws AgencyException {
		VehicleN++;
		if(vehicles.containsKey(VehicleN))
			throw new AgencyException("already exists");
		int index = category-'A';
		if(index>=points.size()|index<0)
			throw new AgencyException("already exists");
		Van v = new Van(manufacturer,model,year,gear,color,category,seats,limit);
		v.setId(VehicleN);
		vehicles.put(VehicleN, v);
		return VehicleN;
	}

	public int addVehicle(Vehicle vehicle) throws AgencyException {
		if(vehicle instanceof Car)
			return addCar(vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getGear(), vehicle.getColor(), vehicle.getCategory(), vehicle.getSeats());
		if(vehicle instanceof Van)
			return addVan(vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getGear(), vehicle.getColor(), vehicle.getCategory(), vehicle.getSeats(),((Van) vehicle).getLimit());
		return VehicleN;
	}

	/**
	 * Retrieves the vehicle information produced by the given manufacturer.
	 * 
	 * The list is sorted based on the vehicle id.
	 * 
	 * @param manufacturer
	 * @return the list of Strings in the format `[model]:[year]:[color]`. An empty
	 *         list is returned if for a given manufacturer no car has been defined.
	 */
	public List<String> getVehiclesOfAManufacturer(String manufacturer) {
		return vehicles.values().stream().filter(v -> v.getManufacturer().equals(manufacturer))
				.map(v -> v.getModel() + ":" + v.getYear() + ":" + v.getColor()).collect(Collectors.toList());
	}


	// R2
	/**
	 * Registers a user to the agency with its name and city
	 * 
	 * @param name
	 * @param city
	 * 
	 * @return unique id that each user is assigned to, progressively, starting from
	 *         0
	 * @throws AgencyException thrown when a user with both same name and city has
	 *                         already been defined
	 */
	public int registerUser(String name, String city) throws AgencyException {
		this.userN++;
		if(users.containsKey(userN))
			throw new AgencyException("dffd");
		for(User u: users.values())
			if(u.getName().equals(name) & u.getCity().equals(city))
				throw new AgencyException("dsfdasdsa");
		User user = new User(name,city);
		user.setCode(userN);
		users.put(userN, user);
		return userN;
	}

	/**
	 * 
	 * Retrieves the user information in the form of a map, associating cities with
	 * user names living in those cities.
	 * 
	 * Cities are sorted alphabetically, while user names in the list are sorted in
	 * reversed alphabetical order
	 * 
	 * @return the map associating cities with the users
	 */
	public Map<String, List<String>> getUserInfo() {
		return users.values().stream().sorted(Comparator.comparing(User::getName).reversed()).collect(Collectors
				.groupingBy(User::getCity, TreeMap::new, Collectors.mapping(User::getName, Collectors.toList())));
	}


	/**
	 * Counts registered users
	 * 
	 * @return the number of registered users
	 */
	public int countUsers() {
		return users.size();
	}

	// R3
	/**
	 * Adds a vehicle reservation. The reservation is made for the first free
	 * vehicle that satisfies the criteria, belonging to the desired category and
	 * having the number of seats higher or equal to the given one. If such vehicle
	 * exists it is immediately set to occupied. If more vehicles satisfy such
	 * criteria, the one that has been registered first is taken. On the other hand,
	 * if no such vehicle is available, no reservation is made.
	 * 
	 * For each successful reservation, the user is given a number of points
	 * associated to the category of the rented vehicle.
	 * 
	 * Note: more than one rent can be associated with a user.
	 * 
	 * @param uid
	 * @param category
	 * @param seats
	 * @param duration
	 * @return the vehicle's unique id in case a vehicle satisfying the criteria is
	 *         found otherwise, NO_VEHICLE constant is returned.
	 * @throws AgencyException thrown if a user with the given id does not exist;
	 *                         additionally, if a category does not exist, an
	 *                         exception is thrown.
	 *                         
	 *  The method makeReservation(int uid, char category, int seats, int duration) adds a vehicle reservation, for the user id, vehicle category, 
	 *  minimum seat number that is required and a number of rent days. If a user with the given id does not exist, an exception is thrown. 
	 * If the category does not exist, an exception is thrown. The reservation is made for the first vehicle that satisfies the criteria,
	 * belonging to the desired category and having the number of seats higher or equal to the given one. If such vehicle exists it is 
	 * immediately set to occupied, and the method returns unique id of that vehicle. If more vehicles satisfy such criteria, the one 
	 *   that has been registered first is taken. On the other hand, if no such vehicle is available, no reservation is made, and the method 
	 *  returns NO_VEHICLE constant. For each successful reservation, the user 
	 *  is given a number of points associated to the category of the rented vehicle. Note: more than one rent can be associated with a user.
	 * 
	 */
	public int makeReservation(int uid, char category, int seats, int duration) throws AgencyException {
		if(!users.containsKey(uid))
			throw new AgencyException("dsssd");
		int index = category-'A';
		if(index>=points.size()|index<0)
			throw new AgencyException("already exists");
		for(Vehicle v: vehicles.values())
			if(v.isCompatible(category, seats) & v.isAvailable()) {
				users.get(uid).addPoints(points.get(index));
				v.pickVehicle();
				Reservation r = new Reservation(uid,category,seats,duration,v);
				reservations.add(r);
				return v.getId();
			}
		
		return NO_VEHICLE;
		
	}

	/**
	 * Retrieves information about users that reserved cars of the given category.
	 * 
	 * The list is sorted alphabetically.
	 * 
	 * @param category
	 * @return a list of names of the users who booked cars of the given category
	 */
	public List<String> getUserNamesForTakenCars(char category) {
		List<String> this_is= new ArrayList<String>();
		this_is = reservations.stream().filter(r->r.getCategory()==category).map(r->users.get(r.getUid())).sorted(Comparator.comparing(User::getName)).map(u->u.getName()).collect(Collectors.toList());
		return this_is;
	}

	/**
	 * Retrieves information about vehicles that have number of seats higher or
	 * equal to the given one.
	 * 
	 * String format for each vehicle `### [category]:[manufacturer]:[model]`, where
	 * ### stands for vehicle id (printed on 3 characters).
	 * 
	 * List is sorted alphabetically based on the vehicle category and then based on
	 * vehicle id in ascending manner.
	 * 
	 * @param seats
	 * @return the list of Strings containing vehicle information
	 */
	public List<String> getAvailableVehicles(int seats) {
		List<String> this_is= new ArrayList<>();
		this_is = vehicles.values().stream().filter(v->v.isAvailable() & v.getSeats()>=seats).sorted(Comparator.comparing(Vehicle::getCategory)).sorted(Comparator.comparing(Vehicle::getId)).map(v->v.print_id()).collect(Collectors.toList());
		return this_is;
	}

	// R4

	/**
	 * Retrieves a map that associates number of points with the user names having
	 * that number of points.
	 * 
	 * Number of points for one user is equal to the sum of points for all of the
	 * reservations the user made. Users with zero points are discarded. Points are
	 * sorted in the descending manner.
	 * 
	 * @return a map associating points with user names.
	 */
	public Map<Double, List<String>> getUsersPerPoints() {
		return users.values().stream().filter(u->u.getPoints()!=0).sorted(Comparator.comparing(User::getPoints).reversed()).collect(Collectors.groupingBy(User::getPoints, TreeMap::new, Collectors.mapping(User::getName, Collectors.toList())));
	}

	/**
	 * Retrieves a map that associates user id & name,`[id]: [name]` with with the
	 * average number of rent days for that user.
	 * 
	 * The map is sorted based on the average number of rent days in descending
	 * order and then based on the user names alphabetically.
	 * 
	 * @return a map that associates user info with the average number of rent days
	 *         for that user
	 */
	
	public double compute_average(int id) {
		double sum=0;
		double N=0;
		for(Reservation r: reservations) {
			if (r.getUid()==id) {
				sum+=(double)r.getDuration();
				N+=1;
			}
		}
		if(N==0.0)
			return 0.0;
		return sum/N;
	}
	public Map<String, Double> evaluateUsers() {
		users.values().stream().forEach(k->{k.setAverage(this.compute_average(k.getCode()));});
		return users.values().stream()
				.sorted(Comparator.comparing(User::getAverage).reversed().thenComparing(User::getName))
				.collect(Collectors.toMap(User::toString, User::getAverage));
}

	/**
	 * Retrieves a map associating a year and information for the cars manufactured
	 * in that year.
	 * 
	 * Car info is in the following format `[manufacturer]:[model]:[color]`.
	 * 
	 * The years are sorted in the descending order.
	 * 
	 * @return a map associating year of production with the car information
	 */
	public Map<Integer, List<String>> getCarInfoForYears() {
		return vehicles.values().stream()
				.sorted(Comparator.comparing(Vehicle::getYear).reversed())
				.collect(Collectors.groupingBy(Vehicle::getYear, Collectors.mapping(Vehicle::print_id2, Collectors.toList())));
	}

	/**
	 * Retrieves a map associating vehicle number with the categories of those
	 * vehicles.
	 * 
	 * The number of vehicles is sorted in ascending manner, while the categories
	 * are sorted alphabetically. The categories with 0 vehicles should also be
	 * considered.
	 * 
	 * @return a map associating number of vehicles with the vehicle categories
	 */

}