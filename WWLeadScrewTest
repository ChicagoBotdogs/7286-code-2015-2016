package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 *
 */
public class WWLeadScrewTest extends OpMode
{
	//Declare the motors being used and a timer called "time"
	DcMotor leadScrew;//Declare Motors for Lead Screw
	Servo tiltLeft;
	Servo tiltRight;

	ServoController screwController;
	ElapsedTime time;


	int screwPosition=0;
	double currentTime=0;

	//Gives the Enum State a name that we can use in the code to reference the 3 actions

	public void init()
	{

		screwController = hardwareMap.servoController.get("servoController");//creats servoControler
		screwController.pwmEnable();

		tiltLeft = hardwareMap.servo.get("tiltLeft");
		tiltRight = hardwareMap.servo.get("tiltRight");
		leadScrew=hardwareMap.dcMotor.get("leadScrew");

		time = new ElapsedTime();

	}

	public void loop()
	{
		double currentTime = time.time();
		if (screwPosition==0)
		{
			time.reset();
			screwPosition =1;//time is zero
		}

		if (screwPosition==1)
		{
			tiltLeft.setPosition(1);
			tiltRight.setPosition(0);
		}

		if (screwPosition==2)
		{
			tiltLeft.setPosition(.5);
			tiltRight.setPosition(.5);
		}

		if (screwPosition==3)
		{
			leadScrew.setPower(1);
		}

		if (screwPosition==4)
		{
			leadScrew.setPower(-1);
		}

		if (screwPosition==5)
		{
			leadScrew.setPower(0);
		}

        if(currentTime<1)//if time is less than the time it takes to run forward then move forward
			screwPosition=1;

		if (currentTime>1 && currentTime< 1.5)//turn right after moving forward
		{
			screwPosition = 2;
		}

		if (currentTime>1.5 && currentTime< 2.5)//turn right after moving forward
		{
			screwPosition = 3;
		}
		if(currentTime>2.5 && currentTime< 3.5)
		{
			screwPosition =4;
		}
		if(currentTime> 3.5)
		{
			screwPosition =5;
		}

	}
}
