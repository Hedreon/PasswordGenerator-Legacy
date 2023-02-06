# Password Generator - UI

This is the main code for the UI/UX.

## What does [`MainForm.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/ui/MainForm.java) do?

Behind the scenes, it's creating the following components:

- A `JFrame`

- A `JLabel`

- A `JPasswordField`

- 3 `JButton`'s

All of these components are customised to the extent of bringing modernization and consistency to the design.

## What does [`OptionsForm.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/ui/OptionsForm.java) do?

It's the same with MainForm.java, but instead of a `JPasswordField`, it has a `JTextField`, with more additional components to it:

- 4 `JCheckBox`-es

- 2 `Action`'s

- A `JFrame`

- A `JLabel`

- A `JPopupMenu`

- A `JMenuItem`

- A `JTextField`

- A `JButton`

Once again, all of these components are customised to the extent of bringing modernization and consistency to the design.

## Question

**Why are there no locations and positions referenced anywhere *during* the UI creation process?**

The layout handles these, because, after the creation of the UI, it then proceeds to set up the layout, which moves all the components into their organized places.