package dev.jason.tilegame.input.pathfinder;

import java.util.ArrayList;

/**
 * A path determined by some path finding algorithm. A series of steps from
 * the starting location to the target location. This includes a step for the
 * initial location.
 * 
 * @author Kevin Glass
 */
public class Path {
	/** The list of steps building up this path */
	private ArrayList steps = new ArrayList();
	private String[] directions;
	
	/**
	 * Create an empty path
	 */
	public Path() {
		
	}

	/**
	 * Get the length of the path, i.e. the number of steps
	 * 
	 * @return The number of steps in this path
	 */
	public int getLength() {
		return steps.size();
	}
	
	/**
	 * Get the step at a given index in the path
	 * 
	 * @param index The index of the step to retrieve. Note this should
	 * be >= 0 and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}
	
	/**
	 * Get the x coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getX(int index) {
		return getStep(index).x;
	}

	/**
	 * Get the y coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getY(int index) {
		return getStep(index).y;
	}
	
	/**
	 * Append a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x,y));
	}

	/**
	 * Prepend a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}
	
	/**
	 * Check if this path contains the given step
	 * 
	 * @param x The x coordinate of the step to check for
	 * @param y The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x,y));
	}
	
	public void generateDirections(){
		directions = new String[getLength() - 1];
		for(int i = 0; i < getLength() - 1; i++){	
			// Unit circle/cartesian coordiate system 
			int xDir = ((Step) steps.get(i + 1)).getX() - ((Step) steps.get(i)).getX();
			int yDir = ((Step) steps.get(i)).getY() - ((Step) steps.get(i + 1)).getY();
			
			if(xDir == 0 && yDir == 1){
				directions[i] = "U";
			}else if(xDir == 1 && yDir == 1){
				directions[i] = "UR";
			}else if(xDir == 1 && yDir == 0){
				directions[i] = "R";
			}else if(xDir == 1 && yDir == -1){
				directions[i] = "DR";
			}else if(xDir == 0 && yDir == -1){
				directions[i] = "D";
			}else if(xDir == -1 && yDir == -1){
				directions[i] = "DL";
			}else if(xDir == -1 && yDir == 0){
				directions[i] = "L";
			}else if(xDir == -1 && yDir == 1){
				directions[i] = "UL";
			}	
			//System.out.print(directions[i]+" ");
		}	
		//System.out.println("\n");	
	}
	
	public String[] getDirections(){
		return directions;
	}
	
	/**
	 * A single step within the path
	 * 
	 * @author Kevin Glass
	 */
	public class Step {
		/** The x coordinate at the given step */
		private int x;
		/** The y coordinate at the given step */
		private int y;
		
		/**
		 * Create a new step
		 * 
		 * @param x The x coordinate of the new step
		 * @param y The y coordinate of the new step
		 */
		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Get the x coordinate of the new step
		 * 
		 * @return The x coodindate of the new step
		 */
		public int getX() {
			return x;
		}

		/**
		 * Get the y coordinate of the new step
		 * 
		 * @return The y coodindate of the new step
		 */
		public int getY() {
			return y;
		}
		
		/**
		 * @see Object#hashCode()
		 */
		public int hashCode() {
			return x*y;
		}

		/**
		 * @see Object#equals(Object)
		 */
		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;
				
				return (o.x == x) && (o.y == y);
			}
			
			return false;
		}
	}
}
