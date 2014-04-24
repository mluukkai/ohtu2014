package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;
    private final int scoreLimit;
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.scoreLimit = 4;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            m_score1 ++;
        }
        else {
            m_score2 ++;
        }
    }
    
    public String getScore() {
        if(this.m_score1 == this.m_score2) {
            return even();
        }
        else if(this.m_score1 >= scoreLimit || this.m_score2 >= scoreLimit) {
            return this.endGame();
        }
        else {
            return this.getCorrespondingCall(m_score1) + "-" + this.getCorrespondingCall(m_score2);
        }
    }
    
    private String endGame() {
        int minusResult = m_score1-m_score2;
        if (minusResult==1) {
            return Score.ADVANTAGE.toString() + " " + this.player1Name;
        }
        else if (minusResult ==-1) {
            return Score.ADVANTAGE.toString() + " " + this.player2Name;
        }
        else if (minusResult>=2) {
            return Score.WIN_FOR + " " + this.player1Name;
        }
        else {
            return Score.WIN_FOR + " " + this.player2Name;
        }
    }
    
    private String even() {
        if(this.m_score1 >= scoreLimit && this.m_score2 >= scoreLimit) {
            return Score.DEUCE.toString();
        }
        return this.getCorrespondingCall(m_score1) + Score.SEPARATOR.toString() + Score.ALL.toString();
    }
    
    private String getCorrespondingCall(int points) {
        switch(points) {
            case 0: return Score.LOVE.toString();
            case 1: return Score.FIFTEEN.toString();
            case 2: return Score.THIRTY.toString();
            case 3: return Score.FORTY.toString();
        }
        return "";
    }
}