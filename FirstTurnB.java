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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp
/*
This class is the intellectual property of team RoboCorp SRL. Any use without the permission of the team may lead
to various lawsuits.
This class tests our 0.2 Beta robots manouvering abilities, namely turning. After hitting START, the robot will start to 
turn around its horizontal axes.
*/
public class FirstTurnB extends OpMode {
    /* Declare OpMode members. */
    private DcMotor topLeftDrive = null;
    private DcMotor bottomLeftDrive = null;
    private DcMotor topRightDrive = null;
    private DcMotor bottomRightDrive = null;
    private double mainPower;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        topLeftDrive = hardwareMap.get(DcMotor.class,"topLeftDrive");
        bottomLeftDrive = hardwareMap.get(DcMotor.class,"bottomLeftDrive");
        topRightDrive = hardwareMap.get(DcMotor.class,"topRightDrive");
        bottomRightDrive = hardwareMap.get(DcMotor.class,"bottomRightDrive");
        topRightDrive.setDirection(DcMotor.Direction.FORWARD);
        topLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        bottomRightDrive.setDirection(DcMotor.Direction.FORWARD);
        bottomLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        
        
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
       if(mainPower != 1){
             mainPower += 0.2;
        topLeftDrive.setPower(mainPower);
        topRightDrive.setPower(mainPower);
        bottomLeftDrive.setPower(mainPower);
        bottomRightDrive.setPower(mainPower);
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
       telemetry.addData("topLeftDriveSpeed: ",topLeftDrive.getPower() );
        telemetry.addData("bottomLeftDriveSpeed",bottomLeftDrive.getPower());
        telemetry.addData("topRightDriveSpeed",topRightDrive.getPower() );
        telemetry.addData("bottomRightDriveSpeed",bottomRightDrive.getPower());
}

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}
