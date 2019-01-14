package AI;

import java.util.HashMap;
import java.util.Map;

public final class AIResource {

	//private int evalPieceValue(int p) {
	//    /* b | s | x | m | j | p | z*/
	//	/* ½«  Ê¿ Ïà Âí ³µ ÅÚ ×ä */
	//    int[] pieceValue = new int[]{1000000, 110, 110, 300, 600, 300, 70};
	//    return pieceValue[p];
	//}
	
	public static Map<String, Integer> basicValues = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put("ºÚ½«", 1000000);
		put("ºìË§", 1000000);
        put("ºÚ³µ", 600);
        put("ºì³µ", 600);
        put("ºÚÂí", 300);
        put("ºìÂí", 300);
        put("ºÚÅÚ", 300);
        put("ºìÅÚ", 300);
        put("ºÚÊ¿", 110);
        put("ºìÊ¿", 110);
        put("ºÚÏó", 110);
        put("ºìÏà", 110);
        put("ºÚ×ä", 70);
        put("ºì±ø", 70);
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
		if (name.equals("ºÚ³µ")) {
			return evalPiecePosition (4, 9 - i, j);
		} else if (name.equals("ºÚÂí")) {
			return evalPiecePosition (3, 9 - i, j);
		} else if (name.equals("ºÚÅÚ")) {
			return evalPiecePosition (5, 9 - i, j);
		} else if (name.equals("ºÚ×ä")) {
			return evalPiecePosition (6, 9 - i, j);
		} else if (name.equals("ºì±ø")) {
			return evalPiecePosition (6, i, j);
		} else if (name.equals("ºì³µ")) {
			return evalPiecePosition (4, i, j);
		} else if (name.equals("ºìÂí")) {
			return evalPiecePosition (3, i, j);
		} else if (name.equals("ºìÅÚ")) {
			return evalPiecePosition (5, i, j);
		}
		return 0;
	}
	
	static private int evalPiecePosition(int p, int i, int j) {
        int[][] pPosition = new int[][]{
                {6, 4, 0, -10, -12, -10, 0, 4, 6},
                {2, 2, 0, -4, -14, -4, 0, 2, 2},
                {2, 2, 0, -10, -8, -10, 0, 2, 2},
                {0, 0, -2, 4, 10, 4, -2, 0, 0},
                {0, 0, 0, 2, 8, 2, 0, 0, 0},
                {-2, 0, 4, 2, 6, 2, 4, 0, -2},
                {0, 0, 0, 2, 4, 2, 0, 0, 0},
                {4, 0, 8, 6, 10, 6, 8, 0, 4},
                {0, 2, 4, 6, 6, 6, 4, 2, 0},
                {0, 0, 2, 6, 6, 6, 2, 0, 0}
        };
        int[][] mPosition = new int[][]{
                {4, 8, 16, 12, 4, 12, 16, 8, 4},
                {4, 10, 28, 16, 8, 16, 28, 10, 4},
                {12, 14, 16, 20, 18, 20, 16, 14, 12},
                {8, 24, 18, 24, 20, 24, 18, 24, 8},
                {6, 16, 14, 18, 16, 18, 14, 16, 6},
                {4, 12, 16, 14, 12, 14, 16, 12, 4},
                {2, 6, 8, 6, 10, 6, 8, 6, 2},
                {4, 2, 8, 8, 4, 8, 8, 2, 4},
                {0, 2, 4, 4, -2, 4, 4, 2, 0},
                {0, -4, 0, 0, 0, 0, 0, -4, 0}
        };
        int[][] jPosition = new int[][]{
                {14, 14, 12, 18, 16, 18, 12, 14, 14},
                {16, 20, 18, 24, 26, 24, 18, 20, 16},
                {12, 12, 12, 18, 18, 18, 12, 12, 12},
                {12, 18, 16, 22, 22, 22, 16, 18, 12},
                {12, 14, 12, 18, 18, 18, 12, 14, 12},
                {12, 16, 14, 20, 20, 20, 14, 16, 12},
                {6, 10, 8, 14, 14, 14, 8, 10, 6},
                {4, 8, 6, 14, 12, 14, 6, 8, 4},
                {8, 4, 8, 16, 8, 16, 8, 4, 8},
                {-2, 10, 6, 14, 12, 14, 6, 10, -2}
        };
        int[][] zPosition = new int[][]{
                {0, 3, 6, 9, 12, 9, 6, 3, 0},
                {18, 36, 56, 80, 120, 80, 56, 36, 18},
                {14, 26, 42, 60, 80, 60, 42, 26, 14},
                {10, 20, 30, 34, 40, 34, 30, 20, 10},
                {6, 12, 18, 18, 20, 18, 18, 12, 6},
                {2, 0, 8, 0, 8, 0, 8, 0, 2},
                {0, 0, -2, 0, 4, 0, -2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        if (p == 3) return mPosition[i][j];
        if (p == 4) return jPosition[i][j];
        if (p == 5) return pPosition[i][j];
        if (p == 6) return zPosition[i][j];
        return -1;
    }
}
