package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    final double DEFAULT_POWER = 0.5;

//     Below, is set of methods for the robot to move forward, back, turn left, turn right,
//     put the token, and land itself from the lander.
//     As parameters, these methods take "power", which is the power with which the robot
//     is going to perform a certain action, and "milliseconds", which represents the
//     amount of time the user wants the robot
//     to perform that certain action.
//
//     The sleep() method is what we are going to use to "put motors to sleep".
//     After a certain amount of milliseconds (this method takes milliseconds as a parameter, not seconds)
//     it shut downs some of the ongoing processes.
//     I am not sure yet, which processes does shut down, but it definitely works for the motors.
//     I am certain it has something to do with "Threads". It would be great if you could help
//     figure out what does it do exactly.
    public void turnLeft(double power, int milliseconds){
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(milliseconds);
//         Since the motors doesn't stop working immediately after sleep() method,
//         We have to wait for some time (0.8 seconds. It is longer than needed. Could be less)
//         for every motor to stop working to prevent any inaccuracies.
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
        rake.setPower(DEFAULT_POWER);
        sleep(1000);
        rake.setPower(0);
        sleep(800);
    }

//       public void landRobot(){
//            lift.setPower(1);
//     sleep(2000);
//     lift.setPower(0);
//     sleep(1000);
//    }

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

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        rake.setDirection(DcMotor.Direction.FORWARD);
//        lift.setDirection(DcMotor.Direction.FORWARD);

////////////
//      Below is the sequence of instructions I was talking about.
//      landRobot();
        driveForward(DEFAULT_POWER, 2200);
        putToken();
        turnRight(DEFAULT_POWER, 2000);
<<<<<<< .merge_file_a06700
        driveForward(1.0, 1850);
=======
        driveForward(1.0, 1850);
////////////
>>>>>>> .merge_file_a08256

        waitForStart();
        runtime.reset();
    }
}
