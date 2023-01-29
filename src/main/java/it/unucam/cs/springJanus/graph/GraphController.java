package it.unucam.cs.springJanus.graph;

import java.util.Map;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
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
    }

    @GetMapping("/connect")
    public String connect() {
        return this.remoteClientService.init();
    }

    @GetMapping("/vertices")
    public Object getVertices() {
        return remoteClientService.getVertices();
    }

    @GetMapping("/add")
    public void addVertex() {
        remoteClientService.addVertex();

    }

    @GetMapping("/export")
    public String export() {
        return remoteClientService.exportGraph();

    }

}
