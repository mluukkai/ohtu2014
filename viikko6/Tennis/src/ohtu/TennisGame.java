package ohtu;

public class TennisGame {
    public final int LOVE=0;
    public final int FIFTEEN=1;    
    public final int THIRTY=2;    
    public final int FOURTY=3;    
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    private String tasan(){
        String tilanne="";
        switch (player1Score)
            {
                case LOVE:
                        tilanne = "Love-All";
                    break;
                case FIFTEEN:
                        tilanne = "Fifteen-All";
                    break;
                case THIRTY:
                        tilanne = "Thirty-All";
                    break;
                case FOURTY:
                        tilanne = "Forty-All";
                    break;
                default:
                        tilanne = "Deuce";
                    break;                
            }
        return tilanne;
    }
    
    private String lopetus(){
        String tilanne="";
            int tulosEro = player1Score-player2Score;
            if (tulosEro==1) tilanne ="Advantage player1";
            else if (tulosEro ==-1) tilanne ="Advantage player2";
            else if (tulosEro>=2) tilanne = "Win for player1";
            else tilanne ="Win for player2";
        return tilanne;    
    }
    
    private String pisteet(){
        String tulos = "";
        int tilanne = 0;
            for (int i=1; i<3; i++)
            {
                if (i==1) tilanne = player1Score;
                else { tulos+="-"; tilanne = player2Score;}
                switch(tilanne)
                {
                    case LOVE:
                        tulos+="Love";
                        break;
                    case FIFTEEN:
                        tulos+="Fifteen";
                        break;
                    case THIRTY:
                        tulos+="Thirty";
                        break;
                    case FOURTY:
                        tulos+="Forty";
                        break;
                }
            }
        return tulos;    
    }
    
    public String getScore() {
        String score = "";
        int tempScore=0;
        if (player1Score==player2Score)
        {
            score=tasan();
        }
        else if (player1Score>=4 || player2Score>=4)
        {
            score = lopetus();
        }
        else
        {
            score = pisteet();
        }
        return score;
    }
}