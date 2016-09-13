#!/bin/bash

JAR_NAME="dist/lib/GoEuroDevTest.jar"
FAT_JAR_NAME="dist/lib/GoEuroDevTest-fat.jar"
MAIN_CLASS="com.goeuro.dev.GoEuroTest"

# Clean & Build application
ant clean && ant

echo
echo
echo "===== Run Tests ====="
echo

# Run simple jar
# $1 - message to display
# $2, ... - application args
function run() {
  echo $1
  shift
  java -cp libs/gson-2.3.1.jar:libs/opencsv-3.8.jar:$JAR_NAME $MAIN_CLASS $@
  echo
}

# Run fat jar 
# $1 - message to display
# $2, ... - application args
function run_fat() {
  echo $1
  shift
  java -jar $FAT_JAR_NAME $@
  echo
}


# Run several test cases
run "No args ..."
run "Too many args ..." "1" "2" "3"
run "No output file specified (use default output file ./output.csv) ..." "Berlin"
run "Output file specified ..." "Paris" "output/paris-output.csv"

echo
echo "Run fat ..."
run_fat "Output file specified ..." "Belgrade" "output/belgrade-output-fat.csv"


#echo "Done."
