
public class GameBoard {
    private char[][] board;
    public GameBoard(){
        board =new  char[3][3];
        initializeBoard();
    }
//intialize board game  by setting to en empty space
    public void initializeBoard(){
        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                board[i][j]=' ';
            }
        }
    }
   //display current state of game board in terminal
    public  void displayBoard(){
        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                System.out.print(" "+board[i][j]);
                if (j<2) System.out.print(" |");
            }
            System.out.println();
            if (i<2)System.out.println("----+----+----");
        }
    }
    //move on the board at the specified postion 1 to 9
    public boolean makeMove(int postion , char marker){
        int row =(postion -1)/3;
        int col = ( postion-1)%3;
        if ( board[row][col]==' '){
            board[row][col]=marker;
            return  true;
        }
        return false;
    }
    //check if specified player and which mark  has won the game
    public  boolean checkIfWin(char marker){
        for (int i =0;i <3; i++){
            if (board[i][0]==marker && board[i][1]== marker && board[i][2] == marker)
                return true;
            if (board[0][i]==marker && board[1][i]== marker && board[2][i]== marker)
                return  true;
        }
        if (board[0][0]==marker && board[1][1]== marker && board[2][2]==marker)
            return  true;
        if (board[0][2]== marker && board[1][1]==marker && board[2][0]==marker)
            return  true;
        return false;
    }
    //check if board is full
    public  boolean isFull(){
        for (int i = 0; i <3;i++){
            for (int j =0;j<3; j++){
                if (board[i][j]==' ')
                    return false;
            }
        }
        return true;
    }
}
