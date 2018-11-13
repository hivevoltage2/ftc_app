
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp3774", group="Linear OpMode")
//@Disabled
public class MainTeleOP3774 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    private Servo flipper;

    double leftPower;
    double rightPower;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initiliazied");
        telemetry.update();

        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        flipper = hardwareMap.servo.get("flipper");
        //flipper = hardwareMap.get(Servo.class, "flipper");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            leftPower = gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;
            if(gamepad1.b){
                arm.setPower(0.4);
            }
            else if(gamepad1.x){
                arm.setPower(-0.4);
            }else{
                arm.setPower(0);
            }

            if(gamepad1.right_bumper && flipper.getPosition() < 1){
                flipper.setPosition(flipper.getPosition()+0.0033);
            }else if(gamepad1.left_bumper && flipper.getPosition() > 0){
                flipper.setPosition(flipper.getPosition()-0.0033);
            }


            backRight.setPower(rightPower);
            backLeft.setPower(leftPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
