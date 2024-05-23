package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
//       Student s = new Student();
//       s.setStudentName("Onkar");
//       s.setStudentId(111);
//       s.setStudentCity("Pune");
//       int result = studentDao.insert(s);
//       System.out.println("Done "+result);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;

		while (flag) {
			System.out.println("Press 1 for add new student");
			System.out.println("Press 2 for display all student");
			System.out.println("Press 3 for get detail of single student");
			System.out.println("Press 4 for delete students");
			System.out.println("Press 5 for update student");
			System.out.println("Press 6 for exit");

			try {

				int input = Integer.parseInt(br.readLine());

				switch (input) {
				case 1:
					// Add a new student
					// Taking input from Students
					System.out.println("Enter Student id : ");
					int sId = Integer.parseInt(br.readLine());

					System.out.println("Enter Student name : ");
					String sName = br.readLine();

					System.out.println("Enter Student city : ");
					String sCity = br.readLine();

					// Creating new student object and setting values
					Student s = new Student();
					s.setStudentId(sId);
					s.setStudentName(sName);
					s.setStudentCity(sCity);

					int result = studentDao.insert(s);
					System.out.println(result + " Student Added");
					System.out.println("************************************");
					System.out.println();
					break;
				case 2:
					// Display all
					System.out.println("************************************");
					List<Student> allStudents = studentDao.getAllStudents();
					for (Student st : allStudents) {
						System.out.println("ID : " + st.getStudentId());
						System.out.println("Name : " + st.getStudentName());
						System.out.println("City : " + st.getStudentCity());
						System.out.println("_____________________________________");
					}
					System.out.println("************************************");
					break;
				case 3:
					// Get single student data
					System.out.println("Enter Student id : ");
					int studentId = Integer.parseInt(br.readLine());
					Student student = studentDao.getStudent(studentId);
					System.out.println("ID : " + student.getStudentId());
					System.out.println("Name : " + student.getStudentName());
					System.out.println("City : " + student.getStudentCity());
					System.out.println("_____________________________________");
					break;
				case 4:
					// Delete student
					System.out.println("Enter Student id : ");
					int Id = Integer.parseInt(br.readLine());
					studentDao.deleteStudent(Id);
					System.out.println("Student Deleted!!");
					break;
				case 5:
					// Updating the Student
					System.out.println("************************************");
					System.out.println("Enter the student id to be updated");
					int updatedId = Integer.parseInt(br.readLine());
					System.out.println("_____________________________________");
					System.out.println("Press 1 for update the name");
					System.out.println("Press 2 for update the city\n");
					System.out.println("_____________________________________");
					System.out.println("Enter your choise for what do you want to update?");
					int choice = Integer.parseInt(br.readLine());
					Student std1 = studentDao.getStudent(updatedId);

					String updatedName = std1.getStudentName();
					String updatedCity = std1.getStudentCity();

					switch (choice) {
					case 1:
						System.out.println("Enter the name to be updated");
						updatedName = br.readLine();
						// setting the student objects value
						std1 = new Student(updatedId, updatedName, updatedCity);
						System.out.println("Name updated successfully");
						break;

					case 2:
						System.out.println("Enter the city name to be updated");
						updatedCity = br.readLine();
						// setting the student objects value in another way
						std1.setStudentId(updatedId);
						std1.setStudentName(updatedName);
						std1.setStudentCity(updatedCity);
						System.out.println("City updated successfully");
						break;
					}

					studentDao.updateStudent(std1);
					System.out.println("Updated successfully\n");
					System.out.println("************************************");
					break;
				case 6:
					flag = false;
					break;

				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Input try with another one !!!");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Thankyou for using my application !");
	}
}
