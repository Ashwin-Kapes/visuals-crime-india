package com.vci;
/* =================   FILE Polygon.java (A)  ================= */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 class Polygon defines a polygon, and includes methods for testing if
 it is convex, CounterClockWise, etc. 

 It also has routines for drawing polygons (using Canvas) and for
 printing polygons (using System.out).

 @author Amitabha Mukerjee 
 @version 24.08.2002
 */

public class Polygon1
{
    private Point2D.Double[] vertex;
    private int numVertices;
    private String color="black"; 

    /**
       Default Constructor; create an array of vertices
       @param n number of vertices
    */
    public Polygon1(int n)
    {
        vertex = new Point2D.Double[n];
        numVertices = 0;
    }

    /**
       Add vertex coordinates for the next vertex; 
       @param Point2D  next vertex coordinates
    */
    public void add(Point2D.Double p)
    {
        if (numVertices < vertex.length)
        {
            vertex[numVertices] = p;
            numVertices++;
        }
    }

    /**
       Change n-th vertex coordinates; 
       @param n vertex to change
       @param Point2D new vertex coordinates
    */
    public void change(int n, Point2D.Double p)
    {
        if (n < vertex.length)
        {
            vertex[n] = p;
        }
    }
    
    /** REWRITE THIS ROUTINE (and this comment) TO TEST THE VARIOUS METHODS */
    public static void testPolygon()
    {
    }

    /**
       True if p1p2 to p2p3 constitutes a Left Turn.  It is a left
       turn if the CrossProduct p1p2 x p2p3 has a +ve z component, i.e.
       if the determinant ( p1.x p1.y 1 , p2.x p2.y 1, p3.x p3.y 1) is +ve. 

       NOTE: A better name for this method may be boolean turnLeft()

       @param p1,p2,p3 - three points
    */
    public static boolean turnTest( Point2D.Double p1, Point2D.Double p2, 
    Point2D.Double p3) 
    {
        double x1 = p1.getX();        double y1 = p1.getY();
        double x2 = p2.getX();        double y2 = p2.getY();
        double x3 = p3.getX();        double y3 = p3.getY();
        return ( (x2 - x1) * (y3 - y2) - (x3-x2) * (y2-y1)  > 0 );
    }

    /**
       Test if the polygon is Convex. 
       It is convex if all vertices are turns in the same direction.
    */
    public boolean isConvex()
    {
        Point2D.Double prev = vertex[numVertices-2];
        Point2D.Double curr = vertex[numVertices-1];
        Point2D.Double next = vertex[0];
        
        boolean isCCW = turnTest (prev, curr, next); 

        for (int i = 1; i < numVertices; i++)
        {
            prev = curr;
            curr = next;
            next = vertex[i];
            if (turnTest (prev, curr, next) != isCCW ) 
                return (false);
        }
        return (true);
    }

    /**
       Test if the polygon is CounterClockWise. 
       It is CCW if the vertex with lowest Y is CCW. 
    */
    public boolean isCCW()
    {
        double min = vertex[0].getY();
        int minIndex = 0;
        for (int i = 1; i < numVertices; i++)
        {   if (vertex[i].getY() < min)
            { 
                min = vertex[i].getY() ;
                minIndex = i;
            }    
        }
        Point2D.Double prev = vertex[(minIndex - 1 + vertex.length) % vertex.length ]; 
        Point2D.Double next = vertex[(minIndex + 1) % vertex.length ];
        return (turnTest (prev, vertex[minIndex], next));
    }

    /**
       If the polygon is ClockWise, reverse its vertex order to make it CCW.
    */
    public void makeCCW()
    {
        if (!isCCW())
        { 
            Polygon1 reverse = new Polygon1(numVertices);        
            for (int i = numVertices-1; i >= 0 ; i--)
            {   reverse.add(vertex[i]);
            }
            for (int i = 0; i < numVertices-1; i++ )
                vertex[i]=reverse.vertex[i];
        }
    }

    /** The "toString()" method is used by System.out to 
        display the object data on the console.
  
        It will invoked when you call 
                System.out.println(polygonName);
    */
    public String toString()
    {
        String s="";
        for (int i = 0; i < numVertices; i++)
        {   s += ("Vertex " + i + ": " + vertex[i] + "\n");
        }
        return s;
    }

    /**
        Change the color. 
        @param newColor Valid colors are "red", "yellow", "blue", "green",
            "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
       Draw the polygon
    */
    public void draw()
    {
        Point2D.Double from = vertex[numVertices-1];
        Point2D.Double to;

        Canvas canvas = new Canvas();
        for (int i = 0; i < numVertices; i++)
        {
            to = vertex[i];
            Line2D.Double double1 = new Line2D.Double(from, to);
//			canvas.paint(g );
//            canvas.wait(10);
            from = to;
        }
    }
}