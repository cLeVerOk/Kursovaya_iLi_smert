public class NamedEntity extends Entity {
    protected String name;

    public NamedEntity(){
    }
    protected NamedEntity(int ID, String name){
        super(ID);
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), ID, name);
    }
}
