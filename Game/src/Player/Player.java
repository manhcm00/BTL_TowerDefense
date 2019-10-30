package Player;

import Entity.Enemy.WaveManager;
import Entity.Tower.BasicTower;
import Entity.Tower.MachineGunTower;
import Entity.Tower.NormalTower;
import Entity.Tower.SniperTower;
import Tile.TileGrid;
import Tile.TileType;
import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.*;



public class Player {
	 private TileGrid grid;
	 private TileType[] types;
	 private int index;
	 private WaveManager waveManager;
	 private ArrayList<BasicTower> towerList;
	 private static int credits = 0;
	 private static int health;
	 private boolean leftMouseButtonDown;

	private static final int STARTINGCREDITS = 200;
	private static final int STARTINGHEALTH = 5;
	 
	 
	 public Player( TileGrid grid, WaveManager waveManager) {
		 this.grid = grid;
		 types = new TileType[3];
		 this.types[0] = TileType.Grass;
		 this.types[1] = TileType.Sand;
		 this.types[2] = TileType.Rock;
		 this.index = 0;
		 this.waveManager = waveManager;
		 towerList = new ArrayList<BasicTower>();
		 this.leftMouseButtonDown = false;
		 this.health = STARTINGHEALTH;
		 this.credits = STARTINGCREDITS;
	 }
	 
	 public void SetTile(  ) {
		 grid.SetTile((int) Math.floor ( Mouse.getX() / 32 ) , (int) Math.floor ((HEIGHT - Mouse.getY() - 1) / 32), types[index]);
	 }
	 
	 public void Update() {
	 	 for (BasicTower t : towerList) t.update();
		 if(Mouse.isButtonDown(0)
				 && !leftMouseButtonDown && !grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).isSolic()
				 && this.credits >= BasicTower.buyingCost) {
		 	if (grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getType() != TileType.Sand)
			 	towerList.add(new MachineGunTower(grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32), waveManager.getCurrentWave().getEnemyList()));
		 		grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).setSolic(true);
			 	this.credits -= BasicTower.buyingCost;
		 }
		 for(BasicTower t : towerList){
		 	t.setEnemies(waveManager.getCurrentWave().getEnemyList());
		 }
		 leftMouseButtonDown = Mouse.isButtonDown(0);

		 while(Keyboard.next()) {
			 if( Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState() ) {
				 Clock.changeMultiplier(0.2f);
			 }
			 if( Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState() ) {
				 Clock.changeMultiplier(-0.2f);
			 }
		 }
	 }
	 
	 public void MoveIndex() {
		 index ++;
		 if(index > types.length - 1 ) {
			 index = 0;
		 }
	 }
	 
	 public static void addCredits(int amount) {
	 	credits += amount;
	 }
	 
	 public static void isAttack(int damage) {health -= damage;}
}
