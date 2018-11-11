/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MainTeleOp", group="Linear OpMode")
//@Disabled
public class MainTeleOp extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;

    //Here I have initialized motors that we are going to use in the future. The naming is temporary, we are probably gonna change how we call them
    private DcMotor slide1;
    private DcMotor slide2;
    private DcMotor lift;

    double leftPower;
    double rightPower;
    double armPower;

    //Power values of slide1, slide2, and lift. The naming is temporary, we are probably gonna change how we call them
    double slide1Power;
    double slide2Power;
    double liftPower;

    //Power values that will determine in what direction the robot is gonna go. Everything is explained below
    double yDirection;
    double xDirection;

    //Power value that will add more power to the motors, as I decided that we don't have to use the full power of the motors as the robot drives
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
        //slide1 = hardwareMap.get(DcMotor.class, "slide1");
        //slide2 = hardwareMap.get(DcMotor.class, "slide2");
        //lift = hardwareMap.get(DcMotor.class, "lift");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);
        //slide1.setDirection(DcMotor.Direction.FORWARD);
        //slide2.setDirection(DcMotor.Direction.FORWARD);
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

            //Here, we say that whenever user presses the right trigger, give extraPower the max value (1.0)
            extraPower = gamepad1.right_trigger;

            armPower = -gamepad1.left_stick_y;

            //In these series of control statements we tone down the power of the motors by half (*0.5) and then add the value of extraPower (0 or 1, depending on the user's actions with the right trigger.
            //In this case we add extraPower to the leftPower value
            if(leftPower > 0){
                backLeft.setPower(leftPower - 0.7*extraPower);
                frontLeft.setPower(leftPower - 0.7*extraPower);
            }else if(leftPower < 0){
                backLeft.setPower(leftPower + 0.7*extraPower);
                frontLeft.setPower(leftPower + 0.7*extraPower);
            }else{
                backLeft.setPower(0);
                frontLeft.setPower(0);
            }

            //In this case we add extraPower to the rightPower value
            if(rightPower > 0){
                frontRight.setPower(rightPower - 0.7*extraPower);
                backRight.setPower(rightPower - 0.7*extraPower);
            }else if(rightPower < 0){
                frontRight.setPower(rightPower + 0.7*extraPower);
                backRight.setPower(rightPower +  0.7*extraPower);
            }else{
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            arm.setPower(armPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
