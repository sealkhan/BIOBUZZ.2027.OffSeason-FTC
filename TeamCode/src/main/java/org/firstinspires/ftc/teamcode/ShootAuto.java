
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// TODO: TEST MOVEMENT TIMES ON FIELD!

@Autonomous(name = "Shoot Auto", group = "Autonomous")

public class ShootAuto extends Hardware {

    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();
        stopAll();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        // STEP 1: Start flywheel
        shooter.setPower(0.55);
        sleep(3000);

        // STEP 2: Start mid spinner
        midSpin.setPower(0.5);

        // Keep both running for 15 seconds
        sleep(15000);

        // STEP 3: Turn off shooting mechanisms
        shooter.setPower(0);
        midSpin.setPower(0);

        sleep(500);

        // STEP 4: Turn around 180 degrees
        rotateBot(0.25);
        sleep(1800); // ESTIMATE - needs testing
        stopMoving();

        sleep(300);

        // STEP 5: Move forward toward hive
        moveY(0.25);
        sleep(1200); // ESTIMATE
        stopMoving();

        sleep(300);

        // STEP 6: Turn left 90 degrees
        rotateBot(0.25);
        sleep(900); // ESTIMATE
        stopMoving();

        // STEP 7: Move forward a little
        moveY(0.25);
        sleep(500); // ESTIMATE
        stopMoving();

        // STEP 8: Turn right 90 degrees
        rotateBot(-0.25);
        sleep(900); // ESTIMATE
        stopMoving();

        // STEP 9: Move forward
        moveY(0.25);
        sleep(1000); // ESTIMATE
        stopMoving();

        // STEP 10: Strafe toward loading zone
        moveX(0.7);
        sleep(800); // ESTIMATE
        stopMoving();

        // STOP EVERYTHING
        stopAll();

        telemetry.addLine("Shoot Auto Complete");
        telemetry.update();
    }
}
