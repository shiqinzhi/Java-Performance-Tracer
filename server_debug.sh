cd lib
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5006  -jar ./jpt-ui-backend-0.2-SNAPSHOT.jar