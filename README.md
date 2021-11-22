# pokedox

Pokedox is my version of the pokemon finder project,

**How to run**
Install _java_ JDK 1.8 (you will most likely have this if you are using intellij)
Install _maven_
Import project into intellij idea, the dependencies will be downloaded automatically, and the project should be ready to
run but if that's not the case then kindly run the `mvn install` command in the directory of the project where you can
find the _pom.xml_ file (this is in the root directory).

**Containerization**

This application is capable of containerizing itself without requiring a docker file, but I have added a docker file just
in case, I integrated a plugin called _jib_ that will build docker images locally or to a container registry.

(Recommended) To build an image to your local docker daemon simply run the command _"mvn jib:dockerBuild"_ in the root
directory, on completion you'll have the image "true-layer-docker-registry/pokedox" ready to run.

To build an image to a private docker registry please change the value between these tags _"<docker.image.prefix>"_  to
the full path of your remote registry and then run the command _"mvn jib:build"_, if you're currently authenticated on
the registry then the image will be built directly onto the registry.

If you decide to use the docker file, please run a maven install in the root directory _"mvn install"_
Next run a docker build : "docker build -t pokedox-application.jar ."
And Finally : "docker run -p 5000:5000 pokedox-application.jar"

**Translation**
The translation decision is made using a _chain of command pattern_, I chose this approach because I considered
that there could be changes or additions to the business rules in the future, this approach makes it easier to test, extend without cascading _if_ statements or modifying existing rules.

**Api Documentation**
the api Documentation can be found on the swagger link at http://localhost:5000/swagger-ui/
you can find details about the application like health by executing the actuator endpoint which you'll find on swagger.