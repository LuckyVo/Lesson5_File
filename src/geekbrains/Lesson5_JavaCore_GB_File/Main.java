package geekbrains.Lesson5_JavaCore_GB_File;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static ArrayList<FileObject> fIleObjectArrayList = new ArrayList<>();
    public static final String pathToFile = "src/source/Lesson5_2.csv";
    public static final String title = "value1" + ";" + "value2"
            + ";" + "value3" + ";"+ System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {


        createFileObjects1();
        writeTextToFile1();
        DataBaseInfo dataBaseInfo1 = readObjectToPath1();

        
    }

//    public static DataBaseInfo createFileObjects2() {
//        DataBaseInfo dataBaseInfo = new DataBaseInfo();
//        List<List<String>> records = new ArrayList<>();
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/source/Lesson5.csv"))) {
//            String str;
//            while ((str = bufferedReader.readLine()) != null) {
//                String[] values = str.split(";");
//                records.add(Arrays.asList(values));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String[] resultHeader = new String[4];
//        for(int i = 0; i < resultHeader.length ; i++){
//                resultHeader[i] = String.valueOf(records.get(0).get(i));
//        }
//
//        String[][] resultData = new String[records.size()][4];
//        for(int i = 0; i < records.size() ; i++){
//            for(int j = 0; j < records.get(i).size() ; j++){
//                resultData[i][j] = String.valueOf(records.get(i).get(j));
//            }
//        }
//
//        dataBaseInfo.setHeader(resultHeader);
//        dataBaseInfo.setData(resultData);
//
//        return dataBaseInfo;
//    }

    public static void createFileObjects1(){
        Random random = new Random();

        for(int i=1;i<10;i++){
            fIleObjectArrayList.add( new FileObject(random.nextInt(100), random.nextInt(1000),
                    random.nextInt(10000)));
        }
    }

    public static void writeTextToFile1() throws IOException {
        try (FileWriter writer = new FileWriter(pathToFile)){
            writer.write(title);
            for(FileObject fileObject : fIleObjectArrayList) {
                writer.write(fileObject.getValue1() + ";" + fileObject.getValue2()
                        + ";" + fileObject.getValue3() + ";" + System.getProperty("line.separator"));
            }
        }
    }

//    public static void writeTextToFile1() throws IOException {
//        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile)){
//            for(byte b :title.getBytes(StandardCharsets.UTF_8)){
//                fileOutputStream.write(b);
//            }
//            for(FileObject fileObject : fIleObjectArrayList){
//                String raw = fileObject.getValue1() + ";" + fileObject.getValue2()
//                        + ";" + fileObject.getValue3() + ";" + System.getProperty("line.separator");
//                for(byte b :raw.getBytes(StandardCharsets.UTF_8)){
//                    fileOutputStream.write(b);
//                }
//            }
//        }
//    }

    public static DataBaseInfo readObjectToPath1() throws IOException {
        DataBaseInfo dataBaseInfo = new DataBaseInfo();
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line = br.readLine();
            dataBaseInfo.setHeader(line.split(";"));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }

        int[][] resultData = new int[records.size()][3];

        for(int i = 0; i < records.size() ; i++){
            for(int j = 0; j < records.get(i).size() ; j++){
                resultData[i][j] = Integer.parseInt(records.get(i).get(j));
            }
        }
        dataBaseInfo.setData(resultData);
        return dataBaseInfo;

    }

}
