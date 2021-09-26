package org.firstinspires.ftc.teamcode.subsystem;

import com.ftc11392.sequoia.subsystem.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm extends Subsystem {

    private Servo armServo;

    @Override
    public void initialize(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class, "left_hand");
    }

    public double getPosition() {
        return armServo.getPosition();
    }

    public void setPosition(double position) {
        armServo.setPosition(position);
    }

    @Override
    public void initPeriodic() {

    }

    @Override
    public void start() {

    }

    @Override
    public void runPeriodic() {

    }

    @Override
    public void stop() {

    }
}
