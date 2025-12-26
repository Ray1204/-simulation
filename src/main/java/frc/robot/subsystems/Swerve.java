package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Modules.SwerveModule;
import frc.robot.TOOLS.PID;
import frc.robot.TOOLS.tools;
import frc.robot.Constants;

public class Swerve extends SubsystemBase {
  /**********swerve motor modules**********/
  private final SwerveModule lf;
  private final SwerveModule lr;  
  private final SwerveModule rf;
  private final SwerveModule rr;

  private final Translation2d m_LeftFrontLocation = new Translation2d(0.29,0.38);
  private final Translation2d m_RightFrontLocation = new Translation2d(0.29,-0.38);
  private final Translation2d m_RightBackLocation = new Translation2d(-0.29,-0.38);
  private final Translation2d m_LeftBackLocation = new Translation2d(-0.29,0.38);

  /**********constants**********/

  public Swerve() {
    lf = new SwerveModule(Constants.MotorControllerID.LF_TurnID, Constants.MotorControllerID.LF_DriveID, new PID(0.3, 2*1e-3, 0));
    lr = new SwerveModule(Constants.MotorControllerID.LR_TurnID, Constants.MotorControllerID.LR_DriveID, new PID(0.3, 2*1e-3, 0));
    rf = new SwerveModule(Constants.MotorControllerID.RF_TurnID, Constants.MotorControllerID.RF_DriveID, new PID(0.3, 2*1e-3, 0));
    rr = new SwerveModule(Constants.MotorControllerID.RR_TurnID, Constants.MotorControllerID.RR_DriveID, new PID(0.3, 2*1e-3, 0));

    lf.setName("Left_front");
    lr.setName("Left_rear");
    rf.setName("Right_front");
    rr.setName("Right_rear");

    lf.setReverse(true);
    rf.setReverse(true);
    rr.setReverse(true);

    move(0, 0, 0);
  }

  @Override
  public void periodic() {}
  /**
   * Move the robot.
   * 
   * @param x 
   * @param y 
   * @param turn 
   */
  public void move(double x, double y, double turn) {
    x = tools.deadband(x, 0.2) * 10.0;
    y = tools.deadband(y, 0.2) * 10.0;
    turn = tools.deadband(turn, 0.2) * 2 * Math.PI;    

    ChassisSpeeds speeds = new ChassisSpeeds(x, y, turn);
    //計算x, y向量
    SwerveModuleState states[] = m_kinematics.toSwerveModuleStates(speeds);

    lf.setpoint(states[0]);
    rf.setpoint(states[1]);
    rr.setpoint(states[2]);
    lr.setpoint(states[3]);
  }
  private final SwerveDriveKinematics m_kinematics =
  new SwerveDriveKinematics(
    m_LeftFrontLocation, m_RightFrontLocation, m_RightBackLocation, m_LeftBackLocation);
}