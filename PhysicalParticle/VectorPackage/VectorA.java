package PhysicalParticle.VectorPackage;
import java.util.*;


/*
 * Class Represents a vector with up to 3 components
 *  - Components stored as a list
 *  - Order matters a list (3 4 5) != (5 4 3)
 *  - Dimensions of created vector are fixed
 *  - Magnitude of vector is determined at obj creation
 *  - Components variables are assigned on creation
 *  - Should contain methods for addition, subtraction, and translation
 *  
 *  Methods
 *    - Empty vector constructor
 *    - Full vector constructor
 *      - INPUT: List<Integer>
 *      - OUTPUT: Vector obj with given dimension
 *    - Empty Vector Constructor
 *      - INPUT: None
 *      - OUTPUT: Initialize empty vector obj
 * 
 *     
 * 
 */
public class VectorA {

    //Components
    private double xComponent = 0;
    private double yComponent = 0;
    private double zComponent = 0;

    //Magnitude of Vector
    private double vMagnitude;

    //Angle of Vector
    private double angle; //maybe? - angle above x axis

    //Constructors
    public VectorA() {

    }

    public VectorA(List<Double> list) throws IllegalVectorSizeError {
        if (!(list.size() >= 2 && list.size() <= 3)) throw new IllegalVectorSizeError();

        //2D vector
        if (list.size() == 2) {
            this.xComponent = list.get(0);
            this.yComponent = list.get(1);
        }

        //3D vector
        if (list.size() == 3) {
            this.xComponent = list.get(0);
            this.yComponent = list.get(1);
            this.zComponent = list.get(2);
        }

        //Magnitude calc
        this.vMagnitude = Math.sqrt((xComponent * xComponent) + (yComponent * yComponent) + (zComponent * zComponent));
        
        //Angle Calc
        this.angle = Math.atan(yComponent/xComponent); 


    }

    //Getters
    public double getX() {return xComponent;}
    public double getY() {return yComponent;}
    public double getZ() {return zComponent;}

    //Setters - in the case of the desire to modify components due to calculation
    public void setX(double component) {this.xComponent = component;}
    public void setY(double component) {this.yComponent = component;}
    public void setZ(double component) {this.zComponent = component;}

    //Simple Vector Operations - Add & Subtract vec1.method(vec2) -> vec1 +/- vec2
    public VectorA add(VectorA V) {
        try {
            VectorA vec = new VectorA(Arrays.asList(this.xComponent + V.xComponent, this.yComponent + V.yComponent, this.zComponent + V.zComponent));
            return vec;
        } catch (IllegalVectorSizeError ivse) {
            ivse.printStackTrace();
            return null;
        }
    }

    public VectorA subtract(VectorA V) {
        try {
            VectorA vec = new VectorA(Arrays.asList(this.xComponent - V.xComponent, this.yComponent - V.yComponent, this.zComponent - V.zComponent));
            return vec;
        } catch (IllegalVectorSizeError ivse) {
            ivse.printStackTrace();
            return null;
        }
    }

    public double dotProd(VectorA V) {
        double product = (this.xComponent * V.xComponent) + (this.yComponent * V.yComponent) + (this.zComponent * V.zComponent);
        return product;
    }

}
