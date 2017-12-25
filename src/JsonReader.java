import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;



//import org.json.JSONObject;

public class JsonReader {

	private static String encodeParams(final Map<String, String> params) {
		
		final String paramsUrl = Joiner.on('&').join(
				
				Iterables.transform(params.entrySet(), new Function<Entry<String, String>, String>() {
					
					
					@Override
					public String apply(final Entry<String, String> input) {
						try {
							final StringBuffer buffer = new StringBuffer();
							buffer.append(input.getKey());// получаем значение вида key=value
							buffer.append('=');
							buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
							return buffer.toString();
						} catch (final UnsupportedEncodingException e) {
							throw new RuntimeException(e);
						}
					}
				}));
		
				
				
		
		return paramsUrl;
	}
	
	
	public static void main(String[] args)  {

		
		final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
		final Map<String, String> params = Maps.newHashMap();
		params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения
		params.put("address", "Россия, Москва, улица Поклонная, 12");// адрес, который нужно геокодировать
		final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
		System.out.println(url);// Путь, что бы можно было посмотреть в браузере ответ службы
		
		

        try {    
        	
        
//        	
//        	
//        	URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=true");
//			// making connection
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//			
//			// Reading data's from url
//			   BufferedReader br = new BufferedReader(new InputStreamReader(
//					(conn.getInputStream())));
//			   
//			   String output;
//				String out="";
//				System.out.println("Output from Server .... \n");
//				while ((output = br.readLine()) != null) {
//					System.out.println(output);
//					out+=output;
//				}
				
				final JSONObject response = JR.read(url);
				JSONObject location = response.getJSONArray("results").getJSONObject(0);
				
				location = location.getJSONObject("geometry");
				location = location.getJSONObject("location");
//				
				final double lng = location.getDouble("lng");// долгота
				final double lat = location.getDouble("lat");// широта
				System.out.println(String.format("%f,%f", lat, lng));// итоговая широта и долгота
		//		
				
        	
//				// Converting Json formatted string into JSON object
//				JSONObject json = (JSONObject) JSONSerializer.toJSON(out);
//				JSONArray results=json.getJSONArray("results");
//				JSONObject rec = results.getJSONObject(0);
//				JSONArray address_components=rec.getJSONArray("address_components");
//				for(int i=0;i<address_components.size();i++){
//				JSONObject rec1 = address_components.getJSONObject(i);
//				//trace(rec1.getString("long_name"));
//				JSONArray types=rec1.getJSONArray("types");
//				String comp=types.getString(0);
//
//				if(comp.equals("locality")){
//					System.out.println("city ————-"+rec1.getString("long_name"));
//				}
//				else if(comp.equals("country")){
//					System.out.println("country ———-"+rec1.getString("long_name"));
//				}
//				}
//				String formatted_address = rec.getString("formatted_address");
//				System.out.println("formatted_address————–"+formatted_address);
//				conn.disconnect();
        	
//        } catch (MalformedURLException e) {
//        	e.printStackTrace();
				} catch (IOException e) {
//			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		

	}

}
