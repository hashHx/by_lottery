package com.hash.by_lottery.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hash.by_lottery.Service.BaseLotteryTicketService;
import com.hash.by_lottery.Service.ExLotteryTicketService;
import com.hash.by_lottery.component.MissionComponent;
import com.hash.by_lottery.entities.ExLotteryTicket;
import org.apache.commons.lang.ArrayUtils;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;


/**
 * @ClassName lotteryUtils
 * @Descritption 彩票数据算法工具
 * @Author Hash
 * @Date 2019/3/20 20:30
 * @Version 1.0
 **/

public class lotteryUtils {

    /**
     * @Description: 返回龙虎数据
     * @Param: [code]
     * @return: java.lang.String[]
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static List<Integer> DragonTiger(int[] code) {
        List<Integer> list = new ArrayList<Integer>();
        switch (code.length) {

            case 10:

                for (int i = 0; i < 5; i++) {
                    list.add(i, Dragon_Tiger(code[i], code[code.length - i - 1]));
                }
                break;
            case 8:
                for (int i = 0; i < 4; i++) {
                    list.add(i, Dragon_Tiger(code[i], code[code.length - i - 1]));
                }
                break;
            case 5:
                list.add(0, Dragon_Tiger(code[0], code[code.length - 1]));
                break;
        }
        return list;
    }

    /**
     * @Description: 返回龙虎list
     * @Param: [dragon, tiger]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int Dragon_Tiger(int dragon, int tiger) {
        return (dragon == tiger) ? 2 : dragon > tiger ? 0 : 1;
    }

    /**
     * @Description: 返回总和
     * @Param: []
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int sum_All(int[] code) {
        int sum = 0;
        for (int i :
                code) {
            sum += i;
        }
        return sum;
    }


    /**
     * @Description: 返回合数列表
     * @Param: [code]
     * @return: int[]
     * @Author: Hash
     * @Date: 2019/4/1
     */
    public static int[] sum_number(int[] code) {
        int[] temp = new int[code.length];
        for (int i = 0; i < code.length; i++) {
            temp[i] = (code[i] % 10) + (code[i] / 10);
        }
        return temp;
    }


    /**
     * @Description: 返回冠亚和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static int sum_FS(int[] code) {
        return code[0] + code[1];
    }

    /**
     * @Description: 返回总和大小 {0：大，1：小 2:和 4：通吃}
     * @Param: [code, type]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/28
     */
    public static int sum_BS(int[] code, int type) {

        int sum = sum_All(code);

        switch (type) {
            case 1:

                return (code[0] + code[1]) > 11 ? 0 : 1;
            case 2:

                return sum > 22 ? 0 : 1;

            case 3:

                return sum == 30 ? 2 : (sum > 30 ? 0 : 1);

            case 4:

                return sum == 84 ? 2 : (sum > 84 ? 0 : 1);

            case 6:

                return sum == 84 ? 2 : (sum > 84 ? 0 : 1);

            case 5:

                return (code[0] == code[1]) && (code[1] == code[2]) ? 4 : (sum > 10 ? 0 : 1);

        }
        return sum;
    }

    /**
     * @Description: 返回总和单双 {0：单，1：双}
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static int sum_SD(int[] code) {

        return (sum_All(code) % 2) != 0 ? 0 : 1;
    }

    public static int FS_sum_SD(int[] code) {
        return (code[0] + code[1]) % 2 != 0 ? 0 : 1;
    }

    public static int FS_sum_BS(int[] code) {
        return (code[0] + code[1]) > 11 ? 0 : 1;
    }

    /**
     * @Description: 返回 前三，中三，后三 数组 (0-杂六，1-半顺，2-顺子，3-对子，4-豹子)
     * @Param: [code]
     * @return: java.lang.String[]
     * @Author: Hash
     * @Date: 2019/3/21
     */
    public static int[] threeFMB(int[] code) {
        int[] str = new int[3];
        str[0] = FMB(code[0], code[1], code[2]);
        str[1] = FMB(code[1], code[2], code[3]);
        str[2] = FMB(code[2], code[3], code[4]);
        return str;
    }

