package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.robotcore.util.ElapsedTime;


public abstract class Hardware extends LinearOpMode {
    BHI260IMU imu;
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;
    DcMotorEx shooter;
    DcMotorEx intake;
    Servo rightFlower;
    Servo leftFlower;
    Servo midSpin;
    
    

    public void initHardware() {
        
        frontLeft = (DcMotorEx) hardwareMap.dcMotor.get("frontLeft");
        frontRight = (DcMotorEx) hardwareMap.dcMotor.get("frontRight");
        backLeft = (DcMotorEx) hardwareMap.dcMotor.get("backLeft");
        backRight = (DcMotorEx) hardwareMap.dcMotor.get("backRight");
        shooter = (DcMotorEx) hardwareMap.dcMotor.get("shooter");
        intake = (DcMotorEx) hardwareMap.dcMotor.get("intake");
        rightFlower = hardwareMap.get(Servo.class, "rightFlower");
        leftFlower = hardwareMap.get(Servo.class, "leftFlower");
        midSpin = hardwareMap.get(Servo.class, "midSpin"); 


        waitForStart();

    }
}
