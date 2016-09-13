#!/bin/bash

JAR_NAME="go-euro-dev-test.jar"
MAIN_CLASS="com.goeuro.dev.GoEuroTest"

# Create .jar
jar cfe $JAR_NAME $MAIN_CLASS -C bin/ .

# Run Test Cases
echo "No args ..."
java -cp libs/gson-2.3.1.jar:libs/opencsv-3.8.jar:$JAR_NAME $MAIN_CLASS
echo
echo "To many args ..."
java -cp libs/gson-2.3.1.jar:libs/opencsv-3.8.jar:$JAR_NAME $MAIN_CLASS "1" "2" "3"
echo
echo "No output file specified ..."
java -cp libs/gson-2.3.1.jar:libs/opencsv-3.8.jar:$JAR_NAME $MAIN_CLASS "Berlin"
echo
echo "Output file specified ..."
java -cp libs/gson-2.3.1.jar:libs/opencsv-3.8.jar:$JAR_NAME $MAIN_CLASS "Paris" "output/paris-output.csv"
echo
echo "Done."
