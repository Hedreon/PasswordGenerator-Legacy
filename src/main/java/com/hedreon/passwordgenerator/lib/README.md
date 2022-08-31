# Password Generator Backend / Password Generator Library

The Password Generator Library/Backend is a public library that anyone can use in their projects that generates a password from a specified length.

### Parameters

**(*)** = REQUIRED

#### `generate` Parameters

- `JTextField` inputTextField - Specify the text field for the input (In this case, the length) **(*)**

- `JTextField` outputTextField - Specify the text field for the output (In this case, the password) **(*)**

#### `copy` Parameters

- `JTextField` outputTextField - Specify the text field for the output (In this case, the password) **(*)**

## Code (In words):

### [`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java)

From package, `com.hedreon.passwordgenerator.lib`, import the following:

- Generator Constants

- `JTextField`

- `StringSelection`

- `Clipboard`

- `Toolkit`

Make a class called `Generator` & make a function called `generate` & `copy`.

#### `generate`

Get the specified length of the password & get String Builder. For integer is 0; integer is less than the specified password length; increment each time. `Random` is an integer, multiply 4 by X while X is random.

Switch cases to `Random`:

- Case #0: Append the password to an integer and multiply 10 by X while X is random.

- Case #1: `Random` is an integer, multiply the length of the alphabet in all lower case using the `LOWER_CASE` constant by X while X is random. After that, append the password to the chosen letter of the lower case alphabet.

- Case #2: `Random` is an integer, multiply the length of the alphabet in all upper case using the `UPPER_CASE` constant by X while X is random. After that, append the password to the chosen letter of the upper case alphabet.

- Case #3: `Random` is an integer, multiply the length of the `SYMBOLS` constant by X while X is random. After that, append the password to the chosen symbol.

After all that is done, set the text of the specified output text field to the generated result in [Strings.](https://en.wikipedia.org/wiki/String_(computer_science))

#### `copy`

Get the text of the specified output text field with the generated result, get the users clipboard, and get the selection of the password (In this case, all of the password). After getting all those, write/copy the generated result into the users clipboard.

### [`GeneratorConstants.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorConstants.java)

From package, `com.hedreon.passwordgenerator.lib`, make a class called `GeneratorConstants`. Inside the `GeneratorConstants` class, make a string on the lower case and upper case alphabet, and make a string on all the allowed symbols.

## Function Tree:

[`Generator.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/Generator.java) **¬**

- `generate`

- `copy`

[`GeneratorConstants.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/lib/GeneratorConstants.java) **¬**

- `LOWER_CASE`

- `UPPER_CASE`

- `SYMBOLS`
