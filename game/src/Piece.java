import java.util.List;

public abstract class Piece implements PieceInterface {
    enum SIDE {
        WHITE,
        BLACK,
        NONE,
        BOTH
    }
    protected SIDE side = SIDE.NONE; 
    protected String name;
    protected PieceType type;
    public Piece(String name, PieceType type, SIDE side) {
        this.name = name;
        this.type = type;
        this.side = side;
    }
    @Override
    public SIDE getSide() {return this.side;}
    @Override
    public String getSideString() {
        if(side == SIDE.WHITE) return "W";
        else if(side == SIDE.BLACK) return "B";
        else return " ";
    }
    @Override
    public List<Move> getValidMoves(Board b, int x, int y) {
        return null;
    }
    @Override
    public boolean preMove(Move m, Board b) {
        return false;
    }
    @Override
    public boolean postMove(Move m, Board b) {
        return false;
    }

}
