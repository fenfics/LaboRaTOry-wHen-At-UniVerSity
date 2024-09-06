public class Graduate extends Student {
    protected String thesisTitle;
    

    public Graduate(int i,String n,double g,String thesisTitle){
        super(i , n ,g);
        this.thesisTitle=thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getThesisTitle(){
        return this.thesisTitle;
    }

    public String toString(){
        return "Graduate Student:\nID: "+this.getId()+
        "\nName: "+this.getName()+
        "\nGPA: "+this.getGPA()+
        "\nThesis: "+this.thesisTitle;
    }
}
