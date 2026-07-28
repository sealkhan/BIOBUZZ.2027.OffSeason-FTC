package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "SampleAuto", group = "LinearOpMode")
public class SampleAuto extends Hardware {
    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();
        stopMoving();

        // CRITICAL: Set shooter hand to UP/BLOCKING position before flywheel starts
        shooterHand.setPosition(0.0);  // UP position (blocks balls)
        sleep(200);                    // Let servo settle

        waitForStart();

        // Now start flywheel warmup
        double shooterVelocity = -1300;
        shooterWheel.setVelocity(shooterVelocity);
        sleep(1500);  // Flywheel spins up to stable speed

        // NOW safe to open gate for first ball - function starts with hand UP
        shootThreeBallsGateStyle();

        // Turn off flywheel when done
        shooterWheel.setVelocity(0);
        shooterHand.setPosition(0.0);  // UP position (blocks balls)
    }
}


