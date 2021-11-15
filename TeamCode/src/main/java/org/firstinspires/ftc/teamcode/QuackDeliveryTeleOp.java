package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "QuackDeliveryTeleOp", group = "Quackology")
public class QuackDeliveryTeleOp extends OpMode {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight, carousel, intake;
    private Servo wristServo;

//    static final double INCREMENT   = 0.01;
//    static final int    CYCLE_MS    =   50;
//    static final double MAX_POS     =  1.0;
//    static final double MIN_POS     =  0.0;

//    double  position = (MAX_POS - MIN_POS) / 2;

    double wristposition = 0.7;
    boolean rampUp = true;
    ShoulderM shoulderm;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        carousel = hardwareMap.get(DcMotorEx.class, "carousel");
        intake = hardwareMap.get(DcMotorEx.class,"intake");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        wristServo = hardwareMap.get(Servo.class,"wrist");

        shoulderm = new ShoulderM(hardwareMap);
        shoulderm.init();


        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {
        double drive;
        double strafe;
        double turn;
        drive = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;


        double frontLeftPower = Range.clip(drive + strafe + turn, -0.75, 0.75);
        double backLeftPower = Range.clip(drive - strafe + turn, -0.75, 0.75);
        double frontRightPower = Range.clip(drive - strafe - turn, -0.75,0.75);
        double backRightPower = Range.clip(drive + strafe - turn, -0.75, 0.75);

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        if (gamepad1.x) {
            carousel.setPower(-0.4);
        }
        if (gamepad1.b) {
            carousel.setPower(0.4);
        }
        if (gamepad1.a) {
            carousel.setPower(0);
        }
        if (gamepad2.a) {
            intake.setPower(0);
        }
        if (gamepad2.b) {
            intake.setPower(0.5);
        }
        if (gamepad2.x) {
            intake.setPower(-0.5);
        }
        if (gamepad2.dpad_right) {
            wristServo.setPosition(0.1);
        }
        if (gamepad2.dpad_left) {
            wristServo.setPosition(0.5);
        }
        if (gamepad2.dpad_up) {
            shoulderm.moveByInchTele(2, 0.15);
        }
        if (gamepad2.dpad_down) {
            shoulderm.moveByInchTele(-2, 0.15);
        }

        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f)",
                frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("Wrist Position", "%5.2f", wristposition);
        telemetry.addData("lift Position", shoulderm.shoulderMotor.getCurrentPosition());
        telemetry.addData("lift Target", shoulderm.targetPos);
        telemetry.update();
    }

//    public final void idle() {
//        // Otherwise, yield back our thread scheduling quantum and give other threads at
//        // our priority level a chance to run
//        Thread.yield();
//    }
//
//    public final void sleep(long milliseconds) {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }

}
