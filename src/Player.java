

public class Player {
    static int count = 0;
    private String name;
    private int id;
    private int score;

    public Player(String name) {
        this.name = name;
        this.id = count;
        this.score = 0;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
