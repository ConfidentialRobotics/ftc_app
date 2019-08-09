package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "DepotAutonomous")
public class DepotAutonomous extends MarvinAutonomous {
  
  protected void claim(double sampleTime) {
    if (opModeIsActive()) {
      bucketArm.claim(sampleTime);
    }
  }
}