import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Graph class used to represent graph in Dijkstra's
 */
public class Graph
{
    /**
     * The adjacency list used to store all intersections and paths between them
     */
    public ArrayList<ArrayList<Edge>> g; //adjacency list that stores adjacent nodes
    /**
     * Hashmap used to access intersections by unique id for insertion into adjaceny list
     */
    private HashMap<String, Vertex> bank; //hashmap used to find vertices
    /**
     * Counter used to count number of intersections read in file to help with adjacency list creation
     */
    private int vertexCounter;
    /**
     * A stack containing all edges
     */
    public Stack<Edge> directions;
    /**
     * Decides whether directions are displayed for a given run through Dijkstra's
     */
    public static boolean showDirections;
    /**
     * The starting intersection that a user calls
     */
    private static String origin;
    /**
     * The ending distribution that a user calls
     */
    private static String disembarkation;

    /**
     * Constructor for Graph class
     * @param fileName name of the file to be read form
     * @param displayDirections decides whether to display direction
     * @param o the origin node
     * @param d the destination node
     * @throws IOException
     */
    public Graph(String fileName, boolean displayDirections, String o, String d) throws IOException
    {
        bank = new HashMap<>();
        g = new ArrayList<>();
        directions = new Stack<>();
        File file = new File(fileName);
        showDirections = displayDirections;
        origin = o;
        disembarkation = d;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(file)); //file reading business
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String st;

        while ((st = br.readLine()) != null) //while there are still lines in file for readLine() to read
        {
            String[] temp =  st.split("\t"); //split each line based on tabs as described in handout
            if (temp[0].equals("i"))  //loop deals with intersections or vertices
            {
                bank.put(temp[1], new Vertex(Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), temp[1], vertexCounter++));//puts vertex in hashmap where key is name of intersection, and the value is the vertex/intersection associated with the vertex
                g.add(new ArrayList<Edge>());
            }
            else
            {
                g.get(bank.get(temp[2]).getIndex()).add(new Edge(temp[1], bank.get(temp[2]), bank.get(temp[3])));
                g.get(bank.get(temp[3]).getIndex()).add(new Edge(temp[1], bank.get(temp[3]), bank.get(temp[2])));
            }

        }
        int random = (int) Math.floor(Math.random() * g.size());
        int random2 = (int) Math.floor(Math.random() * g.size());
        if (origin == null && disembarkation == null)
        {
            dijkstra(g.get(random).get(0).getSource().getID(), g.get(random2).get(0).getSource().getID());
        }
        else
        {
            dijkstra(origin, disembarkation);
        }

    }

    /**
     * Performs Dijkstra's on two vertices
     * @param source origin vertex
     * @param second destination vertex
     */
    public void dijkstra(String source, String second)
    {
        bank.get(source).setDistanceToSource(0);

        PriorityQueue<Vertex> q = new PriorityQueue<>();
        for (int i = 0; i < g.size(); i++)
        {
            q.add(g.get(i).get(0).getSource());
        }
        while (q.size() != 0)
        {
            Vertex v =  q.poll();
            for (int i = 0; i < g.get(v.getIndex()).size(); i++)
            {
                if(!g.get(v.getIndex()).get(i).getDestination().getVisited())
                {
                    if (v.getDistanceToSource() + g.get(v.getIndex()).get(i).getDistance() < g.get(v.getIndex()).get(i).getDestination().getDistanceToSource())
                    {
                        g.get(v.getIndex()).get(i).getDestination().setDistanceToSource(v.getDistanceToSource() + g.get(v.getIndex()).get(i).getDistance());
                        g.get(v.getIndex()).get(i).getDestination().setPredecessor(v);
                        g.get(v.getIndex()).get(i).getDestination().setMainRoad(g.get(v.getIndex()).get(i));
                        q.remove(g.get(v.getIndex()).get(i).getDestination());
                        q.add(g.get(v.getIndex()).get(i).getDestination());
                    }
                }
            }
            v.setVisited(true);
        }
        Stack<Vertex> s = new Stack<>();
        Vertex end = bank.get(second);
        double important = end.getDistanceToSource();
        try
        {
            while (end.getPredecessor() != bank.get(source))
            {
                s.add(end);
                directions.add(end.getMainRoad());
                end = end.getPredecessor();
            }
        }
        catch (NullPointerException n)
        {
            System.out.println("No path between selected intersections.");
            return;
        }
        s.add(end);
        directions.add(end.getMainRoad());
        s.add(bank.get(source));
        directions.add(bank.get(source).getMainRoad());
        if (showDirections)
        {

            if (source.equals(second))
            {
                System.out.println(bank.get(source).getID());
                System.out.println("Travel time is 0 mi.");
            }
            else
            {
                while (s.size() != 0)
                {
                    System.out.println(s.pop().getID());
                }
                System.out.println("Travel time is " + important + " mi.");
            }
        }
    }

    /**
     * Returns the adjacency list
     * @return the adjacency list
     */
    public ArrayList<ArrayList<Edge>> getG()
    {
        return g;
    }

    /**
     * Returns the stack of edges used in Dijkstra's algorithm
     * @return the stack of edges used in Dijkstra's
     */
    public Stack<Edge> getDirections()
    {
        return directions;
    }

    /**
     * Returns a boolean value as to whether directions should be shown or not
     * @return the boolean value as to whether the directions should be shown for this run of Dijkstra's
     */
    public static boolean getShowDirections()
    {
        return showDirections;
    }
}

