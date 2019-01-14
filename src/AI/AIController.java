package AI;

import java.util.List;

import whindow.ChineseChessMainFrame;

public class AIController {

	public AIController() {}
	
	public void play() {
		final AlphaBetaNode currNode = new AlphaBetaNode(ChineseChessMainFrame.MyBoarder);
		System.out.println("AI Play.");
		int count = 0;
		for (int i = 0; i < currNode.chessBoarder.MyPieces.length; i++) {
            for (int j = 0; j < currNode.chessBoarder.MyPieces[i].length; j++) {
            	if (currNode.chessBoarder.MyPieces[i][j] != null) count++;
            }
		}
		int depth = 2;
		if (count < 4) depth = 6;
		else if (count < 6) depth = 5;
		else if (count < 16) depth = 4;
		else if (count < 28) depth = 3;
		else depth = 2;
		AlphaBetaNode.searchTimeCount = 0;
//		AlphaBetaNode maxNextNode = minMaxSearch(currNode, 3);
		AlphaBetaNode maxNextNode = alphaBetaSearch(currNode, depth);
		ChineseChessMainFrame.MyBoarder = maxNextNode.chessBoarder;
		ChineseChessMainFrame.InfBoard.AddLog(maxNextNode.chessBoarder.informations);
		System.out.println("AI Over.");
		System.out.println(AlphaBetaNode.searchTimeCount);
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

//	public AlphaBetaNode alphaBetaSearch(AlphaBetaNode start, int depth) {
//		start.alphaBetaSearch(true, depth);
//		int maxValue = -65536;
//		AlphaBetaNode maxNextNode = null;
//		List<AlphaBetaNode> nextSteps = start.getNextSteps('黑');
//		for (AlphaBetaNode nextStep : nextSteps) {
//			if (maxValue < nextStep.getValue()) {
//				maxValue = nextStep.getValue();
//				maxNextNode = nextStep;
//			}
//		}
//		return maxNextNode; 
//	}
	
	public AlphaBetaNode alphaBetaSearch(AlphaBetaNode start, int depth) {
		return start.alphaBetaSearch(depth, -100000, 100000, true).node;
	}
}
