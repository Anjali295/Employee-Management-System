
public class HW1_Person {
	private String name;
	private int age;
	private String gender;
	private double height;
	private double weight;

	public HW1_Person() {
		name = "Abc";
		age = 0;
		gender = "M";
		height = 0.0;
		weight = 0.0;
	}

	public HW1_Person(String n1, int n2, String n3, double n4, double n5) {
		name = n1;
		age = n2;
		gender = n3;
		height = n4;
		weight = n5;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public void setName(String n) {
		name = n;
	}

	public void setAge(int a) {
		age = a;
	}

	public void setGender(String g) {
		gender = g;
	}

	public void setHeight(double h) {
		height = h;
	}

	public void setWeight(double w) {
		weight = w;
	}

	public boolean equals(HW1_Person p) {
		HW1_Person a = this;
		HW1_Person b = p;
		if (!this.getName().toUpperCase().equals(p.getName().toUpperCase()))
			return false;
		else if (!this.getGender().toUpperCase().equals(p.getGender().toUpperCase()))
			return false;
		else if (this.getAge() != (p.getAge()))
			return false;
		else if (this.getHeight() != (p.getHeight()))
			return false;
		else if (this.getWeight() != (p.getWeight()))
			return false;

		return true;
	}

	public String toString() {
		String st = String.format("%20s %20d %20s %20.2f %20.2f", name, age, gender, height, weight);
		return st;
	}

}
