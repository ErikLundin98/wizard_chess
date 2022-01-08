import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        Board board = new Board();
        board.addPiece(new Pawn(Piece.SIDE.WHITE), 0, 0);
        board.addPiece(new Pawn(Piece.SIDE.BLACK), 0, 1);
        board.addPiece(new AggressiveKing(Piece.SIDE.WHITE), 4, 0);
        board.addPiece(new RegularKing(Piece.SIDE.BLACK), 4, 7);

        Piece.SIDE[] turns = {Piece.SIDE.WHITE, Piece.SIDE.BLACK};
        while(!done) {
            for(Piece.SIDE turn : turns) {
                board.print();
                boolean blackLoss = board.filter(PieceType.KING, Piece.SIDE.BLACK).size() == 0;
                boolean whiteLoss = board.filter(PieceType.KING, Piece.SIDE.WHITE).size() == 0;
                if(blackLoss || whiteLoss) {
                    System.out.println("GAME OVER! " + (blackLoss ? "White wins" : "Black wins"));
                    done=true;
                    break;
                }
                System.out.println((turn==Piece.SIDE.WHITE ? "White's" : "Black's") + " turn! Available moves:");
                for(Move m : board.getValidMoves(turn)) m.print();
                System.out.println("Make a move: ");
                while(true) {
                    String input = scanner.nextLine();
                    if(input.equals("stop")) {
                        done = true;
                        break;
                    }
                    Move toMake = new Move(input);
                    if(board.movePiece(toMake, turn)) {
                        System.out.println("Made move " + toMake.toString());
                        break;
                    }
                    System.out.println("Invalid move! Try again:");
                } 
                if(done) break; 
            }
        }
        scanner.close();
    }
}
