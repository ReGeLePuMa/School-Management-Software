.PHONY build: src

src:
	javac -d PROIECT/ -sourcepath SURSE -verbose SURSE/*.java

.PHONY test: test

test:
	java -cp PROIECT/ Test

.PHONY run: exec

exec:
	java -cp PROIECT/ Main > /dev/null

.PHONY clean:
	rm -rf PROIECT/*.class