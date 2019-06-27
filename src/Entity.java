import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
    private int ID;
    public long HP;
    public int Radius;
    public int MS;   //move speed
    public int DMG;
    public int x;
    public int y;
    private Image image; //изображение

    public Entity(int ID,int HP,int Radius,int MS,int DMG,int x, int y, String path){
        setID(ID);
        setHP(HP);
        setRad(Radius);
        setMS(MS);
        setDMG(DMG);
        setcoord(x, y);
        setSprite(path);
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
    protected void setDMG(int DMG){
        this.DMG = DMG;
    }
    public int IsNew(){
    	this.ID = -1;
        return (int) (this.ID);
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
	
	public void setSprite(String path) {
		
		BufferedImage sourceImage = null;
		try {
			java.net.URL url = this.getClass().getClassLoader().getResource(path);
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
		this.image = image;
	}
	
	public int getWidth() { //получаем ширину картинки
		return image.getWidth(null);
	}

	public int getHeight() { //получаем высоту картинки
		return image.getHeight(null);
	}
	
	public void draw(Graphics g) { //рисуем картинку
		g.drawImage(image,x,y,null);
	}
}
