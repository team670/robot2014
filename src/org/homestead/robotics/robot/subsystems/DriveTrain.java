package org.homestead.robotics.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import org.homestead.robotics.robot.control.ControlSignal;

public class DriveTrain
{
    private Jaguar leftMotor1;
    private Jaguar leftMotor2;
    private Jaguar rightMotor1;
    private Jaguar rightMotor2;
    public boolean isFancy=false;
    public boolean isAuton=false;
    public static final double movementThreshold=0.28;
    public static final double stopMove=0.1;

    public DriveTrain(int leftMotorChannel1, int leftMotorChannel2, int rightMotorChannel1, int rightMotorChannel2)
    {
        this.leftMotor1 = new Jaguar(leftMotorChannel1);
        this.leftMotor2 = new Jaguar(leftMotorChannel2);
        this.rightMotor1 = new Jaguar(rightMotorChannel1);
        this.rightMotor2 = new Jaguar(rightMotorChannel2);
    }

    public String act(ControlSignal command)
    {
        String returnMe = "Drive: Left - " + -command.getJoyStick1_Y() + "   Right - " + -command.getJoyStick2_Y();
        //String returnMe="";
        if(isFancy)
        {
            returnMe = returnMe+" cubic";
        } else {
            returnMe = returnMe+" exponential";
        }
        boolean isPrecisionL = command.getJoyStick1_Trigger();
        boolean isPrecisionR = command.getJoyStick2_Trigger();
        boolean isSuperPrecisionL = command.getJoyStick1_Button3();
        boolean isSuperPrecisionR = command.getJoyStick2_Button3();
        
        if(command.getJoyStick1_Button4())
        {
            isFancy = !isFancy;
        }
        
        if(isSuperPrecisionL)
        {
            this.leftMotor1.set(0.5 * -inputAdjust(command.getJoyStick1_Y()));
            this.leftMotor2.set(0.5 * -inputAdjust(command.getJoyStick1_Y()));
            returnMe += "  /  Super Precision Left";
        }
        else if(isPrecisionL)
        {
            this.leftMotor1.set(0.75 * -inputAdjust(command.getJoyStick1_Y()));
            this.leftMotor2.set(0.75 * -inputAdjust(command.getJoyStick1_Y()));
            returnMe += "  /  Precision Left";
        }
        else
        {
            this.leftMotor1.set(-inputAdjust(command.getJoyStick1_Y()));
            this.leftMotor2.set(-inputAdjust(command.getJoyStick1_Y()));
        }
        
        
        
        if(isSuperPrecisionR)
        {
            this.rightMotor1.set(0.5 * inputAdjust(command.getJoyStick2_Y()));
            this.rightMotor2.set(0.5 * inputAdjust(command.getJoyStick2_Y()));
            returnMe += "  /  Super Precision Right";
        }
        else if (isPrecisionR)
        {
            this.rightMotor1.set(0.75 * inputAdjust(command.getJoyStick2_Y()));
            this.rightMotor2.set(0.75 * inputAdjust(command.getJoyStick2_Y()));
            returnMe += "  /  Precision Right";
        }
        else
        {
            this.rightMotor1.set(inputAdjust(command.getJoyStick2_Y()));
            this.rightMotor2.set(inputAdjust(command.getJoyStick2_Y()));
        }
        
        
        return returnMe;
    }
    
    public double inputAdjust(double orig){
        if(isAuton){
            return orig;
        }
        if(isFancy==true){
            isFancy=false;
            return cubicFunction(orig);
        }
        return squareFunction(orig);
    }
    
    public double squareFunction(double orig){
        if(Math.abs(orig)<this.stopMove)
            return 0;
        if(Math.abs(orig)<movementThreshold){
            if(orig>0){
                return movementThreshold*this.movementThreshold;
            }
            else{
                return -movementThreshold*this.movementThreshold;
            }
        }
        if(orig<0){
            return -(orig*orig);
        }
        return orig*orig;
    }
    
    public double cubicFunction(double orig){
        return orig*orig*orig;
    }
    
}