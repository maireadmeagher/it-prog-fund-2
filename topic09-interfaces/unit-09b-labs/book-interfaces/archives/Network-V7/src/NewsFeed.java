import java.util.ArrayList;
import java.util.List;

/**
 * The NewsFeed class stores news posts for the news feed in a
 * social network application (like FaceBook or Google+).
 * 
 * This version does not save the data to disk, and it does not
 * provide any search or ordering functions.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.2
 */
public class NewsFeed implements INewsFeed
{
    private List<Post> posts;

    /**
     * Construct an empty news feed.
     */
    public NewsFeed()
    {
        posts = new ArrayList<Post>();
    }

    /**
     * Add a post to the news feed.
     * 
     * @param post  The post to be added.
     */
    public void addPost(Post post)
    {
        posts.add(post);
    }

    @Override
    public void deletePost(int index) {
        if ((index >=0 )  && (index < posts.size())) {
            posts.remove(index);
        }
    }

    /**
     * Return the news feed as a String. 
     */
    public String show()
    {
        String str = "";
        int index = 0;  //to display the index number for deleting

        // display all posts
        for(Post post : posts) {
            str += index + ": " + post.display() + "\n\n";
            index++;
        }
        return str;
    }

    public String showExtract()
    {
        String str = "";
        // display all posts
        for(Post post : posts) {
            str += post.displayExtract() + "\n\n";
        }
        return str;
    }
}
