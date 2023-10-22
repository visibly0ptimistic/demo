package com.example.demo.stack;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stack")
public class StackController {

    private final StackArrayBased<Integer> stack = new StackArrayBased<>();

    @PostMapping("/push")
    public String push(@RequestParam Integer value) {
        try {
            stack.push(value);
            return "Pushed " + value;
        } catch (StackException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/pop")
    public String pop() {
        try {
            return "Popped: " + stack.pop();
        } catch (StackException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/peek")
    public String peek() {
        try {
            return "Top of stack: " + stack.peek();
        } catch (StackException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/isEmpty")
    public String isEmpty() {
        return "Is Empty: " + stack.isEmpty();
    }

    @GetMapping("/toString")
    public String toStringRepresentation() {
        return stack.toString();
    }
}
