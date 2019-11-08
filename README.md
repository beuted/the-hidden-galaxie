
The Hidden Galaxie : 2012
=========================


RPG developped in Java.
Interessting points of this project : circular map, A* algorithm, self-developped GUI, map editor

How to compile & run
--------------------

* to be run in `src/`
> `javac -cp ../lib/slick.jar -d ../bin ./**/*.java`


* to be run at root `./`
> add `./the-hidden-galaxie/lib` to your path (to do it temporarly on windows: `export PATH=$PATH:/c/my/path/to/project/the-hidden-galaxie/lib`)
> run game in 32 bits `c:/Program\ Files\ \(x86\)/Java/jdk1.8.0_201/bin/java -d32 -cp "./lib/*;./bin" jeu.SlickGame`

How to play
-----------

### Map Editor

- E      : show list of blocks
- I      : show list of items
- S      : save the map
- ESCAPE : return to the main menu

### Game :

- I           : Show inventory
- RIGHT CLICK : Move
- LEFT CLICK  : Pick up and drop items
- ESCAPE      : return to the main menu

