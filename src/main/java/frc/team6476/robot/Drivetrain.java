package frc.team6476.robot;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {

    WPI_TalonSRX leftA, leftB, rightA, rightB;
    ADXRS450_Gyro gyro;

    // Drivetrain Encoders
    SensorCollection leftEncoder, rightEncoder;

    public void init()
    {
        // Initialise drivetrain
        leftA = new WPI_TalonSRX(Constants.leftA_CanID);
        leftB = new WPI_TalonSRX(Constants.leftB_CanID);
        rightA = new WPI_TalonSRX(Constants.rightA_CanID);
        rightB = new WPI_TalonSRX(Constants.rightB_CanID);

        // Set drivetrain motors to follow each other as are connected together in a Toughbox gearbox
        leftB.follow(leftA);
        rightB.follow(rightA);

        gyro = new ADXRS450_Gyro();
        gyro.reset();

        leftEncoder = new SensorCollection(leftA);
        rightEncoder = new SensorCollection(rightA);
    }

    public void drive(double left, double right)
    {
        // Make sure we never try to set the motor controllers a value outside of -1 to 1
        if (left > 1)
        {
            left = 1;
        }
        if (left < -1)
        {
            left = -1;
        }
        if (right > 1)
        {
            right = 1;
        }
        if (right < -1)
        {
            right = -1;
        }

        leftA.set(left);
        rightA.set(-right);
    }

    public void driveToDistance(int pulses, double speed)
    {
        // Assumes encoders have been reset before calling this
        // As we only use the encoders to drive straight, they should be the same
        int distanceTravelled = (leftEncoder.getQuadraturePosition()-rightEncoder.getQuadraturePosition())/2;

        //Right encoder will be flipped compared to left

        if (distanceTravelled > pulses)
        {
            drive(0,0);
        }
        else
        {
            drive(speed,speed);
        }
    }

    public void publishStats()
    {
        SmartDashboard.putNumber("Left A Motor Speed", leftA.get());
        SmartDashboard.putNumber("Left B Motor Speed", leftB.get());
        SmartDashboard.putNumber("Right A Motor Speed", rightA.get());
        SmartDashboard.putNumber("Right B Motor Speed", rightB.get());
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getQuadraturePosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getQuadraturePosition());
    }

}
