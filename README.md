javalin-rocker-example
======================

This example shows how to add Rocker to a Javalin app.

Adding Rocker as a dependency
-----------------------------

### Adding the runtime dependency

First one needs to add the Rocker runtime to their dependencies.
For maven it looks something like this:

```xml
<dependencies>
    <dependency>
        <groupId>com.fizzed</groupId>
        <artifactId>rocker-runtime</artifactId>
        <version>${rocker.version}</version>
    </dependency>

    <!-- for hot-reloading support only during development -->
    <dependency>
        <groupId>com.fizzed</groupId>
        <artifactId>rocker-compiler</artifactId>
        <version>${rocker.version}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Replace ${rocker.version} with the current version of Rocker.
See [Rocker on maven central](https://mvnrepository.com/artifact/com.fizzed/rocker-runtime) for more details.

### Adding the compiler plugin

See the [official Rocker readme](https://github.com/fizzed/rocker#integrate-parsergenerator-in-build-tool) for details on how to do this.
Rocker also supports Gradle.

Using Kotlin with Rocker
------------------------

Add the following line to the sourceDirs configuration of the
Kotlin Maven plugin:

```xml
<sourceDir>${project.basedir}/target/generated-sources/rocker</sourceDir>
```

Hints
-----

As of Javalin 4 there is no support for double extension files. A new extension `.rhtml` is used for the integration.
For everything to work correctly template files have to end in with `.rocker.rhtml`.
