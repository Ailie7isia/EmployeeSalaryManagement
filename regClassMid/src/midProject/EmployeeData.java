package midProject;

public class EmployeeData {
	
	String code = "", name = "", gen = "", post = "";
	double salary = 0;
	
	public EmployeeData(String code, String name, String gen, 
						String post, double salary) {
		this.code = code;
		this.name = name;
		this.gen = gen;
		this.post = post;
		this.salary = salary;
	}
	
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGen() {
        return gen;
    }

    public String getPost() {
        return post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
	
	

