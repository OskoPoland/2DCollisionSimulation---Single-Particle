import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import PhysicalParticle.Particle;
import PhysicalParticle.VectorPackage.IllegalVectorSizeError;
import PhysicalParticle.VectorPackage.VectorA;

public class ParticleTest {
    //C:\Users\Vinr5\OneDrive\Documents\1DCollisionSimulation\ParticleTests

    public static void main(String[] args) {
        int numTestsDone = 0;
        String path = "C:\\Users\\Vinr5\\OneDrive\\Documents\\1DCollisionSimulation\\ParticleTests\\";

        Scanner s = new Scanner(System.in);
        System.out.println("How many total seconds: ");
        int numSeconds = Integer.parseInt(s.nextLine());
        System.out.println("How many FPS: ");
        int FPS = Integer.parseInt(s.nextLine());
        s.close();

        //Test run at 1FPS
        try {

            File test = new File(path + "test.txt");
            FileWriter writer = new FileWriter(test);

            VectorA initPos = new VectorA(Arrays.asList(200.0, 200.0));
            VectorA initVel = new VectorA(Arrays.asList(20.0, 10.0));

            //Boundary b = new Boundary(400, 400);
            Particle p = new Particle(50.0, initPos, initVel, FPS);

            //Write headers && starting position and velocity
            writer.write("Time Step, Elapsed Time, Collision, px, py, pleft, pright, ptop, pbot, " +
                             "vx, vy\n");
            writer.write(p.getTimeStep() + "," + p.getElapsedTime() + "," + p.checkCollision() + "," + p.getParticleX() + "," + p.getParticleY()
                            + "," + (p.getParticleX() - p.RADIUS) + "," + (p.getParticleX() + p.RADIUS) + "," + (p.getParticleY() - p.RADIUS) + "," + (p.getParticleY() + p.RADIUS) + ","
                            + p.getVelocityX() + "," + p.getVelocityY() + "\n");

            //iterate through fps if fps 100 - then 100 times
            for (int i = 0; i < numSeconds; i++) {
                for (int j = 0; j < FPS; j++) {
                    p.updatePosition();
                    writer.write(p.getTimeStep() + "," + p.getElapsedTime() + "," + p.checkCollision() + "," + p.getParticleX() + "," + p.getParticleY()
                            + "," + (p.getParticleX() - p.RADIUS) + "," + (p.getParticleX() + p.RADIUS) + "," + (p.getParticleY() + p.RADIUS) + "," + (p.getParticleY() - p.RADIUS) + ","
                            + p.getVelocityX() + "," + p.getVelocityY() + "\n");
                }
            }
            writer.flush();
            writer.close();

        } catch (IOException | IllegalVectorSizeError e) {
            e.printStackTrace();
        }
    }
}
