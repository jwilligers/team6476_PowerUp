package frc.team6476.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawRotate {

    Spark clawRotate; //PG Motor
    Encoder clawRotateEncoder;

    public void init()
    {
        clawRotate = new Spark(Constants.clawRotate_PWM);
        clawRotateEncoder = new Encoder(Constants.clawRotateEncoderPortA, Constants.clawRotateEncoderPortB);
        clawRotateEncoder.reset();
    }
    private void setSpeed(double speed)
    {
        clawRotate.set(speed);
    }
    public void rotateUp()
    {
        setSpeed(Constants.clawRotateSpeed);
    }
    public void rotateUpSlowly()
    {
        setSpeed(Constants.clawRotateSlowSpeed);
    }
    public void rotateDown()
    {
        setSpeed(-Constants.clawRotateSpeed);
    }
    public void stop()
    {
        setSpeed(0);
    }
    public void publishStats()
    {
        SmartDashboard.putNumber("Claw Rotate Motor Speed", clawRotate.get());
        SmartDashboard.putNumber("Claw Rotate Encoder", clawRotateEncoder.get());
    }
}




