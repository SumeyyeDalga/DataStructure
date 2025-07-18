collect:
	find src -name "*.java" > sources.txt

build: collect
	@mkdir -p build
	javac -d build @sources.txt

javadoc:
	javadoc -d doc @sources.txt

run: build
	java -cp build Main.Main $(ARGS)

clean:
	rm -rf build sources.txt