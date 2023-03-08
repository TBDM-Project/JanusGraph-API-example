package it.unucam.cs.springJanus.graph;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Service;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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

    public List<Map<Object, Object>> filter(Map<Attributes, String> params) {

        Iterator<Map.Entry<Attributes, String>> iterator = params.entrySet().iterator();
        GraphTraversal<Vertex, Vertex> query = this.g.V();
        while (iterator.hasNext()) {
            Map.Entry<Attributes, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            query = query.has(entry.getKey().toString(), entry.getValue());
        }
        List<Map<Object, Object>> res = query.valueMap().toList();
        return res;

        /*
         * GraphTraversal<Vertex, Vertex> query = this.g.V().has("type", "class_room");
         * return query.valueMap().toList();
         * 
         * this.g.V().has("type", "class_room").valueMap().toList();
         * this.g.V().has("category", "space").has("type",
         * "department").valueMap().toList();
         * this.g.V().has("category", "sensor").valueMap(true, "name").toList();
         */
    }

    public List<Map<Object, Object>> filterGroup(Map<Attributes, String> params, String groupField) {

        Iterator<Map.Entry<Attributes, String>> iterator = params.entrySet().iterator();
        GraphTraversal<Vertex, Vertex> query = this.g.V();
        while (iterator.hasNext()) {
            Map.Entry<Attributes, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            query = query.has(entry.getKey().toString(), entry.getValue());
        }
        try {
            List<Map<Object, Object>> res = query.valueMap().group().by(groupField).toList();
            // System.out.println(query.valueMap().group().by(groupField).toList());
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Map<Object, Object>> findChildrenByName(Map<Attributes, String> params, String vertexName) {

        Iterator<Map.Entry<Attributes, String>> iterator = params.entrySet().iterator();
        GraphTraversal<Vertex, Vertex> query = this.g.V().hasLabel(vertexName).out();
        while (iterator.hasNext()) {
            Map.Entry<Attributes, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            query = query.has(entry.getKey().toString(), entry.getValue());
        }
        List<Map<Object, Object>> res = query.valueMap().toList();
        return res;

    }

    public List<Map<Object, Object>> findEnteringVertices(Map<Attributes, String> params, String vertexName) {

        Iterator<Map.Entry<Attributes, String>> iterator = params.entrySet().iterator();
        GraphTraversal<Vertex, Vertex> query = this.g.V().hasLabel(vertexName).in();
        while (iterator.hasNext()) {
            Map.Entry<Attributes, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            query = query.has(entry.getKey().toString(), entry.getValue());
        }
        List<Map<Object, Object>> res = query.valueMap().toList();
        return res;

    }

    public List<Map<Object, Object>> getDirector() {

        List<Map<Object, Object>> res = this.g.V().has("type", "city").has("name",
                "Camerino")
                .repeat(__.out().simplePath()).until(__.has("type",
                        "department_director"))
                .valueMap()
                .toList();
        return res;

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
