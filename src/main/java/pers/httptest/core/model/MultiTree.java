package pers.httptest.core.model;

import org.apache.log4j.Logger;
import pers.httptest.core.exception.CaseIlleagalException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultiTree {
    private static final Logger log= Logger.getLogger(MultiTree.class);
    private MultiTree fatherNode;
    private List<MultiTree> childrenList;
    private String name;

    private String type;
    private Map<String,Object> var;
    private static String TYPE="type";

    public MultiTree(){
        childrenList=new LinkedList<>();
        var=new HashMap<>();
    }

    public void setUp(MultiTree fatherNode, String name, Map<String,Object> map) throws CaseIlleagalException {
        setFatherNode(fatherNode);
        setName(name);
        if(map==null){
            return;
        }
        setType((String)map.get(TYPE));

        String ct;
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()==null){
                continue;
            }
            ct=entry.getValue().getClass().getName();
            if(ct.equals("java.lang.String")){
                putVar(entry.getKey(),entry.getValue());
            } else if(ct.equals("java.lang.Integer")){
                putVar(entry.getKey(),entry.getValue());
            } else if(ct.equals("java.util.LinkedHashMap")){
                Map<String,Object> value=(Map<String,Object>)entry.getValue();
                if(value.get(TYPE)!=null){
                    MultiTree mt=new MultiTree();
                    mt.setUp(this, entry.getKey(), value);
                    putChildrenList(mt);
                }else{
                    putVar(entry.getKey(),value);
                }
            }else{
                throw new CaseIlleagalException("case contains unexpect type:"+ct);
            }
        }
    }


    public MultiTree getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(MultiTree fatherNode) {
        this.fatherNode = fatherNode;
    }

    public List<MultiTree> getChildrenList() {
        return childrenList;
    }

    public void putChildrenList(MultiTree childrenList) {
        this.childrenList.add(childrenList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getVar() {
        return var;
    }

    public void putVar(String k,Object v){
        this.var.put(k,v);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
