package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.ChadBot;

import java.util.List;

@Autonomous(name = "blueBlock")
public class blueBlock extends OpMode {

    ElapsedTime timer;
    private ChadBot robot;
    private double speed;
    private int state;
    private int inttterState;
    private int encoder = -1;

    private static final String VUFORIA_KEY =
            "AYef6RP/////AAABmQhqgETT3Uq8mNFqAbjPOD990o1n/Osn3oBdTsKI0NXgPuXS612xYfN5Q65srnoMx2" +
                    "eKXe32WnMf6M2BSJSgoPfTZmkmujVujpE/hUrmy5p4L7CALtVoM+TDkfshpKd+LGJT834pEOYU" +
                    "qcUj+vySs3OZQNepaSflmiShfHRNVbrgjrEs1Erlg7zZzc6EQo+yvh0fFtUiQUPLCCcZEPyfnU" +
                    "4k0o8phhbR+Ca9B6dtoeNaYITGHvMmOkBLsyAnR/RQ4Xv8KpvSaSfk0PDyzCG7UsN49k055xOx" +
                    "kFI0iKYp7NMCDF+cezE80dkcnpZCzg1RpGuSpCKGuUbSkJp+q5qudl2qZfWnQntaNI0vlNKD2x1C";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker",
    };
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;
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
            tfod.setZoom(1.25, 8.0 / 4.5);//change
            //can change mag later to find seomthing better- if want to test
            //dont change ratio

            timer = new ElapsedTime();
            robot = new ChadBot();
            robot.init(hardwareMap);
            speed = .5;
            state = 0;
            robot.getBackLeft().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.getBackRight().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

    public void encoder(int left, int right){
        robot.getBackLeft().setTargetPosition(left*encoder);
        robot.getBackRight().setTargetPosition(right*encoder);
        robot.getBackLeft().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.getBackRight().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.getBackRight().setPower(speed);
        robot.getBackLeft().setPower(speed);
    }

    @Override
    public void loop() {
        switch (state) {

            case 0:
                telemetry.addData(String.format("State (%d)", state), state);
                robot.forward(1);
                if (timer.seconds() > 1)
                    next();
                break;

            case 1:
                robot.stop();
                if (timer.seconds() > .5)
                    next();
                break;

            case 2:
                robot.spinleft();
                if (timer.seconds() > .8)
                    next();
                break;

            case 3:
                robot.backward(speed);
                if (timer.seconds() > .3)
                    next();
                break;

            case 4:
                robot.liftUp();
                if (timer.seconds() > 4.5)
                    next();
                break;

            case 5:
                robot.dumpy();
                if (timer.seconds() > 3)
                    next();
                break;

            case 6:
                robot.undumpy();
                if (timer.seconds() > 3)
                    next();
                break;

            case 7:
                robot.dumperStop();
                robot.forward(speed);
                if (timer.seconds() > .2)
                    next();
                break;

            case 8:
                robot.spinleft();
                if (timer.seconds() > robot.getNinety()/2)
                    next();
                break;

            case 9:
                robot.forward(speed);
                if (timer.seconds() > 6)
                    next();
                break;
        }
    }
}
