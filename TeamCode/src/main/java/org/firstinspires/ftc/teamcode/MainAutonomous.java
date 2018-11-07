package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="MainAutonomous", group="Linear Opmode")
//@Disabled
public class MainAutonomous extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;

    private DcMotor slide1;
    private DcMotor slide2;
    private DcMotor lift;

    final double DEFAULT_POWER = 0.5;

    public void turnLeft(int milliseconds){
        frontLeft.setPower(-DEFAULT_POWER);
        backLeft.setPower(-DEFAULT_POWER);
        frontRight.setPower(DEFAULT_POWER);
        backRight.setPower(DEFAULT_POWER);
        sleep(milliseconds);
    }

    public void turnRight(int milliseconds){
        frontLeft.setPower(DEFAULT_POWER);
        backLeft.setPower(DEFAULT_POWER);
        frontRight.setPower(-DEFAULT_POWER);
        backRight.setPower(-DEFAULT_POWER);
        sleep(milliseconds);
    }

    public void driveForward(int milliseconds){
        frontLeft.setPower(DEFAULT_POWER);
        backLeft.setPower(DEFAULT_POWER);
        frontRight.setPower(DEFAULT_POWER);
        backRight.setPower(DEFAULT_POWER);
        sleep(milliseconds);
    }

    public void driveBackward(int milliseconds){
        frontLeft.setPower(-DEFAULT_POWER);
        backLeft.setPower(-DEFAULT_POWER);
        frontRight.setPower(-DEFAULT_POWER);
        backRight.setPower(-DEFAULT_POWER);
        sleep(milliseconds);
    }

    public void liftArm(){
        
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        //slide1.setDirection(DcMotor.Direction.FORWARD);
        //slide2.setDirection(DcMotor.Direction.FORWARD);
        //lift.setDirection(DcMotor.Direction.FORWARD);

        turnLeft(500);

        waitForStart();
        runtime.reset();
    }
}
