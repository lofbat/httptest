package pers.httptest.core;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import pers.httptest.core.exception.FileIlleagalException;
import pers.httptest.core.model.PlanModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class Driver {
    private static final Logger log = Logger.getLogger(Driver.class);

    private Driver(){}

    private static void start(File file){
        Map fileMap=getYamlFileMap(file);
        Map planInfo=(Map)fileMap.get("testplan");
        String name=(String)planInfo.get("name");
        String des=(String)planInfo.get("des");
        planInfo.remove("name");
        planInfo.remove("des");
        PlanModel planModel=new PlanModel(name,des,(Map<String,Map>)planInfo);
    }

    private static Map getYamlFileMap(File file){
        Yaml yaml = new Yaml();
        Map fileMap = null;
        try {
            fileMap = (Map)yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileMap;
    }

    public static void main(String[] args){
        File file=new File("D:\\IdeaProjects\\httptest\\src\\main\\resources\\testcase.yml");
        start(file);
    }
}
