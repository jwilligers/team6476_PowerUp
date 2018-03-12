package frc.team6476.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {
    // Here we declare what our robot has.

    // Subsystems
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    Gripper gripper;

    // Joystick
    Joystick joystick;

    double leftSpeed = 0;
    double rightSpeed = 0;

    @Override
    public void robotInit() {
        // Here we initialise everything our robot has and give port numbers and set values

        // Drivetrain
        drivetrain = new Drivetrain();
        drivetrain.init();

        // Lift
        lift = new Lift();
        lift.init();

        //Intake
        intake = new Intake();
        intake.init();

        // Gripper
        gripper = new Gripper();
        gripper.init();

        joystick = new Joystick(0);

        // Add a camera
        CameraServer.getInstance().startAutomaticCapture();

        publishStats();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() {
        // Reset encoders
        drivetrain.resetEncoders();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() {
    }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() {
        drivetrain.driveToDistance(Constants.autoDistance, Constants.autoSpeed);

        publishStats();
    }

    @Override
    public void teleopPeriodic() {

        // Drivetrain
        leftSpeed = joystick.getY() + joystick.getX();
        rightSpeed = joystick.getY() - joystick.getX();
        drivetrain.drive(leftSpeed, rightSpeed);

        // Lift subsystem
        if (joystick.getRawButton(Constants.buttonLiftUp))
        {
            lift.raise(Constants.liftUpSpeed);
        }
        else if (joystick.getRawButton(Constants.buttonLiftDown))
        {
            lift.lower(Constants.liftDownSpeed);
        }
        else if (joystick.getRawButton(Constants.buttonLiftSwitch))
        {
            // Right now you would have to hold this button down until it reaches the switch
            lift.raiseToSwitch();
        }
        else
        {
            lift.stop();
        }

        // Intake subsystem
        if (joystick.getRawButton(Constants.buttonIntake))
        {
            intake.intake();
        }
        else if (joystick.getRawButton(Constants.buttonOuttake))
        {
            intake.outtake();
        }
        else
        {
            intake.stop();
        }

        // Gripper subsystem
        if (joystick.getRawButton(Constants.buttonGripperUp))
        {
            gripper.rotateUp();
        }
        else if (joystick.getRawButton(Constants.buttonGripperDown))
        {
            gripper.rotateDown();
        }
        else
        {
            gripper.stop();
        }

        publishStats();
    }

    @Override
    public void testPeriodic() {
        publishStats();
    }

    public void publishStats()
    {
        drivetrain.publishStats();
        gripper.publishStats();
        intake.publishStats();
        lift.publishStats();
    }
}