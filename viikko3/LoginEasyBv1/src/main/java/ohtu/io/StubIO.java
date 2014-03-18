package ohtu.io;

import java.util.ArrayList;

public class StubIO implements IO {

    private String[] lines;
    private int i;
    private ArrayList<String> prints;

    public StubIO(String... values) {
        this.lines = values;
        prints = new ArrayList<String>();
    }

    public void print(String toPrint) {
        prints.add(toPrint);
    }

    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(lines[i++]);
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    public String readLine(String prompt) {
        print(prompt);
        if (i < lines.length) {
            return lines[i++];
        }
        return "";
    }
}
