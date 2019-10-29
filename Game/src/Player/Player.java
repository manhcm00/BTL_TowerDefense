package Player;

import Entity.Tower.BasicTower;
import Entity.Tower.WaveManager;
import Tile.TileGrid;
import Tile.TlieType;
import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.*;



public class Player {
	 private TileGrid grid;
	 private TlieType[] types;
	 private int index;
	 private WaveManager waveManager;
	 private ArrayList<BasicTower> towerList;
	 public boolean leftMouseButtonDown;
	 
	 
	 public Player( TileGrid grid, WaveManager waveManager) {
		 this.grid = grid;
		 types = new TlieType[3];
		 this.types[0] = TlieType.Grass;
		 this.types[1] = TlieType.Sand;
		 this.types[2] = TlieType.Rock;
		 this.index = 0;
		 this.waveManager = waveManager;
		 towerList = new ArrayList<BasicTower>();
		 this.leftMouseButtonDown = false;
	 }
	 
	 public void SetTile(  ) {
		 grid.SetTile((int) Math.floor ( Mouse.getX() / 32 ) , (int) Math.floor ((HEIGHT - Mouse.getY() - 1) / 32), types[index]);
	 }
	 
	 public void Update() {
	 	for (BasicTower t : towerList) t.update();

		 if(Mouse.isButtonDown(0) && !leftMouseButtonDown) {
		 	if (grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32).getType() != TlieType.Sand)
			 	towerList.add(new BasicTower(QuickLoad("enemy"), grid.getTile(Mouse.getX() / 32,(HEIGHT - Mouse.getY() - 1) / 32), 10, waveManager.getCurrentWave().getEnemyList()));
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
			 if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
			 	towerList.add(new BasicTower(QuickLoad("enemy"), grid.getTile(9,9), 10, waveManager.getCurrentWave().getEnemyList()));
			 }
		 }
	 }
	 
	 public void MoveIndex() {
		 index ++;
		 if(index > types.length - 1 ) {
			 index = 0;
		 }
	 }
	 
	 
	 
	 
}
