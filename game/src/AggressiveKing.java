import java.util.ArrayList;
import java.util.List;

public class AggressiveKing extends King {

    boolean hitTarget = false;

    public AggressiveKing(Piece.SIDE side) {
        super("AGGK", side);
    }
    
    @Override
    public List<Move> getValidMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(x, y, x, y+7));
        moves.add(new Move(x, y, x, y-7));
        // if(hitTarget) {
        //     int mult = this.side == Piece.SIDE.WHITE ? -1 : 1;
        //     moves.add(new Move(x, y, x, y*mult));
        // }
        return moves;
    }
    // Action associated with a move (before moving)
    @Override
    public boolean preMove(Move m, Board b) { 
        this.hitTarget = b.containsPiece(m.x2, m.y2);
        return this.hitTarget;
    }
    // Action associated with a move (after moving)
    @Override
    public boolean postMove(Move m, Board b) { 
        // int mult = this.side == Piece.SIDE.WHITE ? -1 : 1;
        // boolean postMoved = b.movePiece(new Move(m.x2, m.y2, m.x2, m.y2-2*mult), this.side);
        // this.hitTarget = false;
        // return postMoved;
        return true;
    }
}
