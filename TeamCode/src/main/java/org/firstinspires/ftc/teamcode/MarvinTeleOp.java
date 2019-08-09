package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.BucketArmTeleOp;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.subsystems.LandLatchTeleOp;
import org.firstinspires.ftc.teamcode.subsystems.TrolleyTeleOp;

@TeleOp(name = "MarvinTeleOp")
public class MarvinTeleOp extends LinearOpMode {

  private DriveTrainTeleOp driveTrain;
  private LandLatchTeleOp landLatch;
  private TrolleyTeleOp trolley;
  private BucketArmTeleOp bucketArm;

  @Override
  public void runOpMode() {
    driveTrain = new DriveTrainTeleOp(this);
    landLatch = new LandLatchTeleOp(this);
    trolley = new TrolleyTeleOp(this);
    bucketArm = new BucketArmTeleOp(this);
    
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    // Wait for the game to start (driver presses PLAY)
    waitForStart();
       
    while (opModeIsActive()) {
      driveTrain.pollGamepads();
      landLatch.pollGamepads();
      trolley.pollGamepads();
      bucketArm.pollGamepads();
      // TODO Add meaningful output to telemetry in all subsystems.
    }
  }
}
