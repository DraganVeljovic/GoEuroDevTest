# GoEuroDevTest

## Start
Import in Eclipse or run from command line.

### Command line
#### Use test.sh
Running the script will build a project for you and run some tests (see script for details).

Expected outputs:
```
output.csv
output/paris-output.csv
output/belgrade-output-fat.csv
```

#### Build 
From project root path run:
```
ant
```

You will have ```GoEuroDevTest.jar``` and ```GoEuroDevTest-fat.jar``` (with all dependencies) at ```dist/lib/```

#### Run
```
java -jar GoEuroTest-fat.jar "Belgrade" "output/belgrade.csv"
```
