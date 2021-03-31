all: run

clean:
	rm -f out/Bluck.jar out/BoyerMoore.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java
	@javac -cp out/parcs.jar src/Bluck.java
	@jar cf out/Bluck.jar -C src Bluck.class
	@rm -f src/Bluck.class

out/BoyerMoore.jar: out/parcs.jar src/BoyerMoore.java
	@javac -cp out/parcs.jar src/BoyerMoore.java
	@jar cf out/BoyerMoore.jar -C src BoyerMoore.class
	@rm -f src/BoyerMoore.class

build: out/Bluck.jar out/BoyerMoore.jar

run: out/Bluck.jar out/BoyerMoore.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck

