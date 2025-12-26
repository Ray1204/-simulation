package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;

public final class Constants {

  public static class MechanicalConstants {
    public static final double RobotLength = 0.76;//單位:公尺
    public static final double RobotWidth = 0.58;//單位:公尺
    // 對角線長度 r = (長平方+寬平方)根號
    public static final double r = Math.sqrt(Math.pow(RobotLength ,2) + Math.pow(RobotWidth ,2));
  }
  public static class OperatorConstants {
    public static final int Player1Port = 0;
    public static final int Player2Port = 1;

    public static final double kMove = 0.5;
    public static final double kturn = 0.5;
    // 初始朝向 (機器人 vs 操作者)，單位 = 度
    public static final double OriginRobotHeading = 0;
    public static final double DriverHeading = 0; 
  }
  //motor ID
  public static class MotorControllerID {
    public static final int LF_TurnID         = 1;
    public static final int LR_TurnID         = 4;
    public static final int RF_TurnID         = 2;
    public static final int RR_TurnID         = 3;

    public static final int LF_DriveID        = 5;
    public static final int LR_DriveID        = 8;
    public static final int RF_DriveID        = 6;
    public static final int RR_DriveID        = 7;
  
    public static final int L_getmotor        = 8;
    public static final int R_getmotor        = 9;

    public static final int ShootMotor1       =10;
    public static final int ShootMotor2       =11;
  }

  //motor speed constants
  public static class MotorConstants {
    public static final double kTopTransferSpeed = 0.5;
    public static final double kBottomTransferSpeed = 0.2;
    public static final double kShaftSpeed = 1.0;
  }

  public static class configs{
    public static SparkMaxConfig drivemotorConfig(){
      SparkMaxConfig configs = new SparkMaxConfig();
      return configs;
    }
  }
}