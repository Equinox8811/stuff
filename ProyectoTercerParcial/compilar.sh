#!/bin/bash
rm *.class
javac -classpath .:txtmark-0.11.jar:itextpdf-5.4.4.jar ProyectoIUG.java
java -classpath .:txtmark-0.11.jar:itextpdf-5.4.4.jar ProyectoIUG
