package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 *
 */
public class ClimbBlue extends OpMode
{
	//Declare the motors being used and a timer called "time"
	DcMotor leadScrew;//Declare Motors for Lead Screw
	Servo tiltLeft;
	Servo tiltRight;

	ServoController screwController;
	ElapsedTime time;

	DcMotor motorLeftBack;//Declare Motors for Drive Train
	DcMotor motorLeft;
	DcMotor motorRight;
	DcMotor motorRightBack;

	Servo holdLeft;//Declares servo hooks on front for bars
	Servo holdRight;

	Servo leverBlue;//left lever
	Servo leverRed;
	int count=0;
	int screwPosition=0;

	//Gives the Enum State a name that we can use in the code to reference the 3 actions

	public void init()
	{

		screwController = hardwareMap.servoController.get("servoController");//creats servoControler
		screwController.pwmEnable();

		tiltLeft = hardwareMap.servo.get("tiltLeft");
		tiltRight = hardwareMap.servo.get("tiltRight");
		leadScrew=hardwareMap.dcMotor.get("leadScrew");

		motorRight=hardwareMap.dcMotor.get("motor_3");//rightwheels
		motorRightBack = hardwareMap.dcMotor.get("motor_4");
		motorLeft = hardwareMap.dcMotor.get("motor_1");//left wheels
		motorLeftBack = hardwareMap.dcMotor.get("motor_2");
		motorLeft.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped
		motorLeftBack.setDirection(DcMotor.Direction.REVERSE);

		leverRed = hardwareMap.servo.get("leverRed");
		leverBlue = hardwareMap.servo.get("leverBlue");
		holdLeft = hardwareMap.servo.get("holdLeft");
		holdRight = hardwareMap.servo.get("holdRight");

		holdLeft.setPosition(.5);//sets initial hook position
		holdRight.setPosition(.5);

		leverBlue.setPosition(0);//Initial position
		leverRed.setPosition(1);

		time = new ElapsedTime();

	}

	public void loop()
	{
		double currentTime = time.time();
		if (screwPosition==0)
		{
			time.reset();
			screwPosition =2;//time is zero\
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
		if (count==0)
		{
			time.reset();
			count =1;//time is zero
		}

		if (count==1)
		{
			motorLeft.setPower(1);
			motorLeftBack.setPower(1);
			motorRight.setPower(1);
			motorRightBack.setPower(1);
		}

		if (count==2)
		{
			motorLeft.setPower(-1);
			motorLeftBack.setPower(-1);
			motorRight.setPower(1);
			motorRightBack.setPower(1);
		}

		if (count==3)
		{
			motorLeft.setPower(1);
			motorLeftBack.setPower(1);
			motorRight.setPower(-1);
			motorRightBack.setPower(-1);
		}

		if (count==4)
		{
			motorLeft.setPower(0);
			motorLeftBack.setPower(0);
			motorRight.setPower(0);
			motorRightBack.setPower(0);
		}

		if(currentTime<.9)//go forward for 1 second  screw tilts up   APPROVED
		{
			count=1;
			screwPosition = 1;
		}
        if (currentTime>.9 && currentTime<1.65)//turn left
		{
			count=3;
		}
		if(currentTime>1.65&& currentTime<6.5)//goes forward and drives up ramp
		{
			count=1;
		}
		if(currentTime>6.5 && currentTime<15)//stop
		{
			screwPosition=3;
		}
		if(currentTime>15)
		{

			count=4;
			screwPosition=5;
		}
	}

}
