package Tile;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;


public class Tile {

    private float x , y , width , height;
    private Texture texture;
    private TlieType type;
    private boolean solic;



    public Tile(float x , float y , float width , float height , TlieType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = QuickLoad(type.textureName);
        this.solic = false;
    }

    public void Draw() {
        drawQuadTex(texture , x , y ,width , height);
    }

    public int getXPlace() {
        return (int)(x / 32)  ;
    }

    public int getYPlace() {
        return (int)(y / 32)  ;
    }

    public float getX() {
        return x;
    }



    public void setX(float x) {
        this.x = x;
    }



    public float getY() {
        return y;
    }



    public void setY(float y) {
        this.y = y;
    }



    public float getWidth() {
        return width;
    }



    public void setWidth(float width) {
        this.width = width;
    }



    public float getHeight() {
        return height;
    }



    public void setHeight(float height) {
        this.height = height;
    }



    public Texture getTexture() {
        return texture;
    }



    public void setTexture(Texture texture) {
        this.texture = texture;
    }



    public TlieType getType() {
        return type;
    }



    public void setType(TlieType type) {
        this.type = type;
    }

    public boolean isSolic() {
        return solic;
    }

    public void setSolic(boolean solic) {
        this.solic = solic;
    }
}
