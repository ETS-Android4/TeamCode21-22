package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaFieldNavigation;
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaFieldNavigationWebcam;
//import org.firstinspires.ftc.teamcode.ColorRangeSensor;

public class ChadBot {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private ConceptVuforiaFieldNavigationWebcam phone;

    public void init(HardwareMap map) {
        // Initialize & configure drive motors
        frontLeft = map.dcMotor.get("frontLeft");
        frontRight = map.dcMotor.get("frontRight");
        backLeft = map.dcMotor.get("backLeft");
        backRight = map.dcMotor.get("backRight");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        phone= new ConceptVuforiaFieldNavigationWebcam();

    }

    /**
     * Stop the robot
     */

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }



    /**
     * Enable/disable encoders
     * @param enabled
     */
    public void setEncoders(boolean enabled) {
        if(enabled)
        {
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else
        {
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


    /**
     *
     * @param fL
     * @param fR
     * @param bL
     * @param bR
     * @param speed
     */

    public void drive(double fL, double fR, double bL,double bR, double speed) {
        frontLeft.setPower(fL*speed);
        frontRight.setPower(fR*speed);
        backLeft.setPower(bL*speed*-1);
        backRight.setPower(bR*speed*-1);
    }

    //public void stoppell(){
    //    impell.setPower(0);
    //}

    public void forward(double speed){
        drive(1, 1, 1, 1, speed);
    }

    public void backward(double speed){
        drive(-1,-1,-1,  -1,speed);
    }

    /*public void left(double speed){
        drive(-1, 1, .9, -.9, speed);
    }*/

    /*public void right(double speed){
       drive(1, -1, -.9, .9, speed);
    }*/

    public void spinleft(){
        drive(-1, 1, -1, 1, 1);
    }

    public void spinright(){
        drive(1, -1, 1, -1, 1);
    }


}
