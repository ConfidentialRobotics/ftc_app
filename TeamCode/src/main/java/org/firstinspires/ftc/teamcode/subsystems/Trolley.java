package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Trolley {

  protected LinearOpMode opMode;
  protected DcMotor motorTrolley;

  protected Trolley(LinearOpMode theOpMode) {
    opMode = theOpMode;
    motorTrolley = opMode.hardwareMap.dcMotor.get("motorTrolley");
  }
}
