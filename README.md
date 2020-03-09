# DeepDiveJava14

This is a demo project to show some new features of Java™ 14.
It uses `--enable-preview` and Maven, but you don't need to have Maven installed.

To run this project, you need to have Java™ 14 installed. You can use https://sdkman.io/ and/or https://adoptopenjdk.net/.

If you'd like to run the example app, one of the options is this:

    $ MAVEN_OPTS="--enable-preview" ./mvnw exec:java  -Dexec.mainClass="org.przybyl.ddj14.App"


## jpackage demo
Understand: https://openjdk.java.net/jeps/343

    $ ./mvnw package
    $ cd target
    $ cp ddj14-1.0-SNAPSHOT.jar lib/
    $ jpackage --name DDJ14 --input ./lib --main-jar ddj14-1.0-SNAPSHOT.jar