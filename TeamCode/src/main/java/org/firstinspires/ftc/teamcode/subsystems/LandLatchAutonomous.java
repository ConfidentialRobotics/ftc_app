package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class LandLatchAutonomous extends LandLatch {

  public LandLatchAutonomous(LinearOpMode opMode) {
    super(opMode);
    motorLandLatch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorLandLatch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    motorLandLatch.setDirection(DcMotor.Direction.REVERSE);
  }

  public void land() {
    //motorLandLatch.setTargetPosition(5000); // Use if latch is 23" off ground. DO NOT GO HIGHER OR GEARS WILL GRIND.
    motorLandLatch.setTargetPosition(4675); // Use if latch is 22" off ground. REGULATION.
    //motorLandLatch.setTargetPosition(4100); // Use if latch is 21" off ground.
    motorLandLatch.setPower(0.75);
    waitAndReportPosition();
  }

  public void retractLatch() {
    motorLandLatch.setTargetPosition(0);
    motorLandLatch.setPower(0.5);
    // Intentionally not calling waitAndReportPosition().
  }
  
  private void waitAndReportPosition() {
    while (opMode.opModeIsActive() && motorLandLatch.isBusy()) {
      opMode.telemetry.addData("LandLatch position:", motorLandLatch.getCurrentPosition());
      opMode.telemetry.update();
      opMode.idle();
    }
  }
}
