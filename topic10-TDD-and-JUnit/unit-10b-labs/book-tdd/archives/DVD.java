/**
 * This is a DVD class that stores and maintains the title of a DVD
 * 
 * @author Mairead Meagher, Siobhan Drohan.
 * @version 20th January 2015
 */
public class DVD
{
    private String title;

    /**
     * Constructors for objects of class DVD
     */
    public DVD(String title)
    {
       setTitle(title);
    }

    public DVD(){
    }
    
    /**
     * setTitle() - This mutator method sets the title of the DVD to 
     * the value passed.
     * 
     * @param  title   the new title of the DVD
    */
    public void setTitle(String title)
    {
    	if (title.length() <= 20 ) {
    	    this.title = title;
        }
        else {
        	this.title = title.substring(0,20);
        }
    }

    /**
     * getTitle() - This mutator method returns the title of the DVD.
     * 
     * @return     the title of the DVD entered by the user
    */
    public String getTitle()
    {
      return title;
    }

    /**
     * toString() - This method prints the title of the DVD.
     * 
     * @return     the title of the DVD entered by the user
    */
    public String toString()
    {
      return "DVD Title is: " + title;
    }
}
