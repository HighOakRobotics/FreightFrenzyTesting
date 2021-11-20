package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShoulderM {
    public DcMotorEx shoulderMotor; //for better PID of run to position

    protected HardwareMap hwMap;
    //protected TeleBug teleBug;

    private static final double TICKS_PER_MOTOR_REV     = 30; //1120; //Andymark motor on rev 20 gear box
    private static final double WHEEL_DIAMETER_INCHES   = 1.0;     // For figuring circumference
    public static final double TICKS_PER_INCH = TICKS_PER_MOTOR_REV / (WHEEL_DIAMETER_INCHES * Math.PI);

    private static final int POSITIONING_TOLERANCE = 30; //The amount of ticks the DriveByInch method should be allowed to deviate by
    public static int BOTTOM = 0; //position at the bottom
    private static final int TOP = 6000; //position at the top
    public int targetPos;

    public ShoulderM(HardwareMap hwMap){
        this.hwMap = hwMap;
    }

    public void init() {
        shoulderMotor = hwMap.get(DcMotorEx.class, "shoulder");
        shoulderMotor.setTargetPositionTolerance(POSITIONING_TOLERANCE);
        shoulderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveByInch(double inches, double power){
        targetPos = (int)(inches * TICKS_PER_INCH);

//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shoulderMotor.setPower(power);

        shoulderMotor.setTargetPosition(shoulderMotor.getCurrentPosition() + targetPos);

        shoulderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (shoulderMotor.isBusy() ){}
        shoulderMotor.setPower(0);
    }

    public void moveByInchTele(double inches, double power){
         targetPos = (int)(inches * TICKS_PER_INCH);

//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shoulderMotor.setPower(power);

        shoulderMotor.setTargetPosition(shoulderMotor.getCurrentPosition() + targetPos);

        shoulderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void moveByInchTeleBeyond(double inches, double power){
        int targetPos = (int)(inches * TICKS_PER_INCH);

        shoulderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shoulderMotor.setPower(power);

        //prevent going past 0?
        shoulderMotor.setTargetPosition(shoulderMotor.getCurrentPosition() + targetPos);

        shoulderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stop() {shoulderMotor.setPower(0.0);}

    public int positionTicks() {return shoulderMotor.getCurrentPosition(); }

    public void firstPos() {
        moveByInch(7, 1.);
    }
    public void bottom() { //move to the bottom position
        shoulderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shoulderMotor.setPower(-1.0);

        shoulderMotor.setTargetPosition(BOTTOM-1);
        shoulderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void release() { //move to the bottome position
        moveByInch(-9, 1.);
    }

    public void pull() {
        moveByInch(9, 1.);
    }
}
