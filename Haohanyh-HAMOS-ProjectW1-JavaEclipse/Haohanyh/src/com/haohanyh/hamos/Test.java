package com.haohanyh.hamos;

import com.nle.ZigbeeSensorData;
import com.nle.serialport.SerialPortManager;
import com.nle.serialport.exception.SerialPortException;

import gnu.io.SerialPort;

public class Test {

	public static void main(String[] args) throws SerialPortException {
		double temperature = 0;			//温度
		double humidity = 0;			//湿度
		double light = 0;				//光照
		boolean hasPerson = false;		//人体是否
		boolean hasFire = false;		//火焰是否

		//串口管理对象 serialPort
		SerialPortManager manager = new SerialPortManager();

		//打开端口,COM200 38400,端口号和波特率和仿真软件一致！
		SerialPort serialPort = manager.openPort("COM201", 38400);

		//初始化获取Zigbee传感器数据的对象
		ZigbeeSensorData sensorData = new ZigbeeSensorData(manager, serialPort);

		ThreadRun(3000);

		while(true)
		{
			String temp = null;
			System.out.println("温度"+HaohanyhXZigbeeI(temp,"湿度",temperature));

			String humi = null;
			System.out.println("湿度"+HaohanyhXZigbeeI(humi,"湿度",humidity));

			String illumination = null;
			System.out.println("光照"+HaohanyhXZigbeeI(illumination,"光照",light));

			String person = null;
			System.out.println("人体"+HaohanyhXZigbeeII(person, "人体", hasPerson));

			String fire = null;
			System.out.println("火焰"+HaohanyhXZigbeeII(fire, "火焰", hasFire));

			ThreadRun(1000);
		}

	}

	public static Double HaohanyhXZigbeeI(String sensor,String sensorChinese,Double value) {
		sensor = ZigbeeSensorData.getSensorData(sensorChinese);
		value = Double.valueOf(sensor);
		return value;
	}

	public static Boolean HaohanyhXZigbeeII(String sensor,String sensorChinese,Boolean value) {
		sensor = ZigbeeSensorData.getSensorData(sensorChinese);
		value = sensor.equals("false")?true:false;
		return value;
	}


	public static void ThreadRun(int i) {
		//让线程休眠一段时间,3秒
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
