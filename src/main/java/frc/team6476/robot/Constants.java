package frc.team6476.robot;

public class Constants {

    // Joystick Buttons
    public static final int buttonLiftUp = 1;
    public static final int buttonLiftDown = 2;
    public static final int buttonIntake = 3;
    public static final int buttonOuttake = 4;
    public static final int buttonGripperUp = 5;
    public static final int buttonGripperDown = 6;
    public static final int buttonLiftSwitch = 7;
    public static final int buttonLifLowScale = 8;
    public static final int buttonLifMedScale = 9;
    public static final int buttonLiftHighScale = 10;

    // DIO Ports
    public static final int leftEncoderPortA = 0;
    public static final int leftEncoderPortB = 1;
    public static final int rightEncoderPortA = 2;
    public static final int rightEncoderPortB = 3;
    public static final int liftEncoderPortA = 2;
    public static final int liftEncoderPortB = 3;

    // CAN Bus Node IDs
    public static final int leftA_CanID = 1;
    public static final int leftB_CanID = 2;
    public static final int rightA_CanID = 3;
    public static final int rightB_CanID = 4;
    public static final int liftA_CanID = 5;
    public static final int liftB_CanID = 6;
    public static final int intakeLeft_CanID = 7;
    public static final int intakeRight_CanID = 8;
    public static final int gripperRotate_CanID = 9;

    // Game variables
    public static final double autoSpeed = 0.6;
    public static final int autoDistance = 100;

    public static final double liftUpSpeed = 0.6;
    public static final double liftDownSpeed = -0.05;
    public static final double liftStopSpeed = 0.1; // If this was set to 0, I'm guessing it would go down
    public static final double liftSwitchHeight = 100;
    public static final double liftScaleLowHeight = 200;
    public static final double liftScaleMedHeight = 300;
    public static final double liftScaleHighHeight = 400;

    public static final double intakeSpeed = 0.5;
    public static final double outtakeSpeed = 0.5;
    public static final double gripperRotateSpeed = 0.5;

}
