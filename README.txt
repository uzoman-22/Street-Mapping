INTRODUCTION/GENERAL USAGE NOTES
—————————————————————
THIS PROGRAM IS USED FOR FINDING THE SHORTEST PATH BETWEEN TWO GEOGRAPHICAL POINTS GIVEN A TEXT FILE CONTAINING SUFFICIENT INTERSECTIONS, WITH LONGITUDE AND LATITUDE, AND ROADS. THE PROGRAM CAN PRINT OUT DIRECTIONS IN THE FORM OF A LIST OF NECESSARY INTERSECTIONS TO GET FROM ONE INTERSECTION TO ANOTHER. THE PROGRAM CAN ALSO PRODUCE AN AUTHENTIC MAP OF THE GEOGRAPHICAL AREA PROVIDED BY THE TEXT FILE.

AUTHOR AND CONTACT INFORMATION
———————————————————
UZOMA OHAJEKWE
uohajekw@u.rochester.edu

LIST OF FILES
———————
Graph.java
NYS.java
Vertex.java
Edge.java
MapCanvas.java
README.txt

USES OF EACH FILE
——————————
Vertex.java:
THE CLASS USED FOR THE GRAPHICAL REPRESENTATION OF AN INTERSECTION. 

FIELDS

private double latitude- The field representing the latitude of an intersection

private double longitude- The field representing the longitude of an intersection

private Vertex predecessor- The field representing the Vertex object of the intersection that by relaxing this intersection provided the shortest distance between this intersection and the source intersection.

private String ID- The field representing the unique ID for an intersection

private int index- The field representing the unique index in an n-long adjacency list, where n is the number of total intersections. Indices are assigned by the order intersections appear in file. For example, the first intersection that appears in the file will have an index of 0, while the last will have an index of n-1.

private double distanceToSource- The distance of this intersection to the source intersection

private boolean visited- The field representing a marker as to whether this intersection has had a chance to relax all of  its adjacent, non-visited vertices. If intersection is visited, it should not be in priority queue during shortest path algorithm. See Dijsktra’s algorithm for more information

private Edge mainRoad- The field representing the road connecting this intersection to its predecessor

CONSTRUCTOR

public Vertex(double lat, double lon, String id, int in)
	double lat- The value that will become the latitude for this intersection
	
	double lon- The value that will become the longitude for this intersection
	
	String id- The value that will become the unique id for the intersection

	int in- The value that will become the unique index for this intersection

METHODS

public int getIndex()-
	SUMMARY- Returns the index of this intersection

public double getLatitude()-
	SUMMARY- Returns the latitude of this intersection

public double getLongitude()-
	SUMMARY- Returns the longitude of this intersection

public getDistancetoSource()-
	SUMMARY- Returns the distance of this intersection to the source intersection

public void setDistanceToSource(double dist)-
	double dist- The distance to become the new ‘distanceToSource’ of this intersection
	SUMMARY- Sets the distanceToSource to whatever value dist is

public String getID()-
	SUMMARY- Gets the unique ID for this intersection

public int compareTo(Vertex other)
	Vertex other- The other Vertex object being compared to this Vertex object
	
	SUMMARY- Method compares this Vertex object to the Vertex object other, by way of their 	‘distanceToSource’. Returns 1 if this Vertex object’s ‘distanceToSource’ is greater than other’s 	‘distanceToSource’, 0 if they are the same value, and -1 if this Vertex object’s ‘distanceToSource’ is less than 	other’s ‘distanceToSource’. This method is particularly important as it is used in the Java Priority Queue to 	order elements.

public void setVisited(boolean b)

	boolean b- Truth value that will become value of ‘visited’ 

	SUMMARY- Method that sets the value of ‘visited’ to value of b

public boolean getVisited()
	SUMMARY- Gets the value of the field ‘visited’. 

public void setPredecessor(Vertex p)

	Vertex p- The Vertex object that will become the predecessor of this Vertex

	SUMMARY- Sets the the field ‘predecessor’ to whatever p is

public Vertex getPredecessor()
	SUMMARY- Returns predecessor

public void setMainRoad(Edge r)
	Edge r- The Edge object to become the ‘mainRoad’
	SUMMARY- Sets mainRoad to r

public Edge getMainRoad()
	SUMMARY- Returns the road the connects this intersection to its predecessor

Edge.java:
CLASS USED FOR THE GRAPHICAL REPRESENTATION OF A ROAD

