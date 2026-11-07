
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "Field Oriented Driving")
public class FieldOrientedDriving extends Hardware {

    @Override
    public void runOpMode() {

        initHardware();

        // Driving mode
        boolean fieldOriented = true;
        boolean previousDriveToggle = false;

        // Mechanism toggle states
        boolean intakeOn = false;
        boolean midSpinOn = false;
        boolean rightFlowerOn = false;
        boolean leftFlowerOn = false;
        boolean shooterOn = false;

        // Previous button states
        boolean previousA = false;
        boolean previousB = false;
        boolean previousX = false;
        boolean previousY = false;
        boolean previousRB = false;

        telemetry.addLine("Hardware initialized");
        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        // Reset heading at start
        imu.resetYaw();

        while (opModeIsActive()) {

            // =================================
            // GAMEPAD 1 - DRIVETRAIN
            // =================================

            // Toggle field-oriented / robot-oriented
            boolean driveToggle = gamepad1.x;

            if (driveToggle && !previousDriveToggle) {
                fieldOriented = !fieldOriented;
            }

            previousDriveToggle = driveToggle;

            // Speed control
            double speedModifier;

            if (gamepad1.left_bumper) {
                speedModifier = 0.125; // Slow
            } else if (gamepad1.right_bumper) {
                speedModifier = 0.75; // Fast
            } else {
                speedModifier = 1.0 / 3.0; // Normal
            }

            // Joystick inputs
            double y = -gamepad1.right_stick_y * speedModifier;
            double x = gamepad1.right_stick_x * speedModifier;
            double rotation = gamepad1.left_stick_x * speedModifier;

            // IMU heading
            YawPitchRollAngles orientation =
                    imu.getRobotYawPitchRollAngles();

            double yaw = orientation.getYaw(AngleUnit.RADIANS);

            // Field-oriented transformation
            double rotX = x;
            double rotY = y;

            if (fieldOriented) {
                rotX = x * Math.cos(-yaw) - y * Math.sin(-yaw);
                rotY = x * Math.sin(-yaw) + y * Math.cos(-yaw);
            }

            // Mecanum wheel calculations
            double frontLeftPower =
                    rotY + rotX + rotation;

            double backLeftPower =
                    rotY - rotX + rotation;

            double frontRightPower =
                    rotY - rotX - rotation;

            double backRightPower =
                    rotY + rotX - rotation;

            // Normalize motor powers
            double denominator = Math.max(
                    Math.max(Math.abs(frontLeftPower),
                            Math.abs(backLeftPower)),
                    Math.max(Math.abs(frontRightPower),
                            Math.abs(backRightPower))
            );

            denominator = Math.max(denominator, 1.0);

            frontLeft.setPower(frontLeftPower / denominator);
            backLeft.setPower(backLeftPower / denominator);
            frontRight.setPower(frontRightPower / denominator);
            backRight.setPower(backRightPower / denominator);


            // =================================
            // GAMEPAD 2 - MECHANISMS
            // =================================

            // A - Intake motor
            if (gamepad2.a && !previousA) {
                intakeOn = !intakeOn;
            }
            previousA = gamepad2.a;

            intake.setPower(intakeOn ? 1.0 : 0.0);


            // B - Mid spinner CRServo
            if (gamepad2.b && !previousB) {
                midSpinOn = !midSpinOn;
            }
            previousB = gamepad2.b;

            midSpin.setPower(midSpinOn ? 0.7 : 0.0);


            // X - Right flower CRServo
            if (gamepad2.x && !previousX) {
                rightFlowerOn = !rightFlowerOn;
            }
            previousX = gamepad2.x;

            rightFlower.setPower(rightFlowerOn ? 1.0 : 0.0);


            // Y - Left flower CRServo
            if (gamepad2.y && !previousY) {
                leftFlowerOn = !leftFlowerOn;
            }
            previousY = gamepad2.y;

            leftFlower.setPower(leftFlowerOn ? 1.0 : 0.0);


            // Right bumper - Shooter motor
            if (gamepad2.right_bumper && !previousRB) {
                shooterOn = !shooterOn;
            }
            previousRB = gamepad2.right_bumper;

            shooter.setPower(shooterOn ? 0.6 : 0.0);


            // =================================
            // TELEMETRY
            // =================================

            telemetry.addData("Driving Mode",
                    fieldOriented ? "Field Oriented" : "Robot Oriented");

            telemetry.addData("Heading", yaw);

            telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
            telemetry.addData("Mid Spinner", midSpinOn ? "ON" : "OFF");
            telemetry.addData("Right Flower", rightFlowerOn ? "ON" : "OFF");
            telemetry.addData("Left Flower", leftFlowerOn ? "ON" : "OFF");
            telemetry.addData("Shooter", shooterOn ? "ON" : "OFF");

            telemetry.update();
        }

        // Stop everything when OpMode ends
        stopMoving();
        stopMechanisms();
    }
}
