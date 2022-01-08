


public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        board.addPiece(new Pawn(Piece.SIDE.WHITE), 0, 0);
        board.addPiece(new Pawn(Piece.SIDE.BLACK), 0, 1);
        board.print();

        Move m1 = new Move(0, 0, 0, 1);
        System.out.println(board.movePiece(m1)); // Just move piece
        board.print();

        for(Move m : board.getValidMoves()) {
            m.print();
        }

    }
}