    /**
     * @Description: 返回 (0-杂六，1-半顺，2-顺子，3-对子，4-豹子)
     * @Param: [one, two, three]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int FMB(int one, int two, int three) {
        List<Integer> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        list.add(three);
        Collections.sort(list);
        if (list.contains(0)) {
            if (list.contains(9)) {
                if (simpleFun(one, two, three) == 3) {
                    return 3;
                }
                if (list.contains(8) || list.contains(1)) {
                    return 2;
                } else {
                    return 1;
                }
            } else {
                simpleFun(one, two, three);
            }
        } else {
            simpleFun(one, two, three);
        }
        return simpleFun(one, two, three);
    }


    /**
     * @Description: 不带0 的前中后三比较
     * @Param: [one, two, three]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int simpleFun(int one, int two, int three) {
        if (Math.abs(one - two) == 0 && Math.abs(one - three) == 0) {
            return 4;
        }
        if (Math.abs(one - two) == 0 || Math.abs(one - three) == 0 || Math.abs(two - three) == 0) {
            return 3;
        }
        if (Math.abs(one - two) == 1 && Math.abs(one - three) == 1) {
            return 2;
        }
        if ((Math.abs(one - two) == 1 || Math.abs(one - three) == 1 || Math.abs(two - three) == 1) || (Math.abs(one - two) == 9 || Math.abs(one - three) == 9 || Math.abs(two - three) == 9)) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * @Description: 判断尾数大小(0 - 大 ， 1 - 小)
     * @Param: [code]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public static int tail_BS(int[] code) {
        return (sum_All(code) % 10) < 5 ? 1 : 0;
    }


    public static int tail_SD(int[] code) {
        return (sum_All(code) % 2) == 0 ? 1 : 0;
    }


    public static int[] tail_BS_list(int[] code) {
        int[] temp = new int[code.length];
        for (int i = 0; i < code.length; i++) {
            temp[i] = (code[i] % 10) < 5 ? 1 : 0;
        }
        return temp;
    }

    /**
     * @Description: 百十和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenSum(int[] code) {
        return code[0] + code[1];
    }

    /**
     * @Description: 百个和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneSum(int[] code) {
        return code[0] + code[2];
    }

    /**
     * @Description: 个十和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneSum(int[] code) {
        return code[2] + code[1];
    }

    /**
     * @Description: 百十单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenSingleEven(int[] code) {
        return (hundredTenSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 百个单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneSingleEven(int[] code) {
        return (hundredOneSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 十个单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneSingleEven(int[] code) {
        return (TenOneSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 百十大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenBigSmall(int[] code) {
        return (hundredTenSum(code) % 10 < 5) ? 1 : 0;
    }

    /**
     * @Description: 百个大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneBigSmall(int[] code) {
        return (hundredOneSum(code) % 10 < 5) ? 1 : 0;
    }

    /**
     * @Description: 十个大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneBigSmall(int[] code) {
        return (TenOneSum(code) % 10 < 5) ? 1 : 0;
    }

    /**
     * @Description:返回下期时间
     * @Param: [time, s]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public String next_time(String time, int s) {
        return String.valueOf(Integer.parseInt(time) + s);
    }

    /**
     * @Description: String类型datetime转时间戳
     * @Param: [date_str]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public static String date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String date2TimeStamp_SIXSUM(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * @Description: 判断是否为数字
     * @Param: [code, issue]
     * @return: boolean
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static boolean CodeVerification(String code, String issue) {
        if (code.matches("[0-9]+") && issue.matches("[0-9]+") || issue.matches("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean CodeVerification(String code) {
        if (code.matches("[0-9]+")) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * @Description: 多参数检验输入数据
     * @Param: [service, service_ex, args]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: Hash
     * @Date: 2019/3/29
     */
    public static HashMap<String, Object> ResultByCodeVerification(BaseLotteryTicketService service, ExLotteryTicketService service_ex, Object... args) {

        try {
            if (args[0] == null || CodeVerification((String) args[0])) {
                return ResultGen.getResult(new HashMap<Object, Object>(), 4);
            }
            if (args[1] != null) {
                if (CodeVerification((String) args[0], (String) args[1])) {
                    return ResultGen.getResult(new HashMap<Object, Object>(), 4);
                }
            }
            if (service.findCode((String) args[0]) == 0) {
                return ResultGen.getResult(new HashMap<Object, Object>(), 2);
            }
        } catch (Exception e) {
            return ResultGen.getResult(new HashMap<Object, Object>(), 1);
        }
        return null;
    }


    public static JSONObject SIXSUM_utils(JSONObject jsonObject) {
        jsonObject.put("lotCode", "11009");
        jsonObject.put("lotName", "香港六合彩");
        jsonObject.put("lotType", 7);
        jsonObject.put("serverTime", System.currentTimeMillis());
        jsonObject.put("nextDrawTime", MissionComponent.getNearDate());
        jsonObject.put("imgUrl", "https://static.junanservice.com/lottery/images/xglhc.png");
        jsonObject.put("iconUrl", "https://static.junanservice.com/lottery/images/xglhc_icon.png");
        return jsonObject;
    }

