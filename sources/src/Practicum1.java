package src;

public class Practicum1 {

	public static void main(String[] args) {
		//Opdracht 1
		for (int i = 1; i <= 10; ++i)
			System.out.println(i);

		//Opdracht 2
		int counter = 1;
		while (counter <= 10)
			System.out.println(counter++);

		//Opdracht 3
		for (int i = 0; i < 10; ++i)
			System.out.println(Math.random());

		//Opdracht 4
		int theSum = 0;
		for (int i = 1; i <= 39; ++i) //Beginnen vanaf 1 aangezien nul optellen geen effect heeft
			theSum += i;
		System.out.println("Verwachte uitkomst: 780, Berekende uitkomst: " + theSum);

		//Opdracht 5
		for (int i = 0; i < 4; ++i)
			System.out.println(i % 2 == 0 ? "s" : "ss");
	}
}
