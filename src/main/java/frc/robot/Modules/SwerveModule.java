package frc.robot.Modules;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.TOOLS.PID;

public class SwerveModule{
	private TalonSRX turnmotor;   // 旋轉馬達 (負責調整輪子方向)
	private SparkMax drivemotor;  // 驅動馬達 (負責推進)
	private String name;
	private PID pid;
	private double encValue;

	public SwerveModule(final int turnmotorID,final int drivemotorID,final PID pid){
		turnmotor = new TalonSRX(turnmotorID);
		drivemotor = new SparkMax(drivemotorID, MotorType.kBrushed);
		this.pid = pid;
		this.pid.setDeadband(0.1);
	}
	/* 設 dirve motor 速度 */ 
	public void SetDriveMotor(double value) {
		drivemotor.set(value);	}

	public void SetTurnMotor(double target_angle){
		// angle -> [-180, 180]
		double enc_angle = ((int)turnmotor.getSelectedSensorPosition() & 0x03ff) * 0.3515625;
		enc_angle = enc_angle < 180.0 ? enc_angle : -360.0 + enc_angle;
		double error = (target_angle - encValue)/360;
		turnmotor.set(TalonSRXControlMode.PercentOutput, pid.calculate(error));
	}
	
	public void setpoint(final double speed,final double angle){
		SetDriveMotor(speed);
		SetTurnMotor(angle);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPID(PID pid) {
		this.pid = pid;
	}
}