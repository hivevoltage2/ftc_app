package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Auto3774")
//@Disabled
public class Auto3774 extends LinearOpMode{

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    private Servo flipper;

    double leftPower;
    double rightPower;

    public void runOpMode() {
        telemetry.addData("Status", "Initiliazied");
        telemetry.update();

        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        flipper = hardwareMap.servo.get("flipper");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        backLeft.setPower(0.6);
        backRight.setPower(0.6);
        sleep(2000);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
