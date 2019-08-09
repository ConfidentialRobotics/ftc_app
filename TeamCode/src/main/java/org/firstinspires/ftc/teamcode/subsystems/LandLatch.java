package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class LandLatch {

  protected LinearOpMode opMode;
  protected DcMotor motorLandLatch;

  protected LandLatch(LinearOpMode theOpMode) {
    opMode = theOpMode;
    motorLandLatch = opMode.hardwareMap.dcMotor.get("motorLandLatch");
  }
}
