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
    private DcMotor ducky;
    private DcMotor dumper;

    private ConceptVuforiaFieldNavigationWebcam phone;

    private double ninety;

    public void init(HardwareMap map) {
        // Initialize & configure drive motors
        //frontLeft = map.dcMotor.get("frontLeft");
        //frontRight = map.dcMotor.get("frontRight");
        backLeft = map.dcMotor.get("backLeft");
        backRight = map.dcMotor.get("backRight");
        intake = map.dcMotor.get("intake");
        elevator = map.dcMotor.get("elevator");
        ducky = map.dcMotor.get("duck");
        dumper = map.dcMotor.get("dumper");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        phone= new ConceptVuforiaFieldNavigationWebcam();

    }

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
        backLeft.setPower(bL*speed);
        backRight.setPower(bR*speed);
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
        dumper.setPower(-.9);
       // dumper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // dumper.setTargetPosition(1);
    }

    public void undumpy(){
        dumper.setPower(.9);
        //dumper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // dumper.setTargetPosition(3);
    }

    public void Dump(){
        dumper.setPower(1);
        dumper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         dumper.setTargetPosition(5057);
    }

    public void dumperStop(){
        dumper.setPower(0);
    }

    public void liftBot(){ //bottom layer
        elevator.setPower(1);
        elevator.setTargetPosition(1584);
    }

    public void liftMid(){ //middle layer
        elevator.setPower(1);
        elevator.setTargetPosition(4822);
    }

    public void liftTop(){ //top layer
        elevator.setPower(1);
        elevator.setTargetPosition(5378);
    }

    public void liftUp(){
        elevator.setPower(1);
    }

    public void liftDown(){
        elevator.setPower(-.75);
    }

    public void liftStop(){
        elevator.setPower(0);
    }


    public void clockwiseDuckyTurn(){ ducky.setPower(1); }

    public void counterClockwiseDuckyTurn(){ducky.setPower(-1);}

    public void StopDuckyTurn(){ ducky.setPower(0); }

    public void impelll(){ intake.setPower(1); }

    public void expelll(){ intake.setPower(-1); }

    public void stopelll(){ intake.setPower(0); }

    public double getNinety(){
        ninety=1.19;
        return ninety;
    }
}
