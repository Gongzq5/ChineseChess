package AI;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import chessBoard.ChessBoarder;
import chessBoard.ChessPieces;

public class AlphaBetaNode {
	
	public ChessBoarder chessBoarder = null;
	
	private AlphaBetaNode prev = null;
	private Boolean maxOrMin = null;
	private Integer value = null;
	private List<AlphaBetaNode> nextSteps = null;
	private int currBound = 0;
	
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
				if (map[i][j].name.charAt(0) == '��') {
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
                // ˧�ͽ����Գ�������
                case '˧':
                case '��':
                case '��':
                case '��':
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
                // ���ԴӰ˸�������
                case '��':
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
                // ʿ��������б��
                case 'ʿ':
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
                // �ೢ������б��
                case '��':
                case '��':
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
                // �������������е�ֱ��
                case '��':
                case '��':
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
        for (AlphaBetaNode nextStep : nextSteps) {
        	nextStep.prev = this;
        	nextStep.maxOrMin = !maxOrMin;
        }
    }

	
	public int minMaxSearch(int depth, boolean maxOrMin) {
		this.maxOrMin = maxOrMin;
		currBound = maxOrMin ? -65536 : 65535;
		int re = currBound;
		genrateAllPossibleNextSteps(maxOrMin ? '��' : '��');
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
	
	public int alphaBetaSearch(boolean maxOrMin, int depth) {
		this.maxOrMin = maxOrMin;
		currBound = maxOrMin ? -65536 : 65535;
		genrateAllPossibleNextSteps(maxOrMin ? '��' : '��');
		if (depth == 1) {
			// �����ڶ����ˣ����²�˽ڵ���ݾ�������Լ�
			for (AlphaBetaNode nextStep : nextSteps) {
				nextStep.value = nextStep.estimateSelf();	
				if (maxOrMin ? (nextStep.getValue() > currBound) : (nextStep.getValue() < currBound)) {
					currBound = nextStep.getValue();
				}
			}
			// re�õ������һ��Ľ��
			this.backpatch(currBound);
		} else {
			// �Ƕ˽ڵ㣬��Ҫ���ݷ���
			for (AlphaBetaNode nextStep : nextSteps) {
				nextStep.value = nextStep.alphaBetaSearch(!maxOrMin, depth-1);	
				if (!backpatch(nextStep.value)) {
					break;
				}
			}
		}
		return currBound;
	}
	
	public boolean backpatch(int value) {

		boolean re = false;
		if (maxOrMin) {
			// ����󣬼���ǰ������>=currBound
			// �²�ڵ�������С�ģ������²������<=value
			if (currBound >= value) {
				// ��ʱ˵���õ���û�ã���֦���±ߵĲ���
				re = false;
			} else {
				// ���µ�ǰbound�����Ҽ��������²������
				currBound = value;
				re = true;
			}
		} else {
			// ����С������ǰ������<=currBound
			// �²�ڵ��������ģ������²������>=value
			if (currBound <= value) {
				// ��ʱ˵���õ���û�ã���֦���±ߵĲ���
				re = false;
			} else {
				// ���µ�ǰbound�����Ҽ��������²������
				currBound = value;
				re = true;
			}
		}
		
		if (prev != null) {
			prev.backpatch(currBound);
		}
		
		return re;
	}
}
