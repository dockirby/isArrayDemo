# isArrayDemo
A project going through too much effort to show how an NPM module 'isArray' can return a false positive, utilizing Mozilla's Java based JavaScript engine Rhino

### Build
Have Maven and Java 8 installed on your machine, and use the command

    mvn clean package

### Run
Execute the compiled jar in a JVM to see the example run. For convenience, the JAR is has a version with the dependencies bundled. And example to run is the following

    java -jar Is-Array-Test-1.0.0-jar-with-dependencies.jar

### About
I went though a bit too much effort to prove that this statement I made was accurate:
[Wow, they made an isArray Function that can actually return a false positive.](https://www.reddit.com/r/programming/comments/886zji/why_has_there_been_nearly_3_million_installs_of/dwiw3ja/)

About the libary found [here](https://github.com/juliangruber/isarray)

To be fair, this will almost always work. I actually am not certain you can get it to fail on a web browser anyone actually uses. But I have worked enough in old/weird JavaScript engines to spot a case where it can fail.

First, to point out what that that NPM module is actually doing if it isn't obvious. For reference, the code is equivalent to the following:
The library is equivalent to the following:


    var toString = {}.toString;
    var isArray = Array.isArray || function (arr) {
      return toString.call(arr) == '[object Array]';
    };

It is first checking if Array.isArray exists, and uses it if available. If not, it makes it own isArray function, since Array.isArray was defined in ECMAScript 5. It isn't using "instanceof Array" since that also can fail in different ways.

Since Array.isArray was defined in ECMAScript 5, I'll use an engine with the previous spec, which is ECMAScript 3/"JavaScript 1.5".

Also, I want to point out you can just redefine Object.prototype.toString, but let's there is way I know of to make such an isArray function return a false positive. 

Namely, you just need to define a [Host Object] (http://www.ecma-international.org/ecma-262/5.1/#sec-4.3.8) with the name Array. Why would you do that? Let's leave it at Enterprise Applications. 

Since I want an engine that targets ECMAScript 3/JavaScript 1.5, I'm going to use and old version of [Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino), Specifically [1.5R3](https://mvnrepository.com/artifact/rhino/js/1.5R3]), since it is tracked on central Maven repos.

A bit more background info. Javascript Objects had an internal property called "Class" before ECMAScript 2015, which is what [Object.prototype.toString would would use in those versions](http://www.ecma-international.org/ecma-262/5.1/#sec-15.2.4.2)

[In the ECMAScript 5 and 5.1 specs, it says you are not to give a Host Object a Class property that is used by a built in Object](http://www.ecma-international.org/ecma-262/5.1/#sec-8.6.2)

But the point of the function is work with Engines that aren't ECMAScript 5 compliant. And the previous spec, ECMAScript 3, said the opposite for the Class Property([As seen in the second paragraph on page 34 [44/191] of the spec published by Mozilla](https://www-archive.mozilla.org/js/language/E262-3.pdf)):
> "The value of the [[Class]] property is defined by this specification for every kind of built-in object. The value of the [[Class]] property of a host object may be any value, even a value used by a built-in object for its [[Class]] property."

So, at the time Rhino 1.5R3 was made, it was well with the specs that you were allowed to names other Host Objects Array. 

So to be somewhat realistic in my example, I define a new global object that represents another system, and that object has a child object called Array. If you look though the Java code, you will see that SomeOldApplication.Array is actually a class called NotArray.

All in all, I put far to much time in this.
