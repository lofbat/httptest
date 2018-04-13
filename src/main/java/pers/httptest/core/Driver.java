package pers.httptest.core;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import pers.httptest.core.exception.CaseIlleagalException;
import pers.httptest.core.model.MultiTree;
import pers.httptest.core.sample.Sample;
import pers.httptest.core.sample.HttpSampleImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Driver {
    private static final Logger log = Logger.getLogger(Driver.class);

    private Driver(){}

    private static void start(File file){
        Map<String,Object> fileMap=(Map<String,Object>)getYamlFileMap(file);
        MultiTree root=new MultiTree();
        for(Map.Entry<String,Object> entry:fileMap.entrySet()) {
            try {
                root.setUp(null, entry.getKey(), (Map<String, Object>) entry.getValue());
            } catch (CaseIlleagalException e) {
                log.error("case parse failed:"+entry.getKey());
                e.printStackTrace();
            }
        }
        MultiTreeTraversal(root);
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

    private static void MultiTreeTraversal(MultiTree node){
        modelParser(node.getName(),node.getType(),node.getVar());
        List<MultiTree> cl = node.getChildrenList();
        for(MultiTree m:cl){
            MultiTreeTraversal(m);
        }
    }

    private static void modelParser(String name,String type, Map<String, Object> var){
        if(type.equals("request")){
            Sample httpSample=new HttpSampleImpl(name);
            try {
                httpSample.getSampleResult(var);
                log.info(httpSample.getSampleResult(var));
            } catch (CaseIlleagalException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
        File file=new File("D:\\IdeaProjects\\httptest\\src\\main\\resources\\testcase.yml");
        start(file);
    }
}
