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
        telemetry.addData("elevator", robot.getElevator().getCurrentPosition());
        telemetry.addData("dumper", robot.getDumper().getCurrentPosition());
        telemetry.update();
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
        if (gamepad1.dpad_up) robot.backward(speed * .5);
            // else if (gamepad1.dpad_right) robot.right(speed*.5);
        else if (gamepad1.dpad_down) robot.forward(speed * .5);
            //else if (gamepad1.dpad_left) robot.left(speed*.5);
        else if (Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > .1) {
            if (Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {
                if (gamepad1.left_stick_y > 0.1) {
                    //backward
                    robot.backward(speed);
                } else {
                    //forward
                    robot.forward(speed);
                }

            }


        } else if (Math.abs(gamepad1.right_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1) {
            // right stick logic
            if (gamepad1.right_stick_x > 0.1) {
                robot.spinright();
            } else {
                robot.spinleft();
            }
        } else {
            robot.stop();//stops the robot
        }


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
            robot.stopelll();//pressing x stops the intake
        }

    }

    private void updateCodriver() {

        if (gamepad2.dpad_left) {
            //robot.liftMid();//left on dpad sets the lift to the middle section
        } else if (gamepad2.dpad_up) {
            robot.liftUp();//up on dpad sets the lift to the top section
        } else if (gamepad2.dpad_down) {
            robot.liftDown();//down on dpad sets the lift to the bottom section
        } else if (gamepad2.dpad_right) {
            robot.liftStop();//
        }

        if (gamepad2.a) {
            robot.undumpy();//pressing a dumps
        }
        if (gamepad2.b) {
            robot.dumperStop();
        }
        if (gamepad2.y) {
            robot.dumpy();//pressing y starts spinning the duckkyturnwheeelthing
        }
        if (gamepad2.x) {
            robot.stop();//pressing x stops spinning the duckkyturningwheeelthing
        }
        if (gamepad2.left_trigger == 1) {
            robot.clockwiseDuckyTurn();
        }
        if (gamepad2.right_trigger == 1) {
            robot.counterClockwiseDuckyTurn();//pressing x stops spinning the duckkyturningwheeelthing
        }
        if (gamepad2.right_bumper) {
            robot.StopDuckyTurn();//pressing x stops spinning the duckkyturningwheeelthing
        }


    }

}

