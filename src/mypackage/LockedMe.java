package mypackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class includes the methods regarding LockedMe project like<br>
 * Add a new File<br>
 * Display All the Files<br>
 * Delete a File<br>
 * Search a File<br>
 * 
 * @author Sarvanan G
 *
 */
public class LockedMe {

	public static final String filePath = "E:\\Files\\FileProj";

	public static final String error_message = "Some Error: Please contact Admin";

	static Scanner obj = new Scanner(System.in);

	/*
	 * Display's the list of files available in the directory
	 */
	public static void getAllFiles() {
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null && listOfFiles.length != 0) {
			var stream = Arrays.stream(listOfFiles);
			System.out.println("The available files are");
			stream.forEach(str -> System.out.println(str.getName()));
		} else {
			System.out.println(error_message);
		}
	}

	/**
	 * Deletes the file, if exits
	 */
	public static void deleteFiles() {

		String fileName;
		System.out.println("Enter the file name to be deleted");

		fileName = obj.next();

		File file = new File(filePath + "//" + fileName);
		if (file.exists()) {
			file.delete();
			System.out.println(fileName + " File Deleted Success");
		} else {
			System.out.println(fileName + " File has not been found to delete");
		}

	}

	/**
	 * searches the file entered by the user, if exits 
	 */
	public static void searchFiles() {

		String fileName;
		System.out.println("Enter the file name to be searched");

		fileName = obj.next();

		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		List<String> list = new ArrayList<>();
		var stream = Arrays.stream(listOfFiles);
		stream.forEach(str -> list.add(str.getName()));
		if (list.contains(fileName))
			System.out.println(fileName + " File Exists");
		else
			System.out.println(fileName + " File doesn't exist");

	}

	/**
	 * Method used to create a new file with contents from user<br>
	 * Validates if the file name is not duplicated
	 */
	public static void createFiles() {

		String fileName;
		int lines;
		System.out.println("Enter the file name to be created:");
		fileName = obj.nextLine();
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		List<String> list = new ArrayList<>();
		var stream = Arrays.stream(listOfFiles);
		stream.forEach(str -> list.add(str.getName()));
		if (list.contains(fileName)) {
			System.out.println(fileName + " File Exists Already, Please try with another name");
		} else {
			System.out.println("Enter the number of lines:");
			String st = obj.nextLine();
			lines = Integer.parseInt(st);
			try {
				FileWriter fileWriter = new FileWriter(filePath + "\\" + fileName);
				for (int i = 1; i <= lines; i++) {
					System.out.println("Enter the Line number " + i + " of " + lines);
					fileWriter.write(obj.nextLine() + "\r\n");

				}
				System.out.println(fileName + " File created succesfully");
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Display's list of menu available for File handling
	 */
	public static void displayMenu() {
		System.out.println("******************************************************************");
		System.out.println("\t Welcome to LockedMe project developed by Saravana G");
		System.out.println("******************************************************************");
		System.out.println("\t 1.Add a new File");
		System.out.println("\t 2.Display All the Files");
		System.out.println("\t 3.Delete a File");
		System.out.println("\t 4.Search a File");
		System.out.println("\t 5.EXIT");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		try {

			int ch;
			do {
				displayMenu();
				System.out.println("Enter you choice");
				String st = obj.nextLine();
				ch = Integer.parseInt(st);
				switch (ch) {
				case 1:
					createFiles();
					break;
				case 2:
					getAllFiles();
					break;
				case 3:
					deleteFiles();
					break;
				case 4:
					searchFiles();
					break;
				case 5:
					System.out.println("Thank You!!!");
					System.exit(0);
					break;
				default:
					System.out.println("Enter a valid choice");
				}
			} while (ch > 0);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			obj.close();
		}
	}

}
