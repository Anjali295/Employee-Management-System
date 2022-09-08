import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class PersonDataManager {
	private static HW1_Person[] peopleArray = new HW1_Person[0];
	private static int counter = 0;

	public PersonDataManager() { // no - arg constructor

	}

	public PersonDataManager(HW1_Person[] p) {
		this.peopleArray = p;

	}

	public static PersonDataManager buildFromFile(String location) throws IllegalArgumentException {

		try {
			File f = new File(location);

			Scanner read = new Scanner(f);
			int count = 0;
			while (read.hasNextLine()) {
				read.nextLine();
				count++;
			}

			peopleArray = new HW1_Person[count - 1];

			read = new Scanner(f);
			// skip line 1
			read.nextLine();

			int index = 0;
			while (read.hasNextLine()) {
				String temp = read.nextLine(); // reading the data from file

				String[] vals = temp.split(",");
				if (vals.length != 5)
					continue;

				String name = vals[0].replace('\"', ' ').trim();

				String gender = vals[1].replace('\"', ' ').trim();

				if (!gender.toUpperCase().equals("M") && !gender.toUpperCase().equals("F")) {

					throw new IllegalArgumentException();

				}

				if (name.matches("-?\\d+")) {
					throw new IllegalArgumentException();
				}

				int age = Integer.parseInt(vals[2].trim());
				double height = Double.parseDouble(vals[3].trim());
				double weight = Double.parseDouble(vals[4].trim());

				HW1_Person tempo = new HW1_Person(name, age, gender, height, weight);
				for (int i = 0; i <= index; i++) {
					if (contains(tempo)) {
						throw new IllegalArgumentException();
					}
				}
				peopleArray[index] = tempo; // storing the data in peopleArray

				index++;
			}
			read.close();
			PersonDataManager pm = new PersonDataManager(peopleArray);
			counter = peopleArray.length;
			return pm;

		} catch (IOException io) {
			System.out.print("File not found !");
		} catch (IllegalArgumentException ex) {
			System.out.print(" Input is in the wrong format . Please check the format !");
			throw new IllegalArgumentException();

		}

		throw new IllegalArgumentException();

	}

	public static boolean contains(HW1_Person person2) { // checks if the person with same data exists
		for (int i = 0; i < peopleArray.length; i++) {
			if (peopleArray[i] != null && peopleArray[i].equals(person2)) {
				return true;
			}
		}

		return false;

	}

	public void addPerson(HW1_Person newPerson) throws PersonAlreadyExistsException {

		HW1_Person[] newPeopleArray = new HW1_Person[peopleArray.length + 1];
		for (int i = 0; i < peopleArray.length; i++) {
			if (contains(newPerson)) {
				throw new PersonAlreadyExistsException();
			} else {
				System.arraycopy(peopleArray, 0, newPeopleArray, 0, peopleArray.length);
				newPeopleArray[peopleArray.length] = newPerson;
				break;
			}

		}
		sort(newPeopleArray);
		peopleArray = newPeopleArray;
		counter++;

	}

	public void printTable() {
		System.out.println(
				"               Name                  Age                  Gender             Height              Weight ");
		for (int i = 0; i < peopleArray.length - 1; i++) {
			if (peopleArray[i] != null) {
				System.out.println(peopleArray[i].toString());
			}
		}

	}

	public void getPerson(String name) throws PersonDoesNotExistsException {
		int count = 0;
		int temp = 0;
		for (int i = 0; i < peopleArray.length - 1; i++) {
			if (peopleArray[i] != null) {

				if ((peopleArray[i].getName().toUpperCase()).equals(name.toUpperCase())) {
					if (peopleArray[i].getGender().toUpperCase().equals("M")) {
						count = i;
						temp = 1;
						break;
					} else {
						count = i;
						temp = 2;
						break;
					}

				}

			}
		}
		if (temp == 1) {
			System.out.println(name + " is a " + peopleArray[count].getAge() + " years old male who is "
					+ peopleArray[count].getHeight() + " inches tall and weighs " + peopleArray[count].getWeight()
					+ " pounds");
			return;
		} else if (temp == 2) {
			System.out.println(name + " is a " + peopleArray[count].getAge() + " years old female who is "
					+ peopleArray[count].getHeight() + " inches tall and weighs " + peopleArray[count].getWeight()
					+ " pounds");
			return;
		}

		throw new PersonDoesNotExistsException();
	}

	public void removePerson(String name) throws PersonDoesNotExistsException {
		int count = -1;
		for (int i = 0; i < peopleArray.length - 1; i++) {
			if (peopleArray[i] != null) {
				if ((peopleArray[i].getName().toUpperCase()).equals(name.toUpperCase())) {
					count = i;
				}
			}
		}

		if (count == -1) {
			throw new PersonDoesNotExistsException();
		}

		HW1_Person[] copyPeopleArray = new HW1_Person[peopleArray.length - 1];
		for (int i = 0, j = 0; i < peopleArray.length; i++) {
			if (i == count) {
				continue;
			}
			copyPeopleArray[j++] = peopleArray[i];
		}

		peopleArray = copyPeopleArray;

	}

	public void SaveToFile(String name) throws IOException {
		FileWriter fw = new FileWriter(name);
		PrintWriter out = new PrintWriter(fw);
		out.println(
				"               Name                  Age                  Gender             Height              Weight ");
		for (int i = 0; i < peopleArray.length - 1; i++) {
			if (peopleArray[i] != null) {
				out.println(peopleArray[i].toString());
			}
		}
		out.close();

	}

	public static void sort(HW1_Person[] array) {
		HW1_Person temp;
		for (int a = 0; a < array.length; a++) {
			if (array[a] != null) {
				for (int b = 0; b < array.length - 1; b++) {
					if (array[b] == null && array[b + 1] != null) {
						array[b] = array[b + 1];
						array[b + 1] = null;
					} else if (array[b] != null && array[b + 1] != null) {
						if (array[b].getName().toUpperCase().compareTo(array[b + 1].getName().toUpperCase()) > 0) {
							temp = array[b];
							array[b] = array[b + 1];
							array[b + 1] = temp;
						}
					}
				}
			}
		}
	}

}
