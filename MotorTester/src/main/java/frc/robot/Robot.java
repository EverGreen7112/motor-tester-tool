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
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  public enum Motors{
    SparkMax,
    TalonSRX,
    TalonFX,
    VictorSPX;
  }

  private final SendableChooser<Motors> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Motor ID", -1);
    SmartDashboard.putNumber("Motor Speed", 0);
    m_chooser.addOption("SparkMax", Motors.SparkMax);
    m_chooser.addOption("TalonSRX", Motors.TalonSRX);
    m_chooser.addOption("TalonFX", Motors.TalonFX);
    m_chooser.addOption("VictorSPX", Motors.VictorSPX);
    SmartDashboard.putData(m_chooser);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    int motorID = (int)SmartDashboard.getNumber("Motor ID", -1);
    double motorSpeed = Math.signum(SmartDashboard.getNumber("Motor Speed", 0));
    if(motorID != -1 && motorSpeed != 0){
      switch(m_chooser.getSelected()){
        case SparkMax:
          new CANSparkMax(motorID, MotorType.kBrushless).set(motorSpeed);
          break;
        case TalonSRX:
          new TalonSRX(motorID).set(TalonSRXControlMode.PercentOutput, motorSpeed);
          break;
        case TalonFX:
          new TalonFX(motorID).set(TalonFXControlMode.PercentOutput, motorSpeed);
          break;
        case VictorSPX:
          new VictorSPX(motorID).set(VictorSPXControlMode.PercentOutput, motorSpeed);
          break;
        default:
        break;
      }
    } else{
        switch(m_chooser.getSelected()){
          case SparkMax:
            new CANSparkMax(motorID, MotorType.kBrushless).set(0);
            break;
          case TalonSRX:
            new TalonSRX(motorID).set(TalonSRXControlMode.PercentOutput, 0);
            break;
          case TalonFX:
            new TalonFX(motorID).set(TalonFXControlMode.PercentOutput, 0);
            break;
          case VictorSPX:
            new VictorSPX(motorID).set(VictorSPXControlMode.PercentOutput, 0);
            break;
          default:
          break;
        }
    }
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
