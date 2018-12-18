
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Copycat", group="Linear OpMode")
//@Disabled
public class Copycat extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor rake;
    private DcMotor arm;
    private DcMotor hook;
    private DcMotor lift;

    double leftPower;
    double rightPower;
    double armPower;

    double rakePower;
    double liftPower;

    double yDirection;
    double xDirection;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initiliazied");
        telemetry.update();

        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        rake.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        hook.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
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

            if(gamepad1.right_bumper){
                lift.setPower(1);
            }else if(gamepad1.left_bumper){
                lift.setPower(-1);
            }

            if(gamepad1.y){
                hook.setPower(0.2);
            }else if(gamepad1.a){
                hook.setPower(-0.2);
            }else {
                hook.setPower(0);
            }

            if(leftPower > 0){
                backLeft.setPower(leftPower - 0.2);
                frontLeft.setPower(leftPower - 0.2);
            }else if(leftPower < 0){
                backLeft.setPower(leftPower + 0.2);
                frontLeft.setPower(leftPower + 0.2);
            }else{
                backLeft.setPower(0);
                frontLeft.setPower(0);
            }
            
            if(rightPower > 0){
                frontRight.setPower(rightPower - 0.2);
                backRight.setPower(rightPower - 0.2);
            }else if(rightPower < 0){
                frontRight.setPower(rightPower + 0.2);
                backRight.setPower(rightPower +  0.2);
            }else{
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            arm.setPower(armPower);
            rake.setPower(rakePower);
            lift.setPower(liftPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
