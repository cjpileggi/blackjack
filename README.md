# Blackjack
A simulation of the famous casino game written in Java.

## Compiling and Running
Place the `.java` source files and `img` directory in a directory named `blackjack`

To compile the project, in the parent directory, run the following command:

```sh
$ javac blackjack/Blackjack.java
```
To run:
```sh
$ java blackjack.Blackjack
```

### Creating a JAR
Create a `MANIFEST.MF` file in the parent directory containing the following:
```sh
Manifest-Version: 1.0
Main-Class: blackjack.Blackjack
```
Then run the following to create a JAR file named Blackjack.jar:
```sh
$ jar cfm Blackjack.jar manifest.mf blackjack/.*class blackjack/imgs 
```
The runnable JAR will be located in the parent directory. To run it, use:
```sh
$ java -jar Blackjack.jar 
```

## Version History
* **1.0** - Completed project  
