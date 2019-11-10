package Player;

import Entity.Enemy.WaveManager;
import Entity.Tower.BasicTower;
import State.State.StateManager;
import Tile.Tile;
import Tile.TileGrid;
import Tile.TileType;
import helpers.Clock;
import org.lwjgl.Sys;
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
	 private boolean holdingUpgrade;
	 private boolean holdingRefund;
	 private static boolean win;
	 private BasicTower tempTower;


	private static final int STARTINGCREDITS = 200;
	private static final int STARTINGHEALTH = 5;
	 
	 
	 public Player( TileGrid grid, WaveManager waveManager) {
		 this.grid = grid;
		 types = new TileType[3];
		 this.types[0] = TileType.Mountain;
		 this.types[1] = TileType.Way;
		 this.types[2] = TileType.Rock;
		 this.index = 0;
		 this.waveManager = waveManager;
		 towerList = new ArrayList<BasicTower>();
		 this.leftMouseButtonDown = false;
		 this.rightMouseButtonDown = false;
		 this.health = STARTINGHEALTH;
		 this.credits = STARTINGCREDITS;
		 this.holdingTower = false;
		 this.holdingUpgrade = false;
		 this.holdingRefund = false;
		 this.tempTower = null;
		 this.win = false;
	 }
	 
	 public void SetTile() {
		 grid.SetTile((int) Math.floor ( Mouse.getX() / 32 ) , (int) Math.floor ((HEIGHT - Mouse.getY() - 1) / 32), types[index]);
	 }
	 
	 public void Update() {

	 	 whenHoding();

		 if(Mouse.isButtonDown(0) && !leftMouseButtonDown) {
		 	placeTower();
		 	refundTower();
		 	upgradeTower();
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

	 public String healthString() {
	 	return "Health: " + Integer.toString(health);
	 }

	 public String creditsString() {
	 	return "Credits: " + Integer.toString(credits);
	 }

	 public void MoveIndex() {
		 index ++;
		 if(index > types.length - 1 ) {
			 index = 0;
		 }
	 }

	 private void whenHoding() {
		 if (holdingTower) {
			 this.tempTower.setX(getMouseTile().getX());
			 this.tempTower.setY(getMouseTile().getY());
			 tempTower.Draw();
		 }

		 if (holdingUpgrade) {
			 drawQuadTex(QuickLoad("UpgradeButtonGraphic"),
					 getMouseTile().getX(), getMouseTile().getY(),
					 QuickLoad("UpgradeButtonGraphic").getImageWidth(),
					 QuickLoad("UpgradeButtonGraphic").getImageHeight());
		 }

		 if (holdingRefund) {
			 drawQuadTex(QuickLoad("SellButtonGraphic"),
					 getMouseTile().getX(), getMouseTile().getY(),
					 QuickLoad("SellButtonGraphic").getImageWidth(),
					 QuickLoad("SellButtonGraphic").getImageHeight());
		 }
	 }

	 private void placeTower() {
	 	if (holdingTower) {
			if (grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getType() != TileType.Way
					&& !grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).isSolic()
					&& credits >= tempTower.getBuyingCost()) {
				towerList.add(tempTower);
				grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).setTower(tempTower);
				grid.getTile(Mouse.getX() / 32, (HEIGHT - Mouse.getY() - 1) / 32).setSolic(true);
				credits -= tempTower.getBuyingCost();
			}
			holdingTower = false;
			tempTower = null;
			System.out.println(credits);
		}
	 }

	 private void refundTower() {
	 	if (holdingRefund) {
	 		BasicTower pickingTower = grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getTower();
	 		if (pickingTower != null) {
	 			towerList.remove(pickingTower);
				credits = credits + pickingTower.getRefundPrize();
				grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).setTower(null);
				grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).setSolic(false);
				System.out.println(credits);
			}
		}
		 holdingRefund = false;
	 }


	 private void upgradeTower() {
	 	if (holdingUpgrade) {
	 		BasicTower pickingTower = grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getTower();
	 		if(pickingTower != null) {
				pickingTower.upgrade();
				System.out.println(credits);
			}
		}
		holdingUpgrade = false;
	 }

	 private Tile getMouseTile() {
	 	return grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32);
	 }
	 
	 public static void addCredits(int amount) {
	 	credits += amount;
	 }
	 
	 public static void isAttack(int damage) {
	 	health -= damage;
	 	if (health <= 0) {
            StateManager.setState(StateManager.GameState.AFTERGAME);
            StateManager.setRestarted(false);
        }
	 }

	public BasicTower getTempTower() {
		return tempTower;
	}

	public void setTempTower(BasicTower tempTower) {
		this.tempTower = tempTower;
		holdingTower = true;
	}

	public boolean isHoldingUpgrade() {
		return holdingUpgrade;
	}

	public void setHoldingUpgrade(boolean holdingUpgrade) {
		this.holdingUpgrade = holdingUpgrade;
	}

	public boolean isHoldingRefund() {
		return holdingRefund;
	}

	public void setHoldingRefund(boolean holdingRefund) {
		this.holdingRefund = holdingRefund;
	}

	public static int getHealth() {
		return health;
	}

	public static int getCredits() {
		return credits;
	}

	public static void setCredits(int credits) {
		Player.credits = credits;
	}

	public static boolean isWin() {
		return win;
	}

	public static void setWin(boolean win) {
	 	Player.win = win;
	}
}
