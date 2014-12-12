package com.vci;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.json.JSONArray;
import org.json.JSONObject;

public class Indexer {

	private static final String[] YEARS = new String[] { "2001", "2002",
			"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
			"2011", "2012", };

	public static void main(String[] args) throws ElasticsearchException,
			IOException {

		Node node = nodeBuilder().node();
		Client client = node.client();

		// The name of the file to open.
		String fileName = "/home/pratik/Work/poc/data/json";

		// This will reference one line at a time
		String line = null;
		List<Map<String, Object>> documents = new ArrayList<Map<String, Object>>();
		/*
		 * client.prepareIndex("twitter", "tweet") .setSource(jsonBuilder()
		 * .startObject() .field("user", "kimchy") .field("postDate", new
		 * Date()) .field("message", "trying out Elasticsearch") .endObject() )
		 * .execute() .actionGet();
		 */
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				JSONObject jsonObj = new JSONObject(line);
				JSONArray data = jsonObj.getJSONArray("data");
				for (int i = 1; i < 2; i++) {
					for (int j = 2; j < 14; j++) {
						for (int k = 0; k < ((JSONArray) data.get(i)).getInt(j); k++) {
							Map<String, Object> jsonDocument = getJsonDocument(
									(JSONArray) data.get(i), YEARS[j - 2]);
							client.prepareIndex("crimes", "crime")
									.setSource(jsonDocument).execute()
									.actionGet();
						}
					}

				}

			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		node.close();
	}

	private static Map<String, Object> getJsonDocument(JSONArray arr,
			String year) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("state", arr.get(0).toString().replaceAll(" ", ""));
		jsonDocument.put("crime", arr.get(1));
		jsonDocument.put("year", year);
		return jsonDocument;
	}
}