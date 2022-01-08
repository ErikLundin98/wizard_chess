import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(SIDE side) {
        super("Pawn ", side);
    }

    @Override
    public List<Move> getValidMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(this.side == Piece.SIDE.WHITE ? new Move(x, y, x, y+1) : new Move(x, y, x, y-1));
        if(firstMove) moves.add(this.side == Piece.SIDE.WHITE ? new Move(x, y, x, y+2) : new Move(x, y, x, y-2));
        return moves;
    }
    @Override
    public boolean move(Move m, Board b) { // Action associated with a move (after moving)
        this.firstMove = false;
        return false;
    }
}
