import com.shanxi.water.SpringbootApplication;
import com.shanxi.water.entity.*;
import com.shanxi.water.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)  // 如果是 JUnit 4
@SpringBootTest(classes = SpringbootApplication.class)
public class MyBatisIntegrationTest {
    @Autowired
    private DeviceCountMapper deviceCountMapper;
    @Autowired
    private CityWaterConsumeMapper cityWaterConsumeMapper;
    @Autowired
    private CityWaterTemperatureMapper cityWaterTemperatureMapper;
    @Autowired
    private CityWaterPressureMapper cityWaterPressureMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private CityWaterEnergyConsumptionMapper cityWaterEnergyConsumptionMapper;
    @Autowired
    private PowerConsumeMapper powerConsumeMapper;
    @Autowired
    private HomePageSMapper homePageSMapper;
    @Test
    public void generateCityWaterConsume(){
        ArrayList<String> monthList = new ArrayList<>();
        Collections.addAll(monthList, "一月", "二月", "三月", "四月", "五月", "六月",
                "七月", "八月", "九月", "十月", "十一月", "十二月");
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        for(String city : cityList){
            for(String month : monthList){
                CityWaterConsume cityWaterConsume = new CityWaterConsume();
                cityWaterConsume.setId(UUID.randomUUID().toString());
                cityWaterConsume.setCityName(city);
//                cityWaterConsume.setMonth(month);
                cityWaterConsume.setHotWaterConsume(500+new Random().nextInt(1000));
                cityWaterConsume.setColdWaterConsume(500+new Random().nextInt(1000));
                cityWaterConsumeMapper.insertCityWaterConsume(cityWaterConsume);
            }
        }
    }
    @Test
    public void generateCityWaterTemperature(){
        ArrayList<String> monthList = new ArrayList<>();
        Collections.addAll(monthList, "一月", "二月", "三月", "四月", "五月", "六月",
                "七月", "八月", "九月", "十月", "十一月", "十二月");
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        for(String city : cityList){
            for(String month : monthList){
                CityWaterTemperature cityWaterTemrature = new CityWaterTemperature();
                cityWaterTemrature.setId(UUID.randomUUID().toString());
                cityWaterTemrature.setCityName(city);
                cityWaterTemrature.setColdWaterTemperature(20+new Random().nextInt(10));
                cityWaterTemrature.setHotWaterTemperature(80+new Random().nextInt(10));
                cityWaterTemperatureMapper.insertCityWaterTemperature(cityWaterTemrature);
            }
        }
    }

