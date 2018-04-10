import java.util.Stack;

public interface Command {
	public Stack<GameStatus> undo();
}
