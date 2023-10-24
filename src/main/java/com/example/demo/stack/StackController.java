package com.example.demo.stack;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stack")
public class StackController {

    private final StackArrayBased<Integer> stack = new StackArrayBased<>();

    @PostMapping("/push")
    public ResponseEntity<Map<String, Object>> push(@RequestParam(required = false) Integer value) {
        Map<String, Object> response = new HashMap<>();
        if (value == null) {
            response.put("error", "Value cannot be null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (stack.isFull()) {
            response.put("error", "Stack is full");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            stack.push(value);
            response.put("message", "Pushed: " + value);
            response.put("stack", stack.toList()); // Include the server-side stack in the response using the toList() method
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StackException e) {
            // Log the exception for debugging
            System.out.println("StackException: " + e.getMessage());
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pop")
    public ResponseEntity<Map<String, Object>> pop() {
        Map<String, Object> response = new HashMap<>();
        if (stack.isEmpty()) {
            response.put("error", "Stack is empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Integer poppedValue = stack.pop();
            response.put("message", "Popped: " + poppedValue);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StackException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/peek")
    public ResponseEntity<Map<String, Object>> peek() {
        Map<String, Object> response = new HashMap<>();
        if (stack.isEmpty()) {
            response.put("error", "Stack is empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Integer topValue = stack.peek();
            response.put("message", "Top of stack: " + topValue);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StackException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isEmpty")
    public ResponseEntity<Map<String, Object>> isEmpty() {
        Map<String, Object> response = new HashMap<>();
        boolean empty = stack.isEmpty();
        response.put("message", "Is Empty: " + empty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/isFull")
    public ResponseEntity<Map<String, Object>> isFull() {
        Map<String, Object> response = new HashMap<>();
        boolean full = stack.isFull();
        response.put("message", "Is Full: " + full);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/setMaxSize")
    public ResponseEntity<Map<String, Object>> setMaxSize(@RequestParam Integer newSize) {
    Map<String, Object> response = new HashMap<>();
    try {
        stack.setMaxSize(newSize);
        response.put("message", "Max size set to " + newSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    }


    @PostMapping("/popAll")
    public ResponseEntity<Map<String, Object>> popAll() {
        Map<String, Object> response = new HashMap<>();
        stack.popAll();
        response.put("message", "Stack cleared.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stackList")
    public ResponseEntity<Map<String, Object>> stackList() {
        Map<String, Object> response = new HashMap<>();
        List<Integer> stackList = stack.toList();  // Using the toList() method here
        response.put("stack", stackList);  // Using "stack" as the key for the list
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
