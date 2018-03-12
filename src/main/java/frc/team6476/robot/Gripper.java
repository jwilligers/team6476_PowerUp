package frc.team6476.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gripper {

    WPI_TalonSRX gripperRotate;

    public void init()
    {
        gripperRotate = new WPI_TalonSRX(Constants.gripperRotate_CanID);
    }

    private void setSpeed(double speed)
    {
        gripperRotate.set(speed);
    }
    public void rotateUp()
    {
        setSpeed(Constants.gripperRotateSpeed);
    }
    public void rotateDown()
    {
        setSpeed(-Constants.gripperRotateSpeed);
    }
    public void stop()
    {
        setSpeed(0);
    }
    public void publishStats()
    {
        SmartDashboard.putNumber("Gripper Motor Speed", gripperRotate.get());
    }
}




