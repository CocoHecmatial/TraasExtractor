package traas.exe;

import traas.module.it.polito.appeal.traci.SumoTraciConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author CocoHecmatial
 * @since 2020/5/9 9:51
 **/
public class TraasExtractor {
    public static void main(String[] args) {
        String givenDataString = args[2];
        String outputFilePath = args[0];
//        String outputFilePath = "G:\\GitLabs\\traffic-selfproject\\TraasExtractor\\data\\output\\traceVehicleData";
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath,true))) {
            /* 开始执行数据抽取 */
            TraciExtractProcessor traciExtractProcessor = new TraciExtractProcessor();
            /* step1.初始化并启动traci服务 */
            double step_length = 0.1;
            String sumocfg_filepath = args[1];
//            String sumocfg_filepath = "G:\\GitLabs\\traffic-selfproject\\TraasExtractor\\data\\bk_jh_direpaired.sumocfg";
            SumoTraciConnection sumoTraciConnection = traciExtractProcessor.initTraciServer(sumocfg_filepath,step_length);
            /* step2.过程中抽取轨迹数据 */
            traciExtractProcessor.extractTraceData(sumoTraciConnection,bufferedWriter,givenDataString);
            /* step3.过程中抽取信号灯数据 */
            //待补充
            /* step4.完成数据的抽取 */
            sumoTraciConnection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
