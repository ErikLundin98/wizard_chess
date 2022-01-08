import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    static final int BOARD_WIDTH = 8;
    static final int BOARD_HEIGHT = 8;

    private Piece[][] pieces;
    
    public Board() {
        this.pieces = new Piece[BOARD_WIDTH][BOARD_HEIGHT];
    }

    public String toString() {
        String repr = " |   1   |"+"   2   |"+"   3   |"+"   4   |"+"   5   |"+"   6   |"+"   7   |"+"   8   |\n";
        for(int y = 0 ; y < BOARD_HEIGHT ; y++) {
            repr += y+"|";
            for(int x = 0 ; x < BOARD_WIDTH ; x++) {
                String side;
                if(pieces[x][y] != null ) {
                    side = pieces[x][y].getSideString();
                    repr += pieces[x][y].name;
                }
                else {
                    repr += "empty";
                    side = " ";
                }
                repr += ' ' + side + '|';
            }
            repr += '\n';
        }
        return repr;
    }
    public void print() {
        System.out.println(this.toString());
    }
    // Add a piece to the board
    public void addPiece(Piece p, int x, int y) {
        this.pieces[x][y] = p;
    }
    // Make a move on the board. This method should always be called first
    // Call piece individual move methods from this
    public boolean movePiece(Move m) {
        List<Move> validMoves = getValidMovesAtPosition(m.x1, m.y1);
        if(!validMoves.contains(m)) return false;
        if(moveIsInsideBoard(m)) {
            this.pieces[m.x2][m.y2] = this.pieces[m.x1][m.y1];
            this.pieces[m.x1][m.y1] = null;
            this.pieces[m.x2][m.y2].move(m, this);
            return true;
        }
        return false;
    }
    //Checks if move attempts to go or moves from out of bounds
    public boolean moveIsInsideBoard(Move m) {
        return (0 <= m.x1 && m.x1 < BOARD_WIDTH && 0 <= m.x2 && m.x2 < BOARD_WIDTH && 0 <= m.y1 && m.y1 < BOARD_HEIGHT && 0 <= m.y2 && m.y2 < BOARD_HEIGHT);
    }
    // Filters a list on moves based on moveIsInsideBoard
    public List<Move> filterMoves(List<Move> moves) { // Remove moves out of bounds
        return moves.stream().filter(m -> (moveIsInsideBoard(m))).collect(Collectors.toList());
    }
    // Get a List of all valid moves that can be made on the board
    public List<Move> getValidMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int x = 0 ; x < BOARD_WIDTH ; x++) {
            for(int y = 0 ; y < BOARD_HEIGHT ; y++) {
                if(pieces[x][y] != null) {
                    moves.addAll(pieces[x][y].getValidMoves(this, x, y));
                }
            }
        }
        return filterMoves(moves);
    }
    // Get a List of all valid moves at a certain position
    public List<Move> getValidMovesAtPosition(int x, int y) {
        if(pieces[x][y] != null) {
            return filterMoves(pieces[x][y].getValidMoves(this, x, y));
        }
        return null;
    }
}
