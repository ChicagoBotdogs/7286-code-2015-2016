package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.*;


/**
 *
 */
public class WWAutoBlueFloor extends OpMode
{
	//Declare the motors being used and a timer called "time"
	DcMotor motorLeftBack;//Declare Motors for Drive Train
	DcMotor motorLeft;
	DcMotor motorRight;
	DcMotor motorRightBack;
	ElapsedTime time;


	int count=0;
	double currentTime=0;

	//Gives the Enum State a name that we can use in the code to reference the 3 actions

	public void init()
	{

		motorRight=hardwareMap.dcMotor.get("motor_3");//rightwheels
		motorRightBack = hardwareMap.dcMotor.get("motor_4");
		motorLeft = hardwareMap.dcMotor.get("motor_1");//left wheels
		motorLeftBack = hardwareMap.dcMotor.get("motor_2");
		motorLeft.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped
		motorLeftBack.setDirection(DcMotor.Direction.REVERSE);

		time = new ElapsedTime();

	}

	public void loop()
	{
		double currentTime = time.time();
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

        if(currentTime<1)//if time is less than the time it takes to run forward then move forward
			count=1;

		if (currentTime>1 && currentTime< 1.5)//turn right after moving forward
		{
			count = 3;
		}

		if (currentTime>1.5 && currentTime< 2.5)//turn right after moving forward
		{
			count = 1;
		}
		if(currentTime>2.5)
		{
			count =4;
		}

	}
}