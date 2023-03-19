# Diet

Write an application to manage a diet by means of nutritional values
computation.

The application must allow the definition of raw materials, their use as
ingredients for recipes, the definition of products and menus.

All the classes must be in the package \"**diet**\".

## R1 - Raw Materials 

The system works though the facade class **Food**.

To define a raw material, we can use the method **defineRawMaterial()**
that accepts as arguments the name, the kilo-calories, the amount of
proteins, carbohydrates (carbs), and fat; all the values refer to 100
grams of raw material. The name of the raw material can be considered
unique.

To retrieve some information about the raw materials we can use the
method **rawMaterials()** which returns a list of raw materials, sorted
by name in alphabetic order. To get info about a specific material, we
can use the method **getRawMaterial()** that accepts the name of the raw
material and returns the corresponding raw material.

The raw materials returned by the above methods are objects implementing
the interface **NutritionalElement**, which provides the getter methods
**getName()**, **getCalories()**, **getProteins()**, **getCarbs()**,
**getFat()**. Calories are expressed in KCal, while proteins, carbs, and
fat are expressed in grams.

Moreover the interface includes the method **per100g()** that indicates
whether the values refer to 100 grams of nutritional element or
represent an absolute value. For raw materials the nutritional values
are always expressed per 100 grams, therefore the method returns `true`.

## R2 - Products

The diet may include also pre-packaged products (e.g. an ice cream or a
snack). Products are defined by means of the method **defineProduct()**
of class Food accepting as arguments the name, the kilo-calories, the
amount of proteins, carbohydrates (carbs), and fat. Such values express
the value for the whole product, therefore the method per100g() returns
`false`. The name of the product can be considered unique.

To retrieve information about the products we can use the method
**products()** of class Food that returns a collection of products
sorted by name. To get information about a specific product, method
**getProduct()** is available that accepts the name of the product and
returns the corresponding object.

Both methods return the products as object implementing the interface
*NutritionalElement* (described in the previous requirement); the values
are expressed for the whole product (i.e. the method *per100g()* returns
*false*).

## R3 - Recipes

Raw materials can be combined as ingredients of recipes. To define a
recipe we can use the method **createRecipe()**, from class *Food*, that
accepts as argument the name of the recipe. The name of the recipe can
be considered unique.

A recipe is represented by an object of class **Recipe** that allows
adding new ingredients by means of its method **addIngredient()**
accepting as arguments the name of the raw material and the relative
amount in grams.

Class Recipe implements the interface *NutritionalElement* and the
values are expressed per 100 grams.

To retrieve the information about the recipes we can use the method
**recipes()**, of class *Food*, that returns a collection of recipes
sorted by name. To get information regarding a specific recipe we can
use the method **getRecipe()** that accepts as argument the name of the
recipe and return the corresponding recipe. Both methods return recipes
as *NutritionalElement*

-   **Warning**: While the sum of the amounts of ingredients (in grams)
    of a recipe is not necessarily equal to 100g, the nutritional values
    of the recipe must refer to an ideal portion of 100 grams.

## R4 - Menu

A menu consists of either portions of recipes or pre-packaged products.

Menus can be create with method **createMenu()** of class *Food*, that
accepts as argument the name of the menu.

A menu is represented by class **Menu** that allows to add a portion of
a recipe to the menu through method **addRecipe()** that accepts as
argument the name of the recipe and the size of the portion, in grams.

To add an item of a pre-packaged product, class *Menu* provides the
method **addProduct()** that accepts as argument the name of the
product.

Class *Menu* implements the *NutritionalElement* interface; in this case
the values are referred to the whole menu.
