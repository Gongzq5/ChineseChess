package AI;

import java.util.List;

import whindow.ChineseChessMainFrame;

public class AIController {

	public AIController() {}
	
	public void play() {
		final AlphaBetaNode currNode = new AlphaBetaNode(ChineseChessMainFrame.MyBoarder);
		System.out.println("AI Play.");
		currNode.minMaxSearch(2, true);
		int maxValue = -65536;
		AlphaBetaNode maxNextNode = null;
		List<AlphaBetaNode> nextSteps = currNode.getNextSteps('ºÚ');
		for (AlphaBetaNode nextStep : nextSteps) {
			if (maxValue < nextStep.getValue()) {
				maxValue = nextStep.getValue();
				maxNextNode = nextStep;
			}
		}
		ChineseChessMainFrame.MyBoarder = maxNextNode.chessBoarder;
		ChineseChessMainFrame.InfBoard.AddLog(maxNextNode.chessBoarder.informations);
		System.out.println("AI Over.");
	}
	
	public AlphaBetaNode minMaxSearch(AlphaBetaNode start, int depth) {
		start.minMaxSearch(depth, true);
		return start;
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
