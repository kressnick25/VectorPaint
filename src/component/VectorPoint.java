package component;

class DecimalOutOfRangeException extends Exception {
    public DecimalOutOfRangeException(String message) {
        super(message);
    }
}


public class VectorPoint {

    private double x, y;

    public VectorPoint(double x, double y){
        try {
            assertDecimal(x);
            assertDecimal(y);
        } catch (Exception e){
            // TODO handle exception here
        }


        this.x = x;
        this.y = y;
    }

    // Check to ensure that provided value value is within valid range
    private void assertDecimal(double value) throws DecimalOutOfRangeException {
        if (value < 0.0){
            throw new DecimalOutOfRangeException("Decimal was less than 0.0");
        }
        if (value > 0.99){
            throw new DecimalOutOfRangeException("Decimal was greater than 0.99");
        }
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    // Return point as string with proper formatting for file.
    public String toString(){
        String stringX = Double.toString(x);
        String stringY = Double.toString(y);

        if (x == 0.0){
            stringX = "0.0";
        }
        if (y == 0.0){
            stringY = "0.0";
        }

        return stringX + " " + stringY;
    }
}
