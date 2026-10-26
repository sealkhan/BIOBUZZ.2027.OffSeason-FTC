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

        // stop moving
    public void stopMoving() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    // We can have the go backwards be negative power
    // forward -> use positive power
    // backward -> use negative power
    public void moveY(double power) {
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
    }

    // its the same thing as moveY. Positive power is going to be right and negative is left
    // right -> positive power
    // left -> negative power
    public void moveX(double power) {
        frontRight.setPower(-power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(-power);
    }
     public void goPark(){


        //move back
        moveY(.2);
        sleep(500);

        //move to side of field with parking
        moveX(-0.5);
        sleep(4500);

        //park
        moveY(.3);
        sleep(2000);

//        moveY(-0.2);
//        sleep(300);
    }

    public void rotateBot(int sleep, boolean clockwise){
        int clockwiseNumber;
        if (clockwise == true){
            clockwiseNumber = 1;
        } else {
            clockwiseNumber = -1;
        }
        frontLeft.setPower(0.5 * clockwiseNumber);
        backLeft.setPower(0.5 * clockwiseNumber);
        frontRight.setPower(-0.5 * clockwiseNumber);
        backRight.setPower(-0.5 * clockwiseNumber);
        sleep(sleep);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

    }

        
        
    
    
    
