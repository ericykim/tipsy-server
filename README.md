# tipsy-server

|   Drink   |
|-----------|
drinkId: Integer
drinkName: String
imageUrl: String
creatorId: Integer
creatorUsername: String

One Drink has Many Steps
<br/>
One Drink has Many Ingredients

|   Ingredient   |
|-----------|
ingredientId: Integer
Name: String
Amount: String
ingredientOrder: Integer

|   Steps   |
|-----------|
stepId: Integer
Instructions: String
stepOrder: Integer

|   Bartender   |
|-----------|
Id: Integer
Username: String
Password: String
firstName: String
lastName: String
Email: String
LikedDrinks: Set<Drinks>
CreatedDrinks: Set<Drinks>
  
Many Bartenders can like Many Drinks
<br/>
One Bartenders can create Many Drinks


|   Connoisseur   |
|-----------|
Id: Integer
Username: String
Password: String
firstName: String
lastName: String
Email: String
LikedDrinks: Set<Drinks>
  
Many Connoisseur can like Many Drinks
