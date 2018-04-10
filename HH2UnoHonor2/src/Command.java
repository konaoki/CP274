import java.util.Stack;
/**
 * interface for the command
 * @author konao
 *
 */
public interface Command {
	public Stack<GameStatus> undo();
}