    @Test
    public void generateCityWaterPressure(){

        ArrayList<String> monthList = new ArrayList<>();
        Collections.addAll(monthList, "一月", "二月", "三月", "四月", "五月", "六月",
                "七月", "八月", "九月", "十月", "十一月", "十二月");
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        for(String city : cityList){
            for(String month : monthList){
                CityWaterPressure cityWaterPressure = new CityWaterPressure();
                cityWaterPressure.setId(UUID.randomUUID().toString());
                cityWaterPressure.setCityName(city);
//                cityWaterPressure.setMonth(month);
                DecimalFormat df = new DecimalFormat("#.##");
                cityWaterPressure.setColdWaterPressure(Double.parseDouble(df.format(0.3+Math.random()*0.3)));
                cityWaterPressure.setHotWaterPressure(Double.parseDouble(df.format(0.3+Math.random()*0.3)));
                cityWaterPressureMapper.insertCityWaterPressure(cityWaterPressure);
            }
        }
    }
    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    @Test
    public void  generateDeviceData(){
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        ArrayList<String> deviceNameList = new ArrayList<>();
        Collections.addAll(deviceNameList,"净水器","加热器","过滤器","增压器","电子阀门");
        LocalDate start = LocalDate.now().minusYears(1);
        LocalDate end = LocalDate.now();

        while (!start.isAfter(end)) {
            for (String city : cityList) {
                for (String deviceName : deviceNameList) {
                    for (int i = 0; i < 30; i++) {
                        Random random = new Random();
                        DeviceInfo deviceInfo = new DeviceInfo();
                        deviceInfo.setId(UUID.randomUUID().toString());
                        deviceInfo.setCityName(city);
                        deviceInfo.setDeviceName(deviceName+i);
                        if(generateRandomDeviceStatus(random).equals("故障")){
                            deviceInfo.setDeviceStatus("故障");
                            deviceInfo.setDeviceMaintenanceStatus("正在维修中");
                        }else{
                            deviceInfo.setDeviceStatus(generateRandomDeviceStatus(random));
                            deviceInfo.setDeviceMaintenanceStatus("-");
                        }

                        deviceInfo.setInstallationDate(start);
                        submitInsertTask(deviceInfo);
                    }
                }
            }
            start = start.plusDays(1);
        }
        shutdownAndAwaitTermination();
    }
    private String generateRandomDeviceStatus(Random random) {
        double randomValue = random.nextDouble();
        if (randomValue < 0.05) {
            return "离线";
        } else if (randomValue < 0.85) {
            return "运行";
        } else if (randomValue < 0.91) {
            return "故障";
        } else {
            return "预警";
        }
    }
    private void submitInsertTask(DeviceInfo deviceInfo) {
        executorService.submit(() -> {
            try {
                deviceMapper.insertDeviceInfo(deviceInfo);
            } catch (Exception e) {
                System.err.println("Failed to insert device info: " + e.getMessage());
            }
        });
    }
    private void shutdownAndAwaitTermination() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.MINUTES)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    @Test
    public void generateCityWaterEnergyConsumption(){
        ArrayList<String> monthList = new ArrayList<>();
        Collections.addAll(monthList, "一月", "二月", "三月", "四月", "五月", "六月",
                "七月", "八月", "九月", "十月", "十一月", "十二月");
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        for(String city : cityList){
            for(String month : monthList){
                CityWaterEnergyConsumption cityWaterEnergyConsumption = new CityWaterEnergyConsumption();
                cityWaterEnergyConsumption.setId(UUID.randomUUID().toString());
                cityWaterEnergyConsumption.setCityName(city);
//                cityWaterEnergyConsumption.setMonth(month);
                cityWaterEnergyConsumption.setHotWaterEnergyConsumption(300+new Random().nextInt(1000));
                cityWaterEnergyConsumption.setColdWaterEnergyConsumption(300+new Random().nextInt(1000));
                cityWaterEnergyConsumptionMapper.insertCityWaterEnergyConsumption(cityWaterEnergyConsumption);
            }
        }
    }

    @Test
    public void generatePowerConsume(){
        ArrayList<String> equipments = new ArrayList<>(Arrays.asList("净水器", "增压器", "加热器", "过滤器", "电子阀门"));
        for(String equipment : equipments){
            PowerConsume powerConsume = new PowerConsume();
            powerConsume.setPower(15+new Random().nextInt(15));
            powerConsume.setEquipment(equipment);
            powerConsume.setId(UUID.randomUUID().toString());
            powerConsumeMapper.insertPowerConsume(powerConsume);
        }
    }

    @Test
    public void generateHomePageS(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            HomePageS homePageS = new HomePageS();
            homePageS.setId(UUID.randomUUID().toString());

            homePageS.setColdWaterVolume(0.65);
            homePageS.setHotWaterVolume(0.85);

            homePageS.setColdWaterTemperature(23);
            homePageS.setHotWaterTemperature(95);

            homePageS.setColdWaterPressure(3);
            homePageS.setHotWaterPressure(2);

            homePageSMapper.insertHomePageS(homePageS);
        }
    @Test
    public void deleteDevice(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        executorService.submit(() -> deviceMapper.deleteAll());
    }

    @Test
    public void insertRandomCityWaterTemperatures() {
        List<String> cityList = new ArrayList<>();
        Collections.addAll(cityList, "西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");

        Random random = new Random();
        LocalDate startDate = LocalDate.now().minusDays(365);
        for (String city : cityList) {
            for (int i = 0; i < 365; i++) {
                CityWaterTemperature temp = new CityWaterTemperature();
                temp.setId(UUID.randomUUID().toString());
                temp.setCityName(city);
                // 生成双精度随机温度值并保留两位小数
                double coldWaterTemperature = 20 + random.nextDouble() * 10;  // 20.00-29.99
                double hotWaterTemperature = 70 + random.nextDouble() * 15;  // 70.00-84.99
                temp.setColdWaterTemperature(Math.round(coldWaterTemperature * 100.0) / 100.0);
                temp.setHotWaterTemperature(Math.round(hotWaterTemperature * 100.0) / 100.0);
                temp.setInstallationDate(startDate.plusDays(i));
                cityWaterTemperatureMapper.insertCityWaterTemperature(temp);
            }
        }
    }

    @Test
    public void insertAndFindCityWaterPressures() {
        List<String> cityList = new ArrayList<>();
        Collections.addAll(cityList, "西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");

        Random random = new Random();
        LocalDate startDate = LocalDate.now().minusDays(365);

        // 插入数据
        for (String city : cityList) {
            for (int i = 0; i < 365; i++) {
                CityWaterPressure pressure = new CityWaterPressure();
                pressure.setId(UUID.randomUUID().toString());
                pressure.setCityName(city);
                pressure.setColdWaterPressure(Double.parseDouble(String.format("%.2f", 1.0 + random.nextDouble()))); // 1.0-2.0
                pressure.setHotWaterPressure(Double.parseDouble(String.format("%.2f", 1.0 + random.nextDouble() * 0.3))); // 1.3-1.6
                pressure.setInstallationDate(startDate.plusDays(i));
                cityWaterPressureMapper.insertCityWaterPressure(pressure);
            }
        }

    }

    @Test
    public void insertRandomCityWaterEnergyConsumptions() {
        List<String> cityList = new ArrayList<>();
        Collections.addAll(cityList, "西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");

        Random random = new Random();
        LocalDate startDate = LocalDate.now().minusDays(365);

        for (String city : cityList) {
            for (int i = 0; i < 365; i++) {
                CityWaterEnergyConsumption energy = new CityWaterEnergyConsumption();
                energy.setId(UUID.randomUUID().toString());
                energy.setCityName(city);
                // 生成随机能耗值并保留两位小数
                double coldWaterEnergy = 15 + random.nextDouble() * 10;  // 15.00-24.99
                double hotWaterEnergy = 15 + random.nextDouble() * 10;   // 15.00-24.99
                energy.setColdWaterEnergyConsumption(Math.round(coldWaterEnergy * 100.0) / 100.0);
                energy.setHotWaterEnergyConsumption(Math.round(hotWaterEnergy * 100.0) / 100.0);
                energy.setInstallationDate(startDate.plusDays(i));
                cityWaterEnergyConsumptionMapper.insertCityWaterEnergyConsumption(energy);
            }
        }
    }

    @Test
    public void insertAndFindCityWaterConsumptions() {
        List<String> cityList = new ArrayList<>();
        Collections.addAll(cityList, "西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");

        Random random = new Random();
        LocalDate startDate = LocalDate.now().minusDays(365);

        for (String city : cityList) {
            for (int i = 0; i < 365; i++) {
                CityWaterConsume consumption = new CityWaterConsume();
                consumption.setId(UUID.randomUUID().toString());
                consumption.setCityName(city);
                double coldWater = 1000 + random.nextDouble() * 2000;
                double hotWater = 500 + random.nextDouble() * 500;
                consumption.setColdWaterConsume(Double.parseDouble(String.format("%.2f", coldWater)));
                consumption.setHotWaterConsume(Double.parseDouble(String.format("%.2f", hotWater)));
                consumption.setInstallationDate(startDate.plusDays(i));
                cityWaterConsumeMapper.insertCityWaterConsume(consumption);
            }
        }

        // Verify data insertion for a specific city

    }
}