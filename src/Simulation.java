import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simulation class to build a Customer and Tech call simulation
 * 
 * @author Sprague
 *
 */
public class Simulation {
	/**
	 * Num of customers for initial queue
	 */
	private int numCust;
	/**
	 * iterations of how many times to print to the console of calls
	 */
	private int iterations;
	/**
	 * number of seconds to wait to print
	 */
	private int intervalSec;
	/**
	 * ArrayList of the techs
	 */
	private ArrayList<Tech> techs;
	/**
	 * Arraylist of Customers
	 */
	private ArrayList<Customer> customers;
	/**
	 * Queue of customers
	 */
	private Queue<Customer> custQueue;
	/**
	 * Queue of Techs
	 */
	private Queue<Tech> techQueue;

	/**
	 * Enum of the day of the week for the Tech schedule
	 * 
	 * @author Sprague
	 *
	 */
	public enum Day {
		/** Monday */
		MON,
		/** Tuesday */
		TUES,
		/** Wednesday */
		WED,
		/** Thursday */
		THUR,
		/** Friday */
		FRI,
		/** Saturday */
		SAT,
		/** Sunday */
		SUN
	}

	/**
	 * Simulation constructor
	 * 
	 * @param numCust
	 *            Int for initial queue
	 * @param iterations
	 *            Int of how many times to print to the console
	 * @param intervalSec
	 *            how long until the next print
	 * @throws ClassNotFoundException
	 *             not found
	 * @throws IOException
	 *             not found
	 */
	public Simulation(int numCust, int iterations, int intervalSec) throws ClassNotFoundException, IOException {
		this.numCust = numCust;
		this.iterations = iterations;
		this.intervalSec = intervalSec;

		techs = new ArrayList<Tech>();
		customers = new ArrayList<Customer>();
		techQueue = new Queue<Tech>();
		custQueue = new Queue<Customer>();

		// READ CSV FILE
		readTechFile();
		readCustFile();
	}

	/**
	 * Call Run to execute Building the tech queue, and start the simulation
	 * 
	 * @param day DAY enum
	 */
	public void run(Day day) {
		// BUILD QUEUE
		buildQ(day);
		System.out.println("-----" + day.name() + " SIMULATION-----" + "CustomerQueueSize: " + custQueue.size()
				+ " Iterations: " + iterations);
		// PRINT THE CUSTOMER AND TECH FROM QUEUE
		new SimulationTimer();
	}

	/**
	 * Build the initial customer and tech queue
	 * 
	 * @param day
	 *            Enum Day of the tech schedule
	 */
	private void buildQ(Day day) {
		// BUILD QUEUE
		ArrayList<Tech> scheduledTechs = getScheduledTechs(day);
		Random rand = new Random();
		for (int i = 0; i < numCust; i++) {
			custQueue.add(pickRandCustomer());
		}
		for(int i = scheduledTechs.size()-1; i > 0; i--) {
			// SELECT RANDOM CUSTOMER and TECH from ARRAYLIST
			int techIdx = rand.nextInt(i);
			Tech tech = scheduledTechs.get(techIdx);
			techQueue.add(tech);
			scheduledTechs.remove(techIdx);
		}
	}

	/**
	 * private helper function to pick a random customer from the array that doesnt
	 * exist in the queue
	 * 
	 * @return Customer
	 */
	private Customer pickRandCustomer() {
		Random rand = new Random();
		Customer cust = customers.get(rand.nextInt(customers.size() - 1));
		while (custQueue.contains(cust)) {
			cust = customers.get(rand.nextInt(customers.size() - 1));
		}

		return cust;
	}

	/**
	 * Simpulation Timer to print the queues
	 * 
	 * @author Sprague
	 *
	 */
	private class SimulationTimer {
		/**
		 * Timer
		 */
		Timer timer;

		/**
		 * Sim Timer constructor
		 */
		public SimulationTimer() {
			timer = new Timer();
			timer.schedule(new SimTask(), 0, intervalSec * 1000);
		}

		/**
		 * Task to run and override TimerTask
		 * 
		 * @author Sprague
		 *
		 */
		class SimTask extends TimerTask {
			/**
			 * Print the customer and tech from the queue
			 */
			public void run() {

				Customer custFront = custQueue.peek();
				Tech techFront = techQueue.peek();

				System.out.println(custFront.toString());
				System.out.println(techFront.toString());

				custQueue.remove();

				techQueue.add(techQueue.remove());
				custQueue.add(pickRandCustomer());
				iterations--;

				if (iterations == 0) {
					timer.cancel();
					System.out.println("-----SIMULATION HAS ENDED-----");
				}
			}
		}
	}

	/**
	 * Private list to get the techs with the specified scheduled day
	 * 
	 * @param day
	 *            Day of the week
	 * @return ArrayList of techs
	 */
	private ArrayList<Tech> getScheduledTechs(Day day) {
		ArrayList<Tech> techsByDay = new ArrayList<Tech>();

		for (Tech tech : techs) {
			String[] schedule = tech.getSchedule().split("");

			for (int i = 0; i < schedule.length; i++) {
				if (day.ordinal() + 1 == Integer.parseInt(schedule[i])) {
					techsByDay.add(tech);
				}
			}
		}
		return techsByDay;
	}

	/**
	 * Read the file with the tech information
	 * 
	 * @throws IOException error
	 * @throws ClassNotFoundException error
	 */
	private void readTechFile() throws IOException, ClassNotFoundException {
		techs = new ArrayList<Tech>();
		Path folderPath = Paths.get(System.getProperty("user.dir") + "/files/TECHS.csv");
		File f = new File(folderPath.toString());

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {

			String line = "";
			int row = 0;

			while ((line = br.readLine()) != null) {
				if (row > 0) {
					String[] array = line.split(",");
					techs.add(new Tech(array[0], array[1], array[2], array[3], array[4]));
				}
				row++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * read the customers file
	 * 
	 * @throws IOException error
	 * @throws ClassNotFoundException error
	 */
	private void readCustFile() throws IOException, ClassNotFoundException {
		customers = new ArrayList<Customer>();
		Path folderPath = Paths.get(System.getProperty("user.dir") + "/files/CUST.csv");
		File f = new File(folderPath.toString());

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line = "";
			int row = 0;

			while ((line = br.readLine()) != null) {
				if (row > 0) {
					String[] array = line.split(",");
					customers.add(new Customer(array[0], array[1], array[2], array[3], array[4]));
				}
				row++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
