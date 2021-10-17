package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystem.LiftC;


@TeleOp(name = "MecanumDrive", group = "Quackology")
public class MecanumTeleOp extends OpMode {


    private DcMotorEx frontLeft, frontRight, backLeft, backRight, carousel;
    private Servo clawServo, wristServo;
    static final double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position

    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    double clawPosition = 0.5;
    double wristPosition = 0.7;
    LiftC liftc;
    @Override


    public void init() {
        telemetry.addData("Status", "Initializing...");


        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        carousel = hardwareMap.get(DcMotorEx.class, "carousel");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        liftc.init();

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        //servo = hardwareMap.get(Servo.class, "carousel");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        boolean spin = gamepad1.y;


        double frontLeftPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
        double frontRightPower = Range.clip(drive - strafe - turn, -1.0, 1.0);
        double backLeftPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
        double backRightPower = Range.clip(drive + strafe - turn, -1.0, 1.0);

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        if(gamepad1.x){
            carousel.setPower(0.5);
        }
        if(gamepad1.a){
            carousel.setPower(0);
        }
        while (spin == true) {
            carousel.setPower(1.0);
        }

        if(gamepad2.a){
            clawPosition = 1.0;
        }

        if(gamepad2.y){
            clawPosition = 0.0;
        }
        if(gamepad2.b){
            wristPosition = 0.9;
        }
        if(gamepad2.x){
            wristPosition = 0.7;
        }
        if(gamepad1.dpad_up){
            liftc.moveByInch(2,0.5);
        }
        if(gamepad1.dpad_down){
            liftc.moveByInch(-2,0.5);
        }

        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f)",
                frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("Claw Position", "%5.2f", clawPosition);
        telemetry.addData("Wrist Position", "%5.2f", wristPosition);
        telemetry.update();

        clawServo.setPosition(clawPosition);
        wristServo.setPosition(wristPosition);

    }

}

