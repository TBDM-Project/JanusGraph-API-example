package it.unucam.cs.springJanus.graph;

import org.apache.tinkerpop.gremlin.process.traversal.IO;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class RemoteClientService {

    private GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using("localhost", 8182, "g"));

    public Object getVertices() {
        try {
            // this.g = traversal().withRemote(DriverRemoteConnection.using("localhost",
            // 8182, "g"));
            // g.addV("human").property("name", "stephen").property("age", 33);
            // g.addV("person").property("name", "stephen");
            // return this.g.V().has("name", "bob").values("age").next();
            return this.g.V().has("name", "josh").next();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    public void addVertex() {
        this.g.io("/home/niccolo/springJanus/src/main/java/it/unucam/cs/springJanus/graph/graph.xml")
                .with(IO.reader, IO.graphml).read().iterate();
        // this.g.addV("human").property("name", "stephen").property("age", 33);
        // this.g.addV("person").property("name", "bob").property("age", 272).iterate();
    }

    public String exportGraph() {
        String path = "path";
        try {
            FileOutputStream fos = new FileOutputStream("my-graph.json");
            GraphSONWriter.build().create().writeGraph(fos, this.g.getGraph());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // this.g.io("").with(IO.writer, IO.graphml).write().iterate();

        return path;
    }

}
