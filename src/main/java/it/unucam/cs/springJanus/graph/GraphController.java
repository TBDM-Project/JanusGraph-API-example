package it.unucam.cs.springJanus.graph;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getDirector")
    public List<Map<Object, Object>> getDepartementDirector() {
        return remoteClientService.getDirector();
    }

    @PostMapping("/filter")
    ResponseEntity<List<Map<Object, Object>>> filter(@Valid @RequestBody Map<String, String> params) {
        return ResponseEntity.ok(remoteClientService.filter(params));
    }

    @PostMapping("/filter_group")
    ResponseEntity<List<Map<Object, Object>>> filterGroup(@Valid @RequestBody Map<String, String> params) {
        String groupField = params.get("groupField");
        params.remove("groupField");
        System.out.println(params.toString());
        System.out.println(groupField);
        return ResponseEntity.ok(remoteClientService.filterGroup(params, groupField));
    }

    @PostMapping("/find_children")
    ResponseEntity<List<Map<Object, Object>>> findChildrenByName(@Valid @RequestBody Map<String, String> params) {
        String vertexName = params.get("vertexName");
        params.remove("vertexName");
        return ResponseEntity.ok(remoteClientService.findChildrenByName(params, vertexName));
    }

    @PostMapping("/find_entering")
    ResponseEntity<List<Map<Object, Object>>> findEnteringVertices(@Valid @RequestBody Map<String, String> params) {
        String vertexName = params.get("vertexName");
        params.remove("vertexName");
        return ResponseEntity.ok(remoteClientService.findEnteringVertices(params, vertexName));
    }

    @GetMapping("/export")
    public void export() {
        remoteClientService.exportGraph();

    }

}
