package com.vci;


/**
 * A simple Java REST GET example using the Apache HTTP library. This executes a
 * call against the Yahoo Weather API service, which is actually an RSS service
 * (<a href="http://developer.yahoo.com/weather/"
 * title="http://developer.yahoo.com/weather/"
 * >http://developer.yahoo.com/weather/</a>).
 * 
 * Try this Twitter API URL for another example (it returns JSON results): <a
 * href="http://search.twitter.com/search.json?q=%40apple"
 * title="http://search.twitter.com/search.json?q=%40apple"
 * >http://search.twitter.com/search.json?q=%40apple</a> (see this url for more
 * twitter info: <a href="https://dev.twitter.com/docs/using-search"
 * title="https://dev.twitter.com/docs/using-search"
 * >https://dev.twitter.com/docs/using-search</a>)
 * 
 * Apache HttpClient: <a href="http://hc.apache.org/httpclient-3.x/"
 * title="http://hc.apache.org/httpclient-3.x/"
 * >http://hc.apache.org/httpclient-3.x/</a>
 *
 */
public class ApacheHttpRestClient1 {

    /*public static void main(String[] args) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List<Integer> list = Arrays.asList(21086078,
                23129256,
                18434837,
                22683516);

        try {
            for (Integer integer : list) {

                // specify the host, protocol, and port
                HttpHost target = new HttpHost("api.wikimapia.org", 80, "http");

                // specify the get request
                HttpGet getRequest = new HttpGet(
                        "http://api.wikimapia.org/?key=B1F01457-30EE671F-A1DC148C-DBB21687-75A5FC31-B4824A1D-C7E29F60-26997DE9&function=place.getbyid&id="+integer+"&format=json&pack=&language=en");

                System.out.println("executing request to " + target);

                HttpResponse httpResponse = httpclient.execute(target, getRequest);
                HttpEntity entity = httpResponse.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(httpResponse.getStatusLine());
                Header[] headers = httpResponse.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println(headers[i]);
                }
                System.out.println("----------------------------------------");
                String response = "";
                if (entity != null) {
                    response = EntityUtils.toString(entity);
                    System.out.println(response);
                }

                writeData(integer, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }

    private static void writeData(Integer integer, String response) {
        Writer writer = null;

        try {
            String name = "/home/pratik/Installations/tomcat/webapps/vci/Data/gurgaon/" + integer;
            File file = new File(name);
            file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "utf-8"));
            writer.write(response);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }*/
}