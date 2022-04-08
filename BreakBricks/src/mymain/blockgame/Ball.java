package mymain.blockgame;

import java.awt.Graphics;

import myutil.MyConstant;

public class Ball extends item {

	int radius = 10;
	
	boolean bRight = true; //우
	boolean bDown  = false; //상
	
	int speed = 5;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);;
		
		g.fillOval(pos.x-radius, pos.y-radius, radius*2, radius*2);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		//좌우 이동 
		if(bRight) pos.x += speed;
		else pos.x -= speed;
		
		//상하 이동 
		if(bDown) pos.y += speed;
		else pos.y -= speed;
		
		//우측에 닿냐? 
		if((pos.x + radius) > MyConstant.BlockGame.GAMEPAN_WIDTH) {
			pos.x = MyConstant.BlockGame.GAMEPAN_WIDTH - radius;
			bRight =false;
		} else if((pos.x - radius) <0) {//좌측에 닿냐?
			pos.x = radius;
			bRight = true;
		}
		
		//아래벽에 닿냐? 
		if((pos.y + radius) > MyConstant.BlockGame.GAMEPAN_HEIGHT) {
			
			//바 밑으로 떨어지면 종료
			//System.exit(0);
			
			pos.y = MyConstant.BlockGame.GAMEPAN_HEIGHT - radius;
			bDown =false;
			
		} else if((pos.y - radius) <0) {//위벽에 닿냐?
			pos.y = radius;
			bDown = true;
		}
		
		
		
	}

}
