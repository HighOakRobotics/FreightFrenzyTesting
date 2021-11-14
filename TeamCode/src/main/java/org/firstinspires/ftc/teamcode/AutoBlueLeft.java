package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoBlueRight", group="Quackology")
//@Disabled

public class AutoBlueLeft extends LinearOpMode {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight, carousel;
    private Servo clawServo, wristServo;
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    double clawposition = 0.5;
    double wristposition = 0.7;
    //    boolean rampUp = true;
    LiftM liftm;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // tetrix motor
    static final double     DRIVE_GEAR_REDUCTION    = 60 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public boolean motorsReachedTarget() {
        return Math.abs(frontLeft.getCurrentPosition() - frontLeft.getTargetPosition()) < 10 &&
                Math.abs(frontRight.getCurrentPosition() - frontRight.getTargetPosition()) < 10 &&
                Math.abs(backLeft.getCurrentPosition() - backLeft.getTargetPosition()) < 10 &&
                Math.abs(backRight.getCurrentPosition() - backRight.getTargetPosition()) < 10;
    }

    public void drive(double speed, double leftInches, double rightInches) {
        int newfrontLeftTarget;
        int newfrontRightTarget;
        int newbackLeftTarget;
        int newbackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newfrontLeftTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newfrontRightTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newbackLeftTarget = backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newbackRightTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(newfrontLeftTarget);
            frontRight.setTargetPosition(newfrontRightTarget);
            backLeft.setTargetPosition(newbackLeftTarget);
            backRight.setTargetPosition(newbackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontRight.setPower(Math.abs(speed));
            frontLeft.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            while (!motorsReachedTarget()) {
                telemetry.addData("motorC", "%d %d %d %d",
                        frontRight.getCurrentPosition(),
                        frontLeft.getCurrentPosition(),
                        backRight.getCurrentPosition(),
                        backLeft.getCurrentPosition());
                telemetry.addData("motorT", "%d %d %d %d",
                        frontRight.getTargetPosition(),
                        frontLeft.getTargetPosition(),
                        backRight.getTargetPosition(),
                        backLeft.getTargetPosition());
                telemetry.update();
            }
        }
    }
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        carousel = hardwareMap.get(DcMotorEx.class, "carousel");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
//        servo = hardwareMap.get(Servo.class, "left_hand");
//        clawServo = hardwareMap.get(Servo.class, "clawservo");
//        wristServo = hardwareMap.get(Servo.class, "wristservo");
//        liftm = new LiftM(hardwareMap);
//        liftm.init();

        waitForStart();

        //set the motor's target position to a number of ticks
//        frontRight.setTargetPosition(300);
//        frontLeft.setTargetPosition(300);
//        backRight.setTargetPosition(300);
//        backLeft.setTargetPosition(300);
//
//        //switch to run to position mode
//        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//
//
//        //start the motor moving by setting the max velocity to 200 ticks per second
//        frontLeft.setVelocity(200);
//        frontRight.setVelocity(200);
//        backRight.setVelocity(200);
//        backLeft.setVelocity(200);


        if(opModeIsActive()) {
            drive(0.5, 10, 0);
        }
    }
}