    /**
     * @Description: 双面统计数字
     * @Param: [list]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Hash
     * @Date: 2019/4/9
     */
    public static JSONObject allNumberCount(List<ExLotteryTicket> list) {
        ArrayList<int[]> objects = new ArrayList<>();
        int type = list.get(0).getLot_type();
        for (ExLotteryTicket e :
                list) {
            objects.add(lotteryCodeAdapter.toCalculate(e.getDraw_code()));
        }
        int[] count = null;
        if (type == 2) {
            count = new int[10];
            for (int[] i : objects) {
                for (int j :
                        i) {
                    {
                        count[j]++;
                    }

                }
            }
        }
        if (type == 3) {
            count = new int[11];
            for (int[] i : objects) {
                for (int j :
                        i) {
                    {
                        count[j - 1]++;
                    }

                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numberCount", count);
        return jsonObject;
    }

    /**
     * @Description: 双面统计 单个球
     * @Param: [list]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Hash
     * @Date: 2019/4/9
     */
    public static JSONObject doubleNumberCount(List<ExLotteryTicket> list) {
        int type = list.get(0).getLot_type();
        ArrayList<int[]> objects = new ArrayList<>();
        ArrayList<List> arrayList = new ArrayList();
        for (ExLotteryTicket e :
                list) {
            int[] ints = lotteryCodeAdapter.toCalculate(e.getDraw_code());
            arrayList.add(lotteryUtils.DragonTiger(ints));
            objects.add(ints);
        }

        int length = objects.get(0).length;
        int[][] doubleNum = new int[length][4];
        for (int[] i : objects) {
            for (int j = 0; j < length; j++) {
                if (i[j] % 2 == 1) {
                    doubleNum[j][0]++;
                } else {
                    doubleNum[j][1]++;
                }
                if (B_S(i[j], type) == 0) {
                    doubleNum[j][2]++;
                } else {
                    doubleNum[j][3]++;
                }
            }
        }
        if (type == 1) {
            int[][] dt = new int[5][2];
            for (List l :
                    arrayList) {
                for (int i = 0; i < l.size(); i++) {
                    if ((int) l.get(i) == 0) {
                        dt[i][0]++;
                    } else {
                        dt[i][1]++;
                    }
                }
            }
            int[][] newDoubleNum = new int[5][6];

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 4; k++) {
                    newDoubleNum[j][k] = doubleNum[j][k];
                }
                newDoubleNum[j][4] = dt[j][0];
                newDoubleNum[j][5] = dt[j][1];
            }

            for (int i = 0; i < 5; i++) {
                doubleNum[i] = newDoubleNum[i];
            }

            List<List> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int size = doubleNum[i].length;
                List listIn = new ArrayList();
                for (int j = 0; j < size; j++) {
                    listIn.add(doubleNum[i][j]);
                }
                result.add(listIn);
            }
            Integer[] FS_sum = new Integer[4];
            FS_sum[0] = 0;
            FS_sum[1] = 0;
            FS_sum[2] = 0;
            FS_sum[3] = 0;
            for (int[] i :
                    objects) {
                if ((i[0] + i[1]) % 2 == 0) {
                    FS_sum[1]++;
                } else {
                    FS_sum[0]++;
                }
                if ((i[0] + i[1]) > 11) {
                    FS_sum[2]++;
                } else {
                    FS_sum[3]++;
                }
            }
            result.add(Arrays.asList(FS_sum));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("doubleCount", result);
            return jsonObject;
        }
        if (type == 2) {
            List<List> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int size = doubleNum[i].length;
                List listIn = new ArrayList();
                for (int j = 0; j < size; j++) {
                    listIn.add(doubleNum[i][j]);
                }
                result.add(listIn);
            }
            Integer[] FS_sum = new Integer[4];
            FS_sum[0] = 0;
            FS_sum[1] = 0;
            FS_sum[2] = 0;
            FS_sum[3] = 0;
            for (int[] i :
                    objects) {
                if (sum_All(i) % 2 == 0) {
                    FS_sum[1]++;
                } else {
                    FS_sum[0]++;
                }
                if (sum_All(i) > 23) {
                    FS_sum[2]++;
                } else {
                    FS_sum[3]++;
                }
            }
            result.add(Arrays.asList(FS_sum));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("doubleCount", result);
            return jsonObject;
        }
        if (type == 3) {
            List<List> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int size = doubleNum[i].length;
                List listIn = new ArrayList();
                for (int j = 0; j < size; j++) {
                    listIn.add(doubleNum[i][j]);
                }
                result.add(listIn);
            }
            Integer[] FS_sum = new Integer[4];
            FS_sum[0] = 0;
            FS_sum[1] = 0;
            FS_sum[2] = 0;
            FS_sum[3] = 0;
            for (int[] i :
                    objects) {
                if (sum_All(i) % 2 == 0) {
                    FS_sum[1]++;
                } else {
                    FS_sum[0]++;
                }
                if (sum_All(i) > 30) {
                    FS_sum[2]++;
                }
                if (sum_All(i) < 30) {
                    FS_sum[3]++;
                }
            }
            result.add(Arrays.asList(FS_sum));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("doubleCount", result);
            return jsonObject;
        }
        if (type == 4 || type == 6) {
            int[][] dt = new int[4][2];
            for (List l :
                    arrayList) {
                for (int i = 0; i < l.size(); i++) {
                    if ((int) l.get(i) == 0) {
                        dt[i][0]++;
                    } else {
                        dt[i][1]++;
                    }
                }
            }
            int[][] newDoubleNum = new int[4][6];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    newDoubleNum[j][k] = doubleNum[j][k];
                }
                newDoubleNum[j][4] = dt[j][0];
                newDoubleNum[j][5] = dt[j][1];
            }
            for (int i = 0; i < 4; i++) {
                doubleNum[i] = newDoubleNum[i];
            }

            List<List> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int size = doubleNum[i].length;
                List listIn = new ArrayList();
                for (int j = 0; j < size; j++) {
                    listIn.add(doubleNum[i][j]);
                }
                result.add(listIn);
            }
            Integer[] FS_sum = new Integer[4];
            FS_sum[0] = 0;
            FS_sum[1] = 0;
            FS_sum[2] = 0;
            FS_sum[3] = 0;
            for (int[] i :
                    objects) {
                if (sum_All(i) % 2 == 0) {
                    FS_sum[1]++;
                } else {
                    FS_sum[0]++;
                }
                if (sum_All(i) > 84) {
                    FS_sum[2]++;
                }
                if (sum_All(i) < 84) {
                    FS_sum[3]++;
                }
            }
            result.add(Arrays.asList(FS_sum));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("doubleCount", result);
            return jsonObject;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doubleCount", doubleNum);
        return jsonObject;
    }


    /**
     * @Description: 单双大小历史查询 综合查询
     * @Param: [list]
     * @return: java.lang.Object[][]
     * @Author: Hash
     * @Date: 2019/4/9
     */
    public static Object[][] complexView(List<ExLotteryTicket> list) {
        int bollNum = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        int type = list.get(0).getLot_type();
        ArrayList<String> date = getpastDaysList(15);
        Map<String, ArrayList<int[]>> map = new LinkedHashMap();
        for (String dateStr :
                date) {
            ArrayList<int[]> dateSTRING = new ArrayList<>();
            for (ExLotteryTicket e :
                    list) {
                if (e.getDraw_time().substring(0, 10).equals(dateStr)) {
                    int[] ints = lotteryCodeAdapter.toCalculate(e.getDraw_code());
                    dateSTRING.add(ints);
                }
            }
            map.put(dateStr, dateSTRING);
        }
        Iterator iter = map.entrySet().iterator();
        ArrayList<JSONObject> jsonArray = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ArrayList<int[]> val = (ArrayList<int[]>) entry.getValue();
            int[][] group = new int[bollNum][4];
            for (int[] ints :
                    val) {
                for (int i = 0; i < bollNum; i++) {
                    if (ints[i] % 2 == 1) {
                        group[i][0]++;
                    } else {
                        group[i][1]++;
                    }
                    if (B_S(ints[i], type) == 0) {
                        group[i][2]++;
                    } else {
                        group[i][3]++;
                    }
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key, group);
            jsonArray.add(jsonObject);
        }
        Object[][][] objs = new Object[bollNum][date.size()][4];
        for (int i = 0; i < bollNum; i++) {
            Object[][] objects = new Object[date.size()][5];
            for (int j = 0; j < date.size(); j++) {

                String data = (String) jsonArray.get(j).keySet().toArray()[0];
                int[][] boll = (int[][]) jsonArray.get(j).get(data);
                objects[j][0] = data;
                objects[j][1] = boll[i][0];
                objects[j][2] = boll[i][1];
                objects[j][3] = boll[i][2];
                objects[j][4] = boll[i][3];
            }
            objs[i] = objects;
        }
        return objs;
    }

    /**
     * @Description: 单双大小历史查询  单独查询
     * @Param: [list]
     * @return: java.lang.Object[][]
     * @Author: Hash
     * @Date: 2019/4/9
     */
    public static Object[][] singleView(List<ExLotteryTicket> list) {
        int bollNum = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        int type = list.get(0).getLot_type();
        ArrayList<String> date = getpastDaysList(15);
        Map<String, ArrayList<int[]>> map = new LinkedHashMap();
        for (String dateStr :
                date) {
            ArrayList<int[]> dateSTRING = new ArrayList<>();
            for (ExLotteryTicket e :
                    list) {
                if (e.getDraw_time().substring(0, 10).equals(dateStr)) {
                    int[] ints = lotteryCodeAdapter.toCalculate(e.getDraw_code());
                    dateSTRING.add(ints);
                }
            }
            map.put(dateStr, dateSTRING);
        }

        Iterator iter = map.entrySet().iterator();
        ArrayList<JSONObject> jsonArray = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ArrayList<int[]> val = (ArrayList<int[]>) entry.getValue();
            int[][] group = new int[bollNum][4];
            for (int[] ints :
                    val) {
                for (int i = 0; i < bollNum; i++) {
                    if (ints[i] % 2 == 1) {
                        group[i][0]++;
                    } else {
                        group[i][1]++;
                    }
                    if (B_S(ints[i], type) == 0) {
                        group[i][2]++;
                    } else {
                        group[i][3]++;
                    }
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key, group);
            jsonArray.add(jsonObject);
        }

        Object[][][] objs = new Object[4][date.size()][bollNum];
        for (int i = 0; i < 4; i++) {
            Object[][] objects = new Object[date.size()][bollNum + 1];
            for (int j = 0; j < date.size(); j++) {
                String data = (String) jsonArray.get(j).keySet().toArray()[0];
                int[][] boll = (int[][]) jsonArray.get(j).get(data);
                objects[j][0] = data;
                for (int k = 1; k < bollNum + 1; k++) {
                    objects[j][k] = boll[k - 1][i];
                }
            }
            objs[i] = objects;
        }
        return objs;
    }
