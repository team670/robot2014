package org.homestead.robotics.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import org.homestead.robotics.robot.control.ControlSignal;

public class Shooter
{
    private Jaguar motorWind;
    private Jaguar motorRelease;
    private AnalogChannel loaded;
    private AnalogChannel unwound;
    private AnalogChannel triggered;
    private boolean isLoaded;
    private boolean isUnwound;
    private boolean isTriggered;
    private String returnMe1;
    private String returnMe2;


    public Shooter(int motorChannel1, int motorChannel2, int microChannel1, int microChannel2, int microChannel3)
    {
        motorWind = new Jaguar(motorChannel1);
        motorRelease = new Jaguar(motorChannel2);
        loaded = new AnalogChannel(microChannel1);
        unwound = new AnalogChannel(microChannel2);
        triggered = new AnalogChannel(microChannel3);
        
        isLoaded = loaded.getVoltage() < 1;
        isUnwound = unwound.getVoltage() < 1;
        isTriggered = triggered.getVoltage() < 1;
        
        returnMe1 = "";
        returnMe2 = "";
    }

    public String act(ControlSignal command)
    {   
        isLoaded = loaded.getVoltage() < 1;
        isUnwound = unwound.getVoltage() < 1;
        isTriggered = triggered.getVoltage() < 1;
        
        if(command.getJoyStick3_Button10())
        {
            motorWind.set(0);
        }
        else if(command.getJoyStick3_Button3() == true && Math.abs(motorWind.get()) < 0.01 && (isLoaded == false || isUnwound == false))     //To load
        {
           motorWind.set(0.75);
           returnMe1 = "Loading";
        }        
        else if(isLoaded && Math.abs(motorWind.get() - 0.75) < 0.01)
        {
            motorWind.set(-0.5);
            returnMe1 = "Unwinding";
        }
        else if(isUnwound && Math.abs(motorWind.get() + 0.5) < 0.01)     
        {
            motorWind.set(0);
            returnMe1 = "LOADED";
        }
        
        
        if(command.getJoyStick3_Button7() && isUnwound)      //To realease.
        {
            motorRelease.set(-1); 
            returnMe2 = "FIRE";
            returnMe1 = "Unloaded";
        }
        else if(command.getJoyStick3_Button6())
        {
            motorRelease.set(-1);
            returnMe2 = "Trigger Released";
        }
        else if(isTriggered && command.getJoyStick3_Button7() == false)
        {
            edu.wpi.first.wpilibj.Timer.delay(0.05);
            motorRelease.set(0);
            returnMe2 = "Triggered";
        }
        
        return returnMe1 + " --- " + returnMe2;

    }
}