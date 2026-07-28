package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name = "Straight", group = "LinearOpMode")
public class Straight extends Hardware {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        stopMoving();

        waitForStart(); // Ensure OpMode only runs after "Init" and "Start"

        moveY(0.3);
        sleep(1500);

    }
}

