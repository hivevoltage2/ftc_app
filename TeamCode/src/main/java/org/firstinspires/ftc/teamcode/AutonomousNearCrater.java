package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoNearCrater", group="Linear Opmode")
//@Disabled
public class AutonomousNearCrater extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    private DcMotor rake;
    private DcMotor lift;
    private DcMotor hook;
    //private NormalizedColorSensor colorSensor;

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
        sleep(1500);
        rake.setPower(0);
        sleep(800);
    }

    public void moveArm(){
        arm.setPower(1);
        sleep(1000);
        arm.setPower(0);
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

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        rake = hardwareMap.get(DcMotor.class, "rake");
        arm = hardwareMap.get(DcMotor.class, "arm");
        lift = hardwareMap.get(DcMotor.class, "lift");
        hook = hardwareMap.get(DcMotor.class, "hook");

        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color");

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
        turnLeft(DEFAULT_POWER, 1500);
        driveForward(DEFAULT_POWER, 800);

        turnLeft(DEFAULT_POWER, 1000);
        driveForward(DEFAULT_POWER, 2300);
        moveArm();
        putToken();
        driveBackward(1.0, 1850);
///////////
        //Joey Code (but honestly just copied example code, works better than ghetto code
//        float[] hsv = new float[3];
//        NormalizedRGBA colors = colorSensor.getNormalizedColors();
//
//        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
//        colors.red   /= max;
//        colors.green /= max;
//        colors.blue  /= max;
//        int color = colors.toColor();
//        Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsv);


        runtime.reset();
    }
}
