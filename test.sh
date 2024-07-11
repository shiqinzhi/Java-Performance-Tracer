export jpt_client_log=true
cd lib
java -javaagent:./jpt-agent-0.2-SNAPSHOT.jar \
      -cp ./jpt-collector-0.2-SNAPSHOT.jar:./jpt-tester-0.2-SNAPSHOT.jar \
      com.thirdpart.jpt.test.Test1