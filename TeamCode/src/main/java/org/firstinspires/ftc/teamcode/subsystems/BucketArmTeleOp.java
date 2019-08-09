package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class BucketArmTeleOp extends BucketArm {

  public BucketArmTeleOp(LinearOpMode opMode) {
    super(opMode);
  }

  public void pollGamepads() {
    double bucketPower = -opMode.gamepad2.right_stick_y;
    if (bucketPower > 0.1) {
      motorBucket.setPower(0.5);
    } else if (bucketPower < -0.1) {
      motorBucket.setPower(-0.25);
    } else {
      motorBucket.setPower(0.0);
    }
  
    if (opMode.gamepad2.y) {
      moveBucketTop();
    } else if (opMode.gamepad2.b) {
      moveBucketNeutral();
    } else if (opMode.gamepad2.a) {
      moveBucketBottom();
    }
  }
}
