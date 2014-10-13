package org.homestead.robotics.robot.control;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

public class DriveStation
{
    private Joystick leftJoystick;
    private Joystick rightJoystick;         // Moving Joystick
    private Joystick shooterJoystick;        // Turning Joystick
    
    public DriveStation(int leftJoystickNum, int rightJoystickNum, int shooterDriveJoystickNum)
    {
        this.leftJoystick = new Joystick(leftJoystickNum);
        this.rightJoystick = new Joystick(rightJoystickNum);
        this.shooterJoystick = new Joystick(shooterDriveJoystickNum);
    }
    
    public ControlSignal getControlState()
    {
        return new ControlSignal(
                this.leftJoystick.getX(),
                this.leftJoystick.getY(),
                this.leftJoystick.getTrigger(),
                this.leftJoystick.getRawButton(2),
                this.leftJoystick.getRawButton(3),
                this.leftJoystick.getRawButton(4),
                this.leftJoystick.getRawButton(5),
                this.leftJoystick.getRawButton(6),
                this.leftJoystick.getRawButton(7),
                this.leftJoystick.getRawButton(8),
                this.leftJoystick.getRawButton(9),
                this.leftJoystick.getRawButton(10),
                this.leftJoystick.getRawButton(11),

                this.rightJoystick.getX(),
                this.rightJoystick.getY(),
                this.rightJoystick.getTrigger(),
                this.rightJoystick.getRawButton(2),
                this.rightJoystick.getRawButton(3),
                this.rightJoystick.getRawButton(4),
                this.rightJoystick.getRawButton(5),
                this.rightJoystick.getRawButton(6),
                this.rightJoystick.getRawButton(7),
                this.rightJoystick.getRawButton(8),
                this.rightJoystick.getRawButton(9),
                this.rightJoystick.getRawButton(10),
                this.rightJoystick.getRawButton(11),
                
                this.shooterJoystick.getX(),
                this.shooterJoystick.getY(),
                this.shooterJoystick.getTrigger(),
                this.shooterJoystick.getRawButton(2),
                this.shooterJoystick.getRawButton(3),
                this.shooterJoystick.getRawButton(4),
                this.shooterJoystick.getRawButton(5),
                this.shooterJoystick.getRawButton(6),
                this.shooterJoystick.getRawButton(7),
                this.shooterJoystick.getRawButton(8),
                this.shooterJoystick.getRawButton(9),
                this.shooterJoystick.getRawButton(10),
                this.shooterJoystick.getRawButton(11)

                );
    }
    
    public void display(String line1, String line2, String line3, String line4)
    {
        DriverStationLCD stationLCD = DriverStationLCD.getInstance();

        stationLCD.println(DriverStationLCD.Line.kUser1, 1, "                         ");
        stationLCD.println(DriverStationLCD.Line.kUser2, 1, "                         ");
        stationLCD.println(DriverStationLCD.Line.kUser3, 1, "                         ");
        stationLCD.println(DriverStationLCD.Line.kUser4, 1, "                         ");

        stationLCD.println(DriverStationLCD.Line.kUser1, 1, line1);
        stationLCD.println(DriverStationLCD.Line.kUser2, 1, line2);
        stationLCD.println(DriverStationLCD.Line.kUser3, 1, line3);
        stationLCD.println(DriverStationLCD.Line.kUser4, 1, line4);

        stationLCD.updateLCD();
    }
}
