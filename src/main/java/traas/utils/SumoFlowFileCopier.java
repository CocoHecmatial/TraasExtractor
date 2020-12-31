package traas.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author CocoHecmatial
 * @since 2020/6/9 11:23
 **/
public class SumoFlowFileCopier {
    public static void main(String[] args) {
        String inputSumoFlowFile = "G:\\GitLabs\\traffic-selfproject\\TraasExtractor\\data\\testfile_7days\\bk_jh_direpaired.flow-day1.xml";
        String outputSumoFlowFile = "G:\\GitLabs\\traffic-selfproject\\TraasExtractor\\data\\testfile_7days\\bk_jh_direpaired.flow-day7.xml";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputSumoFlowFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputSumoFlowFile,true))) {
            String lineStr = null;
            while ((lineStr = bufferedReader.readLine()) != null){
                if (lineStr.length()>65){
                    String[] split = lineStr.split("number");
                    String s1 = split[0];
                    String s3 = "=\"";
                    String s2 = "number";
                    String s4 = "\"/>";
                    int n = Integer.parseInt(split[1].replace("=\"","").replace("\"/>",""));
                    double float_percent = (70+Math.random()*5)/100;
                    int number = (int)(n*float_percent);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(s1).append(s2).append(s3).append(number).append(s4);
                    bufferedWriter.append(stringBuilder).append("\r\n");
                    bufferedWriter.flush();
                }else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(lineStr);
                    bufferedWriter.append(stringBuilder).append("\r\n");
                    bufferedWriter.flush();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
