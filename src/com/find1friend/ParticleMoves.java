/*------------------------------------------------------------------------------
 * Advance Computer Graphics
 * Two particle moving across frame with different velocity 
 * and different sizes.
 * Written by: Kevin Duraj
 -----------------------------------------------------------------------------*/
package com.find1friend;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*----------------------------------------------------------------------------*/
public class ParticleMoves {
    
    ArrayList<Image>    fames;
    ArrayList<Particle> particles;
    
    int TotalFrames    = 360;
    int totalParticles = 10;

    
    /*------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {
        ParticleMoves part = new ParticleMoves();
        part.run();

    }
    /*------------------------------------------------------------------------*/

    public void run() {

        fames    = new ArrayList<>(100);
        particles = new ArrayList<>(10);
        int cnt;

        /*    
         Particle part0 = new Particle(
         10, 10, // position x, y
         3, 3,   // velocity x, y
         50,     // size
         750,    // lifespan
         Color.ORANGE);
         */
        for (cnt = 0; cnt < totalParticles; cnt++) {

            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 200);

            int velX = (int) (Math.random() * 5);
            Random random = new Random();
            int velY = (random.nextInt(10) - 5);

            int size = (int) (Math.random() * 40 + 5);

            //System.out.println("x=" + x + "\ty=" + y + "\tvelX=" + velX + "\tvelY=" + velY);
            Color color = getRainbowPastel();
            //Color color = getBrightPastel();
            particles.add(new Particle(x, y, velX, velY, size, 750, color));

        }

        /*---------------------------------------------------*/
        for (cnt = 0; cnt < TotalFrames; cnt++) {
            
            Bezier bezier = drawCurve(640, 480);
            bezier = drawCircles(bezier, cnt); 
            
            moveBoids(bezier);
            fames.add(bezier);
            cnt++;
        }

        /*---------------------------------------------------*/
        cnt = 0;
        for (Image img : fames) {

            String filename = String.format("/Users/ktd/Desktop/img/particle%03d.png", (int) cnt);
            img.write(filename);
            cnt++;
        }
        /*---------------------------------------------------*/
    }
    /*------------------------------------------------------------------------*/

    private Bezier drawCurve(int width, int height) {
        
        int[] Point0 = {0, 239};    // Point 1
        int[] Point1 = {207, 150};  // Control Point 1
        int[] Point2 = {150, 300};  // Control Point 2
        int[] Point3 = {339, 200};  // Point 2
        double Step = 0.001;

        
        Bezier bezier = new Bezier(width, height, Color.BLACK);
        bezier.steps(Point0, Point1, Point2, Point3, Step, Color.cyan);
        Point0[0] = 339; Point0[1] = 200;
        Point1[0] = 450; Point1[1] = 149;
        Point2[0] = 500; Point2[1] = 200;
        Point3[0] = 640; Point3[1] = 240;
        bezier.steps(Point0, Point1, Point2, Point3, Step,Color.cyan);      
        
        return bezier;
    }
    /*------------------------------------------------------------------------*/
    private Bezier drawCircles(Bezier bezier, int TotalFrames) {
        //--- Create a star pattern of lines ---//
        //for (int i = 0; i < 360; i += 30) {
            
            int i = TotalFrames;
            int x = (int) (200 * Math.cos(i * Math.PI / 180.0));
            int y = (int) (200 * Math.sin(i * Math.PI / 180.0));
            System.out.println(i);

            int ox = bezier.image[0][0].length / 2;
            int oy = bezier.image[0].length / 2;

            bezier.bresenhamLine(ox, oy, ox + x, oy + y, Color.cyan);
        //}

        //--- Draw the circles ---//
        for (int radius = 10; radius < bezier.image[0].length; radius += 50) {
            bezier.bresenhamCircle(bezier.image[0][0].length / 2
                    , bezier.image[0].length / 2
                    , radius
                    , Color.RED);
        }
        return bezier;
    }
    /*------------------------------------------------------------------------*/
    private void moveBoids(Image image) {

        int centerX = 0;
        int centerY = 0;
        int count = 0;

        for (Particle boid : particles) {
            if (boid.next) {
                centerX += boid.x;
                centerY += boid.y;
                count++;
            }
        }

        // Calculate center of the boids 
        centerX /= count;
        centerY /= count;

        for (Particle boid : particles) {

            if (boid.next) {
                image.filledCircle(boid);
                boid.move(centerX, centerY);
            }
        }

        /*-----------------------------------------
         for (int n = 0; n < 10; n++) {
         allX += particles.get(n).x;
         allY += particles.get(n).y;
            
         if (particles.get(n).next) {
         image.filledCircle(particles.get(n));
         particles.get(n).move();
         }
         } ----------------------------------------*/
    }

    /*------------------------------------------------------------------------*/
    public Color getRainbowPastel() {
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.8f;//1.0 for brilliant, 0.0 for dull
        final float luminance = 0.8f; //1.0 for brighter, 0.0 for black
        Color color = Color.getHSBColor(hue, saturation, luminance);
        return color;
    }
    /*------------------------------------------------------------------------*/
    public Color getBrightPastel() {
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        Color color = new Color(R, G, B);
        return color;
    }
    /*------------------------------------------------------------------------*/

}
