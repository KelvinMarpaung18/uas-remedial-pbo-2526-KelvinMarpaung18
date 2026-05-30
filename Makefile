# Simple Makefile for the Park-IT project

.PHONY: compile run test clean

compile:
	mvn compile

run:
	mvn exec:java "-Dexec.mainClass=pbo.f01.App"

test:
	mvn test

clean:
	mvn clean
