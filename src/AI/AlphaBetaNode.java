package AI;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import chessBoard.ChessBoarder;
import chessBoard.ChessPieces;

public class AlphaBetaNode {
	
	public ChessBoarder chessBoarder = null;
	
	private Integer value = null;
	private List<AlphaBetaNode> nextSteps = null;
	
	public AlphaBetaNode(ChessBoarder chessBoarder) {
		this.chessBoarder = chessBoarder;
		this.value = null;
		this.nextSteps = null;
	}

	public Integer getValue() {
		if (value == null) value = estimateSelf();
		return value;
	}
	
	public List<AlphaBetaNode> getNextSteps(char which) {
		if (nextSteps == null) 
			genrateAllPossibleNextSteps(which);
		return nextSteps;
	}
	
	public int estimateSelf() {
		int redValue = 0;
		int blackValue = 0;
		ChessPieces[][] map = chessBoarder.MyPieces;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == null) continue;
				if (map[i][j].name.charAt(0) == '红') {
					redValue += (AIResource.basicValues.get(map[i][j].name));
					redValue += (AIResource.getPositionValue(map[i][j].name, i, j));
				} else {
					blackValue += (AIResource.basicValues.get(map[i][j].name));
					blackValue += (AIResource.getPositionValue(map[i][j].name, i, j));
				}
			}
		}
		return blackValue - redValue;
	}
	
	public void genrateAllPossibleNextSteps(char which) {
        nextSteps = new LinkedList<>();
        ChessBoarder currChessBoarder = chessBoarder;
        for (int i = 0; i < currChessBoarder.MyPieces.length; i++) {
            for (int j = 0; j < currChessBoarder.MyPieces[i].length; j++) {
            	if (currChessBoarder.MyPieces[i][j] == null
                	|| currChessBoarder.MyPieces[i][j].name.charAt(0) != which) continue;
                ChessBoarder tBoarder = currChessBoarder.clone();
                Point currPoint = new Point(j, i);
                switch (currChessBoarder.MyPieces[i][j].name.charAt(1)) {
                // 帅和将尝试朝四面走
                case '帅':
                case '将':
                case '卒':
                case '兵':
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i))
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j, i+1))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j, i-1))
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // 马尝试从八个方向走
                case '马':
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i+1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i-1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i+1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i-1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i+2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i-2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i+2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i-2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    
                    break;
                // 士尝试着走斜线
                case '士':
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i+1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i-1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-1, i+1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-1, i+1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+1, i-1)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+1, i-1))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // 相尝试着走斜线
                case '象':
                case '相':
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i+2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i-2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j-2, i+2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j-2, i+2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    if (tBoarder.PieceMove(currPoint, new Point(j+2, i-2)) 
                        || tBoarder.PieceEat(currPoint, new Point(j+2, i-2))) {
                        nextSteps.add(new AlphaBetaNode(tBoarder));
                        tBoarder = currChessBoarder.clone();
                    }
                    break;
                // 车尝试着走所有的直线
                case '车':
                case '炮':
                    for (int ii = 0; ii < 10; ii++) {
                        if (ii == i) continue;
                        if (tBoarder.PieceMove(currPoint, new Point(j, ii)) 
                            || tBoarder.PieceEat(currPoint, new Point(j, ii))) {
                            nextSteps.add(new AlphaBetaNode(tBoarder));
                            tBoarder = currChessBoarder.clone();
                        }
                    }
                    for (int jj = 0; jj < 9; jj++) {
                        if (jj == j) continue;
                        if (tBoarder.PieceMove(currPoint, new Point(jj, i)) 
                            || tBoarder.PieceEat(currPoint, new Point(jj, i))) {
                            nextSteps.add(new AlphaBetaNode(tBoarder));
                            tBoarder = currChessBoarder.clone();
                        }
                    }
                    break;
                default:
                	System.out.println("In default");
                    break;
                }
            }
        }
    }

	
	public int minMaxSearch(int depth, boolean maxOrMin) {
		int re = maxOrMin ? -65536 : 65535;
		genrateAllPossibleNextSteps(maxOrMin ? '黑' : '红');
		if (depth == 1) {
			for (AlphaBetaNode nextStep : nextSteps) {
				nextStep.value = nextStep.estimateSelf();	
			}
		} else {
			for (AlphaBetaNode nextStep : nextSteps) {
				nextStep.value = nextStep.minMaxSearch(depth-1, !maxOrMin);
			}
		}
		for (int i = 0; i < nextSteps.size(); i++) {
			if (maxOrMin ? (nextSteps.get(i).getValue() > re) : (nextSteps.get(i).getValue() < re)) {
				re = nextSteps.get(i).getValue();
			}
		}
		return re;
	}
	
}
