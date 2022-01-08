
public abstract class King extends Piece {

    public King(String name, SIDE side) {
        super(name, PieceType.KING, side);
    }

    // @Override
    // public boolean preMove(Move m, Board b) { // Action associated with a move (after moving)
    //     return true;
    // }
    // @Override
    // public boolean postMove(Move m, Board b) { // Action associated with a move (after moving)
    //     return true;
    // }
}
