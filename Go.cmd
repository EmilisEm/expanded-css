set PATH=%PATH%;
mvn compile test
mvn exec:java -D exec.mainClass="Main" -D exec.args="input_sample.txt input_sample__out_good.md input_sample__out_bad.txt"
mvn exec:java -D exec.mainClass="Main" -D exec.args="input_new_rules.txt input_new_rules__out_good.md input_new_rules__out_bad.txt"
mvn exec:java -D exec.mainClass="Main" -D exec.args="input_bad.txt input_bad__out_good.md input_bad__out_bad.txt"

