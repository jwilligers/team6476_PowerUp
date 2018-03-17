package frc.team6476.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw {
    Spark clawMotor;
    Encoder clawEncoder;
    public void init()
    {
        // Claw Motors
        clawMotor = new Spark(Constants.claw_PWM);
        clawMotor.set(0);
        clawEncoder = new Encoder(Constants.clawEncoderPortA, Constants.clawEncoderPortB);
        clawEncoder.reset();
    }

    private void setSpeed(double speed)
    {
        clawMotor.set(speed);
    }
    public void grab()
    {
        setSpeed(Constants.clawSpeed);
    }
    public void letGo()
    {
        setSpeed(-Constants.clawSpeed);
    }
    public void openUp()
    {
        setSpeed(-Constants.clawStopSpeed);
    }
    public void retain()
    {
        setSpeed(Constants.clawStopSpeed);
    }
    public void publishStats()
    {
        SmartDashboard.putNumber("Claw Motor Speed", clawMotor.get());
        SmartDashboard.putNumber("Claw encoder", clawEncoder.get());
    }
}
