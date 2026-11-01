
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;

public abstract class Hardware extends LinearOpMode {

    // IMU
    IMU imu;

    // Drivetrain
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    // Mechanisms
    DcMotorEx intake;
    DcMotorEx shooter;

    // Continuous rotation servos
    CRServo rightFlower;
    CRServo leftFlower;
    CRServo midSpin;


    // Initialize hardware
    public void initHardware() {

        // Drivetrain
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        // Mechanisms
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");

        // Servos
        rightFlower = hardwareMap.get(CRServo.class, "rightFlower");
        leftFlower = hardwareMap.get(CRServo.class, "leftFlower");
        midSpin = hardwareMap.get(CRServo.class, "midSpin");

        // Drivetrain braking
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Drivetrain run modes
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Mechanism run modes
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // IMU initialization will be added after confirming
        // the exact Control Hub mounting orientation.
        imu = hardwareMap.get(IMU.class, "imu");

    }


    // Stop drivetrain
    public void stopMoving() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }


    // Forward and backward
    public void moveY(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }


    // Strafing
    public void moveX(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }


    // Rotate robot
    public void rotateBot(int duration, boolean clockwise) {

        double power = clockwise ? 0.5 : -0.5;

        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        backRight.setPower(-power);

        sleep(duration);

        stopMoving();
    }


    // Stop all mechanisms
    public void stopMechanisms() {
        intake.setPower(0);
        shooter.setPower(0);

        rightFlower.setPower(0);
        leftFlower.setPower(0);
        midSpin.setPower(0);
    }

}
