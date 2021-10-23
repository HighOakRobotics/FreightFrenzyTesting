package org.firstinspires.ftc.teamcode;

import com.ftc11392.sequoia.SequoiaOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Spinner;
import org.firstinspires.ftc.teamcode.task.GamepadDriveTask;
import org.firstinspires.ftc.teamcode.task.InterpolateArmTask;
import org.firstinspires.ftc.teamcode.task.SpinCarouselTask;

import java.util.concurrent.TimeUnit;

public class DriveOpMode extends SequoiaOpMode {

    Drivetrain drivetrain = new Drivetrain();
    Spinner spinner = new Spinner();
    Arm arm = new Arm();

    @Override
    public void initTriggers() {
    }

    @Override
    public void runTriggers() {
        gamepad1H.sticksButton(0.01).onPress(new GamepadDriveTask(gamepad1, drivetrain));
        gamepad1H.yButton().onPressWithCancel(new SpinCarouselTask(spinner));
        gamepad1H.aButton().onPress(new InterpolateArmTask(arm, 1.0, 1, TimeUnit.SECONDS));
        gamepad1H.bButton().onPress(new InterpolateArmTask(arm, 0.0, 1, TimeUnit.SECONDS));
    }
}
