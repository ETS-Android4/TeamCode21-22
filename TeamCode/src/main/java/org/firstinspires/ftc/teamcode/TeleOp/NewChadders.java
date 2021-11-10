package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "NewChadders")
public class NewChadders extends OpMode{

    private DcMotor fl;//front left
    private DcMotor fr;//front right
    private DcMotor bl;//back left
    private DcMotor br;//back right




    public void init() {
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
    }

    public void loop() {
        fl.setPower(gamepad1.left_stick_y);
        fr.setPower(gamepad1.right_stick_y);
        bl.setPower(gamepad1.left_stick_y);
        br.setPower(gamepad1.right_stick_y);
    }

    public void stop() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

}