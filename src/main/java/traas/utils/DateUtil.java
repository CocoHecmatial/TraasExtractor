package traas.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CocoHecmatial
 * @since 2019/12/4 19:54
 **/
public class DateUtil {

    public static Date getDateByGivenUnitDiff(int unit, int diff){
        Calendar calendar = Calendar.getInstance();
        calendar.add(unit,diff);
        return calendar.getTime();
    }

    public enum DateFormatUnit {
        /**
         * 时间格式精度枚举
         * */
        YEAR("yyyy"),MONTH("yyyyMM"),DAY("yyyyMMdd"),HOUR("yyyyMMddHH"),MINUTE("yyyyMMddHHmm"),SECOND("yyyyMMddHHmmss"),
        HMS("HH:mm:ss"),hms("hhmmss"),HM("HH:mm"),MS("mm:ss");

        DateFormatUnit(String pattern) {
            this.pattern = pattern;
        }

        public SimpleDateFormat getGivenFormat() {
            return new SimpleDateFormat(pattern);
        }

        public String getGivenFormatDate(Date date) {
            return new SimpleDateFormat(pattern).format(date);
        }

        public String getGivenFormatTimeStamp(Long timestamp){
            return new SimpleDateFormat(pattern).format(new Date(timestamp));
        }

        public Date getDateByGivenStr(String dateStr) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                return simpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        public String getCurrent(){
            Date date = new Date();
            return new SimpleDateFormat(pattern).format(date);
        }

        private String pattern;
    }

//    public static void main(String[] args) {
////        System.out.println(DateFormatUnit.hms.getDateByGivenStr("080030").getTime());
////        System.out.println(DateFormatUnit.hms.getDateByGivenStr("080000").getTime());
//
//        System.out.println(DateFormatUnit.SECOND.getDateByGivenStr("20200509000000").getTime());
//        System.out.println(DateFormatUnit.SECOND.getDateByGivenStr("20200509000001").getTime());
//
//    }
}
