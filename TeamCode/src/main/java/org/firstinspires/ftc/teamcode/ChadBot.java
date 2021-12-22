package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaFieldNavigationWebcam;
//import org.firstinspires.ftc.teamcode.ColorRangeSensor;

public class ChadBot {
    //private DcMotor frontLeft;
    //private DcMotor frontRight;
    private DcMotor backLeft;//left
    private DcMotor backRight;//right

    private DcMotor intake;
    private DcMotor elevator;
    //private DcMotor duckyygoturnturn;
    private DcMotor dumper;

    private ConceptVuforiaFieldNavigationWebcam phone;

    public void init(HardwareMap map) {
        // Initialize & configure drive motors
        //frontLeft = map.dcMotor.get("frontLeft");
        //frontRight = map.dcMotor.get("frontRight");
        backLeft = map.dcMotor.get("backLeft");
        backRight = map.dcMotor.get("backRight");
        intake = map.dcMotor.get("intake");
        elevator = map.dcMotor.get("elevator");
        //duckyygoturnturn = map.dcMotor.get("duck");
        dumper = map.dcMotor.get("dumper");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        phone= new ConceptVuforiaFieldNavigationWebcam();

    }

    /**
     * Stop the robot
     */

    public DcMotor getBackLeft(){ return backLeft; }
    public DcMotor getBackRight(){ return backRight; }
    public DcMotor getIntake(){ return intake; }
    public DcMotor getElevator(){ return elevator; }
    public DcMotor getDumper(){ return dumper; }

    public void stop() {
        //frontLeft.setPower(0);
        //frontRight.setPower(0);
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
            //frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else
        {
            //frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


    /**
     *
     *
     *
     * @param bL
     * @param bR
     * @param speed
     */

    public void drive(double bL,double bR, double speed) {
        //frontLeft.setPower(fL*speed);
        //frontRight.setPower(fR*speed);
        backLeft.setPower(bL*speed*-1);
        backRight.setPower(bR*speed*-1);
    }

    //public void stoppell(){
    //    impell.setPower(0);
    //}

    public void forward(double speed){
        drive(1, 1, speed);
    }

    public void backward(double speed){
        drive(-1,  -1,speed);
    }

    /*public void left(double speed){
        drive(-1, 1, .9, -.9, speed);
    }*/

    /*public void right(double speed){
       drive(1, -1, -.9, .9, speed);
    }*/

    public void spinleft(){
        drive(-1, 1, 1);
    }

    public void spinright(){
        drive(1, -1, 1);
    }

    public void dumpy(){
        dumper.setPower(.9);
       // dumper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // dumper.setTargetPosition(1);
    }

    public void undumpy(){
        dumper.setPower(-.9);
       // dumper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // dumper.setTargetPosition(3);
    }

    public void dumperStop(){
        dumper.setPower(0);
    }

    public void liftBot(){ //bottom layer
        elevator.setPower(1);
        elevator.setTargetPosition(0);
    }

    public void liftMid(){ //middle layer
        elevator.setPower(1);
        elevator.setTargetPosition(2);
    }

    public void liftTop(){ //top layer
        elevator.setPower(1);
        elevator.setTargetPosition(4);
    }

    public void liftUp(){
        elevator.setPower(.75);
    }

    public void liftDown(){
        elevator.setPower(-.75);
    }

    public void liftStop(){
        elevator.setPower(0);
    }



/*
    public void duckkyturningwheeelthing(){ duckyygoturnturn.setPower(1); }

    public void STOPduckkyturningwheeelthing(){ duckyygoturnturn.setPower(0); }
*/
    public void impelll(){ intake.setPower(1); }

    public void expelll(){ intake.setPower(-1); }

    public void stopelll(){ intake.setPower(0); }

}
