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
		put("�ڽ�", 65536);
        put("�ڳ�", 950);
        put("����", 450);
        put("����", 450);
        put("��ʿ", 200);
        put("����", 200);
        put("����", 130);
        put("���", 130);
        put("�쳵", 950);
        put("����", 450);
        put("����", 450);
        put("��ʿ", 200);
        put("��˧", 65536);
        put("����", 200);
	}};
	
	public static Map<String, Integer> flexibleCofficients = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put("�ڽ�", 0);
        put("�ڳ�", 7);
        put("����", 13);
        put("����", 7);
        put("��ʿ", 1);
        put("����", 1);
        put("����", 15);
        put("���", 15);
        put("�쳵", 7);
        put("����", 13);
        put("����", 7);
        put("��ʿ", 1);
        put("��˧", 0);
        put("����", 1);
	}};
	
	public static int getPositionValue(String name, int i, int j) {
		if (name.equals("�ڽ�")) {
		    return kingValueInPosition[9-i][j];
		} else if (name.equals("�ڳ�")) {
			return carValueInPosition[9-i][j];
		} else if (name.equals("����")) {
			return horseValueInPosition[9-i][j];
		} else if (name.equals("����")) {
			return paoValueInPosition[9-i][j];
		} else if (name.equals("����")) {
			return soilderValueInPosition[9-i][j];
		} else if (name.equals("���")) {
			return soilderValueInPosition[i][j];
		} else if (name.equals("�쳵")) {
			return carValueInPosition[i][j];
		} else if (name.equals("����")) {
			return horseValueInPosition[i][j];
		} else if (name.equals("����")) {
			return paoValueInPosition[i][j];
		} else if (name.equals("��˧")) {
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
