package frc.team6476.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
    // Here we declare what our robot has.
    // Motors (are connected to Talon SRX motor controller)
    WPI_TalonSRX leftA, leftB, rightA, rightB, liftA, liftB, intakeLeft, intakeRight, gripperRotate;

    // Joystick
    Joystick joystick;

    // Drivetrain Encoders
    Encoder leftEncoder, rightEncoder;

    DifferentialDrive differentialDrive;
    double leftSpeed = 0;
    double rightSpeed = 0;

    @Override
    public void robotInit() {
        // Here we initialise everything our robot has and give port numbers and set values

        // Drivetrain motors
        leftA = new WPI_TalonSRX(Constants.leftA_CanID);
        leftB = new WPI_TalonSRX(Constants.leftB_CanID);
        rightA = new WPI_TalonSRX(Constants.rightA_CanID);
        rightB = new WPI_TalonSRX(Constants.rightB_CanID);

        // Lift motors
        liftA = new WPI_TalonSRX(Constants.liftA_CanID);
        liftB = new WPI_TalonSRX(Constants.liftB_CanID);

        // Intake Motors
        intakeLeft = new WPI_TalonSRX(Constants.intakeLeft_CanID);
        intakeRight = new WPI_TalonSRX(Constants.intakeRight_CanID);
        gripperRotate = new WPI_TalonSRX(Constants.gripperRotate_CanID);

        // Set drivetrain motors to follow each other as are connected together in a Toughbox gearbox
        leftB.follow(leftA);
        rightB.follow(rightA);

        intakeRight.follow(intakeLeft);

        joystick = new Joystick(0);

        // Using 2 channel encoders with channel A and B
        leftEncoder = new Encoder(Constants.leftEncoderPortA, Constants.leftEncoderPortB);
        rightEncoder = new Encoder(Constants.rightEncoderPortA, Constants.rightEncoderPortB);

        differentialDrive = new DifferentialDrive(leftA, rightA);
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() {
        // Reset encoders
        leftEncoder.reset();
        rightEncoder.reset();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() {
        leftEncoder.reset();
        rightEncoder.reset();
    }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() {
        if (0.5*(leftEncoder.get()+rightEncoder.get()) < Constants.autoDistance) {
            differentialDrive.arcadeDrive(Constants.autoSpeed, 0);
        }
        else
        {
            differentialDrive.arcadeDrive(0,0);
        }
    }

    @Override
    public void teleopPeriodic() {

        // Drivetrain
        leftSpeed = joystick.getX() - joystick.getY();
        rightSpeed = joystick.getX() + joystick.getY();

        // Squared inputs give better range of motion.
        differentialDrive.tankDrive(leftSpeed, rightSpeed, true);

        // Lift subsystem
        if (joystick.getRawButton(Constants.buttonLiftUp))
        {
            liftA.set(Constants.liftUpSpeed);
        }
        else if (joystick.getRawButton(Constants.buttonLiftDown))
        {
            liftA.set(-Constants.liftDownSpeed);
        }
        else
        {
            liftA.set(0);
        }

        // Intake subsystem
        if (joystick.getRawButton(Constants.buttonIntake))
        {
            intakeLeft.set(Constants.intakeSpeed);
        }
        else if (joystick.getRawButton(Constants.buttonOuttake))
        {
            intakeLeft.set(-Constants.outtakeSpeed);
        }
        else
        {
            intakeLeft.set(0);
        }

        // Intake subsystem
        if (joystick.getRawButton(Constants.buttonGripperUp))
        {
            gripperRotate.set(Constants.gripperRotateSpeed);
        }
        else if (joystick.getRawButton(Constants.buttonGripperDown))
        {
            gripperRotate.set(-Constants.gripperRotateSpeed);
        }
        else
        {
            gripperRotate.set(0);
        }

    }

    @Override
    public void testPeriodic() {
    }
}