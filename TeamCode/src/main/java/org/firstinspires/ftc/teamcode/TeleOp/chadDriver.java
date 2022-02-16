package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "chadDriver")
public class chadDriver extends OpMode {
    private ChadBot robot;
    private double speed;

    //when init is pressed
    public void init() {
        //declares class chadBot- ChadBot defines all functions in class
        robot = new ChadBot();
        //all motors and servos on robot are correct in the configuration so the the robot knows which part moves at what time.
        robot.init(hardwareMap);
        //speed variable
        speed = 1;
    }

    public void loop() {
        //two separate functions for driver and codriver controllers
        updateDriver();
        updateCodriver();
        //we use telemetry to find the position of all parts on robot
        //feedback(telemetry);
        telemetry.addData("elevator", robot.getElevator().getCurrentPosition());
        telemetry.addData("dumper", robot.getDumper().getPosition());
        telemetry.addData("backLeft",robot.getBackLeft().getCurrentPosition());
        telemetry.addData("backRight",robot.getBackRight().getCurrentPosition());
        telemetry.update();
    }


    /**
     * Process gamepad1 (driver) controls
     */
    private void updateDriver() {//creates the functions for the driver controller
        //driver controller has everything to do with driving the robot and its movements
        //movements: forward, back, spin left, spin right, impel or expel

        // Set variable speed to .75 speed
        speed = .75;
        //On the controller, the left bumper will decrease the variable speed by .25 to create the speed .5
        if (gamepad1.left_bumper)
            speed -= .25;
        //On the controller, the right bumper will increase the bariable speed by .25 to create the speed 1
        if (gamepad1.right_bumper)
            speed += .25;

        // direction controls
        if (gamepad1.dpad_up) robot.backward(speed * .5);//if up on the dpad is pressed, the robot moves backwards .5 speed
            // else if (gamepad1.dpad_right) robot.right(speed*.5);
        else if (gamepad1.dpad_down) robot.forward(speed * .5);//if down on the dpad is pressed, the robot moves forward .5 speed
            //else if (gamepad1.dpad_left) robot.left(speed*.5);
        else if (Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > .1) {
            if (Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {//creates if statement for the left stick on the controller-- if statement will always be true
                //left stick logic
                if (gamepad1.left_stick_y > 0.1) {
                    //down on the left stick makes the robot move backwards
                    robot.forward(speed);
                } else {
                    //up on the left stick makes the robot move forward
                    robot.backward(speed);
                }

            }


        } else if (Math.abs(gamepad1.right_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1) { //creates if statement for the right stick on the controller-- if statement will always be true
            // right stick logic
            if (gamepad1.right_stick_x > 0.1) {
                //right on the right stick makes the robot spin right
                robot.spinleft();
            } else {
                //left on the left stick makes the robot spin left
                robot.spinright();
            }
        } else {
            robot.stop();//stops the robot
        }


        if (gamepad1.a) {
            robot.impelll();//pressing -A- impels the intake to start collecting freight
        }
        if (gamepad1.b) {
            robot.expelll();//pressing -B- expels the intake to release collected freight
        }
        if (gamepad1.y) {
            robot.stop();//pressing -Y- stops the robot
        }
        if (gamepad1.x) {
            robot.stopelll();//pressing -X- stops the intake from moving-- sets power to 0
        }
        if(gamepad1.right_stick_button){
            robot.getBackLeft().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.getBackRight().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.getElevator().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

    }

    private void updateCodriver() {//creates the functions for the codriver controller
        //codriver controller has everything to do with controlling the various parts of the robot
        //parts: dumper, elevator, and duck spinner

        if (gamepad2.dpad_left) {
            //robot.liftMid();//left on dpad sets the lift to the middle section
        } else if (gamepad2.dpad_up) {
            robot.liftDown();//up on dpad sets the lift to the top section
        } else if (gamepad2.dpad_down) {
            robot.liftUp();//down on dpad sets the lift to the bottom section
        } else if (gamepad2.dpad_right) {
            robot.liftStop();//
        }

        if (gamepad2.a) {
            robot.dumpy();//pressing -A- lowers the dumper down to collect more freight
        }
        if (gamepad2.b) {
            //robot.dumperStop();
        }
        if (gamepad2.y) {
            robot.undumpy();//pressing -Y- lifts the dumper up to dumper the freight into hubs
        }
        if (gamepad2.x) {
            robot.halfDump();//stops robot
        }
        if (gamepad2.left_trigger == 1) {//pressing the left trigger makes our duck spinner move clockwise
            robot.clockwiseDuckyTurn();
        }
        if (gamepad2.right_trigger == 1) {//pressing the right trigger makes our duck spinner move counter clockwise
            robot.counterClockwiseDuckyTurn();
        }
        if (gamepad2.right_bumper) {//pressing the right bumper stops our duck spinner's movements
            robot.StopDuckyTurn();
        }


    }

}

