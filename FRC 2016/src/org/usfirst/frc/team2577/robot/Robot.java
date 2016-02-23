/*
 * Jack Proudfoot
 * Febuary 20, 2016
 */

package org.usfirst.frc.team2577.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    RobotDrive drive;
    Talon linearActuator; 
    Joystick drivecontroller;
	
   public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        this.drive = new RobotDrive(0,1,2,3);
        this.linearActuator = new Talon(4);
        
        linearActuator.setSafetyEnabled(true);
        drive.setSafetyEnabled(true);
        
        this.drivecontroller = new Joystick(0);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

   public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

   public void teleopPeriodic() {
	   drive.tankDrive(drivecontroller.getRawAxis(1), -drivecontroller.getRawAxis(5));
	   
	   if (drivecontroller.getRawAxis(2) > .5) {
		  linearActuator.set(.8);
	   }
	   else if (drivecontroller.getRawAxis(3) > .5) {
		   linearActuator.set(-.8);
	   }
	   else{
		   linearActuator.set(0);
	   }
       Timer.delay(0.005);
    }
    
    public void testPeriodic() {
    
    }
    
}
