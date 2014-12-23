package com.vci;

import java.awt.Polygon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CopyOfXMLReader {
	private Document dom;
	static List<Node> nodes = new ArrayList<Node>();
	static Polygon1 polygon = new Polygon1(1);

	public static void main(String[] args) {
		String cityName = "andhrapradesh";
		CopyOfXMLReader xmlReader = new CopyOfXMLReader();
		xmlReader.parseXmlFile(cityName);
		xmlReader.parseDocument();

		List<Point> initialPoints = new ArrayList<Point>();
		List<Point> finalPoints = new ArrayList<Point>();

		for (Node node : nodes) {
			initialPoints.add(new Point(null, null, Double.valueOf(node.lat),
					Double.valueOf(node.longi)));
		}

		int size = initialPoints.size();

		Point currPoint = initialPoints.get(0);
		initialPoints.remove(currPoint);
		finalPoints.add(currPoint);

		Point minPoint = null;

		for (int i = 0; i < size; i++) {
			double minDistance = 1000000;
			for (Point point : initialPoints) {
				double distance = Distance.distance(currPoint.lat,
						currPoint.lon, point.lat, point.lon);
				if (minDistance > distance) {
					minDistance = distance;
					minPoint = point;
				}
			}
			System.out.println(minDistance);
			initialPoints.remove(minPoint);
			if (minDistance < 5.0) {
				finalPoints.add(minPoint);
			}
			currPoint = minPoint;
		}

		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		for (Point point : finalPoints) {
			JSONObject e = new JSONObject();
			e.put("lat", point.lat);
			e.put("lon", point.lon);
			jsonObjects.add(e);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("coords", jsonObjects.toString());
		writeData(cityName, jsonObjects.toString());

	}

	void parseXmlFile(String cityname) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File("/home/pratik/Work/poc/visuals-crime-india/src/main/webapp/archived-data/states_raw_xml/"
					+ cityname + ".xml");
			dom = db.parse(file);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void writeData(String cityName, String response) {
		Writer writer = null;
		try {
			String name = "/home/pratik/Desktop/"
					+ cityName;
			File file = new File(name);
			file.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(name), "utf-8"));
			writer.write(response);
		} catch (IOException ex) {
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	void parseDocument() {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("node");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				Node e = getNode(el);
				nodes.add(e);
			}
		}
	}

	private Node getNode(Element empEl) {
		String lat = getTextValue(empEl, "lat");
		String lon = getTextValue(empEl, "lon");
		Node e = new Node(lat, lon);
		return e;
	}

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
			polygon.addPoint(Integer.valueOf(node.lat),
					Integer.valueOf(node.longi));
		}
		return polygon;
	}
}
