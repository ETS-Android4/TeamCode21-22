package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "SlowDrive")
public class SlowDrive extends OpMode {
    DcMotor left;
    DcMotor right;

    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");

    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up){
            //if up on the dpad is pressed, the robot will move forward
            left.setPower(-1);
            right.setPower(1);
        }
        else if(gamepad1.dpad_down){
            //if down on the dpad is pressed, the robot will move backward
            left.setPower(1);
            right.setPower(-1);
        }
        else if(gamepad1.dpad_left){
            //if left on the dpad is pressed, the robot will move left
            left.setPower(1);
            right.setPower(1);
        }
        else if(gamepad1.dpad_right){
            //if right on the dpad is pressed, the robot will move right
            left.setPower(-1);
            right.setPower(-1);
        }
        else{
            //if the dpad is not pressed, the robot will do nothing
            left.setPower(0);
            right.setPower(0);
        }




        //debug
        telemetry.addData("left encoder",left.getCurrentPosition());
        telemetry.addData("right encoder",right.getCurrentPosition());

    }
}