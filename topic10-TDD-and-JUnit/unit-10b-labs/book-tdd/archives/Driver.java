import java.util.Scanner;
/**
 * The MyDVDs class is a menu driven application where the user can choose to 
 * enter DVD titles (which are then libraryd as Strings in the ArrayList dvds) 
 * or display a list of all the DVD titles libraryd in the ArrayList dvds.  
 * 
 * @author Mairead Meagher, Siobhan Drohan.
 * @version 20th January 2015
 */

public class Driver 
{
	private Scanner input;
	Library library;

	/**
	 * Constructor for objects of class MyDVDs
	 */
	public Driver()
	{
		input = new Scanner(System.in);
		library = new Library ();
	}


	public static void main(String args[])
	{
		Driver app = new Driver();
		app.run();
	}


	/**
	 * mainMenu() - This method displays the menu for the application, 
	 * reads the menu option that the user entered and returns it.
	 * 
	 * @return     the users menu choice
	 */

	private int mainMenu()
	{
		System.out.println("My DVD Library");
		System.out.println("  1) Add a DVD");
		System.out.println("  2) List all DVDs");
		System.out.println("  3) Update a DVD");
		System.out.println("  4) Delete a DVD");
		System.out.println("  -------------------------");
		System.out.println("  5) Save DVDs to dvd.xml");
		System.out.println("  6) Load DVDs from dvd.xml");
		System.out.println("  -------------------------");
		System.out.println("  0) Exit");
		System.out.print("==>>");
		int option = input.nextInt();
		return option;
	}

	/**
	 * run() - This method displays the menu and processes the user's menu 
	 * choice.  Option 0 exits the program.  Option 1 adds a DVD title to the
	 * ArrayList dvds, and Option 2 lists the DVD titles libraryd in the ArrayList.
	 */
	private void run()
	{
		int option = mainMenu();
		while (option != 0)
		{
			switch (option)
			{
			case 1:
				addDVD();
				break;
			case 2:
				System.out.println(library.listDVDs());
				break;
			case 3:
				updateDVD();
				break;
			case 4:
				deleteDVD();
				break;
			case 5: 
				try{
					library.save();
				}
				catch(Exception e){
					System.out.println("Error writing to file: " + e);
				}
				break;
			case 6: 
				try{
					library.load();
				}
				catch(Exception e){
					System.out.println("Error reading from file: " + e);
				}
				break;
			default:
				System.out.println("Invalid option selected.");
				break;
			}

			//display the main menu again
			System.out.println("");
			option = mainMenu();
		}
		System.out.println("Exiting... bye");
	}

	private void addDVD()
	{
		System.out.print("DVD title: ");
		String dvdTitle = input.nextLine();
		dvdTitle = input.nextLine();
		library.add(new DVD(dvdTitle));
	}


	public void updateDVD()
	{
		//list the DVDs and ask the user to choose the DVD to edit
		System.out.println(library.listDVDs());

		if (library.getDVDs().size() != 0){	
			//only process the update if DVDs exist in the ArrayList
			System.out.print("Index of dvd to edit ==>");
			int index = input.nextInt();

			if (index < library.getDVDs().size()){	
				//if the index number exists in the ArrayList, gather the new details from the user
				System.out.print("   Enter a new dvd title: ");
				String desc = input.nextLine();
				desc = input.nextLine();

				//retrieve the DVD from the ArrayList and update the details with the user input
				DVD dvd = library.getDVDs().get(index);
				dvd.setTitle(desc);
			}
			else
			{
				System.out.println("There is no DVD for this index number");
			}
		}
	} 

	public void deleteDVD()
	{
		//list the DVDs and ask the user to choose the dvd to edit
		System.out.println(library.listDVDs());

		if (library.getDVDs().size() != 0){	
			//only process the delete if DVDs exist in the ArrayList
			System.out.print("Index of DVD to delete ==>");
			int index = input.nextInt();

			if (index < library.getDVDs().size()){	
				//if the index number exists in the ArrayList, delete it from the ArrayList
				library.getDVDs().remove(index);
				System.out.println("DVD deleted.");
			}
			else
			{
				System.out.println("There is no DVD for this index number");
			}
		}
	} 

	
}