import com.shanxi.water.SpringbootApplication;
import com.shanxi.water.entity.*;
import com.shanxi.water.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.*;

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
    public void generateDeviceCount(){
        DeviceCount deviceCount = new DeviceCount();
        deviceCount.setId(UUID.randomUUID().toString());
        deviceCount.setOnlineDevicesCount(new Random().nextInt(100));
        deviceCount.setOfflineDevicesCount(new Random().nextInt(100));
        deviceCount.setWarningDevicesCount(new Random().nextInt(100));
        deviceCount.setShutdownDevicesCount(new Random().nextInt(100));
        deviceCountMapper.insertDeviceCount(deviceCount);
    }
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
                cityWaterConsume.setMonth(month);
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
                cityWaterTemrature.setMonth(month);
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
                cityWaterPressure.setMonth(month);
                DecimalFormat df = new DecimalFormat("#.##");
                cityWaterPressure.setColdWaterPressure(Double.parseDouble(df.format(0.3+Math.random()*0.3)));
                cityWaterPressure.setHotWaterPressure(Double.parseDouble(df.format(0.3+Math.random()*0.3)));
                cityWaterPressureMapper.insertCityWaterPressure(cityWaterPressure);
            }
        }
    }

    @Test
    public void  generateDeviceData(){
        ArrayList<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
                "延安市", "汉中市", "榆林市", "安康市", "商洛市");
        ArrayList<String> deviceNameList = new ArrayList<>();
        Collections.addAll(deviceNameList,"净水器","加热器","过滤器","增压器","电子阀门");
        for(String city : cityList){
            for(String deviceName : deviceNameList){
                for(int i=0;i<30;i++){
                    double random = Math.random();
                    DeviceInfo deviceInfo = new DeviceInfo();
                    deviceInfo.setId(UUID.randomUUID().toString());
                    deviceInfo.setCityName(city);
                    deviceInfo.setDeviceName(deviceName+"#"+i);
                    deviceInfo.setDeviceStatus(random<0.9?"设备正常":"设备故障");
                    if ("设备正常".equals(deviceInfo.getDeviceStatus())) {
                        deviceInfo.setDeviceMaintenanceStatus("-");
                    } else {
                        deviceInfo.setDeviceMaintenanceStatus("正在维修中");
                    }
                    deviceMapper.insertDeviceInfo(deviceInfo);
                }
                
            }
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
                cityWaterEnergyConsumption.setMonth(month);
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
}