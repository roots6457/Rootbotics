package org.usfirst.frc.team6457.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import java.lang.Math;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	DoubleSolenoid sol1;
	Compressor com1;
	CameraServer camera;
	Joystick controller;
	Spark motorLeft_1;
	Spark motorRight_1;
	Victor clawMotor;
	Timer timer;
	int timeDuration;
	int robotOff = 9;//i'm guessing check later for buttons on FRC Driver Station
	int robotOn = 10;// i'm guessing check later for buttons on FRC Driver Station
	int leftPWM = 0;// PWM port for the spark that runs one of the left motors
	int rightPWM = 1;// PWM port for the spark that runs one of the right motors
	int clawPWM = 2;// PWM port for the spark that runs one of the cylinder motor
	int controllerUSBPort = 0;
	int clawUpButton = 1;
	int clawDownButton = 2;
	int fastButton = 6;//i'm guessing check later for buttons on FRC Driver Station
	int slowButton = 5; //i'm guessing check later for buttons on FRC Driver Station
	int rightAxis = 1;
	int zAxis = 2;
	int leftAxis = 5;
	int cylinderAxis = 0;
	int solenoidButton = 4;
	
	double leftMotorSpeed = 0;
	double rightMotorSpeed = 0;
	double clawMotorSpeed = 0;
	
	
	//@Override
	public void robotInit()
	{
		controller = new Joystick(controllerUSBPort);
		motorLeft_1 = new Spark(leftPWM);
		motorRight_1 = new Spark(rightPWM);
		clawMotor = new Victor(clawPWM);
		
		sol1 = new DoubleSolenoid(0, 1); 
		com1 = new Compressor(0);
		com1.setClosedLoopControl(true);
		
		clawMotor.set(clawMotorSpeed);
		motorLeft_1.set(leftMotorSpeed);
		motorRight_1.set(-rightMotorSpeed);
		
		CameraServer.getInstance().startAutomaticCapture();
		
	}
	
