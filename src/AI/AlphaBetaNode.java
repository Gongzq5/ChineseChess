package AI;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import chessBoard.ChessBoarder;
import chessBoard.ChessPieces;

public class AlphaBetaNode implements Comparable<AlphaBetaNode> {
	public static int searchTimeCount = 0; 
	public ChessBoarder chessBoarder = null;
	
	private Boolean maxOrMin = null;
	private Integer value = null;
	private List<AlphaBetaNode> nextSteps = null;
	private int currBound = 0;
	private Character moveWhat;
	
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
					redValue += (AIResource.getPositionValue(map[i][j].name, i, j) * 8);
				} else {
					blackValue += (AIResource.basicValues.get(map[i][j].name));
					blackValue += (AIResource.getPositionValue(map[i][j].name, i, j) * 8);// * AIResource.flexibleCofficients.get(map[i][j].name) / 5;
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
                // �������������е�ֱ��
                case '��':
                	moveWhat = '��';
                case '��':
                	if (moveWhat == null) moveWhat = '��';
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
                 // ���ԴӰ˸�������
                case '��':
                	moveWhat = '��';
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
                // ˧�ͽ����Գ�������
                case '˧':
                	if (moveWhat == null) moveWhat = '˧';
                case '��':
                	if (moveWhat == null) moveWhat = '˧';
                case '��':
                	if (moveWhat == null) moveWhat = '��';
                case '��':
                	if (moveWhat == null) moveWhat = '��';
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
                
                // ʿ��������б��
                case 'ʿ':
                	if (moveWhat == null) moveWhat = 'ʿ';
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
                	if (moveWhat == null) moveWhat = '��';
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
               
                default:
                	System.out.println("In default");
                    break;
                }
            }
        }
        for (AlphaBetaNode nextStep : nextSteps) {
        	nextStep.maxOrMin = !maxOrMin;
        }
        Collections.sort(nextSteps);
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
			if (maxOrMin) {
				re = Math.max(re, nextSteps.get(i).getValue());
			} else {
				re = Math.min(re, nextSteps.get(i).getValue());
			}
		}
		searchTimeCount += nextSteps.size();
		return re;
	}

	public alphaBetaReturn alphaBetaSearch(int depth, int alpha, int beta, boolean maxOrMin) {
		this.maxOrMin = maxOrMin;
		AlphaBetaNode node2 = this;
		if (depth == 0 || this.chessBoarder.Winner() != '��') {
			if (this.chessBoarder.Winner() != '��') 
				System.out.println("Depth " + depth + " find a winner " + this.chessBoarder.Winner());
			searchTimeCount++;
			return new alphaBetaReturn(estimateSelf(), this);
		} else {
			genrateAllPossibleNextSteps(maxOrMin ? '��' : '��');
			if (maxOrMin) {
				// �󼫴�
				for (AlphaBetaNode nextStep : nextSteps) {
					alphaBetaReturn r = nextStep.alphaBetaSearch(depth-1, alpha, beta, !maxOrMin);
					searchTimeCount++;
					if (alpha < r.value) {
						alpha = r.value;
						node2 = nextStep;
					}
					if (beta <= alpha) {
						break; // beta ��֦
					}
				
				}
				return new alphaBetaReturn(alpha, node2);
			} else {
				// ��С
				for (AlphaBetaNode nextStep : nextSteps) {
					// �²�ڵ����󼫴󣬴ӷ���ֵ����
					alphaBetaReturn r = nextStep.alphaBetaSearch(depth-1, alpha, beta, !maxOrMin);
					searchTimeCount++;
					if (beta > r.value) {
						beta = r.value;
						node2 = nextStep;
					}
					if (beta <= alpha) {
						break; // alpha��֦
					}
				}
				return new alphaBetaReturn(beta, node2);
			}
		}
	}
	
	public class alphaBetaReturn {
		public int value;
		public AlphaBetaNode node;
		public alphaBetaReturn(int value, AlphaBetaNode node) {
			this.value = value;
			this.node = node;
		}
	}
	
//	function alphabeta(node, depth, ��, ��, Player)
//    //�ﵽ����������Ȼ�ʤ���ѷ�         
//    if  depth = 0 or node is a terminal node
//        return the heuristic value of node
//    if  Player = MaxPlayer // ����ڵ�
//        for each child of node // �ӽڵ��Ǽ�С�ڵ�
//            �� := max(��, alphabeta(child, depth-1, ��, ��, not(Player) ))   
//            if �� �� �� 
//            // �ü���ڵ��ֵ>=��>=�£��ü���ڵ�������������ֵ�϶�����ڦ£���˲��ᱻ���ϲ�ļ�С�ڵ���ѡ���ˡ����ڸ��ڵ㣬��Ϊ������
//                 break //beta��֦                        
//        return ��
//    else // ��С�ڵ�
//        for each child of node //�ӽڵ��Ǽ���ڵ�
//            �� := min(��, alphabeta(child, depth-1, ��, ��, not(Player) )) // ��С�ڵ�
//            if �� �� �� // �ü���ڵ��ֵ<=��<=�����ü�С�ڵ�������������ֵ�϶���С�ڦ�����˲��ᱻ���ϲ�ļ���ڵ���ѡ���ˡ����ڸ��ڵ㣬��Ϊ������
//                break //alpha��֦
//        return �� 
	
	@Override
	public int compareTo(AlphaBetaNode arg0) {
        Map<Character, Integer> order = new HashMap<Character, Integer>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
        	put('��', 1);
        	put('��', 2);
        	put('��', 3);
        	put('��', 4); put('��', 4);
        	put('��', 5); put('��', 5);
        	put('ʿ', 6); put('��', 6);
        	put('˧', 7); 
        }};
        if (moveWhat == null) return 0;
        return order.get(this.moveWhat).compareTo(order.get(arg0.moveWhat));
    }
}
