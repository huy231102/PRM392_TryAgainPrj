package vn.edu.fpt.quizapp;

import java.util.List;

public class ResultGroup {
    private int lanthi;
    private List<String> resultCheckedList;

    public ResultGroup(int lanthi, List<String> resultCheckedList) {
        this.lanthi = lanthi;
        this.resultCheckedList = resultCheckedList;
    }

    public int getLanthi() {
        return lanthi;
    }

    public List<String> getResultCheckedList() {
        return resultCheckedList;
    }
}
