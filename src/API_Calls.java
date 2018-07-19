import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONObject;

public class API_Calls {

	public static String get_Weather(int zipCode) throws Exception {
		JSONObject weatherData = (initialize("https://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&units=imperial&APPID=YOUR_API_KEY_HERE"));
		JSONObject main = weatherData.getJSONObject("main");
		JSONObject current = weatherData.getJSONArray("weather").getJSONObject(0);
		String response = "The weather in " + weatherData.getString("name") + " is " + current.getString("description") + ". It is currently " + main.getDouble("temp") + " degrees with a high of " + main.getDouble("temp_max") + " and a low of " + main.getDouble("temp_min");
		System.out.println(response);
		return response;
	}
	
	public static String get_Weather(String city) throws Exception {
		JSONObject weatherData = (initialize("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&APPID=YOUR_API_KEY_HERE"));
		JSONObject main = weatherData.getJSONObject("main");
		JSONObject current = weatherData.getJSONArray("weather").getJSONObject(0);
		String response = "The weather in " + weatherData.getString("name") + " is " + current.getString("description") + ". It is currently " + main.getDouble("temp") + " degrees with a high of " + main.getDouble("temp_max") + " and a low of " + main.getDouble("temp_min");
		System.out.println(response);
		return response;
	}
	
	public static String get_Weather(String city, String country) throws Exception {
		JSONObject weatherData = (initialize("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units=imperial&APPID=YOUR_API_KEY_HERE"));
		JSONObject main = weatherData.getJSONObject("main");
		JSONObject current = weatherData.getJSONArray("weather").getJSONObject(0);
		String response = "The weather in " + weatherData.getString("name") + " is " + current.getString("description") + ". It is currently " + main.getDouble("temp") + " degrees with a high of " + main.getDouble("temp_max") + " and a low of " + main.getDouble("temp_min");
		System.out.println(response);
		return response;
	}
	
	public static String get_Matchups() throws Exception {
		JSONObject tier1 = (initialize("https://api.guildwars2.com/v2/wvw/matches/1-1"));
		JSONObject tier1worlds = tier1.getJSONObject("worlds");
		String line1 = "Current Matchups: ";
		System.out.println(line1);
		
		String line2 = "Tier 1: " + get_World(tier1worlds.getInt("red")) + " vs. " + get_World(tier1worlds.getInt("blue")) + " vs. " + get_World(tier1worlds.getInt("green")) + "\t";
		System.out.println(line2);
		
		JSONObject tier2 = (initialize("https://api.guildwars2.com/v2/wvw/matches/1-2"));
		JSONObject tier2worlds = tier2.getJSONObject("worlds");
		String line3 = "Tier 2: " + get_World(tier2worlds.getInt("red")) + " vs. " + get_World(tier2worlds.getInt("blue")) + " vs. " + get_World(tier2worlds.getInt("green")) + "\t";
		System.out.println(line3);
		
		JSONObject tier3 = (initialize("https://api.guildwars2.com/v2/wvw/matches/1-3"));
		JSONObject tier3worlds = tier3.getJSONObject("worlds");
		String line4 = "Tier 3: " + get_World(tier3worlds.getInt("red")) + " vs. " + get_World(tier3worlds.getInt("blue")) + " vs. " + get_World(tier3worlds.getInt("green")) + "\t";
		System.out.println(line4);
		
		JSONObject tier4 = (initialize("https://api.guildwars2.com/v2/wvw/matches/1-4"));
		JSONObject tier4worlds = tier4.getJSONObject("worlds");
		String line5 = "Tier 4: " + get_World(tier4worlds.getInt("red")) + " vs. " + get_World(tier4worlds.getInt("blue")) + " vs. " + get_World(tier4worlds.getInt("green")) + "\t";
		System.out.println(line5);
		return (line1 + line2 + "  ||  " + line3 + "  ||  " +  line4 + "  ||  " + line5);
	}
	
	public static String get_Score(int tierNum) throws Exception {
		String tierId = "https://api.guildwars2.com/v2/wvw/matches/1-" + tierNum;
		JSONObject tier = (initialize(tierId));
		JSONObject worlds = tier.getJSONObject("worlds");
		JSONObject scores = tier.getJSONObject("scores");
		String response = "Tier " + tierNum + ": Current Match Scores: \t" + get_World(worlds.getInt("red")) +  ": " + scores.getInt("red") + ", \t" + get_World(worlds.getInt("blue")) + ": " + scores.getInt("blue") + ", \t" + get_World(worlds.getInt("green")) + ": " + scores.getInt("green") + "\n";
		System.out.println(response);
		return response;
	}
	
	public static String get_VP(int tierNum) throws Exception {
		String tierId = "https://api.guildwars2.com/v2/wvw/matches/1-" + tierNum;
		JSONObject tier = (initialize(tierId));
		JSONObject worlds = tier.getJSONObject("worlds");
		JSONObject vp = tier.getJSONObject("victory_points");
		String response = "Tier " + tierNum + ": Current Victory Points: \t" + get_World(worlds.getInt("red")) +  ": " + vp.getInt("red") + ", \t" + get_World(worlds.getInt("blue")) + ": " + vp.getInt("blue") + ", \t" + get_World(worlds.getInt("green")) + ": " + vp.getInt("green") + "\n";
		System.out.println(response);
		return response;
	}
	
	//Incomplete / NYI
	public static String get_KDR(int tierNum) throws Exception {
		String tierId = "https://api.guildwars2.com/v2/wvw/matches/1-" + tierNum;
		JSONObject tier = (initialize(tierId));
		JSONObject worlds = tier.getJSONObject("worlds");
		JSONObject kills = tier.getJSONObject("kills");
		JSONObject deaths = tier.getJSONObject("deaths");
		double redKDR, blueKDR, greenKDR;
		DecimalFormat df = new DecimalFormat("#.##");
		redKDR = (double)kills.getInt("red") / (double)deaths.getInt("red");
		blueKDR = (double)kills.getInt("blue") / (double)deaths.getInt("blue");
		greenKDR = (double)kills.getInt("green") / (double)deaths.getInt("green");
		String redKDRf = df.format(redKDR);
		String blueKDRf = df.format(blueKDR);
		String greenKDRf = df.format(greenKDR);
		String result = "Tier " + tierNum + ": Current Kill/Death Ratios: \t " + get_World(worlds.getInt("red")) + " : " + redKDRf + ", " + get_World(worlds.getInt("blue")) + " : " + blueKDRf + ", " + get_World(worlds.getInt("green")) + " : " + greenKDRf;
		System.out.print("Tier " + tierNum + ": Current Kill/Death Ratios: \t");
		System.out.printf("%s : %.2f \t %s : %.2f \t %s : %.2f \n\n", get_World(worlds.getInt("red")), redKDRf, get_World(worlds.getInt("blue")), blueKDRf, get_World(worlds.getInt("green")), greenKDRf);
		return result;
	}
	
	public static String get_World(int world) throws Exception {
		JSONObject myResponse = (initialize("https://api.guildwars2.com/v2/worlds" + "/" + world));
		return myResponse.getString("name");
	}
	
	//This function handles all the general behavior for an HTTP GET request, and provides a parseable JSONObject.
	public static JSONObject initialize(String address) throws Exception {
		String url = address;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}
		System.out.println(response.toString());
		JSONObject myResponse = new JSONObject(response.toString());
		return myResponse;
	}
}

