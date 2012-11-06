package ScriptingSandbox.ScriptingSandbox;

import java.io.InputStreamReader;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * http://code.google.com/p/jsonpath/wiki/Javascript
 */
public class JSONPath {
  
  public static void eval(ScriptEngine engine, String script) throws Exception {
    URL scriptUrl = ClassLoader.getSystemClassLoader().getResource(script);
    engine.eval(new InputStreamReader(scriptUrl.openStream()));
  }

  public static void main(String[] args) throws Exception {
    ScriptEngineManager factory = new ScriptEngineManager();

    // create a JavaScript engine
    ScriptEngine jsEngine = factory.getEngineByName("JavaScript");
    
    eval(jsEngine, "jsonpath-0.8.0.js");
    eval(jsEngine, "json.js");
    eval(jsEngine, "jsondata.js");
    
    System.out.println(jsEngine.eval("jsonPath(json, \"$.store.book[*].author\").toJSONString()"));


  }

}