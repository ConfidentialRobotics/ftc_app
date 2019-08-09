package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.MarvinAutonomous;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrainAutonomous;

public class CameraAutonomous {
  private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
  private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
  private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

  private static final String VUFORIA_KEY = "AUDYzlH/////AAABmQRxDJWzpUvumrxi/DH2+k4voNsYxT9ETsLL/fbmT+0YZLQFON19IqTP46zeMmrLlQJXs735lWykGqiMnEG9FHRGv42l4EHAOHxU9WvZIgSBBm4Y69hd29sW59onShQvZxVm4Q5Iv2ONvMAeY/lPX9vNjr8a+YIGjDvvWePxActYTKwSwERQ2SQGtppuiBKpaIere+ZBdTO9aqu2iPI9LAS3OTnBoMJl7+yOpn9xSAdSkCbQ0m5T9LjsdcyBuhU7lOd1m7XPDZgCpVeAM5u6Oh0RpgP+RlZG5g1H6/YollVwVhZu69YTVaPa7YISQMbfq6N040iITF9r+vHSqt98dkbl3TFRr6tKKzR63o67uYKN";

  private VuforiaLocalizer vuforia;
  private TFObjectDetector tfod;
  private DriveTrainAutonomous driveTrain;
  private MarvinAutonomous opMode;
  private ElapsedTime elapsedTime = new ElapsedTime();
  
  public CameraAutonomous(MarvinAutonomous theOpMode, DriveTrainAutonomous theDriveTrain) {
    opMode = theOpMode;
    driveTrain = theDriveTrain;
    initVuforia();
    initTfod();
  }

  public double processMinerals() {
    double sampleTime = 0.0d;
    if (opMode.opModeIsActive()) {
      boolean positionedToDrive = false;
      driveTrain.twistLeft(700, true, 0.5);
      tfod.activate();
      elapsedTime.reset();
      driveTrain.twistRight(1400, false, 0.06);
      while (opMode.opModeIsActive() && elapsedTime.milliseconds() < 13000 && !positionedToDrive) {
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
          opMode.telemetry.addData("Number of updatedRecognitions:", updatedRecognitions.size());
          for (Recognition recognition : updatedRecognitions) {
            opMode.telemetry.addData("Recognition label:", recognition.getLabel());
            opMode.telemetry.addData("Recognition left position:", (int)recognition.getLeft());
            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
              if (recognition.getLeft() > 210 && recognition.getLeft() < 310) {
                driveTrain.stopMotion();
                positionedToDrive = true;
              }
            }
          }
          opMode.telemetry.update();
        }
      }
      sampleTime = elapsedTime.milliseconds();
      if (!positionedToDrive && opMode.opModeIsActive()) {
        driveTrain.twistLeft(700, true, 0.35);
      }
      if (opMode.opModeIsActive()) {
        driveTrain.moveRight(1300, 0.35);
      }
    }
    tfod.shutdown();
    return sampleTime;
  }
  
  private void initVuforia() {
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    parameters.vuforiaLicenseKey = VUFORIA_KEY;
    parameters.cameraDirection = CameraDirection.BACK;
    vuforia = ClassFactory.getInstance().createVuforia(parameters);
  }

  private void initTfod() {
    int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
        "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
  }
}
