package pers.httptest.core.model;

import java.util.LinkedList;
import java.util.List;

public class Multitree<T> {
    private Multitree fatherNode;
    private List<Multitree> childrenList;
    private T node;

    private Multitree(){
    }
    public Multitree(Multitree fatherNode,T node){
        this.fatherNode=fatherNode;
        this.node=node;
        childrenList=new LinkedList<Multitree>();
    }

    public void addChild(Multitree child){
        childrenList.add(child);
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<Multitree> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Multitree> childrenList) {
        this.childrenList = childrenList;
    }

    public Multitree getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(Multitree fatherNode) {
        this.fatherNode = fatherNode;
    }
}
