@echo off
echo Compiling code...
CALL mvn compile

echo Running tests...
CALL mvn test

echo Executing program on sample files...
CALL mvn exec:java -D exec.mainClass="Main" -D exec.args="input_sample.txt input_sample__out_good.txt input_sample__out_bad.txt"
CALL mvn exec:java -D exec.mainClass="Main" -D exec.args="input_new_rules.txt input_new_rules__out_good.txt input_new_rules__out_bad.txt"
CALL mvn exec:java -D exec.mainClass="Main" -D exec.args="input_bad_parser.txt input_bad__out_good.txt input_bad__out_bad.txt"