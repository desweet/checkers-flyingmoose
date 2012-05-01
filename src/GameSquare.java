import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameSquare extends JPanel
{
	public boolean validPos;
	public int x;
	public int y;
	
	public GameSquare(int posx, int posy, boolean valid)
	{
		x = posx;
		y = posy;
		validPos = valid;
	}

	public void setPiece(GamePiece piece)
	{
		this.add(piece);
	}
	
	public boolean isValid()
	{
		return validPos;
	}

}
