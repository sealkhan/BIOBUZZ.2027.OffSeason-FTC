package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//TODO: TEST THIS!


@Autonomous(name = "RedAuto", group = "LinearOpMode")
//Declares as autonomous file, SDK thing
public class RedAuto extends Hardware {
    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();
        stopMoving();

        waitForStart();

    }
}
