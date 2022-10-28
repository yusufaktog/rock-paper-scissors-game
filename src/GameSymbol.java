import javax.swing.ImageIcon;

public class GameSymbol {

    private String name;
    private ImageIcon image;
    private final int priority;
    protected Player owner;

    public GameSymbol(String name,int priority, ImageIcon image) {
        this.name = name;
        this.priority = priority;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return this.priority;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
