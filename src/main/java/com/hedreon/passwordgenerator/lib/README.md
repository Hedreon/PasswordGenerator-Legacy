# Password Generator Library

The Password Generator Library is a public library that anyone can use in their projects that generates a password from the specified length.

### Method Parameters

**(*)** = REQUIRED

#### `generate()` Method Parameters

- `JTextField` inputTextField - Specifies the text field for the input (In this case, the length) **(*)**

- `JPasswordField` outputPasswordField - Specifies the password field for the output **(*)**

#### `copy()` Method Parameters

- `JPasswordField` outputPasswordField - Specifies the password field for the output **(*)**

## Code (In words):

### [`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java)

From package, `com.hedreon.passwordgenerator.lib`, import the following:

- Generator Constants

- `JTextField`

- `JPasswordField`

- `StringSelection`

- `Clipboard`

- `Toolkit`

Make a class called `Generator` and make 2 methods called `generate()` and `copy()`

### `generate()`

Get the specified length of the password and get String Builder. For integer is 0; integer is less than the specified password length; increment each time. `Random` is an integer, multiply 4 by *x* while *x* is random.

Switch cases to `Random`:

- Case #0: Append the password to an integer and multiply 10 by *x* while *x* is random.

- Case #1: `Random` is an integer, multiply the length of the alphabet in all lower case using the `LOWER_CASE` constant by *x* while *x* is random. After that, append the password to the chosen letter of the lower case alphabet.

- Case #2: `Random` is an integer, multiply the length of the alphabet in all upper case using the `UPPER_CASE` constant by *x* while *x* is random. After that, append the password to the chosen letter of the upper case alphabet.

- Case #3: `Random` is an integer, multiply the length of the `SYMBOLS` constant by *x* while *x* is random. After that, append the password to the chosen symbol.

After all that is done, set the text of the specified output password field to the generated result in [Strings.](https://en.wikipedia.org/wiki/String_(computer_science))

### `copy()`

Get the password of the specified output password field with the generated result, get the users' clipboard, and get the selection of all the password. After getting all those, copy the generated result into the users' clipboard.

### [`GeneratorConstants.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorConstants.java)

From package, `com.hedreon.passwordgenerator.lib`, make a class called `GeneratorConstants`. Inside the `GeneratorConstants` class, make a string on the lower case and upper case alphabet, and make a string on all the allowed symbols.

## Class Tree:

- [`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java)

  - `generate()`

  - `copy()`

- [`GeneratorConstants.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorConstants.java)

  - `LOWER_CASE`

  - `UPPER_CASE`

  - `SYMBOLS`