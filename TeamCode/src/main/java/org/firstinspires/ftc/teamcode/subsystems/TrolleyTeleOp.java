package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TrolleyTeleOp extends Trolley {

  public TrolleyTeleOp(LinearOpMode opMode) {
    super(opMode);
  }

  public void pollGamepads() {
    double trolleyPower = -opMode.gamepad2.left_stick_y;
    if (trolleyPower > 0.5) {
      trolleyPower = 0.75;
    } else if (trolleyPower < -0.5) {
      trolleyPower = -0.5;
    }
    motorTrolley.setPower(trolleyPower);
  }
}
