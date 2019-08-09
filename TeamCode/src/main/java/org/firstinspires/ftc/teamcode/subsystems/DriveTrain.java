package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class DriveTrain {

  protected LinearOpMode opMode;

  protected DcMotor motorFrontRight;
  protected DcMotor motorFrontLeft;
  protected DcMotor motorBackRight;
  protected DcMotor motorBackLeft;

  protected DriveTrain(LinearOpMode theOpMode) {
    opMode = theOpMode;

    motorFrontRight = opMode.hardwareMap.dcMotor.get("motorFrontRight");
    motorFrontLeft = opMode.hardwareMap.dcMotor.get("motorFrontLeft");
    motorBackRight = opMode.hardwareMap.dcMotor.get("motorBackRight");
    motorBackLeft = opMode.hardwareMap.dcMotor.get("motorBackLeft");
  }

  public void stopMotion() {
    motorFrontRight.setPower(0);
    motorFrontLeft.setPower(0);
    motorBackRight.setPower(0);
    motorBackLeft.setPower(0);
  }
}
