package AI;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AIResource {
	public static Map<String, Integer> basicValues = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put("ºÚ½«", 65536);
        put("ºÚ³µ", 950);
        put("ºÚÂí", 450);
        put("ºÚÅÚ", 450);
        put("ºÚÊ¿", 200);
        put("ºÚÏó", 200);
        put("ºÚ×ä", 130);
        put("ºì±ø", 130);
        put("ºì³µ", 950);
        put("ºìÂí", 450);
        put("ºìÅÚ", 450);
        put("ºìÊ¿", 200);
        put("ºìË§", 65536);
        put("ºìÏà", 200);
	}};
	
	public static Map<String, Integer> flexibleCofficients = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put("ºÚ½«", 0);
        put("ºÚ³µ", 7);
        put("ºÚÂí", 13);
        put("ºÚÅÚ", 7);
        put("ºÚÊ¿", 1);
        put("ºÚÏà", 1);
        put("ºÚ×ä", 15);
        put("ºì±ø", 15);
        put("ºì³µ", 7);
        put("ºìÂí", 13);
        put("ºìÅÚ", 7);
        put("ºìÊ¿", 1);
        put("ºìË§", 0);
        put("ºìÏà", 1);
	}};
	
	public static int getPositionValue(String name, int i, int j) {
		if (name.equals("ºÚ½«")) {
		    return kingValueInPosition[9-i][j];
		} else if (name.equals("ºÚ³µ")) {
			return carValueInPosition[9-i][j];
		} else if (name.equals("ºÚÂí")) {
			return horseValueInPosition[9-i][j];
		} else if (name.equals("ºÚÅÚ")) {
			return paoValueInPosition[9-i][j];
		} else if (name.equals("ºÚ×ä")) {
			return soilderValueInPosition[9-i][j];
		} else if (name.equals("ºì±ø")) {
			return soilderValueInPosition[i][j];
		} else if (name.equals("ºì³µ")) {
			return carValueInPosition[i][j];
		} else if (name.equals("ºìÂí")) {
			return horseValueInPosition[i][j];
		} else if (name.equals("ºìÅÚ")) {
			return paoValueInPosition[i][j];
		} else if (name.equals("ºìË§")) {
			return kingValueInPosition[i][j];
		}
		return 1;
	}
	
	public static int[][] horseValueInPosition = 
		   {{90, 105, 135, 135, 135, 135, 135, 105, 90},
			{105, 135, 165, 165, 165, 165, 165, 135, 105},
			{135, 165, 200, 200, 200, 200, 200, 165, 135},
			{135, 165, 200, 200, 200, 200, 200, 165, 135},
			{135, 165, 200, 200, 200, 200, 200, 165, 135},
			{105, 135, 165, 165, 165, 165, 165, 135, 105},
			{85, 105, 125, 125, 125, 125, 125, 105, 85},
			{70, 85, 105, 105, 105, 105, 105, 85, 70},
			{30, 55, 85, 55, 55, 55, 85, 55, 30},
			{25, 30, 70, 30, 30, 30, 70, 30, 25}};
	
	public static int[][] carValueInPosition =		
			{{160, 160, 160, 175, 195, 175, 160, 160, 160},
			 {175, 175, 175, 195, 205, 195, 175, 175, 175},
			 {160, 160, 160, 175, 195, 175, 160, 160, 160},
			 {145, 145, 145, 160, 175, 160, 145, 145, 145},
			 {145, 145, 145, 160, 175, 160, 145, 145, 145},
			 {145, 145, 145, 160, 175, 160, 145, 145, 145},
			 {120, 120, 120, 145, 165, 145, 120, 120, 120},
			 {115, 115, 115, 125, 165, 125, 115, 115, 115},
			 {115, 115, 115, 125, 170, 125, 115, 115, 115},
			 {100, 100, 100, 125, 165, 125, 100, 100, 100}};

	public static int[][] soilderValueInPosition =
		   {{5, 10, 18, 25, 30, 25, 18, 10, 5},
			{28, 55, 70, 115, 130, 115, 70, 55, 28},
			{22, 50, 65, 85, 110, 85, 65, 50, 22},
			{18, 25, 30, 56, 72, 56, 30, 25, 18},
			{8, 12, 15, 20, 30, 20, 15, 12, 8},
			{5, 0, 8, 0, 10, 0, 8, 0, 5},
			{-5, 0, -7, 0, -1, 0, -7, 0, -5},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0}};
	
	public static int[][] paoValueInPosition =
		   {{5, 10, 18, 25, 30, 25, 18, 10, 5},
			{28, 55, 70, 115, 130, 115, 70, 55, 28},
			{22, 50, 65, 85, 110, 85, 65, 50, 22},
			{18, 25, 30, 56, 72, 56, 30, 25, 18},
			{8, 12, 15, 20, 30, 20, 15, 12, 8},
			{5, 0, 8, 0, 10, 0, 8, 0, 5},
			{-5, 0, -7, 0, -1, 0, -7, 0, -5},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0}};
	
	public static int[][] kingValueInPosition =
			{{0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 20, 20, 20, 0, 0, 0},
			 {0, 0, 0, 20, 20, 20, 0, 0, 0},
			 {0, 0, 0, 20, 50, 20, 0, 0, 0}};
	
}
