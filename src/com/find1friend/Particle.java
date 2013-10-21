package com.find1friend;

import java.awt.Color;
import java.util.Random;

/*----------------------------------------------------------------------------*/
public class Particle {

    public int x;
    public int y;

    private int velX;
    private int velY;

    public int size;
    private int life;
    public Color color;
    public boolean next;


    /*------------------------------------------------------------------------*/
    public Particle(int x, int y, int velocity_x, int velocity_y, int size, int life, Color c) {
        this.x = x;
        this.y = y;

        this.velX = velocity_x;
        this.velY = velocity_y;

        this.size = size;

        this.life = life;
        this.color = c;

        this.next = true;
    }

    /*------------------------------------------------------------------------*/
    public void move(int centerX, int centerY) {
       /*
        System.out.print("x=" + x + "\ty=" + y);
        System.out.print("\tvelX=" + velocity_x + "\tvelY=" + velocity_y);
        System.out.print("\tcenterX=" + centerX + "\tcenterY=" + centerY);

        int sumX = centerX - x;
        int sumY = centerY - x;

        x += sumX / 10;
        y += sumY / 10;

        if (sumX < x) {
            velocity_x -= 1;
        } else {
            velocity_x += 1;
        }

        if (sumY < y) {
            velocity_y -= 1;
        } else {
            velocity_y += 1;
        }
        System.out.println("\tvelX=" + velocity_x + "\tvelY=" + velocity_y);
        */
        
        Random random = new Random();    
        //x += velocity_x;
        //y += velocity_y;
        x +=  (random.nextInt(10)-1);
        y +=  (random.nextInt(10)-5);

        life--;

        if (life <= 0) {
            this.next = false;
        }
        this.next = true;
    }
    /*------------------------------------------------------------------------*/
/*
    public double averageTwoDirections(double angle1, double angle2) {
        double oppositeDirection;
        
        assert (angle1 <= Math.PI * 2);
        assert (angle2 <= Math.PI * 2);
        angle1 = Math.toDegrees(angle1);
        angle2 = Math.toDegrees(angle2);
        
        if (Math.abs(angle1 - angle2) < 180) {
            return Math.toRadians((angle1 + angle2) / 2);
        } else {
            return oppositeDirection((oppositeDirection(angle1) + oppositeDirection(angle2)) / 2);
        }
    }
 */

    public void forward() {
        //NdPoint pt = space.getLocation(this);
        //double moveX = pt.getX() + Math.cos(heading) * distance;
        //double moveY = pt.getY() + Math.sin(heading) * distance;
        //space.moveTo(this, moveX, moveY);
        //grid.moveTo(this, (int) moveX, (int) moveY);
    }
    /*------------------------------------------------------------------------*/
}
