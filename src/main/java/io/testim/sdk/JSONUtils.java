package io.testim.sdk;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class JSONUtils {

	private static final Gson gson = new Gson();

	private JSONUtils() {
	}

	public static boolean isStringJSONValid(String jsonInString) {
		try {
			gson.fromJson(jsonInString, Object.class);
			return true;
		} catch (JsonSyntaxException ex) {
			return false;
		}
	}

	public static boolean isValidJsonReader(JsonReader reader) {
		try {
			gson.fromJson(reader, Object.class);
			return true;
		} catch (JsonSyntaxException ex) {
			return false;
		}
	}

	public static <T> T parseJsonReader(JsonReader reader, Type type) throws TestimException {
		try {
			return gson.fromJson(reader, type);
		} catch (JsonSyntaxException ex) {
			throw new TestimException(ex.getMessage());
		}
	}

	public static <T> T parseStringJson(String jsonString, Type type) throws TestimException {
		try {
			return gson.fromJson(jsonString, type);
		} catch (JsonSyntaxException ex) {
			throw new TestimException(ex.getMessage());
		}
	}
}
