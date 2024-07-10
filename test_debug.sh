export jpt_client_log=true
cd lib
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -javaagent:./jpt-agent-0.2-SNAPSHOT.jar \
      -cp ./jpt-collector-0.2-SNAPSHOT.jar:./jpt-tester-0.2-SNAPSHOT.jar \
      com.thirdpart.jpt.test.JPT_Tester_Application