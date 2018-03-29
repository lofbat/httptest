package pers.httptest.core.model;

import java.util.LinkedList;
import java.util.List;

public class GroupModel {
    private static final String DEFAULT_NAME="testgroup";
    private static final String DEFAULT_DESCRIPTION="this is a group";

    private String name;
    private String description;
    private List<ControllerModel> controllerList;


    public GroupModel() {
        this(DEFAULT_NAME,DEFAULT_DESCRIPTION);
    }

    public GroupModel(String name,String des){
        this.name=name;
        this.description=des;
        this.controllerList=new LinkedList<ControllerModel>();
    }

    public void addController(ControllerModel controller){
        controllerList.add(controller);
    }

}
