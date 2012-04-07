# Makefile version 4 for building Tetrize

program = tetrize


all: clean $(program)

$(program):
	find src/ -name "*.java" > java.list
	javac @java.list -d bin
	cp -r src/com/flogisoft/tetrize/res/ bin/com/flogisoft/tetrize/
	jar -cfe $(program).jar com.flogisoft.tetrize.Launcher -C bin/ com

clean:
	rm -rf bin/*
	rm -f java.list
	rm -f $(program).jar
