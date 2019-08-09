package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

public class DriveTrainTeleOp extends DriveTrain {

  public DriveTrainTeleOp(LinearOpMode opMode) {
    super(opMode);
  }

  public void pollGamepads() {
    if (opMode.gamepad1.a) {
      stopMotion();
    } else {
      updateHolonomicMotion();
    }
  }

  private void updateHolonomicMotion() {
    double gamepad1LeftY = -opMode.gamepad1.left_stick_y;
    double gamepad1LeftX = opMode.gamepad1.left_stick_x;
    double gamepad1RightX = opMode.gamepad1.right_stick_x;

    double frontRightPower = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
    double frontLeftPower = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
    double backRightPower = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
    double backLeftPower = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
                
    frontRightPower = Range.clip(frontRightPower, -1, 1);
    frontLeftPower = Range.clip(frontLeftPower, -1, 1);
    backRightPower = Range.clip(backRightPower, -1, 1);
    backLeftPower = Range.clip(backLeftPower, -1, 1);
            
    frontRightPower = scaleInput(frontRightPower);
    frontLeftPower = scaleInput(frontLeftPower);
    backRightPower = scaleInput(backRightPower);
    backLeftPower = scaleInput(backLeftPower);

    motorFrontRight.setPower(frontRightPower);
    motorFrontLeft.setPower(frontLeftPower);
    motorBackRight.setPower(backRightPower);
    motorBackLeft.setPower(backLeftPower);
  }

  /*
   * This method scales the joystick input so for low joystick values, the
   * scaled value is less than linear.  This is to make it easier to drive
   * the robot more precisely at slower speeds.
   */
  private double scaleInput(double dVal)  {
    //double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
    //      0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
    double[] scaleArray = { 0.0, 0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.14,
          0.17, 0.19, 0.22, 0.25, 0.28, 0.30, 0.33, 0.50, 0.75 };

    // get the corresponding index for the scaleInput array.
    int index = (int) (dVal * 16.0);

    // index should be positive.
    if (index < 0) {
      index = -index;
    }

    // index cannot exceed size of array minus 1.
    if (index > 16) {
      index = 16;
    }

    // get value from the array.
    double dScale = 0.0;
    if (dVal < 0) {
      dScale = -scaleArray[index];
    } else {
      dScale = scaleArray[index];
    }

    // return scaled value.
    return dScale;
  }
}
