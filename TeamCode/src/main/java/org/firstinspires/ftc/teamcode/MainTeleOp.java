
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

    double leftPower;
    double rightPower;

    double yDirection;
    double xDirection;

    double extraPower;

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

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            yDirection = -gamepad1.right_stick_y;
            xDirection = -gamepad1.right_stick_x;
            leftPower = Range.clip(yDirection - xDirection, -1.0, 1.0);
            rightPower = Range.clip(yDirection + xDirection, -1.0, 1.0);

            if(leftPower > 0){
                backLeft.setPower(leftPower - 0.4);
                frontLeft.setPower(leftPower - 0.4);
            }else if(leftPower < 0){
                backLeft.setPower(leftPower + 0.4);
                frontLeft.setPower(leftPower + 0.4);
            }else{
                backLeft.setPower(0);
                frontLeft.setPower(0);
            }

            if(rightPower > 0){
                frontRight.setPower(rightPower - 0.4);
                backRight.setPower(rightPower - 0.4);
            }else if(rightPower < 0){
                frontRight.setPower(rightPower + 0.4);
                backRight.setPower(rightPower +  0.4);
            }else{
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
