package frc.robot.Modules;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.TOOLS.PID;

public class SwerveModule{
	private TalonSRX turnmotor;   // 旋轉馬達 (負責調整輪子方向)
	private SparkMax drivemotor;  // 驅動馬達 (負責推進)
	private String name;
	private PID pid;
	private boolean reverse;

	public SwerveModule(final int turnmotorID,final int drivemotorID,final PID pid){
		turnmotor = new TalonSRX(turnmotorID);
		drivemotor = new SparkMax(drivemotorID, MotorType.kBrushed);
		this.pid = pid;
		this.pid.setDeadband(0.1);
		reverse = false;
	}
	/* 設 dirve motor 速度 */ 
	public void SetDriveMotor(double value) {
		drivemotor.set(value * (reverse ? -1.0 : 1.0));	}

	public void SetTurnMotor(double target_angle){
		// angle -> [-180, 180]
		double enc_angle = getEncValue().getDegrees();
		enc_angle = enc_angle < 180.0 ? enc_angle : -360.0 + enc_angle;
		double error = (target_angle - enc_angle) / 90;
		debug(error);
		turnmotor.set(TalonSRXControlMode.PercentOutput, error);
	}
	
	public void setpoint(SwerveModuleState state) {
		SwerveModuleState desire_state = SwerveModuleState.optimize(state, getEncValue());
		double speed = desire_state.speedMetersPerSecond / 10.0;
		double angle = desire_state.angle.getDegrees();
		SetDriveMotor(speed);
		SetTurnMotor(angle);
	}

	private Rotation2d getEncValue() {
		return new Rotation2d(((int)turnmotor.getSelectedSensorPosition() & 0x03ff) * 0.3515625 * Math.PI / 180.0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public void setPID(PID pid) {
		this.pid = pid;
	}

	public void debug(double value) {
		SmartDashboard.putNumber(name, value);
	}
}