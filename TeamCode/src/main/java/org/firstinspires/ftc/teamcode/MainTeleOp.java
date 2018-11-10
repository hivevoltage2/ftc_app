
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp", group="Linear OpMode")
//@Disabled
public class MainTeleOp extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor rake;
    private DcMotor arm;
    private DcMotor lift;

    double leftPower;
    double rightPower;
    double armPower;

    //We are probably gonna change how we call them
    double rakePower;
    double liftPower;

    //Power values that will determine in what direction the robot is gonna go. Everything is explained below
    double yDirection;
    double xDirection;

    //Power value that will add more power to the motors, as I decided that we
    //don't have to use the full power of the motors as the robot drives
    //If we use full power of the motors we are gonna lose precision.
    double extraPower;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initiliazied");
        telemetry.update();

        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rake = hardwareMap.get(DcMotor.class, "rake");
        //lift = hardwareMap.get(DcMotor.class, "lift");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        rake.setDirection(DcMotor.Direction.FORWARD);
        //lift.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //As we are going to use only one stick (right stick) to control the movement of the robot. These statements make this possible.
            //yDirection stands for movement along the y-axis.
            //xDirection stands for movement along the x-axis.
            //As xDirection increases, leftPower increases
            //As xDirection decreases, rightPower decreases

            yDirection = -gamepad1.right_stick_y;
            xDirection = -gamepad1.right_stick_x;
            leftPower = Range.clip(yDirection - xDirection, -1.0, 1.0);
            rightPower = Range.clip(yDirection + xDirection, -1.0, 1.0);
            armPower = -gamepad1.left_stick_y;
            if(gamepad1.b) {
                rakePower = 1;
            }else if(gamepad1.x){
                rakePower = -1;
            }else{
                rakePower = 0;
            }

            if(gamepad1.dpad_down){
                liftPower = 1;
            }else if(gamepad1.dpad_up){
                liftPower = -1;
            }

            //Here, we say that whenever user presses the right trigger, give extraPower the max value (1.0)
            extraPower = gamepad1.right_trigger;

            //In these series of control statements we tone down the power of the motors by half (*0.5)
            //and then add the value of extraPower (0 or 1, depending on the user's actions with the right trigger.
            //In this case we add extraPower to the leftPower value
            if(leftPower > 0){
                backLeft.setPower(leftPower - 0.5*extraPower);
                frontLeft.setPower(leftPower - 0.5*extraPower);
            }else if(leftPower < 0){
                backLeft.setPower(leftPower + 0.5*extraPower);
                frontLeft.setPower(leftPower + 0.5*extraPower);
            }else{
                backLeft.setPower(0);
                frontLeft.setPower(0);
            }

            //In this case we add extraPower to the rightPower value
            if(rightPower > 0){
                frontRight.setPower(rightPower - 0.5*extraPower);
                backRight.setPower(rightPower - 0.5*extraPower);
            }else if(rightPower < 0){
                frontRight.setPower(rightPower + 0.5*extraPower);
                backRight.setPower(rightPower + 0.5*extraPower);
            }else{
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            arm.setPower(armPower);
            rake.setPower(rakePower);
            //lift.setPower(liftPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
