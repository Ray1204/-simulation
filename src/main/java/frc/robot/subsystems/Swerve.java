package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  private final Translation2d m_LeftFrontLocation = new Translation2d(-0.29,0.38);
  private final Translation2d m_RightFrontLocation = new Translation2d(0.29,0.38);
  private final Translation2d m_RightBackLocation = new Translation2d(0.29,-0.38);
  private final Translation2d m_LeftBackLocation = new Translation2d(-0.29,-0.38);

  /**********constants**********/

  public Swerve() {
    lf = new SwerveModule(Constants.MotorControllerID.LF_TurnID, Constants.MotorControllerID.LF_DriveID, new PID(0.5, 2*1e-3, 0));
    lr = new SwerveModule(Constants.MotorControllerID.LR_TurnID, Constants.MotorControllerID.LR_DriveID, new PID(0.5, 2*1e-3, 0));
    rf = new SwerveModule(Constants.MotorControllerID.RF_TurnID, Constants.MotorControllerID.RF_DriveID, new PID(0.5, 2*1e-3, 0));
    rr = new SwerveModule(Constants.MotorControllerID.RR_TurnID, Constants.MotorControllerID.RR_DriveID, new PID(0.5, 2*1e-3, 0));

    lf.setName("Left_front");
    lr.setName("Left_rear");
    rf.setName("Right_front");
    rr.setName("Right_rear");

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
    x = tools.deadband(x, 0.1) * 10.0;
    y = tools.deadband(y, 0.1) * 10.0;
    turn = tools.deadband(turn, 0.1) * 2 * Math.PI;    

    ChassisSpeeds speeds = new ChassisSpeeds(x, y, turn);
    //計算x, y向量
    SwerveModuleState states[] = m_kinematics.toSwerveModuleStates(speeds);
    double lf_power = states[0].speedMetersPerSecond /10.00;
    Rotation2d lf_angle = states[0].angle;
    double rf_power = states[1].speedMetersPerSecond /10.00;
    Rotation2d rf_angle = states[1].angle;
    double rr_power = states[2].speedMetersPerSecond /10.00;
    Rotation2d rr_angle = states[2].angle;
    double lr_power = states[3].speedMetersPerSecond /10.00;
    Rotation2d lr_angle = states[3].angle;

    SmartDashboard.putNumber("lf_power", lr_power);
    SmartDashboard.putNumber("rf_power", rf_power);
    SmartDashboard.putNumber("rr_power", rr_power);
    SmartDashboard.putNumber("lr_power", lr_power);

    lf.setpoint(lf_power, lf_angle.getDegrees());
    rf.setpoint(rf_power, rf_angle.getDegrees());
    rr.setpoint(rr_power, rr_angle.getDegrees());
    lr.setpoint(lr_power, lr_angle.getDegrees());
  }
  
  private final SwerveDriveKinematics m_kinematics =
  new SwerveDriveKinematics(
    m_LeftFrontLocation, m_RightFrontLocation, m_RightBackLocation, m_LeftBackLocation);
}