FIELDS
private Vertex source- The vertex/intersection that would be u in an edge (u, v)

private Vertex destination- The vertex/intersection that would be v in an edge (u, v)

private double distance- The geographical distance between two intersections, calculate with haversine formula. See FYI for more information on haversine formula 

private String ID- The unique ID for this road

private final double R- Average radius of earth used in haversine formula

CONSTRUCTOR

public Edge(String id, Vertex s, Vertex f)-
	String id- The unique id of the road/edge
	Vertex s- The intersection that will become the source intersection of this road  
	Vertex f- The intersection that will become destination intersection of this road

METHODS
private void haversine()- Calculates geographical distance between source intersection and destination intersection using haversine formula, see FYI for more information on haversine formula. This distance is stored in the field variable ‘distance’, called in the constructor. This distance can be compared to the weight of an edge in a tradition graphical representation

public Vertex getSource()- Returns the Vertex object representing the source intersection of this road

public Vertex getDestination()- Returns the Vertex object representing the destination intersection of this road

public double getDistance()- Returns geographical distance between the source and destination intersection, calculating using haversine()

NYS.java:
CLASS USED TO DIRECT COMMAND LINE FOR STREET MAPPING

FIELDS
private static Graph graph- The main Graph object that will produce the creation of a map and/or directions

METHODS

public static void main(String[] args)-
	String[] args- The various input arguments of Street Mapping program. The first argument must always be a 
	file containing a list of intersections and the roads that connect them. Other arguments may include the 	command “—show”, which prompts the map to be show, “—directions”, which prompts directions to be shown, 	and two intersections to as going from a to b

MapCanvas.java:
THE CLASS THAT CREATES MAP FOR STREET MAPPING

FIELDS
Graph mapGraph- Graph object that is used to draw map

CONSTRUCTORS

public MapCanvas(Graph graph)
	Graph graph- The graph that will become mapGraph

METHODS

public void paintComponent(Graphics g)
	SUMMARY- Method first finds north most, south most, east most and west most intersections and uses them 	as scaling factors. The program then goes through each road and draws the it.

Graph.java:

THE CLASS THAT MANAGES THE THE GRAPH REPRESENTATION BY ADJACENCY LIST AS WELL AS THE ALGORITHM FOR FINDING THE SHORTEST DISTANCES BETWEEN TWO POINTS.

FIELDS
ArrayList<ArrayList<Edge>> g- Adjacency list that represents the graph. Represented by an ArrayList of ArrayList of Edges. Each index of the outer ArrayList is an intersection so the size of the ArrayList is the number of intersection. Members of the inside ArrayList represents the edges or roads connected for a given intersection. Edges are represented by the Edge class.

HashMap<String, Vertex> bank- HashMap that stores vertices for easy access during initialization of adjacency list and initialization of shortest distance algorithm. Vertices are represented by the Vertex class. The key object is a String that id the unique id of the Vertex, for example, bank.get(“i10”) returns the Vertex object representing the intersection i10.

int vertexCounter- Simple counter used to assign index to adjacency list for a given Vertex object. Assignment of indices is dependent on order of intersections read in file. For example, the first intersection read in the file will receive an index of 0, while the last intersection read will receive an index of !(number of intersections -1)

Stack<Edge> directions-Stack of edges used for a given shortest path. Mainly used to draw directions on map

boolean showDirections- The truth value that decides whether a map should be shown. This value is assigned from the constructor

String origin- The id of the intersection a user claims as their source intersection in directions. This value is read from the constructor.

String disembarkation- The id of the intersection a user claims as their destination intersection in directions. This value is assigned from the constructor.

CONSTRUCTOR

