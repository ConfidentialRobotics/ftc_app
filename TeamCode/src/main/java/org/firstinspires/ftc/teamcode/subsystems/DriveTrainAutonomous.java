package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DriveTrainAutonomous extends DriveTrain {

  public DriveTrainAutonomous(LinearOpMode opMode) {
    super(opMode);
    resetEncoders();
  }

  public void moveForward(int encoderTicks, double power) {
    resetEncoders();

    motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
    motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
    motorBackRight.setDirection(DcMotor.Direction.FORWARD);
    motorFrontRight.setDirection(DcMotor.Direction.FORWARD);

    move(encoderTicks, true, power);
  }

  public void moveBackward(int encoderTicks, double power) {
    resetEncoders();

    motorBackRight.setDirection(DcMotor.Direction.REVERSE);
    motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
    motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);

    move(encoderTicks, true, power);
  }

  public void moveLeft(int encoderTicks, double power) {
    resetEncoders();

    motorBackRight.setDirection(DcMotor.Direction.REVERSE);
    motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
    motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
    motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);

    move(encoderTicks, true, power);
  }

  public void moveRight(int encoderTicks, double power) {
    resetEncoders();

    motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
    motorBackRight.setDirection(DcMotor.Direction.FORWARD);
    motorBackLeft.setDirection(DcMotor.Direction.FORWARD);

    move(encoderTicks, true, power);
  }

  public void twistRight(int encoderTicks, boolean waitToFinish, double power) {
    resetEncoders();

    motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
    motorBackRight.setDirection(DcMotor.Direction.REVERSE);
    motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

    move(encoderTicks, waitToFinish, power);
  }

  public void twistLeft(int encoderTicks, boolean waitToFinish, double power) {
    resetEncoders();

    motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
    motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
    motorBackRight.setDirection(DcMotor.Direction.FORWARD);
    motorBackLeft.setDirection(DcMotor.Direction.FORWARD);

    move(encoderTicks, waitToFinish, power);
  }

  private void resetEncoders() {
    motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);    
  }

  private void move(int encoderTicks, boolean waitToFinish, double power) {
    motorBackRight.setTargetPosition(encoderTicks);
    motorBackLeft.setTargetPosition(encoderTicks);
    motorFrontRight.setTargetPosition(encoderTicks);
    motorFrontLeft.setTargetPosition(encoderTicks);

    motorBackRight.setPower(power);
    motorBackLeft.setPower(power);
    motorFrontRight.setPower(power);
    motorFrontLeft.setPower(power);

    if (waitToFinish) {
      waitAndReportPosition();
    }
  }

  private void waitAndReportPosition() {
    while (opMode.opModeIsActive() && motorsAreBusy()) {
      //opMode.telemetry.addData("Front Right Motor position:", motorFrontRight.getCurrentPosition());
      //opMode.telemetry.update();
      opMode.idle();
    }
  }

  private boolean motorsAreBusy() {
    return motorFrontRight.isBusy() ||
           motorFrontLeft.isBusy() ||
           motorBackRight.isBusy() ||
           motorBackLeft.isBusy();
  }
}
