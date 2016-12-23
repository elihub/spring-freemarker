##### Management application products (Create, Delete, Update), with the following entities

* Product
* Category

Application build with Spring 3.2 with completely no XML configuration.

It uses Freemarker, Twitter Bootstrap, JQuery, Hibernate, embedded HSQLDB, and Maven.

There are three scripts:

* build.sh - cleans, compiles, builds WAR file and runs tests
* runTomcat.sh - searchers for CATALINA_COME, copies WAR file there and runs Tomcat
* runJetty.sh - runs app directly from sources with Jetty 8 from jetty-maven-plugin.

Make sure you have execution rights and click this link [http://localhost:8080/test](http://localhost:8080/test)