public graph(String fileName, boolean displayDirections, String o, String d)
	
	String fileName- The name of the text file containing the list of intersections and roads. Main 	informations needed to build adjacency list based on a user’s command line input
	
	boolean displayDirections- Truth value determining whether directions should based on a 	user’s command line input. This value is assigned to field showDirections
	
	String o- The name of the source intersection user wants to start directions from based on 	command line input. This value is assigned to field origin
	
	String d- The name of the destination intersection user wants to start directions from based on 	command line input. This value is assigned to field disembarkation
	
	SUMMARY- The constructor uses a BufferedReader to read text file. As intersections come 
	before roads, the program first reads the file for intersections and puts them in the hash map 
	bank as new Vertex object with its key being its String id. Necessary information like longitude, 	latitude, ID, and index are put in the constructor of the new Vertex object. Longitude, latitude, 	and ID are read from the file for a given Vertex, while index is assigned by vertexCounter, 	based on the order of an intersection appearing in the file. See description of vertexCounter for 	more information, and see description of Vertex.java for more information of the construction of
	Vertex object. Once all intersections are read, all roads are read. Since roads come with the 	list of intersections they connect, and since the adjacency list g represents each intersection, 
	the hash map is called to find the index of an intersection, using the ID provided in the line, and  
	for that index, the inner ArrayList<Edge> adds a new Edge object with intersection called by 	the hash map as the source vertex, the second intersection as the destination vertex. This is 	then done for the other vertex. Note that for a given index in the outer ArrayList, that contains 	an ArrayList of edges where all source nodes for the Edge object are the intersection assigned 	to the index and destination nodes are various members of the intersections adjacency list. For 
	example if the intersection “i6” is represented by the index of 6, g.get(6) will access an 	ArrayList of edges where for all Edge objects the source vertex is i6 and the destination vertex 	is some intersection adjacent to i6 or connected i6 by an edge. One would know “i6”’s index for 	the adjacency list is 6 by doing bank.get(“i6”). This process is done for all roads until the file is 
	completely read. The last few lines deal with logic regarding the user wanting the map being 
	shown without directions, as the map is created with the shortest distance algorithm, which 	requires two intersections, but the user has not used not entered any intersections. The 	program handles this by finding two random indices from 0 to number of intersections-1 and 	assigning a psuedo path between these points, sufficient to run the shortest path algorithm and 	generate a map. Of course, the map nor directions is not shown in this case due to the field 	showDirections, and the logic behind this will be explained later.

METHODS

public void dijkstra(String source, String second)- 

	String source- The starting vertex used in Dijskstra’s algorithm used to calculate distance to all 	other points and the ending vertex by the source.
	
	String second- The destination vertex user wants

	SUMMARY- Method uses Dijsktra’s algorithm to find distance between one node and all other 
	points. See information section if further interest on the algorithm or its creator, Edgar Dijkstra. 
	The algorithm starts by finding using the hash map ‘bank’ to find the intersection of source, 	bank.get(source) and setting that intersection’s distancetoSource to 0, as it is the source. 
	Check Vertex.java for information on distancetoSource. All vertices are then added to a priority 	queue, java.util Priority Queue q. Then, the main part of Dijsktra’s runs, while the priority queue 	is not empty, the vertex with the minimum distancetoSource is removed and its adjacent 	vertices are “relaxed” through it. See information on Dijsktra’s for “relaxing”. This parts works 
	exactly any standard Dijsktra’s algorithm with a few differences. For each vertex relaxed, the
	vertex it was relaxed it was relaxed through the produced the minimum distance is named as 	the ‘predecessor’ for a given vertex. This will remain important in producing directions later in t	he method. Another difference is that a Vertex that has just been relaxed is briefly taken out of 	the queue and put back in, to update its new distancetoSource. Another difference is that a vertex’s ‘mainEdge’ 	which is the edge connecting a vertex and its predecessor. Once the priority queue is 	empty, starting from the destination intersection, all intersections involved in the shortest path 	are put in the stack. Since each intersection has a predecessor, this is done by accessing 	predecessors from the destination vertex to the source. Also note that each ‘mainEdge’ is put 	into the field stack ‘directions’. If ‘showDirections’ is true, the intersections for a shortest path are 
	printed from source to destination.

public ArrayList<ArrayList<Edge>> getG()-
	SUMMARY- Returns the adjacency list g

public Stack<Edge> getDirections()-
	SUMMARY- returns the field stack ‘directions’. Used in CanvasMap.java

public static boolean getShowDirections()-

	SUMMARY- gets the boolean field ‘showDirections’. Used in NYS.java

