package com.vci;

import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	private Document dom;
	static List<Node> nodes = new ArrayList<Node>();
	static Polygon1 polygon = new Polygon1(1);
	private static Point upper;

	public static void main(String[] args) {
		XMLReader xmlReader = new XMLReader();
		xmlReader.parseXmlFile();
		xmlReader.parseDocument();
		
		/*Collections.sort(nodes, new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				Double d = Double.valueOf(o2.longi) + Double.valueOf(o2.lat);
				Double e = Double.valueOf(o1.longi) + Double.valueOf(o1.lat);
				return d.compareTo(e);
			}
		});*/

		/*for (Node node : nodes) {
			System.out.println("new google.maps.LatLng(" + node.lat + ","
					+ node.longi + "),");
			java.awt.geom.Point2D.Double d = new java.awt.geom.Point2D.Double(Double.valueOf(node.lat), Double.valueOf(node.longi));
			polygon.add(d);
		}*/
		
		
		List<Point> points1 = Arrays.asList(
		              new Point("Delhi",null, 28.5500, 76.8458),
		              new Point("Bottom",null, 27.727872, 77.05340),
		              new Point("Top", null,30.89723, 76.9022039)
		          );
		
		
//		List<Point> points1 = new ArrayList<Point>();
		/*for (Node node : nodes) {
			points1.add(new Point(null,null,Double.valueOf(node.lat),Double.valueOf(node.longi)));
		}*/
		
		/*for (Point point : points1) {
			System.out.println(point.label+" new Point("+point.lat+","+point.lon+"),");
		}*/
		
		upper = Point.upperLeft(points1);
		
		
		System.out.println(upper);
		
		List<Point> points2 = new ArrayList<Point>();
		for (Point point : points1) {
			points2.add(new Point(point.label,upper,point.lat,point.lon));
		}
		
		Collections.sort(points2, new Comparator<Point>() {
			
			public int compare(Point p1, Point p2) {
					// Exclude the 'upper' point from the sort (which should come first).
					if (p1 .equals(upper))
						return -1;
					if (p2 .equals(upper))
						return 1;

					// Find the slopes of 'p1' and 'p2' when a line is
					// drawn from those points through the 'upper' point.
					double m1 = upper.getSlope(p1);
					double m2 = upper.getSlope(p2);

					// 'p1' and 'p2' are on the same line towards 'upper'.
					if (m1 == m2) {
						// The point closest to 'upper' will come first.
						return p1.getDistance(upper) < p2.getDistance(upper) ? -1 : 1;
					}

					// If 'p1' is to the right of 'upper' and 'p2' is the the left.
					if (m1 <= 0 && m2 > 0)
						return -1;

					// If 'p1' is to the left of 'upper' and 'p2' is the the right.
					if (m1 > 0 && m2 <= 0)
						return 1;

					// It seems that both slopes are either positive, or negative.
					return m1 > m2 ? -1 : 1;
				}
		});
		
		for (Point point : points2) {
			System.out.println("new Point("+point.lat+","+point.lon+"),");
		}
		
	}

	void parseXmlFile() {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			File file = new File("/home/pratik/Desktop/temp.xml");

			dom = db.parse(file);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	void parseDocument() {
		// get the root element
		Element docEle = dom.getDocumentElement();

		NodeList nl = docEle.getElementsByTagName("node");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// get the employee element
				Element el = (Element) nl.item(i);

				// get the Employee object
				Node e = getNode(el);
				nodes.add(e);

			}
		}
	}

	/**
	 * I take an employee element and read the values in, create an Employee
	 * object and return it
	 */
	private Node getNode(Element empEl) {

		// for each <employee> element get text or int values of
		// name ,id, age and name
		String lat = getTextValue(empEl, "lat");
		String lon = getTextValue(empEl, "lon");

		// Create a new Employee with the value read from the xml nodes
		Node e = new Node(lat, lon);
		return e;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if the
	 * Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		return ele.getAttribute(tagName);
	}

	class Node {
		String lat;
		String longi;

		public Node(String lat, String longi) {
			this.lat = lat;
			this.longi = longi;

		}

		@Override
		public String toString() {
			return lat + "  " + longi;
		}

		@Override
		public boolean equals(Object obj) {
			Node node = (Node) obj;
			return (lat.equals(node.lat) && longi.equals(node.longi));
		}

	}

	public java.awt.Polygon getPolygon() {
		Polygon polygon = new Polygon();
		for (Node node : nodes) {
			polygon.addPoint(Integer.valueOf(node.lat), Integer.valueOf(node.longi));
		}
		return polygon;
	}
}
