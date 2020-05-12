


public class Node {
	
	/*
	 * The Node class defines each vertex and its getters and setters
	 */

	
	   // creating two instance variables for both x and y coordinate
		private int x;
		private int y;
		public int getX() {
			return x; 
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		//Overriding toString to print the vertexes whenever the node is printed
		@Override
		public String toString() {
			
			return "(x=" + x + ", y=" + y + ")";
		}
		
		
	
	}
