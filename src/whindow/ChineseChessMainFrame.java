package whindow;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whindow.LabelEvent.ChessPieceClick;
import chessBoard.ChessBoarder;
import defultSet.DefultSet;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChineseChessMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6381038747365645992L;
	
	private JPanel contentPane;
	private JPanel Pane1;
	private JPanel Pane2;
	private JPanel Pane3;
	private JPanel Pane4;
	
	static public InformationBoard InfBoard;
	
	/**
	 * ��Ϸģʽ
	 * 0��˫�˶Ծ�
	 * 1���˻��Ծ�
	 * 2��������ʾ
	 * 3���˳���Ϸ
	 */
	static public int MenuMode = 0;
	/**
	 * ִ�ӷ�
	 * ��:�췽
	 * ��:�ڷ�
	 * �ޣ��ޣ���������
	 */
	static public char DoPlayer = '��';
	//��������
	static public ChessBoarder MyBoarder;
	
	ChessBoarderCanvas MyCanvas;

	/**
	 * ���������ڣ����캯���������
	 * ��������
	 * ���أ�
	 * ���ߣ���ط��
	 * ʱ�䣺2014-11-12
	 */
	public ChineseChessMainFrame() {
		
		//���ݳ�ʼ��
		DataInit();
		
		//����ͼ��
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/black-jiang.png")));
		//���ñ���
		this.setTitle("�й�����");
		//���ô��ڴ�С
		this.setBounds(0, 0, 1366, 768);
		//���ô��ڲ��ɸı��С
		this.setResizable(false);
		//����Ĭ�Ϲر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ھ���
		this.setLocationRelativeTo(null);
		
		//����ContentPane����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//��ʹ�ò���
		contentPane.setLayout(null);
		//����ContentPaneΪ͸��
		contentPane.setOpaque(false);
		this.setContentPane(contentPane);
		
		//����ContentPane����Ϣ
		
		//��ӱ���ͼƬ
		JLabel BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/background.png"))));
		BackGround.setBounds(0, 0, 1366, 768);
		//��ӱ���ͼƬ�Ĺؼ����
		this.getLayeredPane().add(BackGround, new Integer(Integer.MIN_VALUE)); 
		
		//���������Ϣ
//		JLabel AuthorInf = new JLabel("");
//		AuthorInf.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/information.png"))));
//		//����������Ϣλ��
//		AuthorInf.setBounds(30, 500,
//				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/information.png")).getIconWidth(), 
//				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/information.png")).getIconHeight());
//		contentPane.add(AuthorInf);
		
		//���4����ť
		JLabel Menu1 = new JLabel("");
		Menu1.setOpaque(false);
		JLabel Menu2 = new JLabel("");
		Menu2.setOpaque(false);
		JLabel Menu3 = new JLabel("");
		Menu3.setOpaque(false);
		JLabel Menu4 = new JLabel("");
		Menu4.setOpaque(false);
		
		//˫�˶Ծ���ť��������
		//���ó�ʼͼƬ
		Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
		//�������¼�
		Menu1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 0;
				Pane1.setVisible(true);
				Pane2.setVisible(false);
				Pane3.setVisible(false);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//ȫ����ʼ��ΪͼƬ0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//����MenuModeͼƬ
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		//���ð�ťλ�úʹ�С
		Menu1.setBounds(60, 60,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png")).getIconWidth(), 
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png")).getIconHeight());
		//��Ӱ�ť��ContentPane
		contentPane.add(Menu1);
	
		
		//�˻��Ծ���ť��������
		//���ó�ʼͼƬ
		Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
		//�������¼�
		Menu2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 1;
				Pane1.setVisible(false);
				Pane2.setVisible(true);
				Pane3.setVisible(false);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//ȫ����ʼ��ΪͼƬ0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//����MenuModeͼƬ
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		//���ð�ťλ�úʹ�С
		Menu2.setBounds(60, 140,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png")).getIconWidth(),
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png")).getIconHeight());
		//��Ӱ�ť��ContentPane
		contentPane.add(Menu2);
		
		//������ʾ��ť��������
		//���ó�ʼͼƬ
		Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
		//�������¼�
		Menu3.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 2;
				Pane1.setVisible(false);
				Pane2.setVisible(false);
				Pane3.setVisible(true);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//ȫ����ʼ��ΪͼƬ0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//����MenuModeͼƬ
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		
		//���ð�ťλ�úʹ�С
		Menu3.setBounds(60, 220,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png")).getIconWidth(), 
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png")).getIconHeight());
		//��Ӱ�ť��ContentPane
		contentPane.add(Menu3);
		
		//�˳���Ϸ��ť��������
		//���ó�ʼͼƬ
		Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
		//�������¼�
		Menu4.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 3;
				Pane1.setVisible(false);
				Pane2.setVisible(false);
				Pane3.setVisible(false);
				Pane4.setVisible(true);
				//�˳���Ϸ
				dispose();
				System.exit(0);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//ȫ����ʼ��ΪͼƬ0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//����MenuModeͼƬ
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		
		//���ð�ťλ�úʹ�С
		//Menu4.setBounds(60, 300,new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconWidth(), new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconHeight());
		Menu4.setBounds(60, 140,new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconWidth(), new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconHeight());
		//��Ӱ�ť��ContentPane
		contentPane.add(Menu4);
		
		//��ʱ�����˻�����ʾģʽ
		Menu2.setVisible(false);
		Menu3.setVisible(false);
		
		//��ʼ��4��JPanel
		Pane1 = new JPanel();
		Pane2 = new JPanel();
		Pane3 = new JPanel();
		Pane4 = new JPanel();
		
		//����4��JPanel��λ�ú͹�ͬ����
		Pane1.setBounds(0, 0, 1366, 768);
		Pane1.setOpaque(false);
		Pane1.setVisible(true);
		Pane1.setLayout(null);
		Pane2.setBounds(0, 0, 1366, 768);
		Pane2.setOpaque(false);
		Pane2.setVisible(false);
		Pane2.setLayout(null);
		Pane3.setBounds(0, 0, 1366, 768);
		Pane3.setOpaque(false);
		Pane3.setVisible(false);
		Pane3.setLayout(null);
		Pane4.setBounds(0, 0, 1366, 768);
		Pane4.setOpaque(false);
		Pane4.setVisible(false);
		Pane4.setLayout(null);
		
		//��4��Pane��ӽ�ContentPanel
		contentPane.add(Pane1);
		contentPane.add(Pane2);
		contentPane.add(Pane3);
		contentPane.add(Pane4);
		
		//��Pane1���Canvas����������
		MyCanvas = new ChessBoarderCanvas();
		//����Canvasλ�úʹ�С
		MyCanvas.setBounds(DefultSet.CanvasPosX, DefultSet.CanvasPosY, 661, 728);
		//ΪCanvas��������
		MyCanvas.SendData(this.MyBoarder, Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/background.png")), DefultSet.CanvasPosX, DefultSet.CanvasPosY, DefultSet.CanvasPosX+661, DefultSet.CanvasPosY+728);
		MyCanvas.repaint();
		MyCanvas.addMouseListener(new ChessPieceClick());
		Pane1.add(MyCanvas);
		
		//��Pane1�����Ϣ��
		InfBoard = new InformationBoard();
		InfBoard.setBounds(1011, 50, 394, 481);
		Pane1.add(InfBoard);
		
		InfBoard.AddLog("�췽ִ��");
		
		//������¿�ʼ��ť
		DiyButton AllReset = new DiyButton("/imageLibary/ButtonAllReset_0.png","/imageLibary/ButtonAllReset_1.png");
		AllReset.setBounds(1011, 600, 326, 115);
		AllReset.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				DataInit();
				InfBoard.Clear();
				InfBoard.AddLog("�췽ִ��");
				MyCanvas.SendWinner('��');
				MyCanvas.paintImmediately(0, 0, MyCanvas.getWidth(), MyCanvas.getHeight());
			}
		});
		Pane1.add(AllReset);
		
		//���ʱ���ǩ
		JLabel TimerLabel = new JLabel();
		TimerLabel.setBounds(1030, 570, 100, 50);
		TimerLabel.setFont(new Font("�����п�",Font.CENTER_BASELINE,28));
		Pane1.add(TimerLabel);
		TimerThread MyTimerThread = new TimerThread(TimerLabel);
		MyTimerThread.start();
	}
	
	/**�������ݳ�ʼ��
	 * @author ��ط��
	 * ʱ�䣺20141113
	 */
	public void DataInit(){
		MenuMode = 0;
		DoPlayer = '��';
		MyBoarder = new ChessBoarder();
	}

}
