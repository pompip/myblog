package cn.pompip.admin;

import java.io.File;
import java.util.Map;

public class UI {
    public static void main(String[] args){
//        JFrame frame = new JFrame("MainForm");
//        frame.setContentPane(new MainFrame().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600,500);
//        frame.setPreferredSize(new Dimension(600,500));
//        frame.pack();
//        frame.setVisible(true);

//        File file = new File("C:\\Users\\chong\\Desktop\\pydemo");
//        Map<String,String> filePathList = new HashMap<>();
//        listFile(file.getPath(),file,filePathList);
//        for (Map.Entry<String,String> f:filePathList.entrySet()){
//            System.out.println(f.getKey()+" : "+f.getValue());
//        }

        zz(1,1,10);
    }
    static void listFile(String parentFilePath,File file,Map<String,String> filePathList){
        if(file.isFile()){
            String key = file.getPath();
            String value = key.substring(parentFilePath.length());
             value = value.replace("\\", "/");
            filePathList.put(key,value);
        }else{
            File[] files = file.listFiles();
            for(File f: files){
                listFile(parentFilePath,f,filePathList);
            }
        }
    }

    static int zz(int a,int b,int n){
        if (n == 0) return zz(a,b,0);
        return zz(b,a+b,n-1);

    }
}
