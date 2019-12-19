/**
 * Vertex class used to represent intersections in Street Mapping
 *
 * @author Uzoma Ohajekwe
 * @version 1.0
 * @date 2019-04-29
 */
public class Vertex implements Comparable<Vertex>
{
    /**
     * The latitude for an intersection
     */
    private double latitude;
    /**
     * The longitude for an intersection
     */
    private double longitude;
    /**
     * The predecessor for an intersection calculated using Dijkstra's algorithm
     */
    private Vertex predecessor;
    /**
     * The unqiue String ID for a given vertex
     */
    private String ID;
    /**
     * The index of the intersection or vertex in the n-long adjacency list, where n is the number of vertices/intersections
     */
    private int index;
    /**
     * The distance to intersection's predecessor, calculated using Dijkstra's algorithm
     */
    private double distanceToSource;
    /**
     * Marker determining whether an intersection has had a chance to relax all edges
     */
    private boolean visited;
    /**
     * The main road a given intersection is connected to
     */
    private Edge mainRoad;

    /**
     * Constructor for class Vertes
     * @param lat the value to become an intersection's new latitude
     * @param lon the value to become an intersection's new longitude
     * @param id the value to be come an intersection's unique ID
     * @param in the index of a given intersection in an n-long adjaceny list, where n is the number of intersections
     */
    public Vertex(double lat, double lon, String id, int in)
    {
        this.latitude = lat;
        this.longitude = lon;
        this.ID = id;
        this.index = in;
        distanceToSource = Double.MAX_VALUE;
    }

    /**
     * Returns the index of this intersection in an n-long adjacency list, where n is the number of intersections
     * @return the index of this intersection
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Returns the latitude of this intersection
     * @return the latitude of this intersection
     */
    public double getLatitude()
    {
        return Math.toRadians(latitude);
    }

    /**
     * Returns the longitude of this intersection
     * @return the longitude of this intersection
     */
    public double getLongitude()
    {
        return Math.toRadians(longitude);
    }

    /**
     * Returns the distance of this intersection to the source intersection
     * @return the distance of this intersection to its source intersection
     */
    public double getDistanceToSource()
    {
        return distanceToSource;
    }

    /**
     * Sets distance to predecessor
     * @param dist the value that will become the new distance to an intersection
     */
    public void setDistanceToSource(double dist)
    {
        distanceToSource = dist;
    }

    /**
     * Returns the unique String ID of an intersection
     * @return the unique string id of an intersection
     */
    public String getID()
    {
        return ID;
    }

    /**
     * Compares this intersection to another intersection and returns 1 if this intersections' distance to its predecessor is greater than others, 0 if they are equal and -1 if
     * this intersection's distance to its predecessor is less than another distance predecessor
     * @param other the other Vertex being compared
     * @return 1 if this intersections' distance to its predecessor is greater than others, 0 if they are equal and -1 if
     *    this intersection's distance to its predecessor is less than another distance predecessor
     */
    public int compareTo(Vertex other)
    {
        if (this.getDistanceToSource() > other.getDistanceToSource())
        {
            return 1;
        }
        else if (this.getDistanceToSource() == other.getDistanceToSource())
        {
            return 0;
        }
        return -1;
    }

    /**
     * Sets an intersection if all of its adjacent nodes have been attempted to be relaxed through this intersection
     * @param b true if the above conditions hold, false otherwise
     */
    public void setVisited(boolean b)
    {
        visited = b;
    }

    /**
     * Returns whether an intersection is visited or not
     * @return whether an intersection is visited or not
     */
    public boolean getVisited()
    {
        return visited;
    }

    /**
     * Sets the predecessor of this intersection
     * @param p the intersection to become this node's new predecessor
     */
    public void setPredecessor(Vertex p)
    {
        predecessor = p;
    }

    /**
     * Returns the immediate predecessor of this intersection
     * @return the immediate predecessor of this intersection
     */
    public Vertex getPredecessor()
    {
        return predecessor;
    }

    /**
     * Sets the main road or edge this intersection is connected to its predecessor with
     * @param r the road to become the main road
     */
    public void setMainRoad(Edge r)
    {
        mainRoad = r;
    }

    /**
     * Returns the main road of this intersection
     * @return the main road of this intersection
     */
    public Edge getMainRoad()
    {
        return mainRoad;
    }


}

