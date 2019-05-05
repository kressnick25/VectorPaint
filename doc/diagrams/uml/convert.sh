# convert
## -o ../ --- ouput directory is parent dir
## ./* ---  all files in current dir
## -x plantuml.jar --- exclude jar
## -x *.sh --- exclude this shell script

java -jar plantuml.jar -o ../ ./* -x plantuml.jar -x *.sh 

