import java.util.Random;
import java.util.Scanner;

public class Game {
    private GameBoard board;
    private Player player1;
    private Player player2;
    private boolean isPlayingWithComputer;
    private Player playerCurrent;
    private Random random;

    //constructor initialize the game  board and players
    public Game() {
        board = new GameBoard();
      //  initializePlayers();
        random=new Random();
       // playerCurrent = player1;
        initializeGameMode();


    }

    private void initializeGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choose Game mode :>");
        System.out.println("1 : Play with anther player :");
        System.out.println("2 : Play with computer :");
        int choice;
        do {
            System.out.println("enter your choose -> 1 or -> 2 : ");
            choice=scanner.nextInt();
        }while (choice!=1 && choice!=2);
            if (choice == 1) {
                isPlayingWithComputer = false;
                initializePlayers();
            } else {
                isPlayingWithComputer = true;
                initializePlayersAndComputer();
            }


        playerCurrent=player1;
    }

    private void initializePlayersAndComputer() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your name (X) :");
        String playerName = scanner.nextLine();
        player1=new Player(playerName,'X');
        player2= new Player("Computer",'O');

    }

    // initializes players 1 and 2
    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name for player1  (X) : ");
        String namePlayer1 = scanner.nextLine();
        player1 = new Player(namePlayer1, 'x');

        System.out.println("Enter name for player2  (0) :  ");
        String namePlayer2 = scanner.nextLine();
        player2 = new Player(namePlayer2, '0');
    }

    private void switchPlayer() {
        playerCurrent = (playerCurrent == player1) ? player2 : player1;
    }

    // switch b2 player1 /player2
    public void playGame() {
        boolean wonGame = false;
        board.displayBoard();

        while (!wonGame && !board.isFull()) {
            if (!isPlayingWithComputer || playerCurrent == player1) {
                System.out.println(playerCurrent.getName() + " turn (" + playerCurrent.getMarker() + ")");
                int move = getPlayerMove();
                if (board.makeMove(move, playerCurrent.getMarker())) {
                    board.displayBoard();
                    if (board.checkIfWin(playerCurrent.getMarker())) {
                        System.out.println(playerCurrent.getName() + " wins!");
                        wonGame = true;
                    } else {
                        switchPlayer();
                    }
                }
            } else if (isPlayingWithComputer && playerCurrent == player2) {
                // play computer
                System.out.println("Computer's turn (" + player2.getMarker() + ")");
                int move = getComputerMove();
                board.displayBoard();
                if (board.checkIfWin(playerCurrent.getMarker())) {
                    System.out.println("Computer wins! :(");
                    wonGame = true;
                } else {
                    switchPlayer();
                }
            }
        }

        if (!wonGame) {
            System.out.println("It's a draw!");
        }

        resetGame();
    }


    private int getComputerMove() {
        int move;
        do {
            move=random.nextInt(9)+1;
        }while (!board.makeMove(move,player2.getMarker()));
        return move;
    }

    //rest game and star new game
    private void  resetGame() {
        board.initializeBoard();
        playGame();
    }

    //get the players move 1 to 9 and validation
    private int getPlayerMove() {
       Scanner scanner=new Scanner(System.in);
       int move= -1;
       while (move<1 || move>9){
           System.out.println("Chooes a position from (1 to 9)");
           try {
               move=Integer.parseInt(scanner.nextLine());
               if (move<1 || move>9){
                   System.out.println("Invalid choice, pleas enter a number between 1 and 9");
               }

           }catch (NumberFormatException e){
               System.out.println("invalid input  pleas enter a number between 1 and 9 ");
           }
       }
      return move;
    }
}
