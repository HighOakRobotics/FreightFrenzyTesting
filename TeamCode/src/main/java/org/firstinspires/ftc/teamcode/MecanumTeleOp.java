package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class MecanumTeleOp extends OpMode {

    static final double INCREMENT   = 0.01;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;

    double  position = (MAX_POS - MIN_POS) / 2;
    boolean rampUp = true;

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    DcMotorEx carouselMotor;

    Servo servo;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        carouselMotor = hardwareMap.get(DcMotorEx.class, "carouselMotor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double drive = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        boolean spin = gamepad1.y;

        double flPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
        double frPower = Range.clip(drive - strafe - turn, -1.0, 1.0);
        double blPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
        double brPower = Range.clip(drive + strafe - turn, -1.0, 1.0);

        while (spin = true) {
            carouselMotor.setPower(1.0);
        }

        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(blPower);
        backRight.setPower(brPower);

        if (rampUp) {
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
                rampUp = !rampUp;
            }
        }
        else {
            position -= INCREMENT;
            if (position <= MIN_POS) {
                position = MIN_POS;
                rampUp = !rampUp;
            }
        }

        servo.setPosition(position);
        sleep(CYCLE_MS);
        idle();
    }
    public final void idle() {
        Thread.yield();
    }
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}