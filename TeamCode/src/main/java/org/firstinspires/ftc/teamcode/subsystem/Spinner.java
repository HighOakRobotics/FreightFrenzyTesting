package org.firstinspires.ftc.teamcode.subsystem;

import com.ftc11392.sequoia.subsystem.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spinner extends Subsystem {

    private DcMotorEx spinMotor;

    @Override
    public void initialize(HardwareMap hardwareMap) {
        spinMotor = hardwareMap.get(DcMotorEx.class, "carouselMotor");
    }

    public void spinMotor() {
        spinMotor.setPower(1);
    }

    public void stopMotor() {
        spinMotor.setPower(0);
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
