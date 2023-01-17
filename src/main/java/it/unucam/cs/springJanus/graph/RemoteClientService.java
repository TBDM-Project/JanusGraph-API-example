package it.unucam.cs.springJanus.graph;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.stereotype.Service;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Service
public class RemoteClientService {

    private GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using("localhost", 8182, "g"));

    public Object getVertices() {
        try {
            // this.g = traversal().withRemote(DriverRemoteConnection.using("localhost",
            // 8182, "g"));
            // g.addV("human").property("name", "stephen").property("age", 33);
            // g.addV("person").property("name", "stephen");
            return this.g.V().has("name", "bob").values("age").next();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    public void addVertex() {
        this.g.addV("human").property("name", "stephen").property("age", 33);
        this.g.addV("person").property("name", "bob").property("age", 272).iterate();
    }

}
