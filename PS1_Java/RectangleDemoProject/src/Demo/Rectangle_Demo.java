package Demo;

public class Rectangle_Demo {
	private float length;
    private float width;
 
    // Setter for length
    public void setLength(float length) {
        this.length = length;
    }

    // Getter for length
    public float getLength() {
        return length;
    }

    // Setter for width
    public void setWidth(float width) {
        this.width = width;
    }

    // Getter for width
    public float getWidth() {
        return width;
    }

    public float getArea() {
        return length * width;  
    }
    
    public float getPerimeter() {
        return 2 * (length + width); 
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle_Demo rectangle = new Rectangle_Demo();

        // Set values for length and width
        rectangle.setLength(10.0f);  // Example length
        rectangle.setWidth(5.0f);    // Example width

        // Get and print area and perimeter
        System.out.println("Length: " + rectangle.getLength());
        System.out.println("Width: " + rectangle.getWidth());
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Perimeter: " + rectangle.getPerimeter());
	}

}
