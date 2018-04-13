package pers.httptest.core.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlanModel {
    private static final String DEFAULT_NAME="testplan";
    private static final String DEFAULT_DESCRIPTION="this is a plan";

    private String name;
    private String description;
    private List<GroupModel> groupList;

    public PlanModel() {
        this(DEFAULT_NAME,DEFAULT_DESCRIPTION);
    }

    public PlanModel(String name,String des){
        this.name=name;
        this.description=des;
        this.groupList=new LinkedList<GroupModel>();
    }

    public PlanModel(String name, String des, Map map){
        this(name,des);
        //parserChild(map);
    }

    /*private void parserChild(Map<String,Map> map){
        for(Map.Entry<String,Map> entry:map.entrySet()){
            GroupModel group=new GroupModel(entry.getKey(),);
            addGroup(group);
        }
    }*/

    public void addGroup(GroupModel group){
        groupList.add(group);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
