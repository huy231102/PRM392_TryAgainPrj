package vn.edu.fpt.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.lichSu.Result;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class De_So_1 extends AppCompatActivity {
    TextView stt,noiDung, txtTimer;
    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;
    Button click, click2;
    int i = 1, n = 0;
    public static int flag = 0, correct = 0, wrong = 0;
    int count = 100000;

    ArrayList<Result> results = new ArrayList<>();
    static String[] question ={};
    static String[] answer ={};
    static String[] option ={};

    private Timer timer;

    //cau hoi, lua chon , dap an dung deu luu trong mang
    static final String question1[] = {
            "Lớp truy cập mạng trong mô hình giao thức TCP/IP tương " +
                    "ứng với lớp/cụm các lớp nào trong mô hình OSI?",
            "Chức năng của lớp mạng trong mô hình TCP/IP là?",
            "Kỹ thuật SCMA/CD thì mỗi nút mạng sẽ thử truy cập ngẫu nhiên và đợi trong " +
                    "khoảng thời gian là bao lâu?",
            "Kỹ thuật chuyển thẻ bài được sử dụng trong cấu trúc mạng nào?",
            "Định dạng đơn vị thông tin tại lớp truy nhập mạng là?",
            "Định dạng đơn vị thông tin tại lớp liên mạng là?",
            "Định dạng đơn vị thông tin tại lớp ứng dụng là?",
            "Giao thức IP hoạt động tại lớp nào trong mô hình TCP/IP?",
            "Chức năng của giao thức IP là?",
            "Chức năng của giao thức thức bản tin điều khiển (ICMP- lệnh ping) là?",
    };

    static final String question2[] = {
            "Lớp truy cập mạng trong mô hình giao thức TCP/IP tương " +
                    "ứng với lớp/cụm các lớp nào trong mô hình OSI?",
            "Chức năng của lớp mạng trong mô hình TCP/IP là?",
            "Kỹ thuật SCMA/CD thì mỗi nút mạng sẽ thử truy cập ngẫu nhiên và đợi trong " +
                    "khoảng thời gian là bao lâu?",
            "Kỹ thuật chuyển thẻ bài được sử dụng trong cấu trúc mạng nào?",
            "Định dạng đơn vị thông tin tại lớp truy nhập mạng là?",
            "Định dạng đơn vị thông tin tại lớp liên mạng là?",
            "Định dạng đơn vị thông tin tại lớp ứng dụng là?",
            "Giao thức IP hoạt động tại lớp nào trong mô hình TCP/IP?",
            "Chức năng của giao thức IP là?",
            "Chức năng của giao thức thức bản tin điều khiển (ICMP- lệnh ping) là?",
    };
    static final String question3[] = {
            "Lớp truy cập mạng trong mô hình giao thức TCP/IP tương " +
                    "ứng với lớp/cụm các lớp nào trong mô hình OSI?",
            "Chức năng của lớp mạng trong mô hình TCP/IP là?",
            "Kỹ thuật SCMA/CD thì mỗi nút mạng sẽ thử truy cập ngẫu nhiên và đợi trong " +
                    "khoảng thời gian là bao lâu?",
            "Kỹ thuật chuyển thẻ bài được sử dụng trong cấu trúc mạng nào?",
            "Định dạng đơn vị thông tin tại lớp truy nhập mạng là?",
            "Định dạng đơn vị thông tin tại lớp liên mạng là?",
            "Định dạng đơn vị thông tin tại lớp ứng dụng là?",
            "Giao thức IP hoạt động tại lớp nào trong mô hình TCP/IP?",
            "Chức năng của giao thức IP là?",
            "Chức năng của giao thức thức bản tin điều khiển (ICMP- lệnh ping) là?",
    };
    static final String answer1[] = {
            "B.\tLớp vật lý, lớp liên kết dữ liệu",
            "C.\tĐịnh tuyến",
            "B.\tBằng số ngẫu nhiên với khe thời gian",
            "A.\tCấu trúc Ring",
            "D.\tKhung dữ liệu",
            "A.\tGói dữ liệu",
            "A.\tBản tin",
            "B.\tLớp liên mạng",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet",
            "C.\tKiểm tra các host ở xa có hoạt động hay không"
    };

    static final String answer2[] = {
            "B.\tLớp vật lý, lớp liên kết dữ liệu",
            "C.\tĐịnh tuyến",
            "B.\tBằng số ngẫu nhiên với khe thời gian",
            "A.\tCấu trúc Ring",
            "D.\tKhung dữ liệu",
            "A.\tGói dữ liệu",
            "A.\tBản tin",
            "B.\tLớp liên mạng",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet",
            "C.\tKiểm tra các host ở xa có hoạt động hay không"
    };

    static final String answer3[] = {
            "B.\tLớp vật lý, lớp liên kết dữ liệu",
            "C.\tĐịnh tuyến",
            "B.\tBằng số ngẫu nhiên với khe thời gian",
            "A.\tCấu trúc Ring",
            "D.\tKhung dữ liệu",
            "A.\tGói dữ liệu",
            "A.\tBản tin",
            "B.\tLớp liên mạng",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet",
            "C.\tKiểm tra các host ở xa có hoạt động hay không"
    };

    static final String option1[] = {
            "A.\tLớp vật lý","B.\tLớp vật lý, lớp liên kết dữ liệu","C.\tLớp mạng","D.\tLớp vật lý, lớp lien kết dữ liệu, lớp mạng",
            "A.\tĐóng gói dữ liệu IP vào khung","B.\tĐiểu khiển luồng","C.\tĐịnh tuyến","D.\tÁnh xạ địa chỉ IP sang địa chỉ vật lý",
            "A.\t102.2µs","B.\tBằng số ngẫu nhiên với khe thời gian","C.\t51.2 µs","D.\t52.1 µs",
            "A.\tCấu trúc Ring","B.\tCấu trúc Bus","C.\tCấu trúc Mesh","D.\tCấu trúc Star",
            "A.\tĐoạn dữ liệu","B.\tGói dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tGói dữ liệu","B.\tĐoạn dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tBản tin","B.\tKhung dữ liệu","C.\tĐoạn dữ liệu","D.\tGói dữ liệu",
            "A.\tLớp truy nhập mạng","B.\tLớp liên mạng","C.\tLớp phiên","D.\tLớp truyền tải",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet","B.\tPhân đoạn và tái tạo dữ liệu","C.\tĐịnh hướng đường cho các đơn vị dữ liệu đến các host ở xa","D.\tPhân đoạn",
            "A.\tĐịnh tuyến lại","B.\tĐiều khiển luồng, phát hiện sự không đến đích","C.\tKiểm tra các host ở xa có hoạt động hay không","D.\tĐiều khiển luồng"

    };


    static final String option2[] = {
            "A.\tĐáp án a option2","B.\tLớp vật lý, lớp liên kết dữ liệu","C.\tLớp mạng","D.\tLớp vật lý, lớp lien kết dữ liệu, lớp mạng",
            "A.\tĐóng gói dữ liệu IP vào khung","B.\tĐiểu khiển luồng","C.\tĐịnh tuyến","D.\tÁnh xạ địa chỉ IP sang địa chỉ vật lý",
            "A.\t102.2µs","B.\tBằng số ngẫu nhiên với khe thời gian","C.\t51.2 µs","D.\t52.1 µs",
            "A.\tCấu trúc Ring","B.\tCấu trúc Bus","C.\tCấu trúc Mesh","D.\tCấu trúc Star",
            "A.\tĐoạn dữ liệu","B.\tGói dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tGói dữ liệu","B.\tĐoạn dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tBản tin","B.\tKhung dữ liệu","C.\tĐoạn dữ liệu","D.\tGói dữ liệu",
            "A.\tLớp truy nhập mạng","B.\tLớp liên mạng","C.\tLớp phiên","D.\tLớp truyền tải",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet","B.\tPhân đoạn và tái tạo dữ liệu","C.\tĐịnh hướng đường cho các đơn vị dữ liệu đến các host ở xa","D.\tPhân đoạn",
            "A.\tĐịnh tuyến lại","B.\tĐiều khiển luồng, phát hiện sự không đến đích","C.\tKiểm tra các host ở xa có hoạt động hay không","D.\tĐiều khiển luồng"

    };
    static final String option3[] = {
            "A.\t Dap an a option3","B.\tLớp vật lý, lớp liên kết dữ liệu","C.\tLớp mạng","D.\tLớp vật lý, lớp lien kết dữ liệu, lớp mạng",
            "A.\tĐóng gói dữ liệu IP vào khung","B.\tĐiểu khiển luồng","C.\tĐịnh tuyến","D.\tÁnh xạ địa chỉ IP sang địa chỉ vật lý",
            "A.\t102.2µs","B.\tBằng số ngẫu nhiên với khe thời gian","C.\t51.2 µs","D.\t52.1 µs",
            "A.\tCấu trúc Ring","B.\tCấu trúc Bus","C.\tCấu trúc Mesh","D.\tCấu trúc Star",
            "A.\tĐoạn dữ liệu","B.\tGói dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tGói dữ liệu","B.\tĐoạn dữ liệu","C.\tBản tin","D.\tKhung dữ liệu",
            "A.\tBản tin","B.\tKhung dữ liệu","C.\tĐoạn dữ liệu","D.\tGói dữ liệu",
            "A.\tLớp truy nhập mạng","B.\tLớp liên mạng","C.\tLớp phiên","D.\tLớp truyền tải",
            "A.\tĐịnh nghĩa cơ chế định địa chỉ trong mạng Internet","B.\tPhân đoạn và tái tạo dữ liệu","C.\tĐịnh hướng đường cho các đơn vị dữ liệu đến các host ở xa","D.\tPhân đoạn",
            "A.\tĐịnh tuyến lại","B.\tĐiều khiển luồng, phát hiện sự không đến đích","C.\tKiểm tra các host ở xa có hoạt động hay không","D.\tĐiều khiển luồng"

    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thi);
        stt = (TextView) findViewById(R.id.stt);
        noiDung = (TextView) findViewById(R.id.noi_dung);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        r1 = (RadioButton) findViewById(R.id.rd1);
        r2 = (RadioButton) findViewById(R.id.rd2);
        r3 = (RadioButton) findViewById(R.id.rd3);
        r4 = (RadioButton) findViewById(R.id.rd4);
        click = (Button) findViewById(R.id.click);
        click2 = (Button) findViewById(R.id.click2);
        click2.setEnabled(false);
        txtTimer = (TextView) findViewById(R.id.text_view);
        //lay ra de thi duoc gui tu trang danhsachde thi sang
        int dethi=getIntent().getIntExtra("dethi",0);
        if(dethi==0)
        {//de khong thi bo cau hoi 1
            question=question1;
            option=option1;
            answer=answer1;
        }
        else if(dethi==1){
            //de 1 thi cau hoi 2
            question=question2;
            option=option2;
            answer=answer2;
        }
        else{
            question=question3;
            option=option3;
            answer=answer3;
        }
        for(int j =0;j<10;j++){

            results.add(new Result(j,"",false));
        }
        //radioGroup.setOnCheckedChangeListener();
        //đặt thông tin câu hỏi
        stt.setText("Câu Hỏi Thứ " + i);
        noiDung.setText(question[flag]);
        r1.setText(option[n]);
        r2.setText(option[n+1]);
        r3.setText(option[n+2]);
        r4.setText(option[n+3]);
        //đặt bộ đêsm thời gian 1000milisecond 1p40s
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setDefaultData();

                if(i>=9) {
                    click.setEnabled(false);
                }
                    click2.setEnabled(true);
                    n+=4;
                    flag+=1;
                    stt.setText("Câu Hỏi Thứ " + (i+=1));
                    noiDung.setText(question[flag]);
                    r1.setText(option[n]);
                    r2.setText(option[n+1]);
                    r3.setText(option[n+2]);
                    r4.setText(option[n+3]);
                    for(int j=0;j<results.size();j++) {
                        if(results.get(j).getQuestionNum()==flag){
                            if(results.get(j).getAnswer() == r1.getText().toString()){
                                r1.setChecked(true);
                            }else if(results.get(j).getAnswer() == r2.getText().toString()){
                                r2.setChecked(true);
                            }else if(results.get(j).getAnswer() == r3.getText().toString()){
                                r3.setChecked(true);
                            }else if(results.get(j).getAnswer() == r4.getText().toString()){
                                r4.setChecked(true);
                            }
                        }
                    }
                }





        });

        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setDefaultData();
                RadioButton uans = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                if(i<=2) {
                    click2.setEnabled(false);
                }
                    click.setEnabled(true);
                    n-=4;
                    flag-=1;
                    stt.setText("Câu Hỏi Thứ " + (i-=1));
                    noiDung.setText(question[flag]);
                    r1.setText(option[n]);
                    r2.setText(option[n+1]);
                    r3.setText(option[n+2]);
                    r4.setText(option[n+3]);
                    for(int j=0;j<results.size();j++) {
                        if(results.get(j).getQuestionNum()==flag){
                            if(results.get(j).getAnswer() == r1.getText().toString()){
                                r1.setChecked(true);
                            }else if(results.get(j).getAnswer() == r2.getText().toString()){
                                r2.setChecked(true);
                            }else if(results.get(j).getAnswer() == r3.getText().toString()){
                                r3.setChecked(true);
                            }else if(results.get(j).getAnswer() == r4.getText().toString()){
                                r4.setChecked(true);
                            }
                        }
                    }
                }



        });
    }



    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here

            if (count <= 0) {
                timer.cancel();
                RadioButton uans = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
                if(answer[flag].equals(ansText)){
                    correct++;
                }
                else
                    wrong++;
                if(radioGroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(De_So_1.this, "Please select one choice !", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),Ket_Qua_Sau_Thi.class);
                Bundle bundle = new Bundle();
                String a = bundle.getString("dethi");
                bundle.putInt("correct", correct);
                bundle.putInt("wrong", wrong);
                bundle.putInt("marks",i);
                bundle.putString("de",a);
                intent.putExtras(bundle);
                startActivity(intent);
                i = 1;
                n = 0;
                flag = 0;
                correct = 0;
                wrong = 0;
            }

            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(count),
                    TimeUnit.MILLISECONDS.toMinutes(count) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(count)),
                    TimeUnit.MILLISECONDS.toSeconds(count) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(count)));
            txtTimer.setText(hms);
            count -= 1000;
        }
    };
    private void nextPage() {
        //câu tiếp theo
        RadioButton uans = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String ansText = uans.getText().toString();
        DBHelper dbHelper = new DBHelper(De_So_1.this);
        setDefaultData();
        if(flag==0){
            click2.setEnabled(true);
        }
        if(i<=10){
            if(i<10){
                for(int j=0;j<results.size();j++){
                    if(results.get(j).getQuestionNum()==flag){
                        results.get(j).setAnswer(ansText);
                        if(answer[flag].equals(ansText)){
                            results.get(j).setCheck(true);
                        }
                        else{
                            results.get(j).setCheck(false);
                        }
                    }
                }
                n+=4;
                flag+=1;
                stt.setText("Câu Hỏi Thứ " + (i+=1));
                noiDung.setText(question[flag]);
                r1.setText(option[n]);
                r2.setText(option[n+1]);
                r3.setText(option[n+2]);
                r4.setText(option[n+3]);
                for(int j=0;j<results.size();j++) {
                    if(results.get(j).getQuestionNum()==flag){
                        if(results.get(j).getAnswer() == r1.getText().toString()){
                            r1.setChecked(true);
                        }else if(results.get(j).getAnswer() == r2.getText().toString()){
                            r2.setChecked(true);
                        }else if(results.get(j).getAnswer() == r3.getText().toString()){
                            r3.setChecked(true);
                        }else if(results.get(j).getAnswer() == r4.getText().toString()){
                            r4.setChecked(true);
                        }
                    }
                }
                if(radioGroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(De_So_1.this, "Please select one choice !", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            else if(i==10){
                for(int j=0;j<results.size();j++){
                    if(results.get(j).getQuestionNum()==flag){
                        results.get(j).setAnswer(ansText);
                        if(answer[flag].equals(ansText)){
                            results.get(j).setCheck(true);
                        }
                        else{
                            results.get(j).setCheck(false);
                        }
                    }
                }
                Intent intent = new Intent(getApplicationContext(),Ket_Qua_Sau_Thi.class);
                Bundle bundle = new Bundle();
                String a = bundle.getString("dethi");
                for(int j=0;j<results.size();j++){
                    if(results.get(j).isCheck()==true){
                        correct+=1;
                    }else{
                        wrong+=1;
                    }
                    dbHelper.insertDataChecked(results.get(j).getAnswer(),results.get(j).getQuestionNum()+"");
                }
                bundle.putInt("correct", correct);
                bundle.putInt("wrong", wrong);
                bundle.putInt("marks",i);
                bundle.putString("de",a);
                intent.putExtras(bundle);
                startActivity(intent);
                i = 1;
                n = 0;
                flag = 0;
                correct = 0;
                wrong = 0;
            }
        }
    }

    private void setDefaultData() {
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r4.setChecked(false);
    }
}