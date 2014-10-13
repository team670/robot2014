package org.homestead.robotics.robot.control;

public class ControlSignal
{
    
    private double JoyStick1_X;
    private double JoyStick1_Y;
    private boolean JoyStick1_Trigger;
    private boolean JoyStick1_Button2;
    private boolean JoyStick1_Button3;
    private boolean JoyStick1_Button4;
    private boolean JoyStick1_Button5;
    private boolean JoyStick1_Button6;
    private boolean JoyStick1_Button7;
    private boolean JoyStick1_Button8;
    private boolean JoyStick1_Button9;
    private boolean JoyStick1_Button10;
    private boolean JoyStick1_Button11;

    
    private double JoyStick2_X;
    private double JoyStick2_Y;
    private boolean JoyStick2_Trigger;
    private boolean JoyStick2_Button2;
    private boolean JoyStick2_Button3;
    private boolean JoyStick2_Button4;
    private boolean JoyStick2_Button5;
    private boolean JoyStick2_Button6;
    private boolean JoyStick2_Button7;
    private boolean JoyStick2_Button8;
    private boolean JoyStick2_Button9;
    private boolean JoyStick2_Button10;
    private boolean JoyStick2_Button11;
    
    
    private double JoyStick3_X;
    private double JoyStick3_Y;
    private boolean JoyStick3_Trigger;
    private boolean JoyStick3_Button2;
    private boolean JoyStick3_Button3;
    private boolean JoyStick3_Button4;
    private boolean JoyStick3_Button5;
    private boolean JoyStick3_Button6;
    private boolean JoyStick3_Button7;
    private boolean JoyStick3_Button8;
    private boolean JoyStick3_Button9;
    private boolean JoyStick3_Button10;
    private boolean JoyStick3_Button11;




    
    public ControlSignal(double JoyStick1_X, double JoyStick1_Y, boolean JoyStick1_Trigger, boolean JoyStick1_Button2, boolean JoyStick1_Button3, boolean JoyStick1_Button4, boolean JoyStick1_Button5, boolean JoyStick1_Button6, boolean JoyStick1_Button7, boolean JoyStick1_Button8, boolean JoyStick1_Button9, boolean JoyStick1_Button10, boolean JoyStick1_Button11,
                         double JoyStick2_X, double JoyStick2_Y, boolean JoyStick2_Trigger, boolean JoyStick2_Button2, boolean JoyStick2_Button3, boolean JoyStick2_Button4, boolean JoyStick2_Button5, boolean JoyStick2_Button6, boolean JoyStick2_Button7, boolean JoyStick2_Button8, boolean JoyStick2_Button9, boolean JoyStick2_Button10, boolean JoyStick2_Button11,
                         double JoyStick3_X, double JoyStick3_Y, boolean JoyStick3_Trigger, boolean JoyStick3_Button2, boolean JoyStick3_Button3, boolean JoyStick3_Button4, boolean JoyStick3_Button5, boolean JoyStick3_Button6, boolean JoyStick3_Button7, boolean JoyStick3_Button8, boolean JoyStick3_Button9, boolean JoyStick3_Button10, boolean JoyStick3_Button11)
    {
        this.JoyStick1_X = JoyStick1_X;
        this.JoyStick1_Y = JoyStick1_Y;
        this.JoyStick1_Trigger = JoyStick1_Trigger;
        this.JoyStick1_Button2 = JoyStick1_Button2;
        this.JoyStick1_Button3 = JoyStick1_Button3;
        this.JoyStick1_Button4 = JoyStick1_Button4;
        this.JoyStick1_Button5 = JoyStick1_Button5;
        this.JoyStick1_Button6 = JoyStick1_Button6;
        this.JoyStick1_Button7 = JoyStick1_Button7;
        this.JoyStick1_Button8 = JoyStick1_Button8;
        this.JoyStick1_Button9 = JoyStick1_Button9;
        this.JoyStick1_Button10 = JoyStick1_Button10;
        this.JoyStick1_Button11 = JoyStick1_Button11;

        this.JoyStick2_X = JoyStick2_X;
        this.JoyStick2_Y = JoyStick2_Y;
        this.JoyStick2_Trigger = JoyStick2_Trigger;
        this.JoyStick2_Button2 = JoyStick2_Button2;
        this.JoyStick2_Button3 = JoyStick2_Button3;
        this.JoyStick2_Button4 = JoyStick2_Button4;
        this.JoyStick2_Button5 = JoyStick2_Button5;
        this.JoyStick2_Button6 = JoyStick2_Button6;
        this.JoyStick2_Button7 = JoyStick2_Button7;
        this.JoyStick2_Button8 = JoyStick2_Button8;
        this.JoyStick2_Button9 = JoyStick2_Button9;
        this.JoyStick2_Button10 = JoyStick2_Button10;
        this.JoyStick2_Button11 = JoyStick2_Button11;
        
        this.JoyStick3_X = JoyStick3_X;
        this.JoyStick3_Y = JoyStick3_Y;
        this.JoyStick3_Trigger = JoyStick3_Trigger;
        this.JoyStick3_Button2 = JoyStick3_Button2;
        this.JoyStick3_Button3 = JoyStick3_Button3;
        this.JoyStick3_Button4 = JoyStick3_Button4;
        this.JoyStick3_Button5 = JoyStick3_Button5;
        this.JoyStick3_Button6 = JoyStick3_Button6;
        this.JoyStick3_Button7 = JoyStick3_Button7;
        this.JoyStick3_Button8 = JoyStick3_Button8;
        this.JoyStick3_Button9 = JoyStick3_Button9;
        this.JoyStick3_Button10 = JoyStick3_Button10;
        this.JoyStick3_Button11 = JoyStick3_Button11;


    }

    
    public double getJoyStick1_X(){
        return this.JoyStick1_X;
    }
    public double getJoyStick1_Y(){
        return this.JoyStick1_Y;
    }
    public boolean getJoyStick1_Trigger(){
        return this.JoyStick1_Trigger;
    }
    public boolean getJoyStick1_Button2(){
        return this.JoyStick1_Button2;
    }
    public boolean getJoyStick1_Button3(){
        return this.JoyStick1_Button3;
    }
    public boolean getJoyStick1_Button4(){
        return this.JoyStick1_Button4;
    }
    public boolean getJoyStick1_Button5(){
        return this.JoyStick1_Button5;
    }
    public boolean getJoyStick1_Button6(){
        return this.JoyStick1_Button6;
    }
    public boolean getJoyStick1_Button7(){
        return this.JoyStick1_Button7;
    }
    public boolean getJoyStick1_Button8(){
        return this.JoyStick1_Button8;
    }
    public boolean getJoyStick1_Button9(){
        return this.JoyStick1_Button9;
    }
    public boolean getJoyStick1_Button10(){
        return this.JoyStick1_Button10;
    }
    public boolean getJoyStick1_Button11(){
        return this.JoyStick1_Button11;
    }


