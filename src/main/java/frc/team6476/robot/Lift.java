package frc.team6476.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift {

    Spark lift;
    Encoder liftEncoder;

    public void init()
    {
        // Initialise drivetrain
        lift = new Spark(Constants.lift_PWM);

        liftEncoder = new Encoder(Constants.liftEncoderPortA, Constants.liftEncoderPortB, true);
    }

    private void setSpeed(double speed)
    {
        lift.set(speed);
    }
    public void raise(double speed)
    {
        setSpeed(speed);
    }
    public void lower(double speed)
    {
        setSpeed(speed);
    }
    public void stop()
    {
        setSpeed(Constants.liftStopSpeed);
    }
    public void raiseToLevel(double height)
    {
        /* Very basic code to go to a set height Ideally the speed would change depending on how big the error is
           so if you were on the floor trying to go to the scale, it would go really fast and gradually slow down
           as you reach the target */

        double error = height-liftEncoder.get();
        if (Math.abs(error) < 10)
        {
            stop();
        }
        else {
            if (error > 0)
            {
                lower(Constants.liftUpSpeed);
            } else
            {
                raise(Constants.liftDownSpeed);
            }
        }
    }

    public void publishStats()
    {
        SmartDashboard.putNumber("Lift motor speed", lift.get());
        SmartDashboard.putNumber("Lift encoder", liftEncoder.get());
    }
}
