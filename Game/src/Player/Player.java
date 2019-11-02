package Player;

import Entity.Enemy.WaveManager;
import Entity.Tower.BasicTower;
import Entity.Tower.MachineGunTower;
import Entity.Tower.NormalTower;
import Entity.Tower.SniperTower;
import Tile.Tile;
import Tile.TileGrid;
import Tile.TileType;
import helpers.Clock;
import helpers.StateManager;
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
	 private boolean rightMouseButtonDown;
	 private boolean holdingTower;
	 private BasicTower tempTower;


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
		 this.rightMouseButtonDown = false;
		 this.health = STARTINGHEALTH;
		 this.credits = STARTINGCREDITS;
		 this.holdingTower = false;
		 this.tempTower = null;
	 }
	 
	 public void SetTile() {
		 grid.SetTile((int) Math.floor ( Mouse.getX() / 32 ) , (int) Math.floor ((HEIGHT - Mouse.getY() - 1) / 32), types[index]);
	 }
	 
	 public void Update() {

	 	if (holdingTower) {
	 		this.tempTower.setX(getMouseTile().getX());
	 		this.tempTower.setY(getMouseTile().getY());
	 		tempTower.Draw();
		}


		 if(Mouse.isButtonDown(0) && !leftMouseButtonDown) {
		 	placeTower();
		 }

		 for(BasicTower t : towerList){
		 	t.setEnemies(waveManager.getCurrentWave().getEnemyList());
		 	t.update();
		 }

		 leftMouseButtonDown = Mouse.isButtonDown(0);
		 rightMouseButtonDown = Mouse.isButtonDown(1);

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

	 private void placeTower() {
	 	if (holdingTower) {
			if (grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getType() != TileType.Sand
					&& !grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).isSolic()
					&& this.credits >= tempTower.getBuyingCost()) {
				towerList.add(tempTower);
				grid.getTile(Mouse.getX() / 32, (HEIGHT - Mouse.getY() - 1) / 32).setSolic(true);
				this.credits -= BasicTower.buyingCost;
			}
			holdingTower = false;
			tempTower = null;
			System.out.println(credits);
		}
	 }

	 private Tile getMouseTile() {
	 	return grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32);
	 }
	 
	 public static void addCredits(int amount) {
	 	credits += amount;
	 }
	 
	 public static void isAttack(int damage) {
	 	health -= damage;
	 	if (health <= 0) StateManager.setState(StateManager.GameState.AFTERGAME);;
	 }

	public BasicTower getTempTower() {
		return tempTower;
	}

	public void setTempTower(BasicTower tempTower) {
		this.tempTower = tempTower;
		holdingTower = true;
	}
}
