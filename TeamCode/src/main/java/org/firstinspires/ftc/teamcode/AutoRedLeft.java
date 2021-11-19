package org.firstinspires.ftc.teamcode;

import com.ftc11392.sequoia.SequoiaOpMode;
import com.ftc11392.sequoia.task.InstantTask;
import com.ftc11392.sequoia.task.SequentialTaskBundle;
import com.ftc11392.sequoia.task.WaitTask;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;


@Autonomous(name = "AutoRedLeft", group = "Auto")
public class AutoRedLeft extends SequoiaOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotorEx frontLeft, frontRight, backLeft, backRight, carousel, intake;
    private Servo wristServo;
    private ShoulderM shoulderm;
    private boolean upPressed;
    private boolean downPressed;

    @Override
    public void initTriggers() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        carousel = hardwareMap.get(DcMotorEx.class, "carousel");
        intake = hardwareMap.get(DcMotorEx.class, "intake");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        wristServo = hardwareMap.get(Servo.class, "wrist");

        shoulderm = new ShoulderM(hardwareMap);
        shoulderm.init();
        upPressed = false;
        downPressed = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void runTriggers() {
        scheduler.schedule(new SequentialTaskBundle(
                new InstantTask(() -> {
                    frontLeft.setPower(-0.6);
                    frontRight.setPower(0.6);
                    backLeft.setPower(0.6);
                    backRight.setPower(-0.6);
                }),
                new WaitTask(1, TimeUnit.SECONDS),
                new InstantTask(() -> {
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backLeft.setPower(0);
                    backRight.setPower(0);
                }),
                new InstantTask(() -> {
                    frontLeft.setPower(0.6);
                    frontRight.setPower(0.6);
                    backLeft.setPower(0.6);
                    backRight.setPower(0.6);
                }),
                new WaitTask(1,TimeUnit.SECONDS),
                new InstantTask(() -> {
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backLeft.setPower(0);
                    backRight.setPower(0);
                })

        ));

    }
}
