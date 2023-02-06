# Password Generator - Library

The Password Generator Library is a library that generates a password from the specified length.

## Code

### Method Arguments

**(*)** = REQUIRED

#### `generatePassword()` Method Arguments

- `JPasswordField output` - Specifies the password field for the output. **(*)**

#### `copyPassword()` Method Arguments

- `JPasswordField output` - Specifies the password field for the output. **(*)**

### [`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java)

From package, `com.hedreon.passwordgenerator.lib`, import the following:

- `JPasswordField`

- `StringSelection`

- `Clipboard`

- `Toolkit`

- `SecureRandom`

- `ArrayList`

- `List`

Make a class called `Generator` and make two methods called `generatePassword()` and `copyPassword()`.

#### `generatePassword()`

Initialize a list of available characters to choose from and if `INCLUDE_NUMBERS`, `INCLUDE_SYMBOLS`, `INCLUDE_LOWERCASE_LETTERS`, or `INCLUDE_UPPERCASE_LETTERS` is true, add numbers, symbols, lowercase letters, or uppercase letters to the list.

Initialize a `SecureRandom` instance for generating random numbers in an unpredictable way and initialize a `StringBuilder` instance for building the password. After those two have been initialized, select a random character from the list and append it to the password from the specified number of times.

After the password has been built, set the [stringed](https://en.wikipedia.org/wiki/String_(computer_science)) password to the output `JPasswordField`.

#### `copyPassword()`

A `char[]` array called `password` is created, and it is initialized with the value returned by calling the `getPassword()` method on the output `JPasswordField` object.

Then, a [`String`](https://en.wikipedia.org/wiki/String_(computer_science)) object called `passwordString` is created, and it is initialized with a new `String` object created from the password array.

Next, a `Clipboard` object is created, and it is initialized with the user's default system clipboard.

Then, a `StringSelection` object is created, and it is initialized with a new `StringSelection` object created from `passwordString`.

Finally, the `setContents()` method is called on the `Clipboard` object, with two `StringSelection`'s as arguments.

Overall, this method is used to copy the password stored in the output `JPasswordField` object to the user's clipboard.

### [`GeneratorSettings.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorSettings.java)

From package, `com.hedreon.passwordgenerator.lib`, make a class called `GeneratorSettings`. Inside the `GeneratorSettings` class, make two more classes called `Setting` and `Constant`.

Inside the `Setting` class, make booleans on whether to `INCLUDE_NUMBERS`, `INCLUDE_SYMBOLS`, `INCLUDE_LOWERCASE_LETTERS` or to `INCLUDE_UPPERCASE_LETTERS` and finally, make an integer for the `PASSWORD_LENGTH`.

Inside the `Constant` class, make strings on the `LOWER_CASE` and `UPPER_CASE` alphabet, and make a string on all the allowed `SYMBOLS`.

## Method Tree

- [`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java)

  - `generatePassword()`

  - `copyPassword()`

- [`GeneratorSettings.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorSettings.java)

  - `Setting`
  
    - `INCLUDE_NUMBERS`

    - `INCLUDE_SYMBOLS`

    - `INCLUDE_LOWERCASE_LETTERS`

    - `INCLUDE_UPPERCASE_LETTERS`
    
    - `PASSWORD_LENGTH`

  - `Constant`
  
    - `LOWER_CASE`
    
    - `UPPER_CASE`
    
    - `SYMBOLS`