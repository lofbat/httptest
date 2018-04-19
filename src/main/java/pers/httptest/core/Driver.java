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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Driver {
    private static final Logger log = Logger.getLogger(Driver.class);

    private static final String[] BASE_PACKAGES={
                                                "pers.httptest.assertion",
                                                "pers.httptest.core"
    };

    private static final String[] COMPONENTS={
            "pers.httptest.assertion.Assertion"
    };
    private static Map<String,Class> clazzes;

    private Driver(){}

    private static void start(File file) throws CaseIlleagalException {
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
        clazzes= getClassName(BASE_PACKAGES);
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

    private static void MultiTreeTraversal(MultiTree node) throws CaseIlleagalException {
        Stack<MultiTree> nodeStack=new Stack<MultiTree>();
        nodeStack.push(node);
        MultiTree n;
        int size;
        List<MultiTree> list;
        while(!nodeStack.isEmpty()){
            n=nodeStack.pop();
            list=n.getChildrenList();
            size=list.size();
            for(int i=size;i>0;i--){
                nodeStack.push(list.get(i-1));
            }
            try {
                nodeParser(n.getName(), n.getType(), n.getVar());
            }catch(CaseIlleagalException e){
                log.error("run failed at :"+n.getName(),e);
            }
        }
    }

    private static void nodeParser(String name,String type, Map<String, Object> var) throws CaseIlleagalException {
        if(name==null){
            throw new CaseIlleagalException("name can not be null");
        }
        Class clazz=clazzes.get(type);
        if(clazz==null){
            throw new CaseIlleagalException("class not found :"+type);
        }
        Class superClazz=clazz.getSuperclass();
        if(superClazz==null){
            throw new CaseIlleagalException("not support this component:"+type);
        }
        Method[] method=superClazz.getMethods();
        try {
            Object node=clazz.getConstructor().newInstance();
            for(Method m:method){
                m.
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static Map<String,Class> getClassName(String[] packageNames) {
        Map<String,Class> clazzes = new LinkedHashMap<String,Class>();
        for(String packageName : packageNames) {
            String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
            File file = new File(filePath);
            getClassName(file, clazzes);
        }
        return clazzes;
    }

    private static void getClassName(File file, Map<String,Class> clazzes) {
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                getClassName(childFile, clazzes);
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                try {
                    clazzes.put(childFilePath.substring(childFilePath.lastIndexOf(".")+1),Class.forName(childFilePath));
                } catch (ClassNotFoundException e) {
                    log.error("ClassNotFoundException:"+childFilePath);
                }
            }
        }
    }

    public static void main(String[] args) throws CaseIlleagalException {
        File file=new File("D:\\IdeaProjects\\httptest\\src\\main\\resources\\testcase.yml");
        start(file);
    }
}
