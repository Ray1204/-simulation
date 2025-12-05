package frc.robot.TOOLS;

public class tools {
	// 將向量 (x, y) 轉換成角度 (0~360度)
	public static double toDegrees(final double x, final double y) {
	// 如果向量為 (0,0)，沒有方向，回傳 0
		if(x == 0 && y == 0) 
		return 0;

		double angle =  Math.atan2(y, x) * 180.0 / Math.PI ;//... / 3.1415926 * 180.0

    return angle < 0 ? angle + 360 : angle;
	}

	// 將角度轉換成向量 (x, y)，並乘上半徑 radius
	public static double[] toVector(final double radius, double angle) {
    //確保角度落在0角度落在0~360度
	if(angle < 0) angle += 360;
    else if(angle > 360) angle -= 360;
	// 角度轉換成弧度 (1度 ≈ 0.0174533 弧度)
	final double rad = angle * 0.0174533;//角度*3.1415926/180.0
	// 回傳 (x, y)，這裡用 sin 表示 x，cos 表示 y
	return new double[]{radius * Math.sin(rad), radius * Math.cos(rad)};
	}

	//bounding 
	public static double bounding(double pos, final double min, final double max) {
		pos = pos < min ? min : pos;
		pos = pos > max ? max : pos;

		return pos;
	}

	//bounding 1 ~ -1
	public static double bounding(double pos) {
		pos = pos < -1 ? -1 : pos;
		pos = pos > 1 ? 1 : pos;
		return pos;
	}

	//check deadband
	public static double deadband(double value, final double deadbandValue) {
		return -deadbandValue < value && value < deadbandValue ? 0 : value;
	}
}