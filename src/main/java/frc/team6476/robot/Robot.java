package frc.team6476.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    // Here we declare what our robot has.

    // Subsystems
    Drivetrain drivetrain;
    Lift lift;
    Claw claw;
    ClawRotate clawRotate;

    // Joystick
    Joystick joystick;

    double leftSpeed = 0;
    double rightSpeed = 0;

    public void robotInit() {
        // Here we initialise everything our robot has and give port numbers and set values

        // Drivetrain
        drivetrain = new Drivetrain();
        drivetrain.init();

        // Lift
        lift = new Lift();
        lift.init();

        //Claw
        claw = new Claw();
        claw.init();

        // ClawRotate
        clawRotate = new ClawRotate();
        clawRotate.init();

        joystick = new Joystick(0);

        // Add a camera
        CameraServer.getInstance().startAutomaticCapture();

        publishStats();
    }

    public void disabledInit() { }

    public void autonomousInit() {
        // Reset encoders
        drivetrain.resetEncoders();
    }

    public void teleopInit() {
    }

    public void testInit() {
    }


    public void disabledPeriodic() { }

    public void autonomousPeriodic() {
        drivetrain.driveToDistance(Constants.autoDistance, Constants.autoSpeed);

        publishStats();
    }

    public void teleopPeriodic() {

        // Drivetrain
        leftSpeed = joystick.getY() + joystick.getX();
        rightSpeed = joystick.getY() - joystick.getX();
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
        SmartDashboard.putNumber("Joystick X", joystick.getX());
        SmartDashboard.putNumber("Joystick Y", joystick.getY());

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

        // Claw subsystem
        if (joystick.getRawButton(Constants.buttonGrab))
        {
            claw.grab();
        }
        else if (joystick.getRawButton(Constants.buttonLetGo))
        {
            claw.letGo();
        }
        else
        {
            claw.stop();
        }

        // ClawRotate subsystem
        if (joystick.getRawButton(Constants.buttonGripperUp))
        {
            clawRotate.rotateUp();
        }
        else if (joystick.getRawButton(Constants.buttonGripperDown))
        {
            clawRotate.rotateDown();
        }
        else
        {
            clawRotate.stop();
        }

        publishStats();
    }
    public void testPeriodic() {
        publishStats();
    }

    public void publishStats()
    {
        drivetrain.publishStats();
        clawRotate.publishStats();
        claw.publishStats();
        lift.publishStats();
    }
}