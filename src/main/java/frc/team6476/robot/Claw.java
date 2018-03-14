package frc.team6476.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw {
    Spark intake;
    Encoder clawEncoder;
    public void init()
    {
        // Claw Motors
        intake = new Spark(Constants.claw_PWM);
        intake.set(0);
        clawEncoder = new Encoder(Constants.clawEncoderPortA, Constants.clawEncoderPortB);
    }

    private void setSpeed(double speed)
    {
        intake.set(speed);
    }
    public void grab()
    {
        setSpeed(Constants.intakeSpeed);
    }
    public void letGo()
    {
        setSpeed(Constants.outtakeSpeed);
    }
    public void stop()
    {
        setSpeed(0);
    }
    public void publishStats()
    {
        SmartDashboard.putNumber("Claw Motor Speed", intake.get());
    }
}




