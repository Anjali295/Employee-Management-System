import java.util.Scanner;
import java.util.InputMismatchException;

public class PersonManager {
	private static PersonDataManager pdm= new PersonDataManager();

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.println("Welcome... Starting in few moments...");
		System.out.println("MENU IS HERE! :");
		int i = 0;
		while (i != -1) {
			System.out.println("(I)- Import from File" + '\n' + "(A)- Add Person" + '\n' + "(R)- Remove Person" + '\n'
					+ "(G)- Get Information" + '\n' + "(P)- Print Table" + '\n' + "(S)- Save to File" + '\n'
					+ "(Q)- Quit");
			System.out.println("Please select option: ");
			String input = stdin.nextLine();

			if (input.toUpperCase().equals("Q")) {
				System.out.println("Sorry to see you go! Have a good day :)");
				i = -1;
			} else if (input.toUpperCase().equals("I")) {
				System.out.println("Please enter a location: ");
				String loc = stdin.nextLine();
				PersonDataManager.buildFromFile(loc);
				System.out.println("Loading...." + '\n' + "Person data loaded successfully!");

			} else if (input.toUpperCase().equals("P")) {
				pdm.printTable();
			} else if (input.toUpperCase().equals("A")) {
				try {
				System.out.println("Please enter the name of the person: ");
				String name = stdin.next();
				if(name.matches("-?\\d+")) {           // checking if the input is string 
					throw new IllegalArgumentException();
				}
				System.out.println("Please enter the age of the person: ");
		
				int age= stdin.nextInt();
				System.out.println("Please enter the gender: ");
				String gender = stdin.next();
				if (!gender.toUpperCase().equals("M") && !gender.toUpperCase().equals("F")) {

					throw new IllegalArgumentException();

				}
				System.out.println("Please enter the height of the person: ");
				double height = stdin.nextDouble();
				System.out.println("Please enter the weight of the person: ");
				double weight = stdin.nextDouble();
				HW1_Person newPerson = new HW1_Person(name, age, gender, height, weight);
				
					pdm.addPerson(newPerson);

					System.out.println(newPerson.getName() + " added succesfully");
				} catch (InputMismatchException ex) {
					System.out.println("Input mismatch !");
				}catch(IllegalArgumentException ex) {
					System.out.println("Input Mismatch !");
				}
				catch (PersonAlreadyExistsException io) {
					System.out.println("Person already exists");
				}
				stdin.nextLine();

			} else if (input.toUpperCase().equals("R")) {
				System.out.println("Enter the name of the person: ");
				String name = stdin.nextLine();
				try {
					pdm.removePerson(name);
					System.out.println(name + " has been removed.");
				} catch (PersonDoesNotExistsException pe) {
					System.out.println(name + " does not exist!");
				}

			} else if (input.toUpperCase().equals("G")) {
				System.out.println("Enter the name of the person: ");
				String name = stdin.nextLine();
				try {
					pdm.getPerson(name);

				} catch (PersonDoesNotExistsException pe) {
					System.out.println(name + " does not exist");
				}

			} else if (input.toUpperCase().equals("S")) {
				System.out.println("Please select the name for the file: ");
				String name = stdin.nextLine();
				try {
					pdm.SaveToFile(name);
					System.out.println("A file named " + name + " has been created !");
				} catch (Exception io) {
					System.out.println("");
				}
			}
			else {
				System.out.println("Please select valid option !");
			}

		}
		stdin.close();

	}
}
