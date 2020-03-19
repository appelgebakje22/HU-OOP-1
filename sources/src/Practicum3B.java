package src;

public class Practicum3B {

	public static void main(String[] args)
	{
		Cirkel c1 = null, c2 = null;

		//Hypothese: het programma stopt met uitvoeren
		try
		{
			c1 = new Cirkel(10, 0, 0);
			c2 = new Cirkel(0, 10, 10);
		}
		catch (IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
		}
		//Antwoord: het programma stop interdaad met uitvoeren
		/*
		 * Aangezien IllegalStateException een subclass van RuntimeException is, hoeft deze niet te worden opgevangen.
		 * Dit heet als resultaat dat java "crasht" zodra deze exception buiten de "main"-method valt.
		 */

		System.out.println(c1);
		System.out.println(c2);
	}

	private static class Cirkel {
		private int radius;
		private int xPositie, yPositie;

		public Cirkel(int rad, int x, int y)
		{
			if (rad <= 0)
				throw new IllegalArgumentException("Radius moet groter dan 0 zijn!");
			this.radius = rad;
			this.xPositie = x;
			this.yPositie = y;
		}

		@Override
		public String toString()
		{
			return String.format("circel (%s, %s) met radius: %s", this.xPositie, this.yPositie, this.radius);
		}
	}
}
