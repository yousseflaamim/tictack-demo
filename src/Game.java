import java.util.Scanner;

public class Game {
    private  GameBoard board;
    private Player player1;
    private Player player2;
    private Player playerCurrent;
//constructor initialize the game  board and players
    public  Game (){
        board=new GameBoard();
        initializePlayers();
        playerCurrent = player1;


    }
// initializes players 1 and 2
    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name for player1  (X) : ");
        String namePlayer1 = scanner.nextLine();
        player1 = new Player(namePlayer1, 'x');

        System.out.println("Enter name for player2  (0) :  ");
        String namePlayer2 = scanner.nextLine();
        player2 =new Player(namePlayer2,'0');
    }
    private  void switchPlayer(){
        playerCurrent = (playerCurrent == player1)? player2 : player1;
    }
// switch b2 player1 /player2
    public void  playGame(){
        boolean  wonGame = false;
        board.displayBoard();
        while (! wonGame && ! board.isFull()) {
            System.out.println(playerCurrent.getName() + " turn (" + playerCurrent.getMarker() + ")");
            int move = getPlayerMove();
            if (board.makeMove(move, playerCurrent.getMarker())) {
                board.displayBoard();
                if (board.checkIfWin(playerCurrent.getMarker())) {
                    System.out.println(playerCurrent.getName() + " win!");
                    wonGame = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid moving , try again .");
            }

        }
        if (!wonGame){
            System.out.print("Its a draw !");
        }
        restGmae();
    }
//rest game and star new game
    private void restGmae() {
        board.initializeBoard();
        playGame();
    }
//get the players move 1 to 9 and validation
    private int getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int move;
        do {
            System.out.println("choose a position (1->9)");
            move=scanner.nextInt();
        }while (move < 1 || move>9);
        return move;
    }

}
