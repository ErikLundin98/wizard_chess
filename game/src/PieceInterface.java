import java.util.List;

public interface PieceInterface {

    public List<Move> getValidMoves(Board b, int x, int y);
    public boolean move(Move m, Board b);
    public Piece.SIDE getSide();
    public String getSideString();
}
