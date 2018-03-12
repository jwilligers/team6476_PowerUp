package frc.team6476.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
    WPI_TalonSRX intakeLeft, intakeRight;

    public void init()
    {
        // Intake Motors
        intakeLeft = new WPI_TalonSRX(Constants.intakeLeft_CanID);
        intakeRight = new WPI_TalonSRX(Constants.intakeRight_CanID);

        intakeRight.follow(intakeLeft);
    }

    private void setSpeed(double speed)
    {
        intakeLeft.set(speed);
    }
    public void intake()
    {
        setSpeed(Constants.intakeSpeed);
    }
    public void outtake()
    {
        setSpeed(Constants.outtakeSpeed);
    }
    public void stop()
    {
        setSpeed(0);
    }
    public void publishStats()
    {
        SmartDashboard.putNumber("Intake Motor Speed", intakeLeft.get());
    }
}




