package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "chadDriver")
public class chadDriver extends OpMode {
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
        //feedback(telemetry);
    }


    /**
     * Process gamepad1 (driver) controls
     */
    private void updateDriver() {

        // Set drive speed
        speed = .75;
//slows the thing
        if (gamepad1.left_bumper)
            speed -= .25;
        //speeds the thing
        if (gamepad1.right_bumper)
            speed += .25;

        // direction controls
        if (gamepad1.dpad_up) robot.forward(speed * .5);
            // else if (gamepad1.dpad_right) robot.right(speed*.5);
        else if (gamepad1.dpad_down) robot.backward(speed * .5);
            //else if (gamepad1.dpad_left) robot.left(speed*.5);
        else if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            if (Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {
                if (gamepad1.left_stick_y > 0.1) {
                    //forward
                    robot.forward(speed);
                } else {
                    //backwards
                    robot.backward(speed);
                }

            }
                if (gamepad1.right_stick_x > -0.1) {
                //right
                robot.spinright();
            } else {
                //left
                robot.spinleft();
            }

            } else {
                robot.stop();//stops the robot
            }

            //spin
/*
        if(gamepad1.right_stick_x>0.1){
            robot.spinleft();
        }
        else if(gamepad1.right_stick_x<-0.1){
            robot.spinright();
        }
        */


            if (gamepad1.a) {
                robot.impelll();//pressing a impels
            }
            if (gamepad1.b) {
                robot.expelll();//pressing b expels
            }
            if (gamepad1.y) {
                robot.stop();
            }
            if (gamepad1.x) {
                robot.stopelll();//pressing x stops expeling/impeling
            }

        }

        private void updateCodriver() {

            if (gamepad2.dpad_left) {
                //robot.liftMid();//left on dpad sets the lift to the middle section
            } else if (gamepad2.dpad_up) {
                robot.liftUp();//up on dpad sets the lift to the top section
                telemetry.addData("elevator",robot.getElevator().getCurrentPosition());
            } else if (gamepad2.dpad_down) {
                robot.liftDown();//down on dpad sets the lift to the bottom section
                telemetry.addData("elevator",robot.getElevator().getCurrentPosition());
            } else if (gamepad2.dpad_right) {
                robot.liftStop();//
            }

            if (gamepad2.a) {
                robot.dumpy();//pressing a dumps
                telemetry.addData("dumper",robot.getDumper().getCurrentPosition());
            }
            if (gamepad2.b) {
                robot.undumpy();//pressing b undumps
                telemetry.addData("dumper",robot.getDumper().getCurrentPosition());
            }
            if (gamepad2.y) {
                // robot.duckkyturningwheeelthing();//pressing y starts spinning the duckkyturnwheeelthing
            }
            if (gamepad2.x) {
                robot.dumperStop();
                //robot.STOPduckkyturningwheeelthing();//pressing x stops spinning the duckkyturningwheeelthing
            }
        }

    }

