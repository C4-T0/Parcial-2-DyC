package com.example.parcial2.controller.Memento;

import java.util.ArrayList;

public class CareTaker {
    private ArrayList<MementoListThought> undoStates = new ArrayList<>();
    private ArrayList<MementoListThought> redoStates = new ArrayList<>();

    public void addUndoElement(MementoListThought m) {
        redoStates.clear();
        if (undoStates.size() == 10) {
            undoStates.remove(0);
        }
        undoStates.add(m);
    }

    public void addRedoElement(MementoListThought m) {
        if (redoStates.size() == 10) {
            redoStates.remove(0);
        }
        redoStates.add(m);
    }

    public MementoListThought undoMemento() {
        MementoListThought temp = undoStates.get(undoStates.size() - 1);
        undoStates.remove(temp);
        return temp;
    }

    public MementoListThought redoMemento(MementoListThought mementoListThought) {
        MementoListThought temp = redoStates.get(redoStates.size() - 1);
        undoStates.add(mementoListThought);
        redoStates.remove(temp);
        return temp;
    }


    public int getMementoLengthList() {
        return undoStates.size();
    }

    public int getRedoMementoLengthList() {
        return redoStates.size();
    }
}
