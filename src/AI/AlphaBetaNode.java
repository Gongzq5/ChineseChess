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
				if (map[i][j].name.charAt(0) == '红') {
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
                // 车尝试着走所有的直线
                case '车':
                	moveWhat = '车';
                case '炮':
                	if (moveWhat == null) moveWhat = '炮';
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
                 // 马尝试从八个方向走
                case '马':
                	moveWhat = '马';
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
                // 帅和将尝试朝四面走
                case '帅':
                	if (moveWhat == null) moveWhat = '帅';
                case '将':
                	if (moveWhat == null) moveWhat = '帅';
                case '卒':
                	if (moveWhat == null) moveWhat = '卒';
                case '兵':
                	if (moveWhat == null) moveWhat = '兵';
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
                
                // 士尝试着走斜线
                case '士':
                	if (moveWhat == null) moveWhat = '士';
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
                	if (moveWhat == null) moveWhat = '相';
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
		if (depth == 0 || this.chessBoarder.Winner() != '无') {
			if (this.chessBoarder.Winner() != '无') 
				System.out.println("Depth " + depth + " find a winner " + this.chessBoarder.Winner());
			searchTimeCount++;
			return new alphaBetaReturn(estimateSelf(), this);
		} else {
			genrateAllPossibleNextSteps(maxOrMin ? '黑' : '红');
			if (maxOrMin) {
				// 求极大
				for (AlphaBetaNode nextStep : nextSteps) {
					alphaBetaReturn r = nextStep.alphaBetaSearch(depth-1, alpha, beta, !maxOrMin);
					searchTimeCount++;
					if (alpha < r.value) {
						alpha = r.value;
						node2 = nextStep;
					}
					if (beta <= alpha) {
						break; // beta 剪枝
					}
				
				}
				return new alphaBetaReturn(alpha, node2);
			} else {
				// 极小
				for (AlphaBetaNode nextStep : nextSteps) {
					// 下层节点是求极大，从返回值中求
					alphaBetaReturn r = nextStep.alphaBetaSearch(depth-1, alpha, beta, !maxOrMin);
					searchTimeCount++;
					if (beta > r.value) {
						beta = r.value;
						node2 = nextStep;
					}
					if (beta <= alpha) {
						break; // alpha剪枝
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
	
//	function alphabeta(node, depth, α, β, Player)
//    //达到最深搜索深度或胜负已分         
//    if  depth = 0 or node is a terminal node
//        return the heuristic value of node
//    if  Player = MaxPlayer // 极大节点
//        for each child of node // 子节点是极小节点
//            α := max(α, alphabeta(child, depth-1, α, β, not(Player) ))   
//            if β ≤ α 
//            // 该极大节点的值>=α>=β，该极大节点后面的搜索到的值肯定会大于β，因此不会被其上层的极小节点所选用了。对于根节点，β为正无穷
//                 break //beta剪枝                        
//        return α
//    else // 极小节点
//        for each child of node //子节点是极大节点
//            β := min(β, alphabeta(child, depth-1, α, β, not(Player) )) // 极小节点
//            if β ≤ α // 该极大节点的值<=β<=α，该极小节点后面的搜索到的值肯定会小于α，因此不会被其上层的极大节点所选用了。对于根节点，α为负无穷
//                break //alpha剪枝
//        return β 
	
	@Override
	public int compareTo(AlphaBetaNode arg0) {
        Map<Character, Integer> order = new HashMap<Character, Integer>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
        	put('车', 1);
        	put('炮', 2);
        	put('马', 3);
        	put('兵', 4); put('卒', 4);
        	put('相', 5); put('象', 5);
        	put('士', 6); put('仕', 6);
        	put('帅', 7); 
        }};
        if (moveWhat == null) return 0;
        return order.get(this.moveWhat).compareTo(order.get(arg0.moveWhat));
    }
}
