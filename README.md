> :warning: This project is in an early alpha stage of development. Test coverage is non-existent and documentation is 
poor. But since I won't have too much time during the next months I felt like opening it up to contributions.

## HeadlessMc

HeadlessMc allows you to launch Minecraft from the command line. It also allows you to instrument the game: before
launch the bytecode of the games libraries can be modified. HeadlessMc aims to use this feature to
* add a command line interface to Minecraft, which allows you to control the game.
* redirect every method in the lwjgl library, causing Minecraft not to render anything, thus making it "headless".
* patch the Log4J vulnerability.

## How to use

All you need is the `headlessmc-launcher.jar` file and java &geq; 8 installed. The launcher will start after you
executed `java -jar headlessmc-launcher.jar` in a terminal of your choice. Double-clicking the jar is not supported.
After that HeadlessMc will create a `HeadlessMc/config.properties` file. Edit that file, create a key
`hmc.java.versions` inside. The value should be a `;` separated list to of java executables HeadlessMc can use, like 
this:
```properties
hmc.java.versions=C:/Program Files/Java/jre-<version>/bin/java;C:/Program Files/Java/jdk-<version>/bin/java
```
After you are done, type `config -refresh` then `java -refresh`, press enter, and you are ready start.
Here are some of the most important commands:

| Name        | Description | Args/Flags  |
| ----------- | ----------- | ----------- |
| help | Lists commands and prints informations.| \<command/id\> \<arg\> -id |
| quit | Exits HeadlessMc. | |
| versions | Lists all Minecraft versions currently installed. | -release -snapshot -other |
| download | Lists available Minecraft versions and downloads them. | \<version\> -id -release -snapshot -other -refresh |
| login | Logs into a Microsoft account. | \<email\>, will open a context for entering the password. |
| launch | Launches Minecraft. | \<version/id\> -commands -lwjgl -paulscode -lookup -jndi -noout -keep -exit |

Arguments passed to commands have to be separated using spaces. If you
want to pass an Argument which contains spaces you need to escape it using quotation marks, like this:
`"argument with spaces"`. Quotation marks and backslashes can be escaped by using a backslash.

HeadlessMc can be configured using properties. These can be passed as SystemProperties from the command line or via the 
`HeadlessMc/config.properties` file. Additional configs can be added to the `HeadlessMc/configs` folder. For available
information see the [HmcProperties](headlessmc-commons/src/main/java/me/earth/headlessmc/config/HmcProperties.java), the
[LauncherProperties](headlessmc-launcher/src/main/java/me/earth/headlessmc/launcher/LauncherProperties.java), the
[RuntimeProperties](headlessmc-runtime/src/main/java/me/earth/headlessmc/runtime/RuntimeProperties.java) or the
[LwjglProperties](headlessmc-lwjgl/src/main/java/me/earth/headlessmc/lwjgl/LwjglProperties.java).

> :warning: Because I don't want to get into trouble HeadlessMc will not store your account credentials. If you don't
want to enter them everytime you can supply them using the properties (**at your own risk**).

## Runtime

In attempt to provide some debuggability HeadlessMc can control the game using commands when launching it with the 
`-commands` flag. However, because I wanted this to be "not-version-specific", this is just java-reflection in form of
commands. For more comfortable implementations, which you can use without java-knowledge, take a look at the 
[HMC-Specifics](https://github.com/3arthqu4ke/HMC-Specifics). 

I don't want to go into greater detail about this because it's awful, and I don't even use it myself, so just a quick
example on how to exit the game gracefully in Minecraft 1.12.2 vanilla:

Get the Minecraft class and store it in the 0th register (since this is vanilla we need to use obfuscated names):\
`class bib 0 -dump`\
Get the Minecraft instance from the class and store it in the 1st register:\
`field 0 R 1` or `method 0 z 1`\
Invoke the shutdown method on the Minecraft instance in the 1st register:\
`method 1 n 2`

One day I might add support for mappings, so you don't need to look up the obfuscated names.

## Contributing

You don't need to be able to code to contribute. It would be cool to eliminate as many potential crashes as possible by
running as many versions as possible using the `-lwjgl` flag. When your game crashes please open an issue.

If you contribute code: Please document your stuff and write tests. Also try to follow the code style: wrap your lines 
after 80 characters. [codestyle.xml](codestyle.xml) is code style configuration for Intellij.

## License, more documentation, etc.
Look [here](DEV.md) for more in-depth documentation about the project.

Some cool libraries we use:
* [OpenAuth by Litarvan](https://github.com/Litarvan/OpenAuth)
* [Deencapsulation by xxDark](https://github.com/xxDark/deencapsulation)

HeadlessMc is licensed under the [MIT License](LICENSE).