    public double getJoyStick2_X(){
        return this.JoyStick2_X;
    }
    public double getJoyStick2_Y(){
        return this.JoyStick2_Y;
    }
    public boolean getJoyStick2_Trigger(){
        return this.JoyStick2_Trigger;
    }
    public boolean getJoyStick2_Button2(){
        return this.JoyStick2_Button2;
    }
    public boolean getJoyStick2_Button3(){
        return this.JoyStick2_Button3;
    }
    public boolean getJoyStick2_Button4(){
        return this.JoyStick2_Button4;
    }
    public boolean getJoyStick2_Button5(){
        return this.JoyStick2_Button5;
    }
    public boolean getJoyStick2_Button6(){
        return this.JoyStick2_Button6;
    }
    public boolean getJoyStick2_Button7(){
        return this.JoyStick2_Button7;
    }
    public boolean getJoyStick2_Button8(){
        return this.JoyStick2_Button8;
    }
    public boolean getJoyStick2_Button9(){
        return this.JoyStick2_Button9;
    }
    public boolean getJoyStick2_Button10(){
        return this.JoyStick2_Button10;
    }
    public boolean getJoyStick2_Button11(){
        return this.JoyStick2_Button11;
    }

    public double getJoyStick3_X(){
        return this.JoyStick3_X;
    }
    public double getJoyStick3_Y(){
        return this.JoyStick3_Y;
    }
    public boolean getJoyStick3_Trigger(){
        return this.JoyStick3_Trigger;
    }
    public boolean getJoyStick3_Button2(){
        return this.JoyStick3_Button2;
    }
    public boolean getJoyStick3_Button3(){
        return this.JoyStick3_Button3;
    }
    public boolean getJoyStick3_Button4(){
        return this.JoyStick3_Button4;
    }
    public boolean getJoyStick3_Button5(){
        return this.JoyStick3_Button5;
    }
    public boolean getJoyStick3_Button6(){
        return this.JoyStick3_Button6;
    }
    public boolean getJoyStick3_Button7(){
        return this.JoyStick3_Button7;
    }
    public boolean getJoyStick3_Button8(){
        return this.JoyStick3_Button8;
    }
    public boolean getJoyStick3_Button9(){
        return this.JoyStick3_Button9;
    }
    public boolean getJoyStick3_Button10(){
        return this.JoyStick3_Button10;
    }
    public boolean getJoyStick3_Button11(){
        return this.JoyStick3_Button11;
    }
}