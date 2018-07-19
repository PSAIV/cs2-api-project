import org.jibble.pircbot.*;

public class MyBot extends PircBot {
	
	public MyBot() {
		this.setName("psaivBot");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (message.equalsIgnoreCase("help")) {
			sendMessage(channel, "Commands are weather and wvw. Type each for more detail.");
		}
		
		if (message.startsWith("weather") || message.startsWith("Weather") || message.startsWith("WEATHER")) {
			String param1 = message.substring(message.lastIndexOf("weather") + 7).trim();
			if(param1.isEmpty()){
				sendMessage(channel, "Accepted formats are weather <city>; weather <city> <country code>; weather <zip code>");
			}
			else if(param1.contains(",")) {
				String param2 = message.substring(param1.lastIndexOf(",") + 1).trim();
				try {
					sendMessage(channel, API_Calls.get_Weather(param1, param2));
				} catch (Exception e) {
					sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
				}
			}
			else if(param1.matches("^[0-9]{5}$")){
				int zipCode = Integer.parseInt(param1);
				try {
					sendMessage(channel, API_Calls.get_Weather(zipCode));
				} catch (Exception e) {
					sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
				}
			}
			else {
				try {
					sendMessage(channel, API_Calls.get_Weather(param1));
				} catch (Exception e) {
					sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
				}
			}
		}
		
		
		if (message.startsWith("wvw") || message.startsWith("WvW")) {
			if (message.contains("score")){
				if(message.contains("1")){
					try {
						sendMessage(channel, API_Calls.get_Score(1));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("2")){
					try {
						sendMessage(channel, API_Calls.get_Score(2));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("3")){
					try {
						sendMessage(channel, API_Calls.get_Score(3));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("4")){
					try {
						sendMessage(channel, API_Calls.get_Score(4));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else {
					sendMessage(channel, sender + ": Please specify which tier, for list of matchups, call wvw matchups");
				}
			}
			else if(message.contains("vp") || message.contains("VP") || message.contains("victory") || message.contains("Victory")){
				if(message.contains("1")){
					try {
						sendMessage(channel, API_Calls.get_VP(1));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("2")){
					try {
						sendMessage(channel, API_Calls.get_VP(2));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("3")){
					try {
						sendMessage(channel, API_Calls.get_VP(3));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("4")){
					try {
						sendMessage(channel, API_Calls.get_VP(4));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else {
					sendMessage(channel, sender + ": Please specify which tier, for list of matchups, call wvw matchups");
				}
			}
			
			//Incomplete / NYI
			else if(message.contains("kdr") || message.contains("KDR")) {
				if(message.contains("1")){
					try {
						sendMessage(channel, API_Calls.get_KDR(1));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("2")){
					try {
						sendMessage(channel, API_Calls.get_KDR(2));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("3")){
					try {
						sendMessage(channel, API_Calls.get_KDR(3));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
				}
				else if(message.contains("4")){
					try {
						sendMessage(channel, API_Calls.get_KDR(4));
					} catch (Exception e) {
						sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
					}
			}
			else if(message.contains("matchups") || message.contains("Matchups")) {
				try {
					sendMessage(channel, API_Calls.get_Matchups());
				} catch (Exception e) {
					sendMessage(channel, "Sorry, I didn't understand that. Please try again or type help for help.");
				}
			}
			else if (sender.equalsIgnoreCase("psaiv")) {
				sendMessage(channel, sender + ": For list of matchups, call wvw matchups. For score and victory point details, call wvw vp <tier> or wvw score <tier>");
			}
		}
	}
}
}