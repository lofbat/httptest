package pers.httptest.core.model;

import pers.httptest.core.exception.CaseIlleagalException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Multitree {
    private Multitree fatherNode;
    private List<Multitree> childrenList;
    private String name;
    private Map<String,String> var;

    public Multitree(){
        childrenList=new LinkedList<>();
        var=new HashMap<>();
    }

    public void setUp(Multitree fatherNode,String name,Map<String,Object> map) throws CaseIlleagalException {
        setFatherNode(fatherNode);
        setName(name);
        String entryName;
        for(Map.Entry<String,Object> entry:map.entrySet()){
            entryName=entry.getValue().getClass().getName();
            if(entryName.equals("String")){
                putVar(entry.getKey(),(String)entry.getValue());
            }
            else if(entryName.equals("Map")){
                Multitree mt=new Multitree();
                mt.setUp(this,entry.getKey(),(Map<String,Object>)entry.getValue());
                putChildrenList(mt);
            }else{
                throw new CaseIlleagalException("case contains unexpect type:"+entryName);
            }
        }
    }


    public Multitree getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(Multitree fatherNode) {
        this.fatherNode = fatherNode;
    }

    public List<Multitree> getChildrenList() {
        return childrenList;
    }

    public void putChildrenList(Multitree childrenList) {
        this.childrenList.add(childrenList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getVar() {
        return var;
    }

    public void putVar(String k,String v){
        this.var.put(k,v);
    }

}
