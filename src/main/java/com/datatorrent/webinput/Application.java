/**
 * Put your copyright and license info here.
 */
package com.datatorrent.webinput;

import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.ConsoleOutputOperator;
import com.datatorrent.lib.io.PubSubWebSocketAppDataQuery;
import org.apache.hadoop.conf.Configuration;

@ApplicationAnnotation(name="MyFirstApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    PubSubWebSocketAppDataQuery pubSub = dag.addOperator("webSocketAppDataQuery", PubSubWebSocketAppDataQuery.class);
    pubSub.setTopic("TestBugTopic");
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());
    dag.addStream("randomData", pubSub.outputPort, cons.input);
  }
}
