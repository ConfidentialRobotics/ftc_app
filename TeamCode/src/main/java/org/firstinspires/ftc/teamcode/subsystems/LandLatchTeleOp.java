package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class LandLatchTeleOp extends LandLatch {

  public LandLatchTeleOp(LinearOpMode opMode) {
    super(opMode);
  }

  public void pollGamepads() {
    if (opMode.gamepad1.a) {
      motorLandLatch.setPower(opMode.gamepad1.left_stick_y);
    } else {
      motorLandLatch.setPower(0);
    }
  }
}
