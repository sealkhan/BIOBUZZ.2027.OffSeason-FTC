
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// TODO: TEST MOVEMENT TIMES ON FIELD!

@Autonomous(name = "Move and Park", group = "Autonomous")

public class MoveAndParkAuto extends Hardware {

    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();
        stopAll();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        // STEP 1: Move forward a little
        moveY(0.25);
        sleep(700);
        stopMoving();

        sleep(300);

        // STEP 2: Slide sideways toward parking area
        moveX(-0.7);
        sleep(2500); // ESTIMATE - adjust after field testing
        stopMoving();

        sleep(300);

        moveY(0.1);
        sleep(700);
        stopMoving();

        // STEP 3: Park
        stopAll();

        telemetry.addLine("Move and Park Complete");
        telemetry.update();
    }
}
