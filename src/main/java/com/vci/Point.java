package com.vci;

import java.util.List;

public class Point {
	String label;
	double x;
	double y;
	double lat;
	double lon;
	double distance;
	Point upper;

	public Point(String label , Point upper , double lat, double lon) {
		this.label = label;
		/*this.x = (lon + 180) * 360;
		this.y = (lat + 90) * 180;*/
		this.x = lon;
		this.y = lat;
		this.lat = lat;
		this.lon = lon;
		this.upper=upper;

	}

	double getDistance(Point point) {
		double dX = point.x - this.x;
		double dY = point.y - this.y;
		return Math.sqrt((dX * dX) + (dY * dY));

	}
	
	double getSlope(Point point) {
		double dX = point.x - this.x;
		double dY = point.y - this.y;
		return dY / dX;
	}
	
	@Override
	public String toString() {
		return lat + " , "+ lon;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point p = (Point)obj;
		return (p.lat==this.lat) && (p.lon == this.lon);
	}

	
	

	// Find the upper most point. In case of a tie, get the left most point.
	public static Point upperLeft(List<Point> points) {
		Point top = points.get(0);
		for (int i = 1; i < points.size(); i++) {
			Point temp = points.get(i);
			if (temp.y > top.y || (temp.y == top.y && temp.x < top.x)) {
				top = temp;
			}
		}
		return top;
	}
	
	
}