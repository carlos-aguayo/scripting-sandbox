println("\nthis is the beginning of myscript.js");

var x = 1;
var y = x + 1;
println(x);

(function(){
  var a = 3 + y;
  println(a);
})();

println("length of name: " + name.length);
println("name: " + name);

function foo(x) {
  return "this is a function in a JavaScript file that takes the following parameter: "+x;
}

var implementsRunnable = {
  run : function() {
    println("I'm running!");
  },
  foo : function() {
    println("I'm in foo");
    return"output of foo";
  }
};
