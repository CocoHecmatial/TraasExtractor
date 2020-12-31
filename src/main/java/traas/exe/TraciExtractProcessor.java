package traas.exe;

import traas.db.DBConnection;
import traas.entity.CollidedVehicle;
import traas.entity.VehicleStatus;
import traas.module.de.tudresden.sumo.cmd.Simulation;
import traas.module.de.tudresden.sumo.cmd.Vehicle;
import traas.module.de.tudresden.ws.container.SumoPosition2D;
import traas.module.it.polito.appeal.traci.SumoTraciConnection;
import traas.utils.DateUtil;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.util.*;

/**
 * @author CocoHecmatial
 * @since 2020/5/9 13:28
 **/
public class TraciExtractProcessor {
    /**
     * 初始化并启动traci服务
     * @param config_file :
     * @param step_length :
     * @return traas.module.it.polito.appeal.traci.SumoTraciConnection
     **/
    public SumoTraciConnection initTraciServer(String config_file,
                                               double step_length){
        String sumo_bin = "sumo";
//        String config_file = "G:\\GitLabs\\traffic-selfproject\\TraasExtractor\\data\\bk_jh_direpaired.sumocfg";
        //步长0.1秒
//        double step_length = 0.1;

        SumoTraciConnection sumoTraciConnection = new SumoTraciConnection(sumo_bin, config_file);
        sumoTraciConnection.addOption("step-length", step_length + "");
        sumoTraciConnection.addOption("start", "true"); //start sumo immediately
        //启动traci服务
        try {
            sumoTraciConnection.runServer();
            sumoTraciConnection.setOrder(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return sumoTraciConnection;
    }

    /**
     * 抽取轨迹数据
     * @param sumoTraciConnection :
     * @param bufferedWriter :
     * @return void
     **/
    @SuppressWarnings("unchecked")
    public void extractTraceData(SumoTraciConnection sumoTraciConnection,
                                 BufferedWriter bufferedWriter,
                                 String givenDateString){
        // connection
        Connection connection = DBConnection.getConnection();
        Map<String,Long> linkRSpidMap = DBConnection.getLinkRSpidMap(connection);
        // 行驶中的车辆集合
        HashSet<String> tracingVehicleIDSet = new HashSet<>();
        // 进度记录
        Set<Integer> integerHashSet = new HashSet<>();
        for (int index=0; index<866000; index++){
//        for (int index=0; index<18000; index++){
            try {
                sumoTraciConnection.do_timestep();
                /* step1.抽取当前测试区域内处于行驶状态的车辆 */
                // 获取此刻出发的车辆ID
                List<String> departedVehicleIDList = (List<String>) sumoTraciConnection.do_job_get(Simulation.getDepartedIDList());
                // 加入行驶中的车辆集合
                tracingVehicleIDSet.addAll(departedVehicleIDList);
                // 获取此刻到达的车辆ID
                List<String> arrivedVehicleIDList = (List<String>) sumoTraciConnection.do_job_get(Simulation.getArrivedIDList());
                // 在行驶中的车辆集合中移除已到达的车辆
                tracingVehicleIDSet.removeAll(arrivedVehicleIDList);
//                if (tracingVehicleIDSet.size()>0){
//                    System.out.println();
//                }
                /* step2.获取当前行驶车辆的位置、速度、方向信息 */
//                long currentTimeStamp = (int)sumoTraciConnection.do_job_get(Simulation.getCurrentTime()) + DateUtil.DateFormatUnit.SECOND.getDateByGivenStr("20200509000000").getTime();
                long currentTimeStamp = (int)sumoTraciConnection.do_job_get(Simulation.getCurrentTime()) + DateUtil.DateFormatUnit.SECOND.getDateByGivenStr(givenDateString).getTime();
                // 初始化撞车车辆信息列表
                HashMap<String,CollidedVehicle> collidedVehicleHashMap = new HashMap<>();
                for (String tracingVehicleID : tracingVehicleIDSet){
                    double vehicle_speed = (Double) sumoTraciConnection.do_job_get(Vehicle.getSpeed(tracingVehicleID));
                    double vehicle_angle = (Double) sumoTraciConnection.do_job_get(Vehicle.getAngle(tracingVehicleID));
                    String laneInfo = (String) sumoTraciConnection.do_job_get(Vehicle.getLaneID(tracingVehicleID));
                    SumoPosition2D sumoPosition2D = (SumoPosition2D) sumoTraciConnection.do_job_get(Vehicle.getPosition(tracingVehicleID));
//                    SumoPosition2D sumoPosition2D_converted = new SumoTraciPixelConvertUtils().convert(sumoPosition2D,false);
                    SumoPosition2D sumoPosition2D_converted = (SumoPosition2D) sumoTraciConnection.do_job_get(Simulation.convertGeo(sumoPosition2D.x,sumoPosition2D.y,false));
                    double longitude = sumoPosition2D_converted.x;
                    double latitude = sumoPosition2D_converted.y;
                    VehicleStatus vehicleStatus = new VehicleStatus(tracingVehicleID,
                            vehicle_speed,
                            vehicle_angle,
                            laneInfo,
                            longitude,
                            latitude,
                            currentTimeStamp);
                    long rs_pid = -1;
                    long junction_id = 1001;
                    if (linkRSpidMap.containsKey(laneInfo.split("_")[0])){
                        rs_pid = linkRSpidMap.get(laneInfo.split("_")[0]);
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\"").append(vehicleStatus.getVehicleID()).append("\",")
                            .append(vehicleStatus.getTimestamp()).append(",")
                            .append(vehicleStatus.getLongitude()).append(",")
                            .append(vehicleStatus.getLatitude()).append(",")
                            .append(vehicleStatus.getSpeed()).append(",")
                            .append(vehicleStatus.getAngle() ).append(",")
                            .append("\"").append(vehicleStatus.getLaneInfo().replace(":","")).append("\"").append(",")
                            .append(rs_pid).append(",")
                            .append(junction_id);
                    bufferedWriter.append(stringBuilder).append("\r\n");
                    bufferedWriter.flush();
                }
                /* step3.导出数据*/
                //上一步已完成
            }catch (Exception e){
                e.printStackTrace();
            }
            int wr = index*100/866000;
            if (!integerHashSet.contains(wr)){
                System.out.println("数据模拟进度：" + wr + "%");
                integerHashSet.add(wr);
            }

        }
        System.out.println("模拟完毕");
    }

}
