import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private String[][] piecesArray;
    
    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        piecesArray = new String[][] {
            {"blackBishop.png", "3"}, {"blackBishop.png", "6"}, {"blackKing.png", "5"},
            {"blackKnight.png", "2"}, {"blackKnight.png", "7"}, {"blackPawn.png", "9"},
            {"blackPawn.png", "10"}, {"blackPawn.png", "11"}, {"blackPawn.png", "12"},
            {"blackPawn.png", "13"}, {"blackPawn.png", "14"}, {"blackPawn.png", "15"},
            {"blackPawn.png", "16"}, {"blackQueen.png", "4"}, {"blackRook.png", "1"},
            {"blackRook.png", "8"}, {"whiteBishop.png", "59"}, {"whiteBishop.png", "62"},
            {"whiteKing.png", "60"}, {"whiteKnight.png", "58"}, {"whiteKnight.png", "63"},
            {"whitePawn.png", "49"}, {"whitePawn.png", "50"}, {"whitePawn.png", "51"},
            {"whitePawn.png", "52"}, {"whitePawn.png", "53"}, {"whitePawn.png", "54"},
            {"whitePawn.png", "55"}, {"whitePawn.png", "56"}, {"whiteQueen.png", "61"},
            {"whiteRook.png", "57"}, {"whiteRook.png", "64"}
        };

        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        //Arrays.sort(piecesArray, (a, b) -> a[0].compareTo(b[0]));
        initializeBoard();
        System.out.println(piecesArray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mergeSort(piecesArray, 0, piecesArray.length - 1);
                initializeBoard();
            }
        });
    }

    private void initializeBoard() {
        getContentPane().removeAll();
        setLayout(new GridLayout(SIZE, SIZE));
        int index = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                
                if (row<2 ||row>5) {
                    ImageIcon icon = new ImageIcon(piecesArray[index][0]);
                    Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[index][1], SwingConstants.CENTER);

                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                    index++;
                }
                
                add(squares[row][col]);
            }
        }
        revalidate();
        repaint();
    }

    private void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(String[][] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        String[][] leftArray = new String[leftSize][2];
        String[][] rightArray = new String[rightSize][2];
        
        for (int i = 0; i < leftSize; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < rightSize; j++)
            rightArray[j] = array[middle + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (Integer.parseInt(leftArray[i][1]) <= Integer.parseInt(rightArray[j][1])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}