public class Projectile extends Entity {

		public Projectile(int ID, int HP, int Radius, int MS, int DMG, int x, int y, String path) {
			super(ID, HP, Radius, MS, DMG, x, y, "bullet.png");
			// TODO Auto-generated constructor stub
		}
		public void MoveRight(int x, int y)
		{
			x=+3;
			y=y*1;
		}
		public void MoveLeft(int x, int y)
		{
			x=-3;
			y=y*1;
		}
		public void MoveUp(int x int y)
		{
			x=x*1;
			y=+3;
		}
		public void MoveDown(int x, int y)
		{
			x=x*1;
			y=-3;
		}
		public void MoveRightUp(int x, int y)
		{
			x=+3;
			y=+3;
		}
		public void MoveRightDown(int x, int y)
		{
			x=+3;
			y=-3;
		}
		public void MoveLeftUp(int x, int y)
		{
			x=-3;
			y=+3;
		}
		public void MoveLeftDown(int x, int y)
		{
			x=-3;
			y=-3;
		}
		

}
