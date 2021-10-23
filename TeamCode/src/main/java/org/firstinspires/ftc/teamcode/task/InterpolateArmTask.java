package org.firstinspires.ftc.teamcode.task;

import com.ftc11392.sequoia.task.Task;
import com.ftc11392.sequoia.util.Clock;

import org.firstinspires.ftc.teamcode.subsystem.Arm;

import java.util.concurrent.TimeUnit;

public class InterpolateArmTask extends Task {

    double currentPosition;

    Arm arm;
    double startPosition;
    double finalPosition;
    double time;
    TimeUnit timeUnit;
    double timeRatio;

    int direction;

    Clock clock;

    public InterpolateArmTask(Arm arm, double position, double time, TimeUnit timeUnit) {
        this.arm = arm;
        this.time = time;
        this.timeUnit = timeUnit;
        startPosition = arm.getPosition();
        finalPosition = position;
        timeRatio = (finalPosition - startPosition) / time;
        direction = (int) Math.signum(timeRatio);

        clock = new Clock();
    }

    @Override
    public void init() {
        currentPosition = startPosition;
        arm.setPosition(currentPosition);
        clock.startTiming();
    }

    @Override
    public void loop() {
        if (clock.getTime(timeUnit) >= time) {
            running = false;
        } else {
            currentPosition = startPosition + timeRatio * clock.getTime(timeUnit);
            arm.setPosition(currentPosition);
        }
    }

    @Override
    public void stop(boolean interrupted) {
        arm.setPosition(finalPosition);
    }
}
