import javax.swing.*;
import java.io.IOException;

/**
 * Class used to manage user logic for Street Mapping
 */
public class StreetMap extends JFrame
{
    private static Graph graph;
    public static void main(String[] args) throws IOException
    {

         if (args.length == 2 && args[1].equals("--show"))
         {
             graph = new Graph(args[0], false,  null, null);
             new StreetMap().setVisible(true);
         }
         else if (args.length == 4 && args[1].equals("--show"))
         {
             graph = new Graph(args[0], true, args[2], args[3]);
             new StreetMap().setVisible(true);
         }
         else if (args.length == 4 && args[1].equals("--directions"))
         {
             graph = new Graph(args[0], true, args[2], args[3]);
         }
         else if (args.length == 5)
         {
             graph = new Graph(args[0], true, args[3], args[4]);
             new StreetMap().setVisible(true);
         }

    }

    /**
     * Constructor for class NYS
     */
    public StreetMap()
    {
        setSize(500, 500);
        add(new MapCanvas(graph));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
