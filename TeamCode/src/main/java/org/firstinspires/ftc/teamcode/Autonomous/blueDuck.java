package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.ChadBot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

@Autonomous(name = "blueDuck")
public class blueDuck extends LinearOpMode {

    ElapsedTime timer;
    private ChadBot robot;
    private double speed;
    private int state;

    private static final String VUFORIA_KEY =
            "AYef6RP/////AAABmQhqgETT3Uq8mNFqAbjPOD990o1n/Osn3oBdTsKI0NXgPuXS612xYfN5Q65srnoMx2" +
                    "eKXe32WnMf6M2BSJSgoPfTZmkmujVujpE/hUrmy5p4L7CALtVoM+TDkfshpKd+LGJT834pEOYU" +
                    "qcUj+vySs3OZQNepaSflmiShfHRNVbrgjrEs1Erlg7zZzc6EQo+yvh0fFtUiQUPLCCcZEPyfnU" +
                    "4k0o8phhbR+Ca9B6dtoeNaYITGHvMmOkBLsyAnR/RQ4Xv8KpvSaSfk0PDyzCG7UsN49k055xOx" +
                    "kFI0iKYp7NMCDF+cezE80dkcnpZCzg1RpGuSpCKGuUbSkJp+q5qudl2qZfWnQntaNI0vlNKD2x1C";

    public static final String TAG = "Vuforia VuMark Sample";

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }

    @Override
    public void init() {
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
            //can change mag later to find seomthing better- if want to test
            //dont change ratio
        }


        timer = new ElapsedTime();
        robot = new ChadBot();
        robot.init(hardwareMap);
        speed = .5;
        state = 0;
    }

    public void next() {
        state++;
        timer.reset();
        robot.stop();
    }

    @Override
    public void loop() {
        switch(state)
        {

            case 0:
                    if(tfod != null){
                        List<Recognition> updatedRecognitions = TFObjectDetector.getUpdatedRecognitions();
                        if(updatedRecognitions != null) {
                            if (updatedRecognitions.getleft() < 240) {//left postion
                                next();
                            }
                            else if(updatedRecognitions.getright() >= 240){//right position
                                state = 200;
                                next();
                            }
                            else //middle position
                                state = 100;
                                next();
                            }
                        }
                }
                break;
*/
                //starts of left movements
            case 1: // square A
                robot.left(speed);
                if(timer.seconds()>1)
                    next();
                break;

            case 2:

                robot.forward(speed);
                if(timer.seconds()>1)
                    next();
                break;

            case 3:
                // drop the thing in box using servo
                robot.stop();
                if(timer.seconds()>3)
                    next();
                break;



                // start of middle movemnets
            case 100://square c
                robot.foward(speed);
                if(timer.seconds()>1)
                    next();
                break;

            case 101:
                robot.leftspin(speed);
                if(timer.seconds()>1)
                    next();
                break;

            case 102:
                //park on white tape
                robot.stop(speed);
                if(timer.seconds()>1.8)
                    next();
                break;




                //start of right movements
            case 200://square b
                robot.right(speed);
                if(timer.seconds()>1)
                next();
                break;

            case 201:
                // drop the thing in box using servo
                robot.foward(speed);
                if(timer.seconds()>1)
                    next();
                break;

                case 202:
               //park on white tape
                robot.stop(speed);
                if(timer.seconds()>1.8)
                    next();
                 break;
        }
    }
}
