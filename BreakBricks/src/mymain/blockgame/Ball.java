package mymain.blockgame;

import java.awt.Graphics;

import myutil.MyConstant;

public class Ball extends item {

	int radius = 10;
	
	boolean bRight = true; //��
	boolean bDown  = false; //��
	
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
		//�¿� �̵� 
		if(bRight) pos.x += speed;
		else pos.x -= speed;
		
		//���� �̵� 
		if(bDown) pos.y += speed;
		else pos.y -= speed;
		
		//������ ���? 
		if((pos.x + radius) > MyConstant.BlockGame.GAMEPAN_WIDTH) {
			pos.x = MyConstant.BlockGame.GAMEPAN_WIDTH - radius;
			bRight =false;
		} else if((pos.x - radius) <0) {//������ ���?
			pos.x = radius;
			bRight = true;
		}
		
		//�Ʒ����� ���? 
		if((pos.y + radius) > MyConstant.BlockGame.GAMEPAN_HEIGHT) {
			
			//�� ������ �������� ����
			//System.exit(0);
			
			pos.y = MyConstant.BlockGame.GAMEPAN_HEIGHT - radius;
			bDown =false;
			
		} else if((pos.y - radius) <0) {//������ ���?
			pos.y = radius;
			bDown = true;
		}
		
		
		
	}

}
