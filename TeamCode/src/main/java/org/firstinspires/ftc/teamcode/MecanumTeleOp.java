package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "MecanumDrive", group = "Quackology")
public class MecanumTeleOp extends OpMode {


    private DcMotorEx frontLeft, frontRight, backLeft, backRight, carouselMotor;
    static final double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position

    // Define class members
    Servo servo;
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;


    @Override


    public void init() {
        telemetry.addData("Status", "Initializing...");


        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        servo = hardwareMap.get(Servo.class, "left_hand");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        boolean spin = gamepad1.y;


        double frontLeftPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
        double frontRightPower = Range.clip(drive - strafe - turn, -1.0, 1.0);
        double backLeftPower = Range.clip(drive + strafe - turn, -1.0, 1.0);
        double backRightPower = Range.clip(drive - strafe + turn, -1.0, 1.0);

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        while (spin == true) {
            carouselMotor.setPower(1.0);
        }

        // slew the servo, according to the rampUp (direction) variable.
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position += INCREMENT;
            if (position >= MAX_POS) {
                position = MAX_POS;
                rampUp = !rampUp;   // Switch ramp direction
            }
        } else {
            // Keep stepping down until we hit the min value.
            position -= INCREMENT;
            if (position <= MIN_POS) {
                position = MIN_POS;
                rampUp = !rampUp;  // Switch ramp direction
            }
        }

        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f)",
                frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("Servo Position", "%5.2f", position);
        telemetry.update();

        // Set the servo to the new position and pause;

        servo.setPosition(position);

    }

}