// ---------------------------------------------------------------- 路珠系列开始

    /**
     * @Description: 返回单双大小路珠列表JsonObject
     * @Param: [list]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Hash
     * @Date: 2019/4/13
     */
    public static JSONObject getDSBSRoadBead(List<ExLotteryTicket> list) {
        int bollNum = list.get(0).getDraw_code().split(",").length;
        int type = list.get(0).getLot_type();
        int[][] ints = new int[bollNum][list.size()];
        for (int i = 0; i < bollNum; i++) {
            for (int j = 0; j < list.size(); j++) {
                ints[i][j] = lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())[i];
            }
        }
        ArrayList DS = new ArrayList(); //单双
        ArrayList BS = new ArrayList(); //大小
        for (int i = 0; i < bollNum; i++) {
            int[] DSs = ints[i];
            int[] BSs = ints[i];
            DS.add(moreArr(DS_Filter(DSs, type)));
            BS.add(moreArr(BS_Filter(BSs, type)));
        }
        JSONObject object = new JSONObject();
        object.put("SIZE", BS);
        object.put("DoubleSingle", DS);
        return object;
    }

    /**
     * @Description: 返回龙虎路珠列表JsonObject
     * @Param: [list]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Hash
     * @Date: 2019/4/13
     */
    public static JSONObject getDTRoadBead(List<ExLotteryTicket> list) {
        int bollNum = DragonTiger(lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code())).size();
        int[][] ints = new int[bollNum][list.size()];
        //int type = list.get(0).getLot_type();
        for (int i = 0; i < bollNum; i++) {
            for (int j = 0; j < list.size(); j++) {
                ints[i][j] = DragonTiger(lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())).toArray(new Integer[bollNum])[i];
            }
        }
        ArrayList DS = new ArrayList(); //龙虎
        for (int i = 0; i < bollNum; i++) {
            int[] DSs = ints[i];
            DS.add(moreArr(DSs));
        }
        JSONObject object = new JSONObject();
        object.put("DragonTiger", DS);
        return object;
    }


    /**
     * @Description: 返回冠亚和路珠列表JsonObject
     * @Param: [list]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Hash
     * @Date: 2019/4/13
     */
    public static JSONObject getFSRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int[] FS_list = new int[size];
        for (int i = 0; i < size; i++) {
            FS_list[i] = sum_FS(lotteryCodeAdapter.toCalculate(list.get(i).getDraw_code()));
        }
        int[] BSs = new int[size];
        for (int i = 0; i < size; i++) {
            if (FS_list[i] > 11) {
                BSs[i] = 0;
            } else {
                BSs[i] = 1;
            }
        }
        JSONObject object = new JSONObject();
        object.put("SIZE", moreArr(BSs));
        object.put("DoubleSingle", moreArr(DS_Filter(FS_list, type)));
        return object;
    }

    //总和路珠
    public static JSONObject getTotalRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int[] totalList = new int[size];
        int type = list.get(0).getLot_type();
        for (int i = 0; i < size; i++) {
            totalList[i] = sum_All(lotteryCodeAdapter.toCalculate(list.get(i).getDraw_code()));
        }
        int[] DSs = DS_Filter(totalList, type);
        int[] Bss = new int[size];
        for (int i = 0; i < size; i++) {
            Bss[i] = (totalList[i] > 22 ? 0 : 1);
        }
        JSONObject object = new JSONObject();
        object.put("SIZE", moreArr(Bss));
        object.put("DoubleSingle", moreArr(DSs));
        return object;
    }

    //号码路珠  时时彩，快3 2456
    public static List getNumberRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int num = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        List<int[]> ticketCode = new ArrayList<>();
        for (ExLotteryTicket t :
                list) {
            ticketCode.add(lotteryCodeAdapter.toCalculate(t.getDraw_code()));
        }
        List<List> result = new ArrayList<>();
        switch (type) {
            case 2:
                for (int i = 0; i < 10; i++) {
                    int[] temp = new int[size];
                    for (int j = 0; j < size; j++) {
                        ArrayList arrayList = new ArrayList(Arrays.asList(ArrayUtils.toObject(ticketCode.get(j))));
                        temp[j] = arrayList.contains(i) ? 0 : 1;
                        if (j == size - 1) {
                            result.add(moreArr(temp));
                        }
                    }
                }
                break;
            case 4:
                for (int i = 1; i < 21; i++) {
                    int[] temp = new int[size];
                    for (int j = 0; j < size; j++) {
                        ArrayList arrayList = new ArrayList(Arrays.asList(ArrayUtils.toObject(ticketCode.get(j))));
                        temp[j] = arrayList.contains(i) ? 0 : 1;
                        if (j == size - 1) {
                            result.add(moreArr(temp));
                        }
                    }
                }
                break;
            case 5:
                for (int i = 1; i < 7; i++) {
                    int[] temp = new int[size];
                    for (int j = 0; j < size; j++) {
                        ArrayList arrayList = new ArrayList(Arrays.asList(ArrayUtils.toObject(ticketCode.get(j))));
                        temp[j] = arrayList.contains(i) ? 0 : 1;
                        if (j == size - 1) {
                            result.add(moreArr(temp));
                        }
                    }
                }
                break;
            case 6:
                for (int i = 1; i < 21; i++) {
                    int[] temp = new int[size];
                    for (int j = 0; j < size; j++) {
                        ArrayList arrayList = new ArrayList(Arrays.asList(ArrayUtils.toObject(ticketCode.get(j))));
                        temp[j] = arrayList.contains(i) ? 0 : 1;
                        if (j == size - 1) {
                            result.add(moreArr(temp));
                        }
                    }
                }
                break;
        }

        return result;
    }

    //东南西北路珠
    public static List ESWNRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int num = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        List result = new ArrayList();
        for (int i = 0; i < num; i++) {
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                temp[j] = lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())[i];
            }
            result.add(moreArr(ESWN_Filter(temp)));
        }
        return result;
    }


    //中发白路珠
    public static List ZFBRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int num = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        List result = new ArrayList();
        for (int i = 0; i < num; i++) {
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                temp[j] = lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())[i];
            }
            result.add(moreArr(ZFB_Filter(temp)));
        }
        return result;
    }

    //合数单双路珠
    public static List sumNumberRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int num = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        List result = new ArrayList();
        for (int i = 0; i < num; i++) {
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                temp[j] = lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())[i];
            }
            result.add(moreArr(DS_Filter(sum_number(temp), type)));
        }
        return result;
    }

    //尾数大小路珠
    public static List tailRoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        int num = lotteryCodeAdapter.toCalculate(list.get(0).getDraw_code()).length;
        List result = new ArrayList();
        for (int i = 0; i < num; i++) {
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                temp[j] = lotteryCodeAdapter.toCalculate(list.get(j).getDraw_code())[i];
            }
            result.add(moreArr((tail_BS_list(temp))));
        }
        return result;
    }


    //号码前后路珠
    public static List BARoadBead(List<ExLotteryTicket> list) {
        int size = list.size();
        List<int[]> ticketCode = new ArrayList<>();
        for (ExLotteryTicket t :
                list) {
            ticketCode.add(BA_Filter(lotteryCodeAdapter.toCalculate(t.getDraw_code())));
        }
        List result = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int[] newInt = new int[size];
            for (int j = 0; j < size; j++) {
                newInt[j] = ticketCode.get(j)[i];
            }
            result.add(moreArr(newInt));
        }
        return result;
    }


    //LineAnalysis 路珠分析
    public static List RoadBeadAnalysis(List<ExLotteryTicket> list) {
        int size = list.size();
        int type = list.get(0).getLot_type();
        List<int[]> ticketCode = new ArrayList<>();
        for (ExLotteryTicket t :
                list) {
            ticketCode.add(lotteryCodeAdapter.toCalculate(t.getDraw_code()));
        }
        switch (type) {
            case 1:
                List listNum1 = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    int[] BS = new int[size];
                    int[] DS = new int[size];
                    for (int j = 0; j < size; j++) {

                        BS[j] = ticketCode.get(j)[i];
                        DS[j] = ticketCode.get(j)[i];

                    }
                    listNum1.add(moreArr(DS_Filter(DS, type)));
                    listNum1.add(moreArr(BS_Filter(BS, type)));
                    if (i < 5) {
                        int[] DT = new int[ticketCode.size()];
                        for (int k = 0; k < size; k++) {
                            DT[k] = DragonTiger(ticketCode.get(k)).get(i);
                        }
                        listNum1.add(moreArr(DT));
                    }

                }
                int[] FS_DS = new int[size];
                int[] FS_BS = new int[size];
                for (int j = 0; j < size; j++) {
                    FS_DS[j] = FS_sum_SD(ticketCode.get(j));
                    FS_BS[j] = FS_sum_BS(ticketCode.get(j));
                }
                listNum1.add(moreArr(FS_DS));
                listNum1.add(moreArr(FS_BS));
                return listNum1;
            case 2:
                List listNum2 = new ArrayList();
                for (int i = 0; i < 5; i++) {

                    int[] BS = new int[size];
                    int[] DS = new int[size];
                    for (int k = 0; k < size; k++) {
                        BS[k] = ticketCode.get(k)[i];
                        DS[k] = ticketCode.get(k)[i];
                    }
                    listNum2.add(moreArr(DS_Filter(DS, type)));
                    listNum2.add(moreArr(BS_Filter(BS, type)));
                }
                int[] sum_DS = new int[size];
                int[] sum_BS = new int[size];
                int[] DT = new int[size];
                for (int i = 0; i < size; i++) {
                    sum_DS[i] = (sum_SD(ticketCode.get(i)));
                    sum_BS[i] = (sum_BS(ticketCode.get(i), type));
                    DT[i] = DragonTiger(ticketCode.get(i)).get(0);
                }
                listNum2.add(moreArr(sum_DS));
                listNum2.add(moreArr(sum_BS));
                listNum2.add(moreArr(DT));
                return listNum2;
            case 3:
                List listNum3 = new ArrayList();
                for (int i = 0; i < 5; i++) {
                    int[] BS = new int[size];
                    int[] DS = new int[size];
                    for (int k = 0; k < size; k++) {
                        BS[k] = ticketCode.get(k)[i];
                        DS[k] = ticketCode.get(k)[i];
                    }

                    listNum3.add(moreArr(DS_Filter(DS, type)));
                    listNum3.add(moreArr(BS_Filter(BS, type)));
                }
                int[] sum3_DS = new int[size];
                int[] sum3_BS = new int[size];
                int[] DT3 = new int[size];
                int[] tailBS = new int[size];
                for (int i = 0; i < size; i++) {
                    sum3_DS[i] = (sum_SD(ticketCode.get(i)));
                    sum3_BS[i] = (sum_BS(ticketCode.get(i), type));
                    DT3[i] = DragonTiger(ticketCode.get(i)).get(0);
                    tailBS[i] = tail_BS(ticketCode.get(i));
                }
                listNum3.add(moreArr(sum3_DS));
                listNum3.add(moreArr(sum3_BS));
                listNum3.add(moreArr(DT3));
                listNum3.add(moreArr(tailBS));
                return listNum3;
            case 4:
                List listNum4 = new ArrayList();
                for (int i = 0; i < 8; i++) {
                    int[] BS = new int[size];
                    int[] DS = new int[size];
                    int[] ZFB = new int[size];
                    int[] ESWN = new int[size];
                    int[] tail_BS = new int[size];
                    int[] sumNumber = new int[size];
                    for (int k = 0; k < size; k++) {
                        BS[k] = ticketCode.get(k)[i];
                        DS[k] = ticketCode.get(k)[i];
                        ZFB[k] = ticketCode.get(k)[i];
                        ESWN[k] = ticketCode.get(k)[i];
                        tail_BS[k] = ticketCode.get(k)[i];
                        sumNumber[k] = ticketCode.get(k)[i];
                    }
                    listNum4.add(moreArr(BS_Filter(BS, type)));
                    listNum4.add(moreArr(DS_Filter(DS, type)));
                    if (i < 4) {
                        int[] DT4 = new int[ticketCode.size()];
                        for (int k = 0; k < size; k++) {
                            DT4[k] = DragonTiger(ticketCode.get(k)).get(i);
                        }
                        listNum4.add(moreArr(DT4));

                    }
                    listNum4.add(moreArr(tail_BS_list(tail_BS)));
                    listNum4.add(moreArr(DS_Filter(sum_number(sumNumber), type)));
                    listNum4.add(moreArr(ZFB_Filter(ZFB)));
                    listNum4.add(moreArr(ESWN_Filter(ESWN)));
                }
                int[] sum_DS4 = new int[size];
                int[] sum_BS4 = new int[size];
                int[] sum_tail_BS4 = new int[size];
                for (int i = 0; i < size; i++) {
                    sum_DS4[i] = (sum_SD(ticketCode.get(i)));
                    sum_BS4[i] = (sum_BS(ticketCode.get(i), type));
                    sum_tail_BS4[i] = tail_BS(ticketCode.get(i));
                }
                listNum4.add(moreArr(sum_DS4));
                listNum4.add(moreArr(sum_BS4));
                listNum4.add(moreArr(sum_tail_BS4));
                return listNum4;
            case 6:
                List listNum6 = new ArrayList();
                for (int i = 0; i < 8; i++) {
                    int[] BS = new int[size];
                    int[] DS = new int[size];
                    int[] ZFB = new int[size];
                    int[] ESWN = new int[size];
                    int[] tail_BS = new int[size];
                    int[] sumNumber = new int[size];
                    for (int k = 0; k < size; k++) {
                        BS[k] = ticketCode.get(k)[i];
                        DS[k] = ticketCode.get(k)[i];
                        ZFB[k] = ticketCode.get(k)[i];
                        ESWN[k] = ticketCode.get(k)[i];
                        tail_BS[k] = ticketCode.get(k)[i];
                        sumNumber[k] = ticketCode.get(k)[i];
                    }
                    listNum6.add(moreArr(BS_Filter(BS, type)));
                    listNum6.add(moreArr(DS_Filter(DS, type)));
                    if (i < 4) {
                        int[] DT6 = new int[ticketCode.size()];
                        for (int k = 0; k < size; k++) {
                            DT6[k] = DragonTiger(ticketCode.get(k)).get(i);
                        }
                        listNum6.add(moreArr(DT6));

                    }
                    listNum6.add(moreArr(tail_BS_list(tail_BS)));
                    listNum6.add(moreArr(DS_Filter(sum_number(sumNumber), type)));
                    listNum6.add(moreArr(ZFB_Filter(ZFB)));
                    listNum6.add(moreArr(ESWN_Filter(ESWN)));
                }
                int[] sum_DS6 = new int[size];
                int[] sum_BS6 = new int[size];
                int[] sum_tail_BS6 = new int[size];
                for (int i = 0; i < size; i++) {
                    sum_DS6[i] = (sum_SD(ticketCode.get(i)));
                    sum_BS6[i] = (sum_BS(ticketCode.get(i), type));
                    sum_tail_BS6[i] = tail_BS(ticketCode.get(i));
                }
                listNum6.add(moreArr(sum_DS6));
                listNum6.add(moreArr(sum_BS6));
                listNum6.add(moreArr(sum_tail_BS6));
                return listNum6;
        }
        return null;
    }

