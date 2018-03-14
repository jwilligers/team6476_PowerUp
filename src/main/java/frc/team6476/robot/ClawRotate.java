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
        if (clawRotateEncoder.get()>Constants.clawUpPosition)
        {
            setSpeed(Constants.clawRotateSpeed);
        }
        else
        {
            setSpeed(0);
        }
    }
    public void rotateDown()
    {
        if (clawRotateEncoder.get()<Constants.clawDownPosition)
        {
            setSpeed(-Constants.clawRotateSpeed);
        }
        else
        {
            setSpeed(0);
        }
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




