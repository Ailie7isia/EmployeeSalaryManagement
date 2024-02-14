package midProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ChipiChapa {
	
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	ArrayList<EmployeeData> storage = new ArrayList<>();
    Map<String, Integer> positionCounts = new HashMap<>();
    
    final int bonusLimit = 3;
    final double bonusManager = 0.10;
    final double bonusSupervisor = 0.075;
    final double bonusAdmin = 0.05;

	public ChipiChapa() {
		menu();
	}
	
	void menu() {
		int opt = 0;
		
		System.out.println("PT ChipiChapa Employee Data");
		System.out.println("=============================");
		System.out.println("1. Insert Data");
		System.out.println("2. View Data");
		System.out.println("3. Update Data");
		System.out.println("4. Delete Data");
		System.out.print(">> ");
		opt = sc.nextInt(); sc.nextLine();
		
		
		switch (opt) {
		case 1:
			insert();
			break;
		case 2:
			view();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
			
		default:
			menu();
			break;
		}
		
	}
	
	private void salBonus(String position) {
	        int bonusCount = 0;
	        double bonusPercentage = 0.0;

	        switch (position) {
	            case "Manager":
	                bonusPercentage = bonusManager;
	                break;
	            case "Supervisor":
	                bonusPercentage = bonusSupervisor;
	                break;
	            case "Admin":
	                bonusPercentage = bonusAdmin;
	                break;
	            default:
	                break;
	        }
	        
	        for (EmployeeData employee : storage) {
	            if (employee.getPost().equals(position)) {
	                if (bonusCount < bonusLimit) {
	                    double newSalary = employee.getSalary() * (1 + bonusPercentage);
	                    employee.setSalary(newSalary);
	                    bonusCount++;
	                } else {
	                    break;
	                }
	            }
	        }
	   }
	 
	 
	
	void ret() {
		System.out.println();
		sc.nextLine();
		menu();
	}

	private void delete() {
		display();
		
		int opt = 0;
		
		do {
			System.out.println("Which data would you like to update? [1-" + storage.size()+1 +"]");
			System.out.print("[0 to go back]");
			System.out.print(">> ");
			} while(opt > storage.size()+1);
		
		if (opt == 0) {
			ret();
		}
		
		storage.remove(opt-1);
	}

	private void update() {
		display();
		
		String name = "", gen = "", post = "";
		double salary = 0;
		int opt = 0;
		System.out.println();
		
		do {
		System.out.println("Which data would you like to update? [1-" + storage.size() +"]");
		System.out.print("[0 to go back]");
		System.out.print(">> ");
		} while(opt > storage.size()+1);
		
		if (opt == 0) {
			ret();
		}
		
		do {
			System.out.print("Input Employee Name: ");
			name = sc.nextLine();
			
			if (name.equals("0")) {
				name = storage.get(opt+1).name;
			}
		} while (!alpCount(name));
		
		do {
			System.out.print("Input Employee Gender [Female/Male]: ");
			gen = sc.nextLine();
			
			if (gen.equals("0")) {
				gen = storage.get(opt+1).gen;
			}
		} while (!(gen.equals("Female")) && !(gen.equals("Male")));
		
		do {
			System.out.print("Input Employee Position [Manager/Supervisor/Admin]: ");
			post = sc.nextLine();
			
			if (post.equals("0")) {
				post = storage.get(opt+1).post;
			}
			
		} while (!(post.equals("Manager")) && !(post.equals("Supervisor")) && !(post.equals("Admin")));
		
		switch (post) {
		case "Manager":
			salary = 8000000;
			break;
		case "Supervisor":
			salary = 6000000;
			break;
		case "Admin":
			salary = 4000000;
			break;
		}
		
		storage.get(opt+1).name = name;
		storage.get(opt+1).gen = gen;
		storage.get(opt+1).post = post;
		storage.get(opt+1).salary = salary;
		
		System.out.println();
		System.out.println("Employee with the code " + storage.get(opt+1).code + " has been updated" );
		ret();
		
	}
	
	void display() {
		asc();
		
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%3s|%15s|%15s|%10s|%13s|%10s\n", "No.", "Employee Code",
						 "Name", "Gender", "Position", "Salary|");
		System.out.println("-----------------------------------------------------------------------");
		
		 for (int i = 0; i < storage.size(); i++) {
		        EmployeeData employee = storage.get(i);
		        System.out.printf("%3s|%15s|%15s|%10s|%13s|%10s\n", (i + 1), employee.getCode(),
		                employee.getName(), employee.getGen(), employee.getPost(), employee.getSalary());
		    }
		 
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
	}

	private void view() {

		if (storage.isEmpty()) {
			System.out.println("No data has been recorded");
			ret();
		}
		
		for (EmployeeData employee : storage) {
	        String position = employee.getPost();
	        positionCounts.put(position, positionCounts.getOrDefault(position, 0) + 1);
	    }
		
		for (String position : positionCounts.keySet()) {
	        salBonus(position);
	    }
		display();
		
		ret();
		
	}

	private void asc() {
		for (int i = 0; i < storage.size()-1; i++) {
			for (int j = 0; j < storage.size()-1-i; j++) {
				if (storage.get(j).name.compareTo(storage.get(j+1).name) < 0) {
					
					EmployeeData a = storage.get(j);
					storage.set(j, storage.get(j+1));
					storage.set(j+1, a);
				}
			}
		}
		
	}
	


	private void insert() {

		String name = "", code = "", gen = "", post = "";
		double salary = 0;
		System.out.println();
		
		do {
			System.out.print("Input Employee Name: ");
			name = sc.nextLine();
		} while (!alpCount(name));
		
		do {
			System.out.print("Input Employee Gender [Female/Male]: ");
			gen = sc.nextLine();
		} while (!(gen.equals("Female")) && !(gen.equals("Male")));
		
		do {
			System.out.print("Input Employee Position [Manager/Supervisor/Admin]: ");
			post = sc.nextLine();
		} while (!(post.equals("Manager")) && !(post.equals("Supervisor")) && !(post.equals("Admin")));
		
		switch (post) {
		case "Manager":
			salary = 8000000;
			break;
		case "Supervisor":
			salary = 6000000;
			break;
		case "Admin":
			salary = 4000000;
			break;
		}
		
		int letA, letB;
		
			letA = rd.nextInt(26) + 'A'; 
	        letB = rd.nextInt(26) + 'A';
	        
	        char charA = (char) letA;
	        char charB = (char) letB;
		
	     code = "" + charA + charB + "-" + rd.nextInt(10) + rd.nextInt(10) + rd.nextInt(10) + rd.nextInt(10);

		EmployeeData insert = new EmployeeData(code, name, gen, post, salary);
		storage.add(insert);
		
		System.out.println();
		System.out.println("New Employee Data has been recorded");
		ret();
	}
	
	Boolean alpCount(String name) {
//		boolean hasNum = false;
		int alp = 0;
		
		for (int i = 0; i < name.length(); i++) {
			if (Character.isAlphabetic(name.charAt(i))) {
				alp++;
			}
		}
		
//		for (int n = 0; n < name.length(); n++) {
//			if (Character.isDigit(name.charAt(n))) {
//				hasNum = true;
//			}
//		}
		
//		if (alp > 3 && !hasNum) {
		if (alp > 3) {
			return true;
		} else {
		return false;
		}
	}

	public static void main(String[] args) {
		new ChipiChapa();
		
	}

}
