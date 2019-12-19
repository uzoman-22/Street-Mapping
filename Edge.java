/**
 * Edge class used to represent roads in Street Mapping
 *
 * @author Uzoma Ohajekwe
 * @version 1.0
 * @date 2019-04-27
 */
public class Edge
{
    /**
     * The intersection that would represent u in an edge (u, v)
     */
    private Vertex source;
    /**
     * The intersection that would represent v in an edge (u, v)
     */
    private Vertex destination;
    /**
     * The weight of an edge (u, v), where u and v are intersections. Weight calculated using haversine formula
     */
    private double distance;
    /**
     * The unique String ID for this edge/road
     */
    private String ID;
    /**
     * Average radius of earth used in haversine formula
     */
    private final double R = 3961;

    /**
     * Constructor for class Edge
     * @param id the id of the road/edge
     * @param s the vertex to become the source intersection
     * @param f the vertex to become the destination intersection
     */
    public Edge(String id, Vertex s, Vertex f)
    {
        source = s;
        destination = f;
        ID = id;
        haversine();

    }

    public String getID()
    {
        return ID;
    }

    /**
     * Calculates the geographical distance between the source and destination intersection using haversine formula
     */
    private void haversine()
    {
        double lat_difference = (source.getLatitude() - destination.getLatitude());
        double long_difference = (source.getLongitude() - destination.getLongitude());
        double a = Math.pow(Math.sin(lat_difference/2), 2) + Math.cos(source.getLatitude()) * Math.cos(destination.getLatitude()) * Math.pow(Math.sin(long_difference/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = c * R;
    }

    /**
     * Returns the source intersection of this edge
     * @return the source intersection of this edge
     */
    public Vertex getSource()
    {
        return source;
    }

    /**
     * Returns the destination intersection of this edge
     * @return the destination intersection of this edge
     */
    public Vertex getDestination()
    {
        return destination;
    }

    /**
     * Returns the weight of the edge or the distance between the two intersections
     * @return the distance between the source and destination intersection
     */
    public double getDistance()
    {
        return distance;
    }
}
