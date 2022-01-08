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
        String repr = " |   0  |"+"   1  |"+"   2  |"+"   3  |"+"   4  |"+"   5  |"+"   6  |"+"   7  |\n";
        for(int y = 0 ; y < BOARD_HEIGHT ; y++) {
            repr += y+"|";
            for(int x = 0 ; x < BOARD_WIDTH ; x++) {
                String side;
                if(pieces[x][y] != null ) {
                    side = pieces[x][y].getSideString();
                    repr += pieces[x][y].name;
                }
                else {
                    repr += "    ";
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
    public boolean movePiece(Move m, Piece.SIDE side) {
        List<Move> validMoves = getValidMovesAtPosition(side, m.x1, m.y1);
        if(validMoves==null || !validMoves.contains(m)) return false;
        if(moveIsInsideBoard(m)) {
            this.pieces[m.x1][m.x2].preMove(m, this);
            this.pieces[m.x2][m.y2] = this.pieces[m.x1][m.y1];
            this.pieces[m.x1][m.y1] = null;
            this.pieces[m.x2][m.y2].postMove(m, this);
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
    public List<Move> getValidMoves(Piece.SIDE turn) {
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int x = 0 ; x < BOARD_WIDTH ; x++) {
            for(int y = 0 ; y < BOARD_HEIGHT ; y++) {
                if(pieces[x][y] != null && pieces[x][y].getSide() == turn) {
                    moves.addAll(pieces[x][y].getValidMoves(this, x, y));
                }
            }
        }
        return filterMoves(moves);
    }
    // Get a List of all valid moves at a certain position
    public List<Move> getValidMovesAtPosition(Piece.SIDE turn, int x, int y) {
        if(pieces[x][y] != null && pieces[x][y].getSide() == turn) {
            return filterMoves(pieces[x][y].getValidMoves(this, x, y));
        }
        return null;
    }
    // Get a List of pieces matchin the criteria
    public List<Piece> filter(PieceType type, Piece.SIDE side) {
        List<Piece> found = new ArrayList<Piece>();
        for(int x = 0 ; x < BOARD_WIDTH ; x++) {
            for(int y = 0 ; y < BOARD_HEIGHT ; y++) {
                if(pieces[x][y] != null) {
                    if((side == Piece.SIDE.BOTH) || side==pieces[x][y].getSide()) {
                        if(pieces[x][y].type == type) found.add(pieces[x][y]);
                    }
                }
            }
        }
        return found;
    }

    public boolean containsPiece(int x, int y) {return this.pieces[x][y] != null;}
}
