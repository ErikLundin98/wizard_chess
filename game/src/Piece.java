import java.util.List;

public abstract class Piece implements PieceInterface {
    enum SIDE {
        WHITE,
        BLACK,
        NONE
    }
    protected SIDE side = SIDE.NONE; 
    protected String name;
    public Piece(String name, SIDE side) {
        this.name = name;
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
    public boolean move(Move m, Board b) {
        return b.movePiece(m);
    }

}