;	public void autonomousInit() 
	{
	}
	 
	//@Override
	public void autonomousPeriodic() 
	{
		motorLeft_1.set(- .25);
		motorRight_1.set(.25);
	}
	//@Override
	public void teleopPeriodic() 
	{
		double slowPercent = .25;
		double defaultPercent = 1;
			
			double leftAxisRaw = controller.getRawAxis(leftAxis);
			double rightAxisRaw = controller.getRawAxis(rightAxis);
			boolean leftNegative = leftAxisRaw < 0;
			boolean rightNegative = rightAxisRaw < 0;
			
			int leftValue = (int) (leftAxisRaw * (leftNegative ? -1 : 1) *100);
			int rightValue = (int) (rightAxisRaw * (rightNegative ? -1 : 1) *100);
			
			if (leftValue <= 5)
				leftAxisRaw = 0;
			else if (leftValue > 5 && leftValue <= 10)
				leftAxisRaw = Math.pow(2, 3) + 
				4 * (leftValue - 5);
			else if (leftValue > 10 && leftValue <= 15)
				leftAxisRaw = Math.pow(2, 3) + 
				4 * (leftValue - 5);
			else if (leftValue > 15 && leftValue <= 25)
				leftAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * (leftValue - 15);
			else if (leftValue > 25 && leftValue <= 45)
				leftAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * (leftValue - 25);
			else if (leftValue > 45 && leftValue <= 75) 
				leftAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * 10 +
				0.8 * (leftValue - 45);
			else if (leftValue > 75 && leftValue <= 100)
				leftAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * 10 +
				0.8 * 20 +
				0.2 * (leftValue - 75);
			
//			if (rightValue <= 3)
//				rightAxisRaw = 0;
//			if ((0 - rightValue) < .01 || (0 - rightValue) > .01)
//				rightAxisRaw = 0;
//			if (rightValue > 3 && rightValue <= 5)
//				rightAxisRaw = Math.pow(2, rightValue);
//			    rightAxisRaw = 0;
			if (rightValue <= 5)
				rightAxisRaw = 0;
			else if (rightValue > 5 && rightValue <= 10)
				rightAxisRaw = Math.pow(2, 3) + 
				4 * (rightValue - 5);
			else if (rightValue > 10 && rightValue <= 15)
				rightAxisRaw = Math.pow(2, 3) + 
				4 * (rightValue - 5);
			else if (rightValue > 15 && rightValue <= 25)
				rightAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * (rightValue - 15);
			else if (rightValue > 25 && rightValue <= 45)
				rightAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * (rightValue - 25);
			else if (rightValue > 45 && rightValue <= 75) 
				rightAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * 10 +
				0.8 * (rightValue - 45);
			else if (rightValue > 75 && rightValue <= 100)
				rightAxisRaw = Math.pow(2, 3) +
				4 * 5 +
				2 * 10 +
				1 * 10 +
				0.8 * 20 +
				0.2 * (rightValue - 75);
			
			rightAxisRaw *= rightNegative ? -1 : 1;
			leftAxisRaw *= leftNegative ? -1 : 1;
			
			//compressor functions
			
			
			
			leftMotorSpeed = leftAxisRaw * defaultPercent / 100;
			rightMotorSpeed = rightAxisRaw * defaultPercent / 100;
			
			
			/* if(controller.getRawButton(fastButton))
			{
				leftMotorSpeed = leftAxisRaw;
				rightMotorSpeed = rightAxisRaw;
				//fastTimer.start();
			}
			if(controller.getRawButton(slowButton))// I don't know if it's necessary to put an else if on this function and then a else for the default speed
			{
				leftMotorSpeed = leftAxisRaw * slowPercent;
				rightMotorSpeed = rightAxisRaw * slowPercent;
				//slowTimer.start();
			} */
			motorLeft_1.set(leftMotorSpeed);//setting the left motors to a certain speed
			motorRight_1.set(-rightMotorSpeed);//setting the right motors to a certain speed
			
			if(controller.getRawButton(clawUpButton))
			{
				clawMotorSpeed = .5;
			}
			else if(controller.getRawButton(clawDownButton))
			{
				clawMotorSpeed = -.5;
			}
			else
			{
				clawMotorSpeed = 0;
			}
			
			clawMotor.set(clawMotorSpeed);
			
			
			//solenoid controls
			
			if(controller.getRawButton(solenoidButton))
			{
				sol1.set(DoubleSolenoid.Value.kForward);
				
			}
			else
			{
				sol1.set(DoubleSolenoid.Value.kReverse);
			}
			
			/*if(controller.getRawButton(fastButton) == true && controller.getRawButton(slowButton) == true) // Make it so if you press a slow button and the fast button at the same time then its going to take the one you chose first and run that and the run the second one you chose 
			{
				fastTimer.stop();
				slowTimer.stop();
				if(fastTimer.get() < slowTimer.get())
				{
					leftMotorSpeed = -controller.getRawAxis(leftAxis);
					rightMotorSpeed = controller.getRawAxis(rightAxis);
					fastTimer.reset();
					slowTimer.reset();
				}
				if(slowTimer.get() < fastTimer.get())
				{
					leftMotorSpeed = -controller.getRawAxis(leftAxis) * slowPercent;
					rightMotorSpeed = controller.getRawAxis(rightAxis) * slowPercent;
					fastTimer.reset();
					slowTimer.reset();
				}
			}*/
		
		//**************************************************************************************
			// need a fast and slow button and conditions to turning
			//if(fastbutton = true && controller.getRawAxis(zAxis) = 0) the same goes for slow
			//leftMotorSpeed = -controller.getRawAxis(leftAxis) * defaultPercent;  // LOGITECH CONTROLLER
			//rightMotorSpeed = controller.getRawAxis(leftAxis) * defaultPercent;
			//if(controller.getRawAxis(zAxis) < 0)// if the joystick is getting turned to the left then the robot is getting turned to the left
			//{
			   //rightMotorSpeed = controller.getRawAxis(leftAxis); // right wheels are going at the value of the joystick's axis         
			   //leftMotorSpeed = -controller.getRawAxis(leftAxis) * defaultPercent; // left wheels are going at the half of the value of the joystick's axis
			//}                                                                  
			//if(controller.getRawAxis(zAxis) > 0)// if the joystick is getting turned to the right then the robot is getting turned to the right 								
			//{
			//	leftMotorSpeed = -controller.getRawAxis(leftAxis);
			//    rightMotorSpeed = controller.getRawAxis(leftAxis) * defaultPercent;
		//	}
		//**************************************************************************************
			
		///Driver Station Management	
		//Disable Driver Station With Button

	}
	
	
	//@Override
	public void testPeriodic()
	{
	}
}
