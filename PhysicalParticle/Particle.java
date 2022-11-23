package PhysicalParticle;

import DrawingPan.DrawingPanel;
import PhysicalParticle.VectorPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.awt.Graphics.*;
import java.awt.Color.*;

public class Particle {
    /*
     * Particle represents the moving object in the collisions. WHat needs to happen?
     *   - Access location, velocity, mass
     *   - Calculate position at time t - Use kinematic equations
     *   - Calculate velocity at time t - Use kinematic equations
     *   - Particle will have acceleration but it will be constant so @ 0
     *
     *   - This class will also handle the graphical creation of the particle using OBJ
     *
     *   - Time Discretization: 1/FPS = timeChange per frame
     *   - Position calc takes the time that is tracked to determine next position
     */

    private VectorA position;
    private final VectorA velocity;

    private final double ACCELERATION = 0.0;

    public final double RADIUS;

    //priv classes for testing purposes
    private double FPS = 0.0;
    private double timeStep = 0.0;
    private double elapsedTime = 0.0;

    //Static Boundary Creation
    private static DrawingPanel panel = new DrawingPanel(500,500);



    //Constructors
    public Particle(double radius, VectorA startingPos, VectorA startingVel, double FPS) {
        this.RADIUS = radius;
        this.position = startingPos;
        this.velocity = startingVel;
        this.FPS = FPS;

    }

    //Getters
    public double getParticleX() {return position.getX();}
    public double getParticleY() {return position.getY();}
    //public double getParticleZ() {return position.zComponent;}

    public double getVelocityX() {return velocity.getX();}
    public double getVelocityY() {return velocity.getY();}
    //public double getVelocityZ() {return velocity.zComponent;}

    //testing getters
    public double getTimeStep() {return this.timeStep;}
    public double getElapsedTime() {return this.elapsedTime;}

    //updates and renders particles for each frame where time change based on FPS
    public void updatePosition() {
        this.timeStep = 1/this.FPS;
        //Using xf = Xi + vit + 1/2at^2
        double x = position.getX() + velocity.getX() * this.timeStep;
        double y = position.getY() + velocity.getY() * this.timeStep;

        try {
            position = new VectorA(Arrays.asList(x, y));
        } catch (IllegalVectorSizeError ivse) {
            ivse.printStackTrace();
        }
        checkCollision();
        renderParticle();
        this.elapsedTime += 1/this.FPS;

    }

    //Handles checking if the particle is colliding
    public boolean checkCollision() {
        //if RADIUS + x/y pos > bound then flip it
        double distFromLeft = panel.getWidth() - (position.getX() - RADIUS);
        double distFromRight = panel.getWidth() - (position.getX() + RADIUS);
        double distFromTop = panel.getHeight() - (position.getY() - RADIUS);
        double distFromBot = panel.getHeight() - (position.getY() + RADIUS);

        if(position.getX() - RADIUS <= 0) {
            System.out.println("Collide right " + distFromLeft + " " + position.getX() + " " + position.getY());
            velocity.setX(-velocity.getX());
            position.setX(position.getX() + (RADIUS + RADIUS + RADIUS));
            return true;
        } else if(position.getX() + RADIUS >= panel.getWidth()) {
            System.out.println("Collide left " + distFromRight + " " + position.getX() + " " + position.getY());
            velocity.setX(-velocity.getX());
            position.setX(position.getX() - (RADIUS + RADIUS + RADIUS));
            return true;
        }else if(position.getY() - RADIUS <= 0) {
            System.out.println("Collide bot " + distFromTop + " " + position.getX() + " " + position.getY());
            velocity.setY(-velocity.getY());
            position.setY(position.getY() + (RADIUS + RADIUS + RADIUS));
            return true;
        }else if(position.getY() + RADIUS >= panel.getHeight()) {
            System.out.println("Collide top " + distFromBot + " " + position.getX() + " " + position.getY());
            velocity.setY(-velocity.getY());
            position.setY(position.getY() - (RADIUS + RADIUS + RADIUS));
            return true;
        }
        return false;
    }

    //Handles rendering the particle needs to be called after every position update
    private void renderParticle() {
        //Drawing the particle
        Graphics g = panel.getGraphics();
        panel.clear();
        g.setColor(Color.RED);

        int drawX = (int)(position.getX() - RADIUS);
        int drawY = (int)(position.getY() - RADIUS);
        int diameter = (int)(RADIUS * 2);
        g.fillOval(drawX, drawY, diameter, diameter);
        panel.sleep(1);
    }


}