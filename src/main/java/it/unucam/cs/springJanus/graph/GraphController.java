package it.unucam.cs.springJanus.graph;

import java.util.List;
import java.util.Map;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/graph")
public class GraphController {

    private final RemoteClientService remoteClientService;

    @Autowired
    public GraphController(RemoteClientService remoteClientService) {
        this.remoteClientService = remoteClientService;
        this.remoteClientService.init();
        System.out.println("Connection successfull");
    }

    @GetMapping("/connect")
    public String connect() {
        return this.remoteClientService.init();
    }

    @GetMapping("/vertices")
    public List<Map<Object, Object>> getAllVertices() {
        return remoteClientService.getAllVertices();
    }

    @GetMapping("/edges")
    public List<Map<Object, Object>> getAllEdges() {
        return remoteClientService.getAllEdges();
    }

    @GetMapping("/countV")
    public List<Long> countVertices() {
        return remoteClientService.countVertices();
    }

    @GetMapping("/countE")
    public List<Long> countEdges() {
        return remoteClientService.countEdges();
    }

    @GetMapping("/export")
    public void export() {
        remoteClientService.exportGraph();

    }

}
