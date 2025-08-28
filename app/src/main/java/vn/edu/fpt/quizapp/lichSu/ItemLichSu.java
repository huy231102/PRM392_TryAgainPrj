package vn.edu.fpt.quizapp.lichSu;

public class ItemLichSu {
    int id;
    String deThi;
    String cauDung;
    String cauSai;
    String tongCau;
    long timestamp;

    public ItemLichSu(int id, String deThi, String cauDung, String cauSai, String tongCau, long timestamp) {
        this.id = id;
        this.deThi = deThi;
        this.cauDung = cauDung;
        this.cauSai = cauSai;
        this.tongCau = tongCau;
        this.timestamp = timestamp;
    }

    public String getFormattedTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date(timestamp * 1000));
    }
}