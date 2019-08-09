package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.DepotAutonomous;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrainAutonomous;

public class BucketArmAutonomous extends BucketArm {

  private DriveTrainAutonomous driveTrain;
  
  public BucketArmAutonomous(LinearOpMode theOpMode, DriveTrainAutonomous theDriveTrain) {
    super(theOpMode);
    driveTrain = theDriveTrain;
    motorBucket.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorBucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }
  
  public void claim(double sampleTime) {
    opMode.telemetry.addData("Sample Time", sampleTime);
    opMode.telemetry.update();
    if (sampleTime >= 0.0 && sampleTime <= 3800.0) {
      driveTrain.twistLeft(2650, true, 1.0);
      dropMarker(2200);
    } else if (sampleTime >3800.0 && sampleTime <= 6800.0) {
      driveTrain.twistRight(1300, true, 1.0);
      dropMarker(1500);
    } else {
      driveTrain.twistRight(400, true, 1.0);
      dropMarker(2400);
    }
  }
  
  public void park(double sampleTime) {
    opMode.telemetry.addData("Sample Time", sampleTime);
    opMode.telemetry.update();
    if (sampleTime >= 0.0 && sampleTime <= 3800.0) {
      driveTrain.twistLeft(2750, true, 1.0);
      crossPlane();
    } else if (sampleTime >3800.0 && sampleTime <= 6800.0) {
      driveTrain.twistLeft(3350, true, 0.5);
      crossPlane();
    } else {
      driveTrain.moveRight(225, 0.5);
      driveTrain.twistRight(600, true, 1.0);
      crossPlane();
    }
  }
 
  private void dropMarker(int moveForwardTicks) {
    driveTrain.moveForward(moveForwardTicks, 1.0);
    motorBucket.setDirection(DcMotor.Direction.FORWARD);
    motorBucket.setTargetPosition(300);
    motorBucket.setPower(1.0);
    while (opMode.opModeIsActive() && motorBucket.isBusy()) {
      opMode.idle();
    }
    driveTrain.moveBackward(800, 1.0);
  }
  
  private void crossPlane() {
    motorBucket.setDirection(DcMotor.Direction.FORWARD);
    motorBucket.setTargetPosition(250);
    motorBucket.setPower(0.5);
    while (opMode.opModeIsActive() && motorBucket.isBusy()) {
      opMode.idle();
    }
    moveBucketNeutral();
    while (opMode.opModeIsActive()) {
      opMode.idle();
    }
  }
}
      
      //moveBucketBottom();
      /*ElapsedTime servoMovingTime = new ElapsedTime();
      servoMovingTime.reset();
      while (opMode.opModeIsActive() && servoMovingTime.milliseconds() < 2000) {
        opMode.idle();
      }
      driveTrain.moveForward(400, 1.0);
      driveTrain.moveBackward(400, 1.0);
      moveBucketNeutral();
      servoMovingTime.reset();
      while (opMode.opModeIsActive() && servoMovingTime.milliseconds() < 2000) {
        opMode.idle();
      }*/