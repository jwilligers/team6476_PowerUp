package frc.team6476.robot;

public class Constants {

    // Joystick Buttons
    public static final int buttonLiftUp = 1;
    public static final int buttonLiftDown = 2;
    public static final int buttonGrab = 3;
    public static final int buttonLetGo = 4;
    public static final int buttonClawRotateUp = 5;
    public static final int buttonClawRotateDown = 6;
    public static final int buttonLiftSwitch = 7;
    public static final int buttonLiftLowScale = 8;
    public static final int buttonLiftMedScale = 9;
    public static final int buttonLiftHighScale = 10;
    public static final int buttonLiftMaxHeight = 11;

    // Xbox Buttons

    // DIO Ports
    public static final int liftEncoderPortA = 0;
    public static final int liftEncoderPortB = 1;
    public static final int clawEncoderPortA = 2;
    public static final int clawEncoderPortB = 3;
    public static final int clawRotateEncoderPortA = 4;
    public static final int clawRotateEncoderPortB = 5;
    public static final int liftLimitSwitchLower = 6;

    // PWM Ports
    public static final int lift_PWM = 0;
    public static final int claw_PWM = 1;
    public static final int clawRotate_PWM = 2;

    // CAN Bus Node IDs
    public static final int leftA_CanID = 1;
    public static final int leftB_CanID = 2;
    public static final int rightA_CanID = 3;
    public static final int rightB_CanID = 4;

    // Game variables
    public static final double autoSpeed = 0.7;
    public static final int autoDistance = 7000;

    // Lift Variables
    public static final double liftUpSpeed = 0.85;
    public static final double liftDownSpeed = 0.85;
    public static final double liftStopSpeed = 0;
    public static final double liftSwitchHeight = 1200;
    public static final double liftScaleLowHeight = 2350;
    public static final double liftScaleMedHeight = 2900;
    public static final double liftScaleHighHeight = 3300;
    public static final double liftMaxHeight = 3600;

    // Claw Grabber variables
    public static final double clawSpeed = 0.75;
    public static final double clawStopSpeed = -0.45;
    public static final double clawOutPosition = 100; // Invert
    public static final double clawInPosition = -20;

    // Claw Rotate Variables
    public static final double clawRotateSpeed = 0.9;
    public static final double clawRotateSlowSpeed = 0.5;
    public static final double clawUpPosition = 100;
    public static final double clawDownPosition = -100;
}
