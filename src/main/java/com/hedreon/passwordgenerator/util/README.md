# Password Generator - Utilities

This is all the utilities that Password Generator uses.
The utilities are here to simplify the code.

## What does [`IconUtils.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/util/IconUtils.java) do?

Behind the scenes, it's creating a method called
`loadIcon()` with an argument of `String icon`, which
lets you input the file path of your desired icon.

## What does [`FormUtils.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/util/FormUtils.java) do?

Behind the scenes, it's creating a method called `showForm()`
with arguments of `Class<? extends JFrame> form`, basically
requiring a class that extends `JFrame` and `Object... args`,
an optional argument that should only be used if the class that
extends `JFrame` requires any arguments. What it does is show
your desired form once you've inputted the arguments.