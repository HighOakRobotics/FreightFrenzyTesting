package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.ftc11392.sequoia.SequoiaOpMode;
import com.ftc11392.sequoia.task.InstantTask;
import com.ftc11392.sequoia.task.ParallelRaceBundle;
import com.ftc11392.sequoia.task.Scheduler;
import com.ftc11392.sequoia.task.SequentialTaskBundle;
import com.ftc11392.sequoia.task.Task;
import com.ftc11392.sequoia.task.WaitTask;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.subsystem.Spinner;
import org.firstinspires.ftc.teamcode.task.SpinCarouselTask;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Autonomous(name = "RoadRunnerAuto", group = "Quackology")
@Disabled
public class RoadRunnerAuto extends SequoiaOpMode {
    private final Spinner spinner = new Spinner();


    @Override
    public void initTriggers() {

    }

    @Override
    public void runTriggers() {
        Scheduler.getInstance().schedule(new SequentialTaskBundle(

        ));
    }

}
