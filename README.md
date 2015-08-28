Fault Localization Research 

This repo contains the implementations of two fault localization tools(GZoltar and Tarantula), 
as well as a test program(Triangle).

Test Program: Triangle
----------------------
Triangle.java is a short java program, there is a fault in line 18 of the program.
TestSuite.java is a suite with 33 tests for triangle, the command mvn test should report 4 failed tests

GZoltar
-------
GZoltar is one of the fault localization tool in this repo, it can be used as a plugin on Eclipse, for this project we use
the GZoltar jar file which will calculate the suspiciousness of lines, it is found in the lib dir. 

Tarantula
---------
Tarantula the second fault localization tool in this repo. For this project we obtain a Json format cov matrix of the program
and then calculate the suspiciousness using the TarantulaMain.java class.

TarantulaMain requires several arguments to be run:
	- The cov-matrix in Json format
	- The class file of the Test program


Getting started
---------------
1. Test and install primitive hamcrest onto the local repo (needed for Tarantula)
	- cd primitive-hamcrest
	- mvn test
	- mvn install

2. Compile tacoco (needed for Tarantula)
	- cd tacoco
	- mvn compile

3. Build triangle
	- cd triangle
	- mvn clean package

Evaluating bugs using GZoltar
1. Enter the script directory
	- cd script

2. Enter the following command
	- ./run-gzoltar ../triangle triangle target/classes/:target/test-classes
	- (Optional) ./run-gzoltar ../triangle triangle target/classes/:target/test-classes | grep "Triangle.java" will print only the suspiciousness for lines in Triangle

3. Generalize; the general form is 
	- ./run-gzoltar project dir test package to execute class/path


Evaluating bugs using Tarantula
1. Run Tacoco
	- ./run-tacoco /abs/path/to/repo/triangle /abs/path/to/repo/tacoco

2. Run Jacoco.exec Analyzer
	- ./run-jacoco /abs/path/to/repo/triangle triangle /abs/path/to/repo/tacoco

3. Create a .class file for the Test program
	- cd /triange/path/to/TestSuite.java
	- javac TestSuite.java
	- mv TestSuite.class /abs/path/to/repo/tarantula

3. Run TarantulaMain 
	- java TarantulaMain /abs/path/to/repo/tacoco/triange-mcn-compact-cov-matrix.json TestSuite.class



