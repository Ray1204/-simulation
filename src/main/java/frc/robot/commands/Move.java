package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Swerve;

public class Move extends Command{
  private Swerve m_swerve;
  private double m_x, m_y, m_turn; 

  public Move(
    Swerve swerve,
    double x,
    double y,
    double turn)
    {
    m_swerve = swerve;
    m_x = x;
    m_y = y;
    m_turn = turn;
    addRequirements(m_swerve);
  }
  
  @Override
  public void execute() {
    m_swerve.move(m_x, -m_y, m_turn);
  }
}