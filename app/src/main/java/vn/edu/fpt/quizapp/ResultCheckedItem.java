package vn.edu.fpt.quizapp;

public class ResultCheckedItem {
    private int questionNum;
    private String selectedAnswer;
    private String correctAnswer;

    public ResultCheckedItem(int questionNum, String selectedAnswer, String correctAnswer) {
        this.questionNum = questionNum;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
