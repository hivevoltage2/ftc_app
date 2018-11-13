package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
//@Disabled
public class LatchAuto4890 extends LinearOpMode {

    private DcMotor latch;

    @Override
    public void runOpMode() throws InterruptedException {
        latch = hardwareMap.dcMotor.get("latch");
        waitForStart();
    }
}
