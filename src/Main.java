import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
			
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number of customers to start the Queue: ");
		while(!scanner.hasNextInt()) {
			System.out.println("Please enter a number between 0-1000 for number of customers");
			scanner.nextLine();
		}
	
		int numCust = scanner.nextInt();
		
		System.out.println("Enter the number of iterations: ");
		while(!scanner.hasNextInt()) {
			System.out.println("Please enter a valid number of intervals");
			scanner.nextLine();
		}
		int iterations = scanner.nextInt();
		
		System.out.println("Enter the seconds for the call interval: ");
		while(!scanner.hasNextInt()) {
			System.out.println("Please enter a valid number of seconds");
			scanner.nextLine();
		}
		int intervalSec = scanner.nextInt();
		
		scanner.close();
		
		Simulation s = new Simulation(numCust,iterations,intervalSec);

		s.run(Simulation.Day.MON);
		
	}

}
