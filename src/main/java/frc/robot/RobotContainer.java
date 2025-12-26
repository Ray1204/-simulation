package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.JoystickSwerve;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/***subsystem***/
import frc.robot.subsystems.Swerve;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a 
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot
 */
public class RobotContainer {
private final Swerve swerve = new Swerve();

private final Joystick js1 = new Joystick(Constants.OperatorConstants.Player1Port);
private final Joystick js2 = new Joystick(Constants.OperatorConstants.Player2Port);

public RobotContainer() {
  player1CommandList();
}

private void player1CommandList() {
    // swerve
    swerve.setDefaultCommand(new JoystickSwerve(swerve, 
    ()->-js1.getY(),
    ()->js1.getX(),
    ()->-js1.getRawAxis(4))
    );
  }
}