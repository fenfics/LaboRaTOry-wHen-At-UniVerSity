public class TestBox {

    public static void resizeBox(Box b,int fold){
        b.width *= fold;
        b.height *= fold;
        b.depth *= fold;
    }

    public static void main(String[] args) {
        Box box1 = new Box(3,4,5);
        //box1.Box(3, 4, 5);
        resizeBox(box1, 2);
        System.out.println(box1.getVolume());
        
    }

    

}
