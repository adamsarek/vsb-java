public class Dum {

    private static final Canvas CANVAS = Canvas.getInstance();

    private static int step = 50;

    private int xPos = 0;
    private int yPos = 0;
    private int width = 0;
    private int height = 0;

    public Dum(){
        //new Triangle(0,0,50,50, MyColor.GREEN);
        //new Rectangle(0,0,50,50, MyColor.GRAY);
        paint();
    }
    public int getX(){
        return xPos;
    }
    public int getY(){
        return yPos;
    }
    public void moveRight(){
        setPosition(xPos+step,yPos);
    }
    public void moveUp(){
        setPosition(xPos,yPos-step);
    }
    public void moveLeft(){
        setPosition(xPos-step,yPos);
    }
    public void moveDown() {
        setPosition(xPos,yPos+step);
    }
    private void setPosition(int x, int y){
        erase();
        xPos = x;
        yPos = y;
        paint();
    }
    public void paint(){
        CANVAS.setColorOfForeground(MyColor.LIGHT_GRAY);
        new Triangle(xPos,yPos,50,50, MyColor.GREEN);
        new Rectangle(xPos,yPos+50,50,50, MyColor.GRAY);
    }
    public void erase(){
        CANVAS.erase();
    }
}
