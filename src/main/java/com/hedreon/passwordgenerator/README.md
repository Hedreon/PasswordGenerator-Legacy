# Password Generator Frontend
This is the main code for the frontend, the UI/UX.

## What does it do?

Behind the scenes, it's creating multiple components:
- A `JFrame`
- 2 `JLabel`'s
- 2 `JTextField`'s
- 2 `JButton`'s

All of these components are customised to the extent of bringing modernization & consistency to the design *~~(unlike [Microsoft](https://youtu.be/NQPDe_eSQRo?t=35))~~*

### Q&A
Q: **Why is there no locations & positions referenced anywhere *during* the UI creation process?**

A: The layout handles these, because, after the creation of the UI, it then proceeds to setup the layout, which moves all the components into their organized places.

## Which ones which?

- [`Init.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/Init.java) is the *main* class as it creates the [components](https://github.com/Hedreon/PasswordGenerator/tree/main/src/main/java/com/hedreon/passwordgenerator#what-does-it-do) and the UI.

- [`Main.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/Main.java) is the class that loads [`Init.java`](https://github.com/Hedreon/PasswordGenerator/blob/main/src/main/java/com/hedreon/passwordgenerator/Init.java), handles the launch process & sets up the [look and feel.](https://en.wikipedia.org/wiki/Look_and_feel)
