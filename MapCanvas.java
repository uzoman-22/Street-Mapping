import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Class used to draw map for Street Mapping
 *
 * @author Uzoma Ohajekwe
 * @version 1.0
 * @date 2019-05-03
 */

public class MapCanvas extends JComponent
{
    Graph mapGraph;

    /**
     * Constructor for class MapCanvas
     * @param graph theGraph object used to draw Graph
     */
    public MapCanvas(Graph graph)
    {
        mapGraph = graph;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int w = getWidth();
        int h = getHeight();
        double emost = -Double.MAX_VALUE;
        double wmost = Double.MAX_VALUE;
        double nmost = -Double.MAX_VALUE;
        double smost = Double.MAX_VALUE;

        Iterator<ArrayList<Edge>> iti = mapGraph.getG().iterator();
        while(iti.hasNext())
        {
            Vertex i = iti.next().get(0).getSource();
            if(i.getLongitude()<wmost)
            {
                wmost = i.getLongitude();
            }
            if(i.getLongitude()>emost)
            {
                emost = i.getLongitude();
            }
            if(i.getLatitude()<smost)
            {
                smost = i.getLatitude();
            }
            if(i.getLatitude()>nmost)
            {
                nmost = i.getLatitude();
            }
        }
        double latpixperdeg = h/(nmost-smost);
        double longpixperdeg = w/(emost-wmost);

        Iterator<ArrayList<Edge>> itr = mapGraph.getG().iterator();
        while(itr.hasNext())
        {
            ArrayList<Edge> e = itr.next();
            for (int i = 0; i < e.size(); i++)
            {
                int x1 = (int)Math.round(Math.abs(wmost-e.get(i).getSource().getLongitude())*longpixperdeg); //a is first endpoint of road, b is second
                int x2 = (int)Math.round(Math.abs(wmost-e.get(i).getDestination().getLongitude())*longpixperdeg);
                int y1 = (int)Math.round(Math.abs(nmost-e.get(i).getSource().getLatitude())*latpixperdeg);
                int y2 = (int)Math.round(Math.abs(nmost-e.get(i).getDestination().getLatitude())*latpixperdeg);
                if(mapGraph.getShowDirections() && mapGraph.getDirections().search(e.get(i)) != -1)
                {
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setColor(new Color(117, 204, 255));
                    g2d.setStroke(new BasicStroke(5));
                    g2d.drawLine(x1,y1,x2,y2);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.setColor(Color.black);
                    g.drawLine(x1,y1,x2,y2);
                }
                else
                {
                    g.drawLine(x1,y1,x2,y2);
                }
            }

        }

    }
}