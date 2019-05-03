class DecimalOutOfRangeException extends Exception {
    public DecimalOutOfRangeException(String message) {
        super(message);
    }
}


public class Point {

    private float x, y;

    public Point(float x, float y) throws DecimalOutOfRangeException{
        assertDecimal(x);
        assertDecimal(y);

        this.x = x;
        this.y = y;
    }

    // Check to ensure that provided value value is within valid range
    private void assertDecimal(float value) throws DecimalOutOfRangeException {
        if (value < 0.0){
            throw new DecimalOutOfRangeException("Decimal was less than 0.0");
        }
        if (value > 0.99){
            throw new DecimalOutOfRangeException("Decimal was greater than 0.99");
        }
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    // Return point as string with proper formatting for file.
    public String toString(){
        String stringX = Float.toString(x);
        String stringY = Float.toString(y);

        if (x == 0.0){
            stringX = "0.0";
        }
        if (y == 0.0){
            stringY = "0.0";
        }

        return stringX + " " + stringY;
    }
}
