package mymain.blockgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class item {
	Point pos = new Point();
	Color color = Color.red;
	
	public abstract void draw(Graphics g);
	public abstract void move();

	
}
