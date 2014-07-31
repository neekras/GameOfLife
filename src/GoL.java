/* GoL.java
 * Author: Nick Hampshire
 * Website: nhampshire.com
 * Description:
 * Runs game of life in a JFrame
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;

public class GoL extends JFrame
{
	int w = 150; // width of array
	int h = 150; //height of array
	int[][] cellList = new int[h][w]; //stores cell data (1 = alive, 0 = dead)
	Random rand = new Random();
	int winX = 600; //window width
	int winY = 600; //window height
	int generation = 0; //current generation - will be displayed as window title

	/**
	 * Sets all cells to dead, JFrame info
	 */
	public GoL()
	{
		this.setSize(winX,winY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
		getContentPane().setBackground(Color.BLACK);
		for(int x=0;x<cellList.length;x++)
		{
			for(int y=0;y<cellList.length;y++)
				{
					cellList[x][y] = 0;
				}
		}
	}
	/**
	 * Infinitely runs Game of Life, updates cells each generation and displays them.
	 */
	public void run()
	{
		initialize();
		visible(true);
		while(true)
		{
			cellList = updateCells();
			paintCells();
			//try{Thread.sleep(300);}catch(Exception e){} //if you want to slow it down
		}
	}
	/**
	 * Sets visibility of window (if working in console, hide window before running)
	 *
	 * @param state - boolean defining whether or not window should be visible
	 */
	public void visible(boolean state)
	{
		this.setVisible(state);
	}
	/**
	 * Assigns a random 10% of cells to be alive initially
	 */
	public void initialize()
	{
		for(int x=0;x<cellList.length;x++)
		{
			for(int y=0;y<cellList.length;y++)
			{
				int num = rand.nextInt(10);
				if(num == 1){cellList[x][y] = 1; }
				else{cellList[x][y] = 0;}		
			}
		}
	}

	/**
	 * Paints alive cells over a black rectangle
	 */
	public void paintCells()
	{
		Graphics g = getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 2000, 2000); //cover old generation
		for(int x=0;x<cellList.length;x++)
		{
			for(int y=0;y<cellList.length;y++)
			{
				int rectX = Math.round(getInsets().left + 4 * y); //take into account top and side insets
				int rectY = Math.round(getInsets().top + 4 * x);
				int rectWidth = 4;
				int rectHeight = 4;
				if(cellList[x][y] == 1)
				{
					g.setColor(Color.GREEN);
					g.fillRect(rectX, rectY, 4, 4);
				}
			}
		}
	}
	/**
	 * Checks neighbours of each cell and increments neighbours for each that is alive.
	 * Determines whether the current cell is to be alive or dead in the next generation.
	 * @return array containing next generation of cells.
	 */
	public int[][] updateCells()
	{
		int[][] tempList = new int[h][w];
		for(int x=0;x<cellList.length;x++)
		{
			for(int y=0;y<cellList.length;y++)
			{
				int neighbours = 0;
				try{if(cellList[x-1][y] == 1){neighbours++;}}catch(Exception e){} //top
				try{if(cellList[x+1][y] == 1){neighbours++;}}catch(Exception e){} //bottom
				try{if(cellList[x][y-1] == 1){neighbours++;}}catch(Exception e){} //left
				try{if(cellList[x][y+1] == 1){neighbours++;}}catch(Exception e){} //right
				try{if(cellList[x-1][y-1] == 1){neighbours++;}}catch(Exception e){} //top left
				try{if(cellList[x-1][y+1] == 1){neighbours++;}}catch(Exception e){} //top right
				try{if(cellList[x+1][y-1] == 1){neighbours++;}}catch(Exception e){} //bottom left
				try{if(cellList[x+1][y+1] == 1){neighbours++;}}catch(Exception e){} //bottom right
				if(neighbours<2 || neighbours>3){tempList[x][y] = 0;} //if underpopulated or overpopulated kill cell
				else if(neighbours == 3){tempList[x][y] = 1;} //cell is born/cell remains alive
				else if(neighbours == 2 && cellList[x][y] == 1){tempList[x][y] = 1;} //cell remains alive
				else{tempList[x][y] = 0;} //cell remains dead
			}
		}
		this.setTitle("Generation: " + generation);
		generation++;
		return tempList;
	}
	/**
	 * Draws a "Gosper glider gun"
	 *
	 * @param a, b - offsets origin of gun.
	 */
	public void initializeGun(int a, int b)
	{
		int q = a;
		int z = b;
		//left block
		cellList[15+q][11+z]	= 1;
		cellList[16+q][11+z]	= 1;
		cellList[15+q][12+z]	= 1;
		cellList[16+q][12+z]	= 1;
		//right block
		cellList[13+q][45+z]	= 1;
		cellList[14+q][45+z] = 1;
		cellList[13+q][46+z] = 1;
		cellList[14+q][46+z] = 1;

		cellList[15+q][21 + z]	= 1;
		cellList[16+q][21 + z]	= 1;
		cellList[17+q][21 + z]	= 1;

		cellList[14+q][22 + z]	= 1;
		cellList[18+q][22 + z]	= 1;

		cellList[13+q][23 + z]	= 1;
		cellList[19+q][23 + z]	= 1;
		cellList[13+q][24 + z]	= 1;
		cellList[19+q][24 + z]	= 1;

		cellList[16+q][25 + z] 	= 1;

		cellList[14+q][26 + z]	= 1;
		cellList[18+q][26 + z]	= 1;

		cellList[15+q][27 + z]	= 1;
		cellList[16+q][27 + z]	= 1;
		cellList[17+q][27 + z]	= 1;

		cellList[16+q][28 + z]	= 1;
		//-------------------------

		cellList[13+q][31 + z]	= 1;
		cellList[14+q][31 + z] = 1;
		cellList[15+q][31 + z]	= 1;
		cellList[13+q][32 + z] = 1;
		cellList[14+q][32 + z] = 1;
		cellList[15+q][32 + z] = 1;
		
		cellList[12+q][33 + z] = 1;
		cellList[16+q][33 + z] = 1;

		cellList[11+q][35 + z] = 1;
		cellList[12+q][35 + z] = 1;
		cellList[16+q][35 + z] = 1;
		cellList[17+q][35 + z] = 1;
	}
}
