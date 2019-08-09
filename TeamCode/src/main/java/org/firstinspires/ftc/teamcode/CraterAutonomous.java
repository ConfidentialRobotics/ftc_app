package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "CraterAutonomous")
public class CraterAutonomous extends MarvinAutonomous {
  
  protected void claim(double sampleTime) {
    if (opModeIsActive()) {
      bucketArm.park(sampleTime);
    }
  }
}