package PhysicalParticle.VectorPackage;

public class IllegalVectorSizeError extends Exception{
    public IllegalVectorSizeError() {
        super("Given duoble array is either too small or too large.");
    }
}
