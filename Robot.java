// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 3;
  private static final int kRearLeftChannel = 9;
  private static final int kFrontRightChannel = 12;
  private static final int kRearRightChannel = 14;

  private static final int kJoystickChannel = 0;

  private final MecanumDrive m_robotDrive;
  private final Joystick m_stick;
  
  private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(kFrontLeftChannel);
  private final WPI_VictorSPX rearLeft  = new WPI_VictorSPX(kRearLeftChannel);
  private final WPI_VictorSPX frontRight = new WPI_VictorSPX(kFrontRightChannel);
  private final WPI_VictorSPX rearRight = new WPI_VictorSPX(kRearRightChannel);

  /** Called once at the beginning of the robot program. */
  public Robot() {

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft::set, rearLeft::set, frontRight::set, rearRight::set);

    m_stick = new Joystick(kJoystickChannel);

    SendableRegistry.addChild(m_robotDrive, frontLeft);
    SendableRegistry.addChild(m_robotDrive, rearLeft);
    SendableRegistry.addChild(m_robotDrive, frontRight);
    SendableRegistry.addChild(m_robotDrive, rearRight);
  
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    SmartDashboard.putNumber("frenteEsq", frontLeft.get());
    SmartDashboard.putNumber("frenteDir", frontRight.get());
    SmartDashboard.putNumber("trasEsq", rearLeft.get());
    SmartDashboard.putNumber("trasDir", rearRight.get());
    m_robotDrive.driveCartesian(m_stick.getX(), m_stick.getY(), m_stick.getRawAxis(4));
  }
}