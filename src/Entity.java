public class Entity {
    private int ID;
    public long HP;
    public int Radius;
    public int MS;   //move speed
    public int DMG;
    public int x;
    public int y;

    public Entity(){
    }
    protected Entity(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }
    protected void setID(int ID){
        this.ID = ID;
    }
    public long getHP(){
        return HP;
    }
    protected  void setHP(int HP){
        this.HP = HP;
    }
    public int getRad() {
        return Radius;
    }
    protected void setRad( int Radius){
        this.Radius = Radius;
    }
    public int getMS(){
        return MS;
    }
    protected void setMS(int MS){
        this.MS = MS;
    }
    public int getDMG(){
        return DMG;
    }
    protected void steDMG(int DMG){
        this.DMG = DMG;
    }
    public boolean IsNew(){
        return(this.ID = null);
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    protected void setcoord(int x, int y){
        this.x = x;
        this.y = y;
    }
}

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getID());
}
