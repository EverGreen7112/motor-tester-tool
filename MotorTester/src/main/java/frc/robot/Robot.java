// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  public enum Motors {
    SparkMax,
    TalonSRX,
    TalonFX,
    VictorSPX;
  }

  private final SendableChooser<Motors> m_chooser = new SendableChooser<>();
  private CANSparkMax spark;
  private TalonSRX talonSRX;
  private TalonFX talonFX;
  private VictorSPX victorSPX;
  double prevMotorID;
  double motorSpeed;

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Motor ID", -1);
    SmartDashboard.putNumber("Motor Speed", 0);
    m_chooser.addOption("SparkMax", Motors.SparkMax);
    m_chooser.addOption("TalonSRX", Motors.TalonSRX);
    m_chooser.addOption("TalonFX", Motors.TalonFX);
    m_chooser.addOption("VictorSPX", Motors.VictorSPX);
    SmartDashboard.putData(m_chooser);
    prevMotorID = 0;
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void disabledExit() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
    motorSpeed = 0;
    int motorID = (int) SmartDashboard.getNumber("Motor ID", -1);
    if (motorID != -1) {
      switch (m_chooser.getSelected()) {
        case SparkMax:
          if (spark == null || prevMotorID != motorID)
            spark = new CANSparkMax(motorID, MotorType.kBrushless);
          break;
        case TalonSRX:
          if (talonSRX == null || prevMotorID != motorID)
            talonSRX = new TalonSRX(motorID);
          break;
        case TalonFX:
          if (talonFX == null || prevMotorID != motorID)
            talonFX = new TalonFX(motorID);
          break;
        case VictorSPX:
          if (victorSPX == null || prevMotorID != motorID)
            victorSPX = new VictorSPX(motorID);
          break;
        default:
          break;
      }
    }
    prevMotorID = motorID;
  }

  @Override
  public void teleopPeriodic() {
    motorSpeed = MathUtil.clamp(SmartDashboard.getNumber("Motor Speed", 0), -1, 1);
    if (spark != null)
      spark.set(motorSpeed);
    if (talonSRX != null)
      talonSRX.set(TalonSRXControlMode.PercentOutput, motorSpeed);
    if (talonFX != null)
      talonFX.set(TalonFXControlMode.PercentOutput, motorSpeed);
    if (victorSPX != null)
      victorSPX.set(VictorSPXControlMode.PercentOutput, motorSpeed);
  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }
}
