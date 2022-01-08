public class Move {

    public int x1,x2,y1,y2;

    public Move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String toString() {
        return "(("+this.x1+','+this.y1+")->("+this.x2+','+this.y2+"))";
    }

    public void print() {
        System.out.println(this.toString());
    }

    // To make contains method work
    @Override
    public boolean equals(Object o) {
        if(o==this) return true;
        if(!(o instanceof Move)) return false;
        Move mo = (Move)o;
        return mo.x1 == this.x1 && mo.x2 == this.x2 && mo.y1 == this.y1 && mo.y2 == this.y2;
    }
}
