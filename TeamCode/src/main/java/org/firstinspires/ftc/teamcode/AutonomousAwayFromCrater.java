package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoAwayFromCrater", group="Linear Opmode")
//@Disabled
public class AutonomousAwayFromCrater extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    private DcMotor rake;
    private DcMotor lift;
    private DcMotor hook;

    final double DEFAULT_POWER = 0.5;

    public void turnLeft(double power, int milliseconds){

        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(milliseconds);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(800);
    }

    public void turnRight(double power, int milliseconds){
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
        sleep(milliseconds);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(800);
    }

    public void driveForward(double power, int milliseconds){
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(milliseconds);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(800);
    }

    public void driveBackward(double power, int milliseconds) {
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
        sleep(milliseconds);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(800);
    }

   public void putToken() {
        rake.setPower(-1);
        sleep(1000);
        rake.setPower(0);
        sleep(800);
    }

    public void landRobot(){
        sleep(500);
        lift.setPower(-1);
        sleep(2500);
        lift.setPower(0);
        sleep(1000);
        hook.setPower(-0.2);
        sleep(1000);
        hook.setPower(0);
        sleep(500);
    }

    public void moveArm(boolean back){
        if(back){
            arm.setPower(1);
            sleep(1500);
            arm.setPower(0);
            sleep(800);
        }else{
            arm.setPower(-1);
            sleep(1500);
            arm.setPower(0);
            sleep(800);
        }
    }

//    As you may have noticed, in the runOpMode, we are not using the while (opModeIsActive()) loop
//    Since we are using a sequence of instructions to guide the robot's movement, we don't need this loop
//    Therefore, whenever a user is going to press "init" on the drive station phone, the code below is going
//    to be executed, and the robot will start completing its instruction.

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rake = hardwareMap.get(DcMotor.class, "rake");
        lift = hardwareMap.get(DcMotor.class, "lift");
        hook = hardwareMap.get(DcMotor.class, "hook");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        rake.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        hook.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
////////////
//      Below is the sequence of instructions I was talking about.
            landRobot();
            driveForward(DEFAULT_POWER, 800);
            moveArm(true);
            putToken();
            moveArm(false);
            turnRight(DEFAULT_POWER, 2000);
            driveForward(1.0, 2050);
//      landRobot();
        driveForward(DEFAULT_POWER, 2200);
        putToken();
        turnRight(DEFAULT_POWER, 2000);

        driveForward(1.0, 1850);
        driveForward(1.0, 1850);

///////////

        runtime.reset();
    }
}
