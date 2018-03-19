package frc.team6476.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends IterativeRobot {
    // Here we declare what our robot has.

    // Subsystems
    Drivetrain drivetrain;
    Lift lift;
    Claw claw;
    ClawRotate clawRotate;

    // Controllers
    Joystick joystick;
    XboxController xboxController;

    double leftSpeed = 0;
    double rightSpeed = 0;
    double speedMultiplier = 0;
    boolean clawGrabCube = true;
    String gameData;

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
        xboxController = new XboxController(1);

        // Add a camera
        CameraServer.getInstance().startAutomaticCapture();

        publishStats();
}

    public void disabledInit() { }

    public void autonomousInit() {
        // Reset encoders
        drivetrain.resetEncoders();
        clawGrabCube = true;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
    }

    public void teleopInit() {
        clawGrabCube = true;
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
        leftSpeed = joystick.getY() - joystick.getX();
        rightSpeed = joystick.getY() + joystick.getX();
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
        SmartDashboard.putNumber("Joystick X", joystick.getX());
        SmartDashboard.putNumber("Joystick Y", joystick.getY());
        speedMultiplier = 0.5*(1-joystick.getThrottle());
        drivetrain.drive(leftSpeed*speedMultiplier, rightSpeed*speedMultiplier);
        System.out.println(xboxController.getTriggerAxis(GenericHID.Hand.kLeft));
        // Lift subsystem
        if (xboxController.getAButton())
        {
            lift.raise(Constants.liftUpSpeed);
        }
        else if (xboxController.getY(GenericHID.Hand.kRight)>0)
        {
            lift.lower(xboxController.getY(GenericHID.Hand.kRight));
        }
        else if (xboxController.getY(GenericHID.Hand.kRight)<0)
        {
            lift.raise(-xboxController.getY(GenericHID.Hand.kRight));
        }
        else if (xboxController.getBButton())
        {
            lift.lower(Constants.liftDownSpeed);
        }
        else
        {
            lift.stop();
        }

        // Claw subsystem
        if (xboxController.getBumper(GenericHID.Hand.kLeft))
        {
            claw.letGo();
            clawGrabCube = false;
        }
        else if (xboxController.getBumper(GenericHID.Hand.kRight))
        {
            claw.grab();
            //clawGrabCube = true;
        }
        else if (xboxController.getTriggerAxis(GenericHID.Hand.kLeft) > 0.2)
        {
            claw.letGo();
            clawGrabCube = false;
        }
        else if (xboxController.getTriggerAxis(GenericHID.Hand.kRight) > 0.2)
        {
            claw.grab();
            //clawGrabCube = true;
        }
        else
        {
            if(clawGrabCube)
            {
                claw.grab();
            }
            else {
                claw.letGo();
            }
        }
        SmartDashboard.putNumber("Xbox POV", xboxController.getPOV());
        SmartDashboard.putBoolean("Grab cube", clawGrabCube);

        // ClawRotate subsystem
        if (xboxController.getPOV() == 0)
        {
            clawRotate.rotateUp();
        }
        else if (xboxController.getPOV() == 180)
        {
            clawRotate.rotateDown();
        }
        else
        {
            clawRotate.stop();
        }

        if (xboxController.getStartButton()) {
            lift.raise(0.5);
            clawRotate.rotateUp();
            claw.grab();
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
        SmartDashboard.putNumber("Xbox Right Stick YAxis", xboxController.getY(GenericHID.Hand.kRight));
        SmartDashboard.putNumber("Speed Multiplier", speedMultiplier);
    }
}