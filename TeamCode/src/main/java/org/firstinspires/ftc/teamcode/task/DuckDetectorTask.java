package org.firstinspires.ftc.teamcode.task;

import com.ftc11392.sequoia.task.Task;

import org.firstinspires.ftc.teamcode.subsystem.DuckDetector;

public class DuckDetectorTask extends Task {
    DuckDetector detector;

    public DuckDetectorTask(DuckDetector detector) {
        this.running = true;
        this.detector = detector;

        addSubsystems(detector);
    }

    @Override
    public void init() {
    }

    @Override
    public void loop() {
        detector.detectDucks();
    }

    @Override
    public void stop(boolean interrupted) {
        detector.stop();
        this.running = false;
    }
}