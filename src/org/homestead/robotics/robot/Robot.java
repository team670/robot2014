/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.homestead.robotics.robot;

import edu.wpi.first.wpilibj.*;
import org.homestead.robotics.robot.control.ControlSignal;
import org.homestead.robotics.robot.control.DriveStation;
import org.homestead.robotics.robot.subsystems.*;
import org.homestead.robotics.util.Targeter;
import org.homestead.robotics.util.TargeterTelop;
//import org.homestead.robotics.util.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends SimpleRobot
{
    private DriveStation driveStation;
    private Shooter shooter;
    private DriveTrain drive;
    private Pickup pickup;
    private Targeter targeter;
    private TargeterTelop targeterTelop;
    
    public Robot()     
    {
        driveStation = new DriveStation(3, 2, 1);
        shooter = new Shooter(9,5,2,3,4);
        drive = new DriveTrain(1,2,3,4);
        pickup = new Pickup(7,8,1, 5, 6);
        targeter = new Targeter();
        targeterTelop = new TargeterTelop();
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous()
    {   
        this.drive.isAuton=true;
        //double rangeActual = 178.5764892
        //move distance = 93 inches
        
         ControlSignal moveUpArm = new ControlSignal(0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, true, false, false, false, false, false, false, false, false, false);
        ControlSignal shoot = new ControlSignal(0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, true, false, false, false, false, false);
        ControlSignal hasShot = new ControlSignal(0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false);
        ControlSignal nothing = new ControlSignal(0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,0,false, false, false, false, false, false, false, false, false, false, false);
        ControlSignal moveUp = new ControlSignal(0,-0.5,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,-0.5,false, false, false, false, false, false, false, false, false, false, false,
                                                 0,-0.6,false, false, false, false, false, false, false, false, false, false, false);
        String da;
        String pa;
        String sa;
        long startTime = System.currentTimeMillis();
        long time = System.currentTimeMillis();
                System.out.println("attempting moveup");
        while(System.currentTimeMillis() - time < 1500 /** 1.5*/)
        {
            da = this.drive.act(moveUp);
            pa = this.pickup.act(moveUp);
            sa = this.shooter.act(moveUp);
            this.driveStation.display(da, pa, sa, "");
            edu.wpi.first.wpilibj.Timer.delay(0.01);
        }
        System.out.println("moveup completed");
        
        
        time = System.currentTimeMillis();
                System.out.println("moveup arm started");
        while(System.currentTimeMillis() - time < 900)
        {
            da = this.drive.act(moveUpArm);
            pa = this.pickup.act(moveUpArm);
            sa = this.shooter.act(moveUpArm);
            this.driveStation.display(da, pa, sa, "");
            edu.wpi.first.wpilibj.Timer.delay(0.01);
        }
        System.out.println("moveup arm completed");
        
        double distance = this.targeter.act(); 
        System.out.println("Distance is: "+distance);
        if(distance < 0) 
        { //hot target not detected
            System.out.println("distance not detected");
            time = System.currentTimeMillis();
        
            while(System.currentTimeMillis() - time < 2500 && Math.abs(distance+1) < 0.0001)
            {
                da = this.drive.act(nothing);
                pa = this.pickup.act(nothing);
                sa = this.shooter.act(nothing);
                this.driveStation.display(da, pa, sa, "");
               // distance = this.targeter.act();
                edu.wpi.first.wpilibj.Timer.delay(0.01); 
            } 
        }
        
        time = System.currentTimeMillis();
        System.out.println("shcot sequence running");
        while(System.currentTimeMillis() - time < 500)
        {
            da = this.drive.act(shoot);
            pa = this.pickup.act(shoot);
            sa = this.shooter.act(shoot);
            this.driveStation.display(da, pa, sa, "");
            edu.wpi.first.wpilibj.Timer.delay(0.01);
        }

        System.out.println("running postauton code");
        while(isAutonomous())
        {
            da = this.drive.act(hasShot);
            pa = this.pickup.act(hasShot);
            sa = this.shooter.act(hasShot);
            this.driveStation.display(da, pa, sa, "");
            edu.wpi.first.wpilibj.Timer.delay(0.01);
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        this.drive.isAuton=false;
        while(this.isOperatorControl())
        {
            ControlSignal command = this.driveStation.getControlState();
            String da = this.drive.act(command);
            String pa = this.pickup.act(command);
            String sa = this.shooter.act(command);
            
            String ta;
            double dist = this.targeterTelop.act();
            if(0.00000001 > Math.abs(dist + 1))
                ta = "Target not found";
            else
            {
               if(dist > 176 && dist < 181)
                    ta = "TARGET IN RANGE: SHOOT      " + dist;
                else
                    ta = "Target not in range         " + dist;
            }
            
            this.driveStation.display(da, pa, sa, ta);
            
            
            edu.wpi.first.wpilibj.Timer.delay(0.01);
        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test()
    {
       
        DriverStationLCD stationLCD = DriverStationLCD.getInstance();
        //AnalogPotentiometer potentiometer = new AnalogPotentiometer(6);
//        AnalogChannel loaded = new AnalogChannel(2);
//        AnalogChannel unwound = new AnalogChannel(3);
//        AnalogChannel triggered = new AnalogChannel(4);
//        Joystick stick1 = new Joystick(1);
//        Joystick stick2 = new Joystick(2);
//        Joystick stick3 = new Joystick(3);
//        Jaguar motor1 = new Jaguar(5);
//        Jaguar motor2 = new Jaguar(7);
//        Jaguar motor3 = new Jaguar(8);
//        Jaguar motor4 = new Jaguar(9);
        String line1;
        String line2;
        String line3;
        String line4;
        
        while(this.isTest())
        {
           double d = this.targeter.act();
            System.out.println(d);
            /*if(stick1.getTrigger())     //Shooter Trigger, shooter joystick
                motor1.set(-1);
            else
                motor1.set(0);
            
            motor2.set(-stick1.getY()); //Shooter joystick, arm motor
            motor3.set(-stick2.getY()); //Right joystick, spinning motor
            motor4.set(-stick3.getY()); //Left joystick, shooter wind arm
            
            if(loaded.getVoltage() > 4)
                line1 = "on";
            else
                line1 = "off";
            
            if(unwound.getVoltage() > 4)
                line2 = "on";
            else
                line2 = "off";
            
            if(triggered.getVoltage() > 4)
                line3 = "on";
            else
                line3 = "off"; */
            
            stationLCD.println(DriverStationLCD.Line.kUser1, 1, d + " -1 no tar");
            stationLCD.println(DriverStationLCD.Line.kUser2, 1, "                         ");
            stationLCD.println(DriverStationLCD.Line.kUser3, 1, "                         ");

//            stationLCD.println(DriverStationLCD.Line.kUser1, 1, loaded.getVoltage() + "");
//            stationLCD.println(DriverStationLCD.Line.kUser2, 1, unwound.getVoltage() + "");
//            stationLCD.println(DriverStationLCD.Line.kUser3, 1, triggered.getVoltage() + "");
            stationLCD.println(DriverStationLCD.Line.kUser1, 1,  "");
            stationLCD.println(DriverStationLCD.Line.kUser2, 1, "");
            stationLCD.println(DriverStationLCD.Line.kUser3, 1,  "");
            stationLCD.println(DriverStationLCD.Line.kUser4, 1, d + " -1 no tar");

            stationLCD.updateLCD();
        }

    }
}