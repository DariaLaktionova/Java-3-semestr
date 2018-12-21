package peresdacha;

public class Question {
    private int x;
    private int y;
    private String questionString;

    Question(int x, int y, String questionString) {
        this.x = x;
        this.y = y;
        this.questionString = questionString;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getQuestionString() {
        return questionString;
    }
}
