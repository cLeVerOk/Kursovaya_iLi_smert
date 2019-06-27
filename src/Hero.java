public class Hero extends Entity{

		public Hero(int ID, int HP, int Radius, int MS, int DMG, int x, int y, String path) {
		super(ID, HP, Radius, MS, DMG, x, y, path);
	}
	
	private int multiplier = 1;

	public void update(boolean leftPressed,boolean rightPressed,boolean downPressed,boolean upPressed,boolean shiftPressed, boolean spacePressed) {
		if (shiftPressed == true) {
			multiplier = 2;
		}else if(shiftPressed == false) {
			multiplier = 1;
		}
		if (leftPressed == true) {
				x-=2*multiplier;
		}

		if (rightPressed == true) {
				x+=2*multiplier;
		}
		
		if (upPressed == true) {
				y-=2*multiplier;
		}
		
		if (downPressed == true) {
				y+=2*multiplier;
		}

	}
}
