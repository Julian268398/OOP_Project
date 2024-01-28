Cystic fibrosis is a disease in which a supply of digestive support drugs with digestive enzymes is often needed.
One of the most popular medicines (at least in Poland) that helps digestion is "Kreon Travix 10 000". Each capsule contains 10 000 units of pancreatin.

In my project, I have created two classes that implement an interface in which I have declared a method that returns the number of tablets to be taken after a meal.

1. First class "PerWeight" get dosage of Kreon based on patients age (if he is over 4 years old). Depending on this it counts the number of pills per meal taking into account how much the patient weighs.

In the constructor the user has to declare if he is over 4 years old and how much he weighs.

The "doseOfMedicine" method gets how many pills the patient has to take after a meal, using the information given in the constructor. 
It then returns the number of pills.

2. The second class "PerFat" helps to count how many pills of Kreon the patient has to take based on the fat contained in the meal the patient has eaten.

 This class has a constructor with a typical doctor's prescription of 10 000 pancreatin units (1 pill) per 5 grams of fat. 
 There is also a second option, the user can enter in the constructor how much pancreatin he needs to take for how much fat.

 The "addProduct" method is used to add the name of the ingredient of the meal (or the name of the whole meal if it is in a JSON file) and the amount of this ingredient in grams.

 "The removeProduct method is used to remove ingredients from the map.

 The user can use "productsInfo" to make sure he has not made a mistake when adding or removing products (ingredients) from the hash map. 
 It will return the string with all information.

The "productsConverterToFat" method is used to add elements from the products hash map to the productsWithFat hash map, changing the value from incredient amount to incredinet fat amount. 
The JSON file contains the names of the products and their amount of fat per 100 grams.
The productsConverterToFat(String filePath) method reads data from a file, expecting each line to contain a product name and its corresponding fat percentage, separated by a semicolon. It then processes this data as follows:
It uses a scanner to iterate through each line of the file.
Each line is split using the ";" delimiter to extract the product name and the fat percentage.
If both pieces of information are found on a line, they are stored in a HashMap called productFatMap.
For each entry in the products map (which is assumed to contain product names and their quantities):
The method retrieves the product name and quantity.
If the productFatMap contains the product name:
It calculates the fat content as the product of the quantity and the fat content percentage (obtained from productFatMap) divided by 100.
The calculated fat content is stored in another map called productsWithFat, where the product name is the key.
If the product name is not found in productFatMap, an error message is printed stating that the product was not found in the file.
In addition, the method handles FileNotFoundException by printing an error message if the specified file path is incorrect or the file does not exist. 
This method facilitates the conversion of product quantities to their respective fat contents based on predefined percentages, enabling further analysis or processing of the data.

The addProductToJSON method is for when the product from the products hash map does not exist in the JSON file.
The user can easily add this product to the database by specifying the name and the amount of fat in the product per 100 grams.
Method creates string "myData" based on given information. BufferWriter will extend the JSON file by adding a new line with "myData" string.
This method will also catch the IOException.

The "doseOfMedicine" method will give the dose of Kreon that the patient should take after this meal. 
Check for empty map:
It first checks if the productsWithFat card is empty.
If it is empty, it prints a message indicating that the productsConverterToFat method must first be executed to populate the map.
It then returns 0 to indicate that no dose calculation is possible.
Calculate dose:
If the map is not empty, the method proceeds to calculate the medicine dose.
It calculates the sum of the fat contents of all products in the productsWithFat map using a stream operation.
This sum is multiplied by the numberOfUnits and divided by the product of fatQuantity and 10000.0.
The result is then rounded up to the nearest integer using Math.ceil() and cast to an integer before being returned.

Sources of information about taking Kreon were the website ( https://www.aptekaolmed.pl/produkt/kreon-travix-10000-lek-poprawiajacy-trawienie-50-kapsulek-14847,14847.html ) and information from the person taking the medicine.
