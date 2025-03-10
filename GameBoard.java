import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for board
    private String[][] piecesArray; //2D array = image::board position

    public GameBoard() {
        setTitle("Chess Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the board layout

        // Initialize the 2D array of panels
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE); // Color white for even squares
                } else {
                    squares[row][col].setBackground(Color.BLACK); // Color black for odd squares
                }
                add(squares[row][col]); // Add each square to the board
            }
        }

        // Initialize pieces array
        piecesArray = new String[32][3];  //your array should be at least 2 columns
        loadPieces();
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        // Initially populate the board with pieces
        populateBoard();
    }

    public JPanel[][] sortImages(JPanel[][] finalPositions) {
        Arrays.sort(piecesArray, new Comparator<String[]>() {
            @Override
            public int compare(String[] piece1, String[] piece2) {
                return Integer.compare(Integer.parseInt(piece1[2]), Integer.parseInt(piece2[2]));
            }
        });
        return finalPositions;
    }

    private void populateBoard() {
        int pieceRow = 0; //keeps track of number of images by row should be minimum of 32
        int squareName = 1; //all squares are numbered 1-64 top left is 1, bottom right is 64
    
        squares = sortImages(squares);
    
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pieceRow < piecesArray.length) {
                    int pokePosition = Integer.parseInt(piecesArray[pieceRow][2]);
    
                    System.out.println("squareName: " + squareName + ", pokePosition: " + pokePosition + ", pieceRow: " + pieceRow);
    
                    if (squareName == pokePosition) {
                        String imagePath = piecesArray[pieceRow][0];  //save the file name to a string variable
    
                        System.out.println("Loading image: " + imagePath + " at position: " + squareName);
    
                        ImageIcon icon = new ImageIcon(imagePath);
                        Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);
    
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
    
                        JPanel piecePanel = new JPanel(new BorderLayout());
                        piecePanel.setOpaque(false); // false sets img bg transparent, image must already be a transparent png
                        piecePanel.add(pieceLabel, BorderLayout.CENTER);
    
                        squares[row][col].setLayout(new BorderLayout());
                        squares[row][col].add(piecePanel, BorderLayout.CENTER);
    
                        pieceRow++;
                    }
                }
                squareName++;
            }
        }
    
        revalidate(); // Ensure layout updates
        repaint(); // Refresh view
    }

    private void loadPieces() {
        piecesArray[0][0] = "king.png";  piecesArray[0][2] = "8"; // Top right corner
        piecesArray[1][0] = "Knight.png";  piecesArray[1][2] = "10";
        piecesArray[2][0] = "queen.png";  piecesArray[2][2] = "55";
        piecesArray[3][0] = "blackQueen.png";  piecesArray[3][2] = "25";
        piecesArray[4][0] = "blackRook.png";  piecesArray[4][2] = "33";
        piecesArray[5][0] = "blackRook.png";  piecesArray[5][2] = "41";
        piecesArray[6][0] = "blackKinight.png";  piecesArray[6][2] = "2";
        piecesArray[7][0] = "whitePonds.png";  piecesArray[7][2] = "3";
        piecesArray[8][0] = "whitePonds.png";  piecesArray[8][2] = "11";
        piecesArray[9][0] = "whitePonds.png";  piecesArray[9][2] = "19";
        piecesArray[10][0] = "whitePonds.png";  piecesArray[10][2] = "27";
        piecesArray[11][0] = "whitePonds.png"; piecesArray[11][2] = "36";
        piecesArray[12][0] = "whitePonds.png"; piecesArray[12][2] = "49";
        piecesArray[13][0] = "whitePonds.png";  piecesArray[13][2] = "54";
        piecesArray[14][0] = "whitePonds.png";  piecesArray[14][2] = "62";
        piecesArray[15][0] = "blackPonds.png";  piecesArray[15][2] = "63";
        piecesArray[16][0] = "blackPonds.png";  piecesArray[16][2] = "4";
        piecesArray[17][0] = "blackPonds.png";  piecesArray[17][2] = "37";
        piecesArray[18][0] = "blackPonds.png";  piecesArray[18][2] = "28";
        piecesArray[19][0] = "blackPonds.png"; piecesArray[19][2] = "55";
        piecesArray[20][0] = "blackPonds.png";  piecesArray[20][2] = "12";
        piecesArray[21][0] = "blackPonds.png";  piecesArray[21][2] = "20";
        piecesArray[22][0] = "blackPonds.png";  piecesArray[22][2] = "50";
        piecesArray[23][0] = "blackPonds.png";  piecesArray[23][2] = "10";
        piecesArray[24][0] = "blackPonds.png";  piecesArray[24][2] = "8";
        piecesArray[25][0] = "blackPonds.png";  piecesArray[25][2] = "16";
        piecesArray[26][0] = "blackKing.png"; piecesArray[26][2] = "24";
        piecesArray[27][0] = "blackPonds.png";  piecesArray[27][2] = "32";
        piecesArray[28][0] = "blackPonds.png";  piecesArray[28][2] = "40";
        piecesArray[29][0] = "blackPonds.png";  piecesArray[29][2] = "48";
        piecesArray[30][0] = "blackPonds.png"; piecesArray[30][2] = "52";
        piecesArray[31][0] = "blackPonds.png";  piecesArray[31][2] = "51";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}