// ---------------------------------------------------------------- 路珠系列结束


// ---------------------------------------------------------------- 走势系列开始

    //号码走势 pk10
    public static List NumberTrend(List<ExLotteryTicket> list) {
        int size = list.size();
        List<int[]> ticketCode = new ArrayList<>();
        List<String> issueCode = new ArrayList<>();
        for (ExLotteryTicket t :
                list) {
            ticketCode.add(lotteryCodeAdapter.toCalculate(t.getDraw_code()));
            issueCode.add(t.getDraw_issue());
        }
        List numList = new ArrayList();
        for (int index = 1; index < 11; index++) {
            List numListIn = new ArrayList();
            List numIn = new ArrayList();
            for (int j = 1; j < 11; j++) {
                Integer[] numInteger = new Integer[ticketCode.size()];
                Integer count = 1;
                for (int i = ticketCode.size() - 1; i >= 0; i--) {
                    if (ticketCode.get(i)[index - 1] == j) {
                        numInteger[i] = index;
                        count = 1;
                    } else {
                        numInteger[i] = count;
                        count++;
                    }
                }
                numListIn.add(numInteger);
            }
            for (int[] ints:
                 ticketCode) {
                numIn.add(ints[index-1]);
            }
            numListIn.add(0,issueCode);
            numListIn.add(1,numIn);
            numList.add(numListIn);
        }

        return numList;
    }

    //东南西北过滤器
    private static int[] ESWN_Filter(int[] i) {
        int[] ints = new int[i.length];
        Integer[] East = {1, 5, 9, 13, 17};
        Integer[] SOUTH = {2, 6, 10, 14, 18};
        Integer[] WEST = {3, 7, 11, 15, 19};
        Integer[] NORTH = {4, 8, 12, 16, 20};
        for (int j = 0; j < i.length; j++) {
            if (Arrays.asList(East).contains(i[j])) {
                ints[j] = 0;
            }
            if (Arrays.asList(SOUTH).contains(i[j])) {
                ints[j] = 1;
            }
            if (Arrays.asList(WEST).contains(i[j])) {
                ints[j] = 2;
            }
            if (Arrays.asList(NORTH).contains(i[j])) {
                ints[j] = 3;
            }
        }

        return ints;
    }

    //中发白过滤器
    private static int[] ZFB_Filter(int[] i) {
        int[] ints = new int[i.length];
        Integer[] Z = {1, 2, 3, 4, 5, 6, 7};
        Integer[] F = {8, 9, 10, 11, 12, 13, 14};
        Integer[] B = {15, 16, 17, 18, 19, 20};
        for (int j = 0; j < i.length; j++) {
            if (Arrays.asList(Z).contains(i[j])) {
                ints[j] = 0;
            }
            if (Arrays.asList(F).contains(i[j])) {
                ints[j] = 1;
            }
            if (Arrays.asList(B).contains(i[j])) {
                ints[j] = 2;
            }
        }

        return ints;
    }

    //单双过滤器
    private static int[] DS_Filter(int[] i, int type) {
        int[] ints = new int[i.length];
        if (type == 3) {
            for (int j = 0; j < i.length; j++) {
                ints[j] = (i[j] == 11 ? 2 : (i[j] % 2 == 1 ? 0 : 1));
            }
            return ints;
        }
        for (int j = 0; j < i.length; j++) {
            ints[j] = (i[j] % 2 == 1 ? 0 : 1);
        }
        return ints;
    }

    //大小过滤器
    private static int[] BS_Filter(int[] i, int type) {
        int[] ints = new int[i.length];
        switch (type) {
            case 1:
                for (int j = 0; j < i.length; j++) {
                    ints[j] = (i[j] > 5 ? 0 : 1);
                }
                return ints;
            case 2:
                for (int j = 0; j < i.length; j++) {
                    ints[j] = (i[j] > 4 ? 0 : 1);
                }
                return ints;
            case 3:
                for (int j = 0; j < i.length; j++) {
                    ints[j] = (i[j] == 11 ? 2 : (i[j] > 5 ? 0 : 1));
                }
                return ints;
            case 4:
                for (int j = 0; j < i.length; j++) {
                    ints[j] = (i[j] > 10 ? 0 : 1);
                }
                return ints;
            case 6:
                for (int j = 0; j < i.length; j++) {
                    ints[j] = (i[j] > 10 ? 0 : 1);
                }
                return ints;
        }
        return i;
    }

    //路珠分组组件
    private static List<List<Integer>> moreArr(int[] args) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList();
        int index = 0;
        for (int i = 0; i < args.length - 1; i++) {
            list.add(args[i]);
            if (args[i] != args[i + 1]) {
                result.add(list);
                list = new ArrayList<>();
                index = i;
            }
        }
        List<Integer> ex = new ArrayList<>();
        for (int j = index + 1; j < args.length; j++) {
            ex.add(args[j]);
        }
        result.add(ex);
        return result;
    }


    public static ArrayList<String> getpastDaysList(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i < intervals
                ; i++) {
            pastDaysList.add(getPastDate(i));
            fetureDaysList.add(getFetureDate(i));
        }
        return pastDaysList;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * @Description: 返回单球大小
     * @Param: [i, type]
     * @return: int
     * @Author: Hash
     * @Date: 2019/4/12
     */
    private static int B_S(int i, int type) {
        switch (type) {
            case 1:
                return (i > 5 ? 0 : 1);
            case 2:

                return (i > 4 ? 0 : 1);
            case 3:
                return (i > 5 ? 0 : 1);
            case 4:
                return (i > 10 ? 0 : 1);
            case 6:
                return (i > 10 ? 0 : 1);

        }
        return i;
    }

    //号码前后过滤器(传入每期号码数组)
    private static int[] BA_Filter(int[] code) {
        int[] temp = new int[code.length];
        List list = Arrays.asList(ArrayUtils.toObject(code));
        for (int i = 1; i < code.length + 1; i++) {
            temp[i - 1] = (list.indexOf(i) + 1) < 6 ? 0 : 1;
        }
        return temp;
    }


    public static Long getThisWeekDate(int mode) {
        DateTime now = DateTime.now();
        switch (mode) {
            case 1:
                if (now.getDayOfWeek() == 7) {
                    now = now.plusWeeks(2).withHourOfDay(21).withMinuteOfHour(30).withSecondOfMinute(0);
                } else {
                    now = now.withDayOfWeek(2).withHourOfDay(21).withMinuteOfHour(30).withSecondOfMinute(0);
                }
                break;
            case 2:
                now = now.withDayOfWeek(4).withHourOfDay(21).withMinuteOfHour(30).withSecondOfMinute(0);
                break;
            case 0:
                now = now.withDayOfWeek(6).withHourOfDay(21).withMinuteOfHour(30).withSecondOfMinute(0);
                break;
        }

        return now.getMillis();
    }


}
