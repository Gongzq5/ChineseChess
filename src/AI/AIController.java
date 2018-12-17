package AI;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.security.auth.kerberos.KerberosKey;

import chessBoard.ChessBoarder;
import chessBoard.ChessPieces;
import whindow.ChineseChessMainFrame;

public class AIController {

	
	
	public AIController() {}
	
	public void play() {
		final ChessBoarder currChessBoarder = ChineseChessMainFrame.MyBoarder;
		System.out.println("AI Play.");
		List<ChessBoarder> nextSteps = genrateAllPossibleNextSteps(currChessBoarder, '��');
		System.out.println("Genrate all possible step, In all " + nextSteps.size());
		int maxValue = -65536;
		ChessBoarder maxBoarder = null;
		for (ChessBoarder chessBoarder : nextSteps) {
			int value = estimate(chessBoarder, 'b');
			if (value > maxValue) {
				maxBoarder = chessBoarder;
				maxValue = value;
			}
		}
		ChineseChessMainFrame.MyBoarder = maxBoarder;
		ChineseChessMainFrame.InfBoard.AddLog(maxBoarder.informations);
		System.out.println("AI Over.");
	}
	
    public List<ChessBoarder> genrateAllPossibleNextSteps(ChessBoarder currChessBoarder, char which) {
        List<ChessBoarder> nextSteps = new LinkedList<>();
        for (int i = 0; i < currChessBoarder.MyPieces.length; i++) {
            for (int j = 0; j < currChessBoarder.MyPieces[i].length; j++) {             
                if (currChessBoarder.MyPieces[i][j] == null
                	|| currChessBoarder.MyPieces[i][j].name.charAt(0) != which) continue;
                ChessBoarder tBoarder = currChessBoarder.clone();
                Point currPoint = new Point(j, i);
                switch (currChessBoarder.MyPieces[i][j].name.charAt(1)) {
                // ˧�ͽ����Գ�������
                case '˧':
                case '��':
                case '��':
                case '��':
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i))
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j, i+1))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j, i-1))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // ���ԴӰ˸�������
                case '��':
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i+1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i-1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i+1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i-1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i+2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i-2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i+2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i-2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    
                    break;
                // ʿ��������б��
                case 'ʿ':
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i+1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i-1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i+1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i-1))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // �ೢ������б��
                case '��':
                case '��':
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i+2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i-2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i+2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i-2))) {
                        nextSteps.add(tBoarder);
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // �������������е�ֱ��
                case '��':
                case '��':
                    for (int ii = 0; ii < 10; ii++) {
                        if (ii == i) continue;
                        if (tBoarder.PieceMove(currPoint, new Point(j, ii)) 
                            || tBoarder.PieceEat(currPoint, new Point(j, ii))) {
                            nextSteps.add(tBoarder);
                            tBoarder = currChessBoarder.clone();
                        }
                    }
                    for (int jj = 0; jj < 9; jj++) {
                        if (jj == j) continue;
                        if (tBoarder.PieceMove(currPoint, new Point(jj, i)) 
                            || tBoarder.PieceEat(currPoint, new Point(jj, i))) {
                            nextSteps.add(tBoarder);
                            tBoarder = currChessBoarder.clone();
                        }
                    }
                    break;
                // case '��':
                // case '��':
                    // if (tBoarder.PieceMove(currPoint, new Point(j+1, i))
                    //  || tBoarder.PieceEat(currPoint, new Point(j+1, i))) {
                    //  nextSteps.add(tBoarder);
                    //  tBoarder = currChessBoarder.clone();
                    // }
                    // if (tBoarder.PieceMove(currPoint, new Point(j-1, i))
                    //  || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                    //  nextSteps.add(tBoarder);
                    //  tBoarder = currChessBoarder.clone();
                    // }
                    // if (tBoarder.PieceMove(currPoint, new Point(j, i+1))
                    //  || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                    //  nextSteps.add(tBoarder);
                    //  tBoarder = currChessBoarder.clone();
                    // }
                    // if (tBoarder.PieceMove(currPoint, new Point(j, i-1))
                    //  || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                    //  nextSteps.add(tBoarder);
                    //  tBoarder = currChessBoarder.clone();
                    // }
                    // break;
                default:
                    break;
                }
            }
        }
        return nextSteps;
    }
	
//	public int alphaBetaSearch(int alpha, int beta, int depth) {
//		if (depth == 0) {
//			return estimate();
//		}
//		
//		List<ChessBoarder> nextSteps = new LinkedList<>();
//		for (ChessBoarder nextStep : nextSteps) {
//			// get a step from tree
//			int value = -alphaBetaSearch(-beta, -alpha, depth-1);
//		}
//		
//		return 0;
//	}
	
	public int estimate(ChessBoarder chessBoarder, char which) {
		int redValue = 0;
		int blackValue = 0;
		ChessPieces[][] map = chessBoarder.MyPieces;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == null) continue;
				System.out.println("" + map[i][j].name);
				if (map[i][j].name.charAt(0) == '��') {
					redValue += (AIResource.basicValues.get(map[i][j].name));
					redValue += (AIResource.getPositionValue(map[i][j].name, i, j));
				} else {
					blackValue += (AIResource.basicValues.get(map[i][j].name));
					blackValue += (AIResource.getPositionValue(map[i][j].name, i, j));
				}
			}
		}
		return which == 'r' ? redValue - blackValue : blackValue - redValue;
	}
}
