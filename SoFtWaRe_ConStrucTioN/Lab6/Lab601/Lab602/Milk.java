public class Milk extends Product {
    
    protected int Volumn;

    public Milk(int p){
        super(p);
    }

    public void setVolumn(int Volumn){
        this.Volumn=Volumn;
    }

    public int getVolumn(){
        return this.Volumn;
    }

    public String toString(){
        return getClass().getSimpleName()+"\t"+this.getVolumn()+"\tCC\t"+this.getPrice();
    }
}
