package org.homestead.robotics.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Jaguar;
import org.homestead.robotics.robot.control.*;

public class Pickup{

        private Jaguar liftMotor;           // liftMotor refers to the motor that lifts the arm holding the wheels
        private Jaguar pickupMotor;         // pickupMotor refers to the motor controlling the pickup wheels
        private AnalogChannel down;
        private AnalogChannel up;
        private AnalogPotentiometer potentiometer;
        private boolean isMovingToLoad;
        
        public final static double MIN_VOLTAGE = 2.68;  //1.37 //shooting 3.12
        public final static double TOTAL_VOLTAGE = 4.38;       //3, 2.68
        public final static double SHOOTING_VOLTAGE = 3.12;    //1.71. Assumes lower = less, higher = more, joystick forward = up, downward = down
        public final static double INTAKE_VOLTAGE = 4.13;
        private static double PWM_RATIO = 0.25;

    public Pickup(int liftMotorChannel, int pickupMotorChannel, int microChannel1, int microChannel2, int potChannel){
        this.liftMotor = new Jaguar(liftMotorChannel);
        this.pickupMotor = new Jaguar(pickupMotorChannel);
        down = new AnalogChannel(microChannel1);
        up = new AnalogChannel(microChannel2);
        potentiometer = new AnalogPotentiometer(potChannel);
        isMovingToLoad = false;
    }

    public String act(ControlSignal command) {
        
        String returnMe = "";
        
        DriverStationLCD stationLCD = DriverStationLCD.getInstance();
        stationLCD.println(DriverStationLCD.Line.kUser5, 1, "                         ");
        stationLCD.println(DriverStationLCD.Line.kUser5, 1, potentiometer.get() + "");
        stationLCD.updateLCD();
        
        if(command.getJoyStick3_Button11() == true)
        {
            isMovingToLoad = true;
        }
        
        
        if((up.getVoltage() < 1 || potentiometer.get() >= TOTAL_VOLTAGE ) && potentiometer.get() >= TOTAL_VOLTAGE - 0.5 && -command.getJoyStick3_Y() > 0)
        {
            liftMotor.set(0);
            isMovingToLoad = false;
            returnMe = "Pickup is at max position: Cannot move that direction";
        }
        
        //else if((down.getVoltage() < 1 ||  potentiometer.get() <= MIN_VOLTAGE ) && potentiometer.get() <= MIN_VOLTAGE + 0.5 && -command.getJoyStick3_Y() < 0)
        else if((down.getVoltage() < 1 || potentiometer.get() <= MIN_VOLTAGE ) && potentiometer.get() <= MIN_VOLTAGE + 0.5 && -command.getJoyStick3_Y() < 0)
        {
            liftMotor.set(0);
            returnMe = "Pickup is at min position: Cannot move that direction";
        }
        
        
        else if(command.getJoyStick3_Button2())
        {
            returnMe = "Pickup moving to shooting position";
            double pos = potentiometer.get();
            isMovingToLoad = false;
            
            if(0.05 > Math.abs(pos-SHOOTING_VOLTAGE))
            {
                liftMotor.set(0);
                returnMe = "Pickup in shooting position ";
            }
            else if(pos < SHOOTING_VOLTAGE)
            {
                double difference = Math.abs(pos - SHOOTING_VOLTAGE);
                if(difference >= 1)
                    liftMotor.set(1);
                else if(difference <= 0.5)
                    liftMotor.set(0.5);
                else
                    liftMotor.set(difference);
            }
            else if(pos > SHOOTING_VOLTAGE)
            {
                double difference = Math.abs(pos - SHOOTING_VOLTAGE);
                if(difference >= 1)
                    liftMotor.set(-1);
                else if(difference <= 0.5)
                    liftMotor.set(-0.5);
                else
                    liftMotor.set(-difference);
            }
        }
        else if(isMovingToLoad)
        {
            liftMotor.set(1);
        }
        else
        {
            liftMotor.set(-command.getJoyStick3_Y());
            //pickupMotor.set((-command.getJoyStick3_Y()));
            returnMe = "Pickup moving at " + -command.getJoyStick3_Y();
        }
            
      
        
        if (command.getJoyStick3_Button5())
        {
            pickupMotor.set(0.5);
            returnMe += "      Pickup wheel is spinning";
        }
        else if (command.getJoyStick3_Button4())
        {
            pickupMotor.set(-0.5);
            returnMe += "      Pickup wheel is spinning";
        }
        
        
        if(potentiometer.get() > SHOOTING_VOLTAGE && command.getJoyStick3_Trigger()) //if taking in ball
        {
            pickupMotor.set(1);
            returnMe += "      Pickup wheel is spinning";  
        } 
        else if (command.getJoyStick3_Button5()) //if button 5 pressed rotate wheels outwards
        {
            pickupMotor.set(0.5);
            returnMe += "      Pickup wheel is spinning";
        }
        else if (command.getJoyStick3_Button4()) //if button 4 pressed rotate wheels inwards
        {
            pickupMotor.set(-0.5);
            returnMe += "      Pickup wheel is spinning";
        }
        else
        {
            if(Math.abs(liftMotor.get()) < 0.05 || (potentiometer.get() < 1.5 && liftMotor.get()<0))
            {
                pickupMotor.set(0);
            } else {
                pickupMotor.set(-PWM_RATIO * liftMotor.get()); 
            }
        }
        
        /*else if(potentiometer.get() > SHOOTING_VOLTAGE && command.getJoyStick3_Trigger()) //taking in ball
        {
            pickupMotor.set(1);
            returnMe += "      Pickup wheel is spinning";  
        }
        else
        {
            if(Math.abs(liftMotor.get()) < 0.05 || (potentiometer.get() < 1.5 && liftMotor.get()<0))
            {
                pickupMotor.set(0);
            }
            else if(liftMotor.get()>0)
            {
                pickupMotor.set(-0.25);
            }
            else
            {
                if(potentiometer.get() > TOTAL_VOLTAGE - 0.6)
                {
                    pickupMotor.set(0.5);
                }
                else
                {
                    pickupMotor.set(0.25);
                }
            }
        } */
        //returnMe = " & d " + down.getVoltage() + "u " + up.getVoltage();
        return returnMe;
    }
    
    private String getAnalogChannels ()
    {
        String s = "";
        for(int i = 1; i < 6; i++)
        {
            s += " " + (int)((new AnalogChannel(i)).getVoltage() * 10 );
        }
        return s;
    }
 
}
