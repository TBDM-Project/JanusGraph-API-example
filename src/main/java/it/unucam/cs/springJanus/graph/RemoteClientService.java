package it.unucam.cs.springJanus.graph;

import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.IO;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Service;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

import java.util.List;
import java.util.Map;

@Service
public class RemoteClientService {

    private GraphTraversalSource g;

    public String init() {
        try {
            this.g = traversal().withRemote("conf/remote-graph.properties");
            // this.g = traversal().withRemote(DriverRemoteConnection.using("localhost",
            // 8182, "g"));
            return "Connection successfull";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Map<Object, Object>> getAllVertices() {
        try {
            return this.g.V().valueMap().toList();

        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return null;

    }

    public List<Map<Object, Object>> getAllEdges() {
        try {
            return this.g.E().valueMap().toList();

        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return null;

    }

    public List<Long> countVertices() {
        try {
            return this.g.V().count().toList();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return null;
    }

    public List<Long> countEdges() {
        try {
            return this.g.E().count().toList();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return null;
    }

    public void exportGraph() {
        /*
         * String path = "path";
         * try {
         * FileOutputStream fos = new FileOutputStream("my-graph.json");
         * GraphSONWriter.build().create().writeGraph(fos, this.g.getGraph());
         * } catch (IOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         * this.g.io("").with(IO.writer, IO.graphml).write().iterate();
         */
        this.g.io("graph-prova.xml").write().iterate();

    }

}
