package vn.edu.fpt.quizapp.lichSu;

public class Result {
    private int questionNum;
    private String answer;

    private boolean check;

    public Result() {
    }

    public Result(int questionNum, String answer, boolean check) {
        this.questionNum = questionNum;
        this.answer = answer;
        this.check = check;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
