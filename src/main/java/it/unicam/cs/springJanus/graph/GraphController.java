package it.unicam.cs.springJanus.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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

    @GetMapping("/vertices/{id}")
    public Object getVertexById(@PathVariable("id") Object id) {
        return remoteClientService.getVertexById(id);
    }

    @GetMapping("/vertices/label")
    public Object getVerticesByLabel(@RequestParam("label") String label) {
        return remoteClientService.getVerticesByLabel(label);
    }

    @GetMapping("/vertices/properties")
    public Object getVerticesByProperties(
            @RequestParam("properties") List<String> properties,
            @RequestParam("values") List<Object> values) throws Exception {
        if (properties.size() != values.size())
            throw new Exception("Mismatch between properties and values amount");
        Iterator<String> p = properties.iterator();
        Iterator<Object> v = values.iterator();
        Map<String, Object> propertyValueMap = IntStream.range(0, properties.size()).boxed()
                .collect(Collectors.toMap(_i -> p.next(), _i -> v.next()));
        return remoteClientService.getVerticesByProperties(propertyValueMap);
    }

    @PostMapping("/vertices")
    public Object addVertex(@RequestParam("label") String label) {
        return remoteClientService.addVertex(label);
    }

    @DeleteMapping("/vertices/{id}")
    public void removeVertex(@PathVariable("id") Object id) {
        remoteClientService.removeVertex(id);
    }

    @PostMapping("/vertices/{id}")
    public void addPropertyV(@PathVariable("id") Object id, @RequestParam("key") String key,
            @RequestParam("value") Object value) {
        remoteClientService.addPropertyV(id, key, value);
    }

    @GetMapping("/edges/{id}")
    public Object getEdgeById(@PathVariable("id") Object id) {
        return remoteClientService.getEdgeById(id);
    }

    @GetMapping("/edges/label")
    public Object getEdgesByLabel(@RequestParam("label") String label) {
        return remoteClientService.getEdgesByLabel(label);
    }

    @GetMapping("/edges/properties")
    public Object getEdgesByProperties(
            @RequestParam("properties") List<String> properties,
            @RequestParam("values") List<Object> values) throws Exception {
        if (properties.size() != values.size())
            throw new Exception("Mismatch between properties and values amount");
        Iterator<String> p = properties.iterator();
        Iterator<Object> v = values.iterator();
        Map<String, Object> propertyValueMap = IntStream.range(0, properties.size()).boxed()
                .collect(Collectors.toMap(_i -> p.next(), _i -> v.next()));
        return remoteClientService.getEdgesByProperties(propertyValueMap);
    }

    @PostMapping("/edges")
    public Object addEdge(@RequestParam("label") String label, Object from, Object to) {
        return remoteClientService.addEdge(label, from, to);
    }

    @DeleteMapping("/edges/{id}")
    public void removeEdge(@PathVariable("id") Object id) {
        remoteClientService.removeEdge(id);
    }

    @PostMapping("/edges/{id}")
    public void addPropertyE(@PathVariable("id") Object id, @RequestParam("key") String key,
            @RequestParam("value") Object value) {
        remoteClientService.addPropertyE(id, key, value);
    }

    @GetMapping("/getDirector")
    public List<Map<Object, Object>> getDepartementDirector() {
        return remoteClientService.getDirector();
    }

    @PostMapping("/filter")
    ResponseEntity<List<Map<Object, Object>>> filter(@Valid @RequestBody Map<Attributes, String> params) {
        return ResponseEntity.ok(remoteClientService.filter(params));
    }

    @PostMapping("/filter_group")
    ResponseEntity<List<Map<Object, Object>>> filterGroup(@Valid @RequestBody Map<Attributes, String> params) {
        String groupField = params.get(Attributes.groupField);
        params.remove(Attributes.groupField);

        System.out.println(params.toString());
        System.out.println(groupField);
        return ResponseEntity.ok(remoteClientService.filterGroup(params, groupField));
    }

    @PostMapping("/find_children")
    ResponseEntity<List<Map<Object, Object>>> findChildrenByName(@Valid @RequestBody Map<Attributes, String> params) {
        String vertexName = params.get(Attributes.vertexName);
        params.remove(Attributes.vertexName);
        return ResponseEntity.ok(remoteClientService.findChildrenByName(params, vertexName));
    }

    @PostMapping("/find_entering")
    ResponseEntity<List<Map<Object, Object>>> findEnteringVertices(@Valid @RequestBody Map<Attributes, String> params) {
        String vertexName = params.get(Attributes.vertexName);
        params.remove(Attributes.vertexName);
        return ResponseEntity.ok(remoteClientService.findEnteringVertices(params, vertexName));
    }

    @GetMapping("/export")
    public void export() {
        remoteClientService.exportGraph();

    }

    @GetMapping("/load")
    public void loadData() {
        remoteClientService.importData();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleValidationExceptions2() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
    }

}
