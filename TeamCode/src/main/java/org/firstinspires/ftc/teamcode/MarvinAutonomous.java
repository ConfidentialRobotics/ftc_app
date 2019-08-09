package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BucketArmAutonomous;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrainAutonomous;
import org.firstinspires.ftc.teamcode.subsystems.LandLatchAutonomous;
import org.firstinspires.ftc.teamcode.subsystems.CameraAutonomous;

public abstract class MarvinAutonomous extends LinearOpMode {

  private DriveTrainAutonomous driveTrain;
  private LandLatchAutonomous landLatch;
  private CameraAutonomous camera;
  protected BucketArmAutonomous bucketArm;

  @Override
  public void runOpMode() {
    driveTrain = new DriveTrainAutonomous(this);
    landLatch = new LandLatchAutonomous(this);
    camera = new CameraAutonomous(this, driveTrain);
    bucketArm = new BucketArmAutonomous(this, driveTrain);
    
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    waitForStart();

    bucketArm.moveBucketTop();

    landLatch.land();
   
    if (opModeIsActive()) {
      driveTrain.moveRight(40, 0.35);
    }
    if (opModeIsActive()) {
      driveTrain.moveBackward(300, 0.35);
    }
    if (opModeIsActive()) {
      driveTrain.moveRight(750, 0.35);
    }
    if (opModeIsActive()) {
      driveTrain.moveForward(450, 0.35);
    }
    if (opModeIsActive()) {
      landLatch.retractLatch();
    }
    double sampleTime = 0.0d;
    if (opModeIsActive()) {
      sampleTime = camera.processMinerals();
    }
    claim(sampleTime);
  }
  
  protected abstract void claim(double sampleTime);
}