package org.firstinspires.ftc.teamcode.task;

import com.ftc11392.sequoia.task.InstantTask;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;

public class GamepadDriveTask extends InstantTask {
    public GamepadDriveTask(Gamepad gamepad, Drivetrain drivetrain) {
        super(() -> {
            drivetrain.driveDST(gamepad);
        });
    }
}
