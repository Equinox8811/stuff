#!/bin/bash
rm *.class
javac -classpath .:txtmark-0.11.jar ProyectoIUG.java
java -classpath .:txtmark-0.11.jar ProyectoIUG