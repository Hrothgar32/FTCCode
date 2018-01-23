/*
Copyright 2018 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 */
@TeleOp
/*
This OP mode is for our fifth robot, who has five servos and two drives. It is controlled by a gamepad, and it is capable of grabbing cubes.  
*/
public class MenjPls extends OpMode {
	//Initializing our instance objects: the two servos and DcMotors for the intake gears and arms, and the driving DcMotors.
    private DcMotor leftSide = null;
    private DcMotor rightSide = null;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo left = null;
    private Servo right = null;
    double leftPosition, rightPosition; //The position of the intake-arms' servos.
	double power = 0; //This variable sets the amount of power given to the gears' motors.

    @Override
    public void init() {
		// Getting access to our motors and servos.
        telemetry.addData("Status", "Initialized");
        leftSide = hardwareMap.get(DcMotor.class, "leftSide");
        rightSide = hardwareMap.get(DcMotor.class, "rightSide");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
        leftSide.setDirection(DcMotor.Direction.REVERSE);
        leftPosition = left.getPosition();
        rightPosition = right.getPosition();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        while(gamepad1.right_stick_y != 0){						//This loop gives power to the landing gears' motors through the right stick. 
            leftDrive.setPower(-gamepad1.right_stick_y * 100);	//It moves the robot vertically.
            rightDrive.setPower(gamepad1.right_stick_y * 100);
        }
        while(gamepad1.right_stick_x != 0){
            leftDrive.setPower(gamepad1.right_stick_x * -100); //This loop also gives power to the landing gears' motors.
            rightDrive.setPower(gamepad1.right_stick_x * -100);//It moves the robot horizontally.
        }
        leftDrive.setPower(0); // After the stick moves back to its initial position, the motors' power level is set to 0.
        rightDrive.setPower(0);
        if(gamepad1.left_bumper && power > 0){ //The left bumper takes away power from the intake gears.
            power -= 0.005;
            leftSide.setPower(power);
            rightSide.setPower(power);
        }
        if(gamepad1.right_bumper && power < 1){ //The right bumper gives power to the intake gears.
            power += 0.005;
            leftSide.setPower(power);
            rightSide.setPower(power);
        }
        if(gamepad1.left_trigger != 0 ){ //The left trigger closes the intake arms.
            rightPosition -= (gamepad1.left_trigger / 500);
            leftPosition += (gamepad1.left_trigger / 500);
            right.setPosition(rightPosition);
            left.setPosition(leftPosition);
            
        }
        if(gamepad1.right_trigger != 0 ){ //The right trigger opens the intake arms.
            rightPosition += (gamepad1.right_trigger / 500);
            leftPosition -= (gamepad1.right_trigger / 500);
            right.setPosition(rightPosition);
            left.setPosition(leftPosition);
            
        }
		//Telemetry information.
        telemetry.addData("right",rightPosition);
        telemetry.addData("left",leftPosition);
        telemetry.addData("leftSidePower:", leftSide.getPower());
        telemetry.addData("rightSidePower:", rightSide.getPower());
         telemetry.addData("leftDrive Power:", leftDrive.getPower());
        telemetry.addData("rightDrive Power:", rightDrive.getPower());
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    /*
     * Code to run ONCE after the driver hits STOP
    ;
    telemetry.update();*/
    @Override
    public void stop() {

    }
}
