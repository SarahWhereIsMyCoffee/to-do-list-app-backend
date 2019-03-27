all: clean-and-package run

clean:
	mvn clean

clean-and-package:
	mvn clean package

package:
	mvn package

run:
	java -jar ./target/TaskManagerApp-1.0-SNAPSHOT.jar
