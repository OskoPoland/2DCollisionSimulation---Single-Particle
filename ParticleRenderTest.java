import PhysicalParticle.Particle;
import PhysicalParticle.VectorPackage.IllegalVectorSizeError;
import PhysicalParticle.VectorPackage.VectorA;

import java.util.Arrays;
import java.util.Scanner;

public class ParticleRenderTest {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("How many total seconds: ");
        int numSeconds = Integer.parseInt(s.nextLine());
        System.out.println("How many FPS: ");
        int FPS = Integer.parseInt(s.nextLine());
        s.close();


        try {
            VectorA initPos = new VectorA(Arrays.asList(400.0, 400.0));
            VectorA initVel = new VectorA(Arrays.asList(180.0, -150.0));

            Particle p = new Particle(30.0, initPos, initVel, FPS);

            for (int i = 0; i < numSeconds; i++) {
                for (int j = 0; j < FPS; j++) {
                    p.updatePosition();
                }
            }
        } catch (IllegalVectorSizeError ivse) {
            ivse.printStackTrace();
        }
    }
}
