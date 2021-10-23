package org.firstinspires.ftc.teamcode.task;

import com.ftc11392.sequoia.task.Task;

import org.firstinspires.ftc.teamcode.subsystem.Spinner;

public class SpinCarouselTask extends Task {

    Spinner spinner;

    public SpinCarouselTask(Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void init() {
        spinner.spinMotor();
    }

    @Override
    public void loop() { }

    @Override
    public void stop(boolean interrupted) {
        spinner.stopMotor();
    }
}
