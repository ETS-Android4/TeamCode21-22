package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "brianDrive")
public class chadDriver extends OpMode{
    private ChadBot robot;
    private double speed;

    public void init() {
        robot = new ChadBot();
        robot.init(hardwareMap);
        speed = 1;
    }

    public void loop() {
        // Routines separated into separate functions
        updateDriver();
        updateCodriver();
        feedback(telemetry);
    }

    public void feedback(Telemetry telemetry) {
        // Print out the color sensor RGB values
        telemetry.addData("Right R", robot.rightColor.red());
        telemetry.addData("Right G", robot.rightColor.green());
        telemetry.addData("Right B", robot.rightColor.blue());
        // Print out the distance from the right sensor in CM
        //telemetry.addData("Right Distance (cm)", this.getDistance(DistanceUnit.CM));
    }

    /**
     * Process gamepad1 (driver) controls
     */
    private void updateDriver() {

        // Set drive speed
        speed = .75;

        if (gamepad1.left_bumper)
            speed -= .25;
        if (gamepad1.right_bumper)
            speed += .25;

        // direction controls
        if (gamepad1.dpad_up) robot.forward(speed*.5);
        else if (gamepad1.dpad_right) robot.right(speed*.5);
        else if (gamepad1.dpad_down) robot.backward(speed*.5);
        else if (gamepad1.dpad_left) robot.left(speed*.5);
        else if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            if (Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {
                if (gamepad1.left_stick_y > 0.1) {
                    //forward
                    robot.backward(speed);
                } else {
                    //backwards
                    robot.forward(speed);
                }
            } else if (gamepad1.left_stick_x > -0.1) {
                //right
                robot.right(speed);
            } else {
                //left
                robot.left(speed);
            }
        }
        else {
            robot.stop();
        }

        //spin
        if(gamepad1.right_stick_x>0.1){
            robot.spinright();
        }
        else if(gamepad1.right_stick_x<-0.1){
            robot.spinleft();
        }

        if (gamepad1.a){

        }
        if(gamepad1.b){

        }
        if (gamepad1.y){

        }

    }
    /**
     * Process gamepad2 (codriver) controls
     *
     */

    private void updateCodriver() {

        if(gamepad2.dpad_left){

        }
        else if (gamepad2.dpad_right){

        }

        if(gamepad2.a){

        }
        else if (gamepad2.b){

        }

        if (gamepad2.dpad_up){

        }
        else if(gamepad2.dpad_down){

        }
    }



}