package ScriptingSandbox.ScriptingSandbox;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class App {
  
  public interface Test {
    public String foo();
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    // create a script engine manager
    ScriptEngineManager factory = new ScriptEngineManager();

    List<ScriptEngineFactory> factories = factory.getEngineFactories();
    System.out.println("There are " + factories.size() + " script engine factories.");
    for (ScriptEngineFactory sef : factories) {
      System.out.println("Engine: " + sef.getEngineName());
    }

    // create a JavaScript engine
    ScriptEngine engine = factory.getEngineByName("JavaScript");

    // evaluate JavaScript code from String
    engine.eval("print('In here I am evaluating JavaScript code from a Java String: ' + (1+1));");

    engine.put("name", "Carlos");

    URL scriptUrl = ClassLoader.getSystemClassLoader().getResource("myscript.js");
    engine.eval(new InputStreamReader(scriptUrl.openStream()));

    Invocable inv = (Invocable) engine;

    // invoke the global function named "hello"
    Object o = inv.invokeFunction("foo", "world" );
    System.out.println(o);
    System.out.println(o.getClass());

    Object implementsRunnable = engine.get("implementsRunnable");
    inv.invokeMethod(implementsRunnable, "run");

    new Thread(inv.getInterface(implementsRunnable, Runnable.class)).start();

    Test test = inv.getInterface(implementsRunnable, Test.class);

    String resultFromFoo = test.foo();

    System.out.println(resultFromFoo);

  }

}