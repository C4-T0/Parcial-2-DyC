package com.example.parcial2.controller.Memento;

import com.example.parcial2.model.pojo.Thought;

import java.util.List;

public class MementoListThought {
    public List<Thought> state;

    public MementoListThought(List<Thought> state) {
        this.state = state;
    }

    public List<Thought> returnState() {
        return this.state;
    }
}

