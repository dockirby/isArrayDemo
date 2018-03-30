Print.out("Now demoing potential failure of npm module \"isArray\"");
Print.out("Is Array.isArray defined: " + (Array.isArray !== undefined));
Print.out("isArray Function" + isArray);

var realArray = [];
var fakeArray = SomeOldApplication.Array;

Print.out("isArray on realArray, an actual JavaScript Array object: " + isArray(realArray));
Print.out("isArray on fakeArray, a SomeOldApplication.Array object: " + isArray(realArray));

Print.out("That second one is not an JavaScript Array at all, but the isArray function returns true\n");
Print.out("realArray.constructor: " + realArray.constructor);
Print.out("fakeArray.constructor: " + fakeArray.constructor);

Print.out("\So a false positive of isArray is demonstrated.\n");

Print.out("Will now call the kirbyDance method of fakeArray, to show that it isn't the same object");
fakeArray.kirbyDance(10);

Print.out("Will also show kirbyDance is not part of normal Array Object");
try {
	Array.kirbyDance(15);
} catch (e) {
	Print.out(e);
}

try {
	realArray.kirbyDance(15);
} catch (e) {	
	Print.out(e);
}

Print.out("And thats it");
fakeArray.kirbyDance(8);
fakeArray.kirbyDance(9);
fakeArray.kirbyDance(7);