package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class BucketArm {

  protected LinearOpMode opMode;

  protected DcMotor motorBucket;
  protected Servo   servoBucket;

  protected BucketArm(LinearOpMode theOpMode) {
    opMode = theOpMode;

    motorBucket = opMode.hardwareMap.dcMotor.get("motorBucket");
    motorBucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    servoBucket = opMode.hardwareMap.servo.get("servoBucket");
  }

  public void moveBucketTop() {
    servoBucket.setPosition(0.75);
  }

  public void moveBucketAlmostTop() {
    servoBucket.setPosition(0.65);
  }

  public void moveBucketNeutral() {
    servoBucket.setPosition(0.2);
  }

  public void moveBucketBottom() {
    servoBucket.setPosition(0.0);
  }
}
