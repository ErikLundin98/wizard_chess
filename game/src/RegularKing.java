import java.util.ArrayList;
import java.util.List;

public class RegularKing extends King{

    public RegularKing(Piece.SIDE side) {
        super("RegK", side);
    }
    
    @Override
    public List<Move> getValidMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(x, y, x+1, y));
        moves.add(new Move(x, y, x+1, y+1));
        moves.add(new Move(x, y, x, y+1));
        moves.add(new Move(x, y, x-1, y+1));
        moves.add(new Move(x, y, x-1, y));
        moves.add(new Move(x, y, x-1, y-1));
        moves.add(new Move(x, y, x, y-1));
        moves.add(new Move(x, y, x+1, y-1));
        return moves;
    }
    
}
