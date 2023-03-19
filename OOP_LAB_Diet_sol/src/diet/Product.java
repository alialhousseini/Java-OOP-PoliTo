package diet;


class Product implements NutritionalElement {

	private String name;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;

	public Product(String nome, double calories, double proteins, double carbs, double fat) {
		this.name = nome;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		return calories;
	}

	@Override
	public double getProteins() {
		return proteins;
	}

	@Override
	public double getCarbs() {
		return carbs;
	}

	@Override
	public double getFat() {
		return fat;
	}

	@Override
	public boolean per100g() {
		return false;
	}
}
