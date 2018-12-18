package AI;

import java.util.List;

import whindow.ChineseChessMainFrame;

public class AIController {

	public AIController() {}
	
	public void play() {
		final AlphaBetaNode currNode = new AlphaBetaNode(ChineseChessMainFrame.MyBoarder);
		System.out.println("AI Play.");
		AlphaBetaNode maxNextNode = minMaxSearch(currNode, 2);
		ChineseChessMainFrame.MyBoarder = maxNextNode.chessBoarder;
		ChineseChessMainFrame.InfBoard.AddLog(maxNextNode.chessBoarder.informations);
		System.out.println("AI Over.");
	}
	
	public AlphaBetaNode minMaxSearch(AlphaBetaNode start, int depth) {
		// 首层，必定是取极大
		start.minMaxSearch(depth, true);
		int maxValue = -65536;
		AlphaBetaNode maxNextNode = null;
		List<AlphaBetaNode> nextSteps = start.getNextSteps('黑');
		for (AlphaBetaNode nextStep : nextSteps) {
			if (maxValue < nextStep.getValue()) {
				maxValue = nextStep.getValue();
				maxNextNode = nextStep;
			}
		}
		return maxNextNode;
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

}
