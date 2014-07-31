/* GoLInterface.java
 * Author: Nick Hampshire
 * Website: nhampshire.com
 * Description:
 * Provides an interface when needed, runs instance of Game of Life.
 */

import java.io.*;
import java.util.*;

public class GoLInterface
{
	//long start = System.nanoTime(); //for timing program
	GoL gol = new GoL();
	//Scanner scanner = new Scanner(System.in);
	public void run()
	{
		gol.run();
		//double elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9; //for timing program (when not infinitely looping)
		//System.out.println(elapsedTimeInSec);
	}
	
	/**
	 * Creates instance of GoLInterface, use run() for non-static method
	 */
	public static void main(String[] args)
	{
		GoLInterface ifc = new GoLInterface();
		ifc.run();
	}
}
