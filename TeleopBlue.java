
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ServoController;
/**
 * TeleOp Mode
 * <p>
 * @version: 11/1/2015
 * Enables control of the robot via the gamepad
 */
public class Teleop extends OpMode
{

	DcMotor motorLeftBack;//Declare Motors for Drive Train
	DcMotor motorLeft;
	DcMotor motorRight;
	DcMotor motorRightBack;

	DcMotor hangArm;//Declare Motors for hanging
	DcMotor flingArm;

	DcMotor leadScrew;//Declare Motors for Lead Screw
	Servo tiltLeft;
	Servo tiltRight;

	Servo holdLeft;//Declares servo hooks on front for bars
	Servo holdRight;

	Servo leverBlue;//left lever

	ServoController screwController;//Declares servo controller

	double screwPosition1;//Declares values for initial screw position
	double screwPosition2;
	// amount to change the claw servo position by




	/**
	 * Constructor
	 */
	public Teleop()

	{
	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {


		/*  CONFIGURATION NAMES
		 *
		 *   "motor_1" is front left
		 *   "motor_2" is back left
		 *   "motor_3" is front right
		 *   "motor_4" is back back right
		 *	 "leadScrew is the lead screw DC motor
		 *	 "flingArm" is the motor that flips the arm out
		 *	 "hangArm" is the motor that pulls the nylon cord up
		 *
		 *   "servoController" is the servo Controller
		 * "tiltLeft"  //Tilts the lead screw
		 * "tiltRight"
		 *
		 * "holdLeft"//hooks on the front for the bar
		 * holdRight"
		 *
		 */

		motorRight=hardwareMap.dcMotor.get("motor_3");//rightwheels
		motorRightBack = hardwareMap.dcMotor.get("motor_4");


		motorLeft = hardwareMap.dcMotor.get("motor_1");//left wheels
		motorLeftBack = hardwareMap.dcMotor.get("motor_2");

		motorLeft.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped
		motorLeftBack.setDirection(DcMotor.Direction.REVERSE);

		screwController = hardwareMap.servoController.get("servoController");//creats servoControler
		screwController.pwmEnable();
		leverBlue = hardwareMap.servo.get("leverBlue");

		tiltLeft = hardwareMap.servo.get("tiltLeft");
		tiltRight = hardwareMap.servo.get("tiltRight");
		holdLeft = hardwareMap.servo.get("holdLeft");
		holdRight = hardwareMap.servo.get("holdRight");

		leadScrew=hardwareMap.dcMotor.get("leadScrew");

		screwPosition1 = .4;
		screwPosition2 = .6;

        holdLeft.setPosition(1);//sets initial hook position
		holdRight.setPosition(1);

		leverBlue.setPosition(1);//Initial position

		tiltLeft.setPosition(screwPosition1);//sets initial degree that the leadscrew is tilted
		tiltRight.setPosition(screwPosition2);

		flingArm = hardwareMap.dcMotor.get("flingArm");
		hangArm = hardwareMap.dcMotor.get("hangArm");
	}

	/*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {

        float left = -gamepad1.left_stick_y;//gets information from joystick
        float right = -gamepad1.right_stick_y;
		float powerScrew = -gamepad2.left_stick_y;
		float powerHang = -gamepad2.right_stick_y;

		// clip the right/left values so that the values never exceed +/- 1


		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.

		// write the values to the motors


		right = Range.clip(right, -1, 1);//clips values into section
		left = Range.clip(left, -1, 1);
		powerHang = Range.clip(powerHang, -1, 1);
		powerScrew = Range.clip(powerScrew, -1, 1);
		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.

		powerHang =  (float)scaleInput(powerHang); //scales input
		right = (float)scaleInput(right);
		left =  (float)scaleInput(left);
		left = (float) (left*.9);
		right = (float) (right*.9);
		powerScrew = (float)scaleInput(powerScrew);
		// write the values to the motors


		hangArm.setPower(powerHang);//set motor values to power retireived from remote
		motorLeftBack.setPower(left);
		motorLeft.setPower(left);

		motorRight.setPower(right);
		motorRightBack.setPower(right);
		leadScrew.setPower(powerScrew);


		// clip the right/left values so that the values never exceed +/- 1


		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.

		// write the values to the motors

		if (gamepad2.left_bumper) //left bumper tilts screw up
		{
			tiltLeft.setPosition(1);
			tiltRight.setPosition(0);
		}

		if (gamepad2.right_bumper) //right bumper tilts screw down
		{
			tiltLeft.setPosition(.4);
			tiltRight.setPosition(.6);
		}
		if (gamepad2.a)
		{
			flingArm.setPower(1);
		}

		else if (gamepad2.b)
		{
			flingArm.setPower(-1);

		}
		else
		{
			flingArm.setPower(0);

		}


		if (gamepad1.x) //hooks up or down
		{
			holdLeft.setPosition(.25);
			holdRight.setPosition(.25);
		}
		if (gamepad1.y)
		{
			holdLeft.setPosition(1);
			holdRight.setPosition(1);
		}

		if (gamepad1.a) //hooks up or down
		{
			leverBlue.setPosition(1);
		}
		if (gamepad1.b)
		{
			leverBlue.setPosition(.5);
		}
		// clip the position values so that they never exceed their allowed range.




		telemetry.addData("Text", "*** Robot Data***");
		telemetry.addData("right tgt pwr", "leadScrew power: " + String.format("%.2f", powerScrew));
		telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
		telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
		telemetry.addData("right tgt pwr", "Hanging power: " + String.format("%.2f", powerHang));



}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}
	
	/*
	 * This method scales the joystick input so for low joystick values, the 
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
		
		// get the corresponding index for the scaleInput array.
		int index = (int) (dVal * 16.0);
		
		// index should be positive.
		if (index < 0) {
			index = -index;
		}

		// index cannot exceed size of array minus 1.
		if (index > 16) {
			index = 16;
		}

		// get value from the array.
		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}

		// return scaled value.
		return dScale;
	}

}