INSTRUCTIONS TO USE PROGRAM
—————————————————
USERS, MUST FIRST MAKES SURE THEY HAVE VARIOUS PROGRAMS AND FILES TO RUN PROGRAM. USERS NEED TO BE ABLE TO FIRST RUN JAVA PROGRAMS, THEN THEY NEED THE FILES Graph.java, Edge.java, Vertex.java, StreetMap.java and MapCanvas.java. ON A USER’S COMMAND LINE INTERFACE, THEY MUST ENTER ‘java StreetMap inputFile.txt’ followed on the same line of the commands ‘—show’, ‘—directions”, ‘startIntersection’, ‘endIntersection’. Input file must be a text file, in this format:
i IntersectionID Latitude Longitude
r RoadID Intersection1ID Intersection2ID
Intersections must have a unique IntersectionID, they must have valid longitudes (numbers between -180 and 180) and valued latitudes (numbers between -90 and 90) and they must become before roads
Roads must come AFTER all intersections have been listed and Intersection1ID and Intersection2ID must be the ids of two valid intersections, listed somewhere along with many other intersections. Failure to comply with these requirements may result in program crashing. Users may do what they think advisable at their own risk. The command ‘—show’ comes after the input file as either the second or third argument. The command “—show” simply displays the map, and has no restrictions on its use. Combined with the command ‘—directions’, a blue line may appear on the map highlight the path between a starting intersection and a finishing intersection. The command ‘—directions’ gives directions between two intersections in different forms. This command requires that two input arguments follow the argument ‘—directions’ that are ids of two intersections, the id of a user’s choice for a starting intersection and the id of a user’s choice for an ending intersection. Failure to include these intersection, failure to provide intersections that exist in input file, failure to provide them in the correct order (strictly after argument —directions) may result in program crashing. Users may do what they think is advisable at their own risk. As mentioned before, —directions produces different behaviors in the program. If ‘—directions’ is called without ‘—show’, a list of intersections needed to get from the starting intersection to the ending intersection is printed out along with the distance in miles. If ‘—directions’ is called with ‘—show’, the same list of intersections with distance is printed out along with a blue line on the map highlighting the shortest path from a starting intersection to an ending intersection. NOTE THAT ON LARGER MAPS, FOR INTERSECTIONS THAT ARE CLOSE TO EACH OTHER, BLUE LINE WILL BE SMALL AND WILL REQUIRE CLOSE ATTENTION TO SEE.

SELECTED RUNTIMES
———————————
THE PREDICTED RUNTIME TO INITIALIZE THE ADJACENCY LIST IN Graph.java is O(V + E), where V is the number of intersections and E is the number of roads. For Graph.dijsktra(), entering each intersection into the priority queue takes O(vlogv), where v is the number of intersections. For each intersection it takes logarithmic time to add it to the priority queue, hence the run time. Permanent removal of a vertex of a queue also takes O(vlogv) time. The process of relaxing happens at most once for each edge/road, and that combined with the need to remove and reenter a relaxed vertex to satisfy java.util.PriorityQueue, this will take O(ELOGV), where e is the number of roads and v is the number of intersections. The rest of the method will then run for O(# of roads and intersections in shortest path), which is involved in putting intersections and roads involved in the main path into stacks, and printing out directions based on command line input. This comes down to a final runtime for Graph.java of O((V+E)LOGV), AS V+ELOGV is the greatest run time calculated and all other runtimes are discarded except for the maximum when adding them. To draw the graph, it takes O(V) time to find the north most, east most, south most, and west most points, where V is the number of intersections, as all intersections are iterated through to find this. It then takes O(E) time to draw edges, where E is the number of roads.

CHALLENGES AND OBSTACLES
———————————————-
ONE OF THE MOST FRUSTRATING EXPERIENCES WAS NOT REALIZING THAT THE JAVA PRIORITY QUEUE DID NOT CHANGE ITS ORDERING OF ELEMENTS UNLESS SOMETHING WAS TAKEN OUT OR ADDED TO THE QUEUE. THIS RESULTED IN THE PROGRAM COMPLETETLY FAILING UNTIL THE ISSUE WAS PROPERLY DIAGNOSED AND RESOLVED. THIS WAS RESOLVED BY FIRST TAKING EVERYTHING OUT OF THE QUEUE AND PUTTING IT BACK IN EVERYTIME AN INTERSECTION WAS RELAXED AND THE PRIORITY QUEUE ORDERING, ORDERED BY DISTANCE TO SOURCE, NEEDED TO BE CHANGED. ALTHOUGH THIS WORKED ON SMALL MAPS LIKE UR.TXT, ON LARGER MAPS, IT WAS TAKING TOO LONG TO FIND THE SHORTEST PATH. SO A CHANGE WAS MADE SO ONLY THE VERTEX THAT HAD BEEN RELAXED WOULD BE REMOVED, AND THE PROGRAM WORKED CORRECTLY WITH THE ADDED BENEFIT OF BEING MUCH FASTER

FYI
——
Dijsktra’s algorithm-  https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

Haversine formula- https://en.wikipedia.org/wiki/Haversine_formula