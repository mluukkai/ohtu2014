package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            return scoreEven();
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            return scoreAdvantage();
        } else {
            return score(score);
        }
    }

    private String score(String score) {
        int tempScore;
        for (int i = 1; i < 3; i++) {
            score = scoreBuild(i, score);
        }
        return score;
    }

    private String scoreBuild(int i, String score) {
        int tempScore;
        if (i == 1) {
            tempScore = m_score1;
        } else {
            score += "-";
            tempScore = m_score2;
        }
        return tempScore(tempScore, score);
    }

    private String tempScore(int tempScore, String score) {
        switch (tempScore) {
            case 0: score += "Love"; break;
            case 1: score += "Fifteen"; break;
            case 2: score += "Thirty"; break;
            case 3: score += "Forty"; break;
        }
        return score;
    }

    private String scoreAdvantage() {
        if (m_score1 - m_score2 == 1) {
            return "Advantage player1";
        } else if (m_score1 - m_score2 == -1) {
            return "Advantage player2";
        } else if (m_score1 - m_score2 >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String scoreEven() {
        String score;
        switch (m_score1) {
            case 0: score = "Love-All"; break;
            case 1: score = "Fifteen-All"; break;
            case 2: score = "Thirty-All"; break;
            case 3: score = "Forty-All"; break;
            default: score = "Deuce"; break;
        }
        return score;
    }
}
