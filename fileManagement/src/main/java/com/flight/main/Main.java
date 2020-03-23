package com.flight.main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.lockedme.bo.LockedmeBOImpl;
import com.lockedme.exception.BusinessException;
import com.lockedme.model.FileObj;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LockedmeBOImpl bOItem = new LockedmeBOImpl();
		Scanner scanner = new Scanner(System.in);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Welcome to Lockedme.com - File Management System");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n");
		System.out.println("Developed By- Aakash Patel user Aksh.2102");
		System.out.println("\n");

		int ch = 0;
		do {
			System.out.println("Main Menu");
			System.out.println("===========");
			System.out.println("1)List all existing files in the directory");
			System.out.println("2)Add/Delete/Search files in the directory");
			System.out.println("3)EXIT");
			System.out.println("Enter the choice(1-3)");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {
			case 1:
				
				List<FileObj> fileList = new LinkedList<>();
				try {
					fileList=bOItem.listFiles();;
					for(FileObj f:fileList) {
						System.out.println(f.toString());
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());;
				}
				
				break;
			case 2:
				do {
					System.out.println("Sub Menu");
					System.out.println("===========");
					System.out.println("1)Create a new file in directory");
					System.out.println("2)Delete a file from the directory");
					System.out.println("3)Search file with filename from directory");
					System.out.println("4)Go back to Main Menu");
					System.out.println("Enter the choice(1-4)");
					try {
						ch = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
					}
					switch (ch) {
					case 1:
						String Fname = null;
						System.out.println(
								"Please Enter the exact file name to add the required file(including the extenssion of file)");
						System.out.println("Example of file name: Document1.docx, folder name: wedding photos");
						Fname = scanner.nextLine();

						try {
							bOItem.addFile(Fname);
						} catch (BusinessException e) {

							System.out.println(e.getMessage());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());;
						}
						break;
					case 2:
						String Fname2 = null;
						System.out.println(
								"Please Enter the exact file name to delete the required file(including the extenssion of file)");
						System.out.println("Example of file name: Document1.docx, folder name: wedding photos");
						System.out.println("Caution! This operation is case sensitive");
						Fname2 = scanner.nextLine();

						try {
							bOItem.deleteFile(Fname2);
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 3:
						String Fname3 = null;
						System.out.println(
								"Please Enter the file name you want to search, Please donot provide extention of the file here");
						System.out.println("Entering Document would return document folder as well document.docx");
						Fname3 = scanner.nextLine();
						List<FileObj> fileList2 = new LinkedList<>();
						try {
							fileList2=bOItem.searchFile(Fname3);
							for(FileObj f:fileList2) {
								System.out.println(f.toString());
							}
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());;
						}
						break;
					case 4:
						System.out.println("Returning to Main Menu");
						break;
					default:
						System.out.println("Invalid choice.. CHoice should be integer between 1-4");
						break;

					}
				} while (ch != 4);

				break;

			case 3:
				System.out.println("Thank You for using our File Management System");
				break;
			default:
				System.out.println("Invalid choice.. Choice should be integer between 1-3");
				break;

			}
		} while (ch != 3);
		scanner.close();
	}

}
