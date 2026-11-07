
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Straight Auto", group = "Autonomous")
public class StraightAuto extends Hardware {

    // Estimated movement settings
    private static final double DRIVE_POWER = 0.25;
    private static final int MOVE_TIME = 1000;

    @Override
    public void runOpMode() {

        initHardware();
        stopAll();

        telemetry.addLine("Straight Auto Ready");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        // Move forward a little away from the wall
        moveY(DRIVE_POWER);
        sleep(MOVE_TIME);

        // Stop
        stopAll();

        telemetry.addLine("Straight Auto Complete");
        telemetry.update();
    }
}
