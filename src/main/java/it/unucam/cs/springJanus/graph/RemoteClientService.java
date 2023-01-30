package it.unucam.cs.springJanus.graph;

import org.apache.tinkerpop.gremlin.process.traversal.IO;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Service;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;
import java.util.Map;

@Service
public class RemoteClientService {

    private GraphTraversalSource g;

    public String init() {
        try {
            this.g = traversal().withRemote("conf/remote-graph.properties");
            return "Connection successfull";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public GraphTraversal<Vertex, Map<Object, Object>> getVertices() {
        try {
            return this.g.V().elementMap();
            // GraphTraversal<Vertex, Long> res = this.g.V().has("type", "airport").count();

        } catch (Exception e) {

        }
        return null;

    }

    public void addVertex() {
        this.g.io("/home/niccolo/springJanus/src/main/java/it/unucam/cs/springJanus/graph/graph.xml")
                .with(IO.reader, IO.graphml).read().iterate();
        // this.g.addV("human").property("name", "stephen").property("age", 33);
        // this.g.addV("person").property("name", "bob").property("age", 272).iterate();
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
