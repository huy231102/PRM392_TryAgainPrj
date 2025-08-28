package vn.edu.fpt.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.lichSu.Result;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.util.Log;

public class De_So_1 extends AppCompatActivity {
    public static final int TOTAL_QUESTIONS = 10;
    TextView stt,noiDung, txtTimer;
    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;
    Button click, click2, btnSubmit; // Thêm nút nộp bài
    int i = 1, n = 0;
    public static int flag = 0;
    public static int correct = 0;
    public static int wrong = 0;
    int count = 100000;
    // Chỉ số bộ đề được chọn
    private int deThiIndex;
    
    // Biến để kiểm tra xem đã nộp bài chưa
    private boolean isSubmitted = false;
    
    // Biến để theo dõi xem đáp án có được chọn bởi người dùng hay không
    private boolean isUserSelection = false;

    // Tham chiếu tới dialog xác nhận để tránh Window leak
    private AlertDialog submitDialog;

    ArrayList<Result> results = new ArrayList<>();
    static String[] question ={};
    static String[] answer ={};
    static String[] option ={};

    private Timer timer;

    // Danh sách tên các bộ đề
    private static final String[] SET_NAMES = {"Lập trình Mobile App", "Lập trình OOP", "Toán xác suất"};

    //cau hoi, lua chon , dap an dung deu luu trong mang
    static final String question1[] = {
        "Nền tảng nào sau đây được sử dụng để phát triển ứng dụng Android gốc?",
        "Thành phần nào quản lý giao diện người dùng và vòng đời màn hình trong Android?",
        "Layout nào thường dùng để sắp xếp các thành phần giao diện theo hàng ngang hoặc dọc?",
        "Trong iOS, ngôn ngữ nào được Apple khuyến nghị cho lập trình ứng dụng hiện đại?",
        "Component nào trong Android dùng để lưu trữ dữ liệu cục bộ đơn giản theo cặp khóa-giá trị?",
        "Thư viện nào thường được dùng để quản lý hình ảnh và tải hình ảnh bất đồng bộ trong Android?",
        "Kiến trúc MVVM gồm các thành phần nào?",
        "Gradle trong Android Studio dùng để làm gì?",
        "Phương thức nào của Activity được gọi khi màn hình được tạo lần đầu?",
        "Intent nào được sử dụng để chuyển đổi giữa các Activity?"
    };

    static final String question2[] = {
        "Nguyên tắc SOLID đầu tiên (Single Responsibility Principle) nêu gì?",
        "Trong OOP, tính đóng gói (encapsulation) mang lại lợi ích nào?",
        "Kế thừa (inheritance) cho phép làm gì?",
        "Đa hình (polymorphism) gồm những dạng nào?",
        "Interface khác gì với abstract class trong Java?",
        "Trong Java, từ khóa nào dùng để tạo dựng lớp con kế thừa?",
        "Tính trừu tượng (abstraction) giúp gì cho thiết kế?",
        "Visibility modifier nào cho phép truy cập trong cùng package?",
        "Phương thức nào có thể bị ghi đè (override)?",
        "Constructor có thể được kế thừa không?"
    };
    static final String question3[] = {
        "Xác suất của biến cố chắc chắn là bao nhiêu?",
        "Xác suất của biến cố rỗng (biến cố không thể xảy ra) là bao nhiêu?",
        "Xác suất P(A hoặc B) bằng công thức nào khi A và B độc lập?",
        "Xác suất P(A và B) bằng công thức nào khi A và B độc lập?",
        "Kỳ vọng E(X) của biến ngẫu nhiên rời rạc là gì?",
        "Phương sai Var(X) được tính bằng công thức nào?",
        "Đối với phân phối nhị thức B(n,p), kỳ vọng là gì?",
        "Đối với phân phối nhị thức B(n,p), phương sai là gì?",
        "Ý nghĩa của xác suất có điều kiện P(A|B) là gì?",
        "Định lý xác suất toàn phần cho P(B) là gì?"
    };
    static final String answer1[] = {
        "A. Java","C. Activity","A. LinearLayout","B. Swift","B. SharedPreferences",
        "B. Picasso","B. Model, View, ViewModel","A. Quản lý dependencies và build","B. onCreate()","C. Cả explicit và implicit"
    };

    static final String answer2[] = {
        "A. Mỗi lớp chỉ chịu trách nhiệm một chức năng","A. Bảo vệ dữ liệu và ẩn chi tiết cài đặt","A. Cho phép tái sử dụng mã","B. Compile-time và Runtime","A. Chỉ khai báo phương thức, không có triển khai","A. extends","B. Giảm độ phụ thuộc và phức tạp","A. package-private","B. Public hoặc protected","B. Không"
    };

    static final String answer3[] = {
        "A. 1","A. 0","C. P(A)+P(B)-P(A)P(B)","B. P(A)P(B)","D. Tổng xi*P(X=xi)","A. E(X^2)-[E(X)]^2","B. np","C. np(1-p)","B. Xác suất A xảy ra khi B đã xảy ra","C. \u2113umi P(Ai)P(B|Ai)"
    };

    static final String option1[] = {
        "A. Java","B. Kotlin","C. Swift","D. React Native",
        "A. Service","B. BroadcastReceiver","C. Activity","D. ContentProvider",
        "A. LinearLayout","B. ConstraintLayout","C. RelativeLayout","D. FrameLayout",
        "A. Objective-C","B. Swift","C. C#","D. Java",
        "A. SQLiteDatabase","B. SharedPreferences","C. Room","D. ContentProvider",
        "A. Retrofit","B. Picasso","C. Volley","D. OkHttp",
        "A. Model, View, Controller","B. Model, View, ViewModel","C. Model, View, Presenter","D. Model, Service, View",
        "A. Quản lý dependencies và build","B. Quản lý version","C. Quản lý layout","D. Quản lý database",
        "A. onStart()","B. onCreate()","C. onResume()","D. onPause()",
        "A. Explicit Intent","B. Implicit Intent","C. Cả explicit và implicit","D. Không Intent nào"
    };


    static final String option2[] = {
        "A. Mỗi lớp chỉ chịu trách nhiệm một chức năng","B. Nhiều trách nhiệm","C. Kết hợp nhiều lớp","D. Đa kế thừa",
        "A. Bảo vệ dữ liệu và ẩn chi tiết cài đặt","B. Tăng tốc độ thực thi","C. Giảm bộ nhớ","D. Quản lý exception",
        "A. Cho phép tái sử dụng mã","B. Thay đổi tên lớp","C. Ghi đè biến toàn cục","D. Tạo interface",
        "A. Compile-time","B. Runtime","C. Cả hai","D. Không hình thức nào",
        "A. Chỉ khai báo phương thức, không có triển khai","B. Cho phép cài đặt chi tiết","C. Có thể lưu trạng thái","D. Có constructor",
        "A. implements","B. extends","C. import","D. package",
        "A. Tự động khởi tạo object","B. Giấu chi tiết và nhấn mạnh giao diện","C. Tăng tốc độ lập trình","D. Tối ưu bộ nhớ",
        "A. public","B. package-private","C. private","D. protected",
        "A. Static methods","B. Public hoặc protected","C. Private methods","D. Constructors",
        "A. Có","B. Không","C. Chỉ với abstract class","D. Chỉ với final class"
    };
    static final String option3[] = {
        "A. 1","B. 0.5","C. 0","D. -1",
        "A. 0","B. 1","C. 0.5","D. -1",
        "A. P(A)+P(B)","B. P(A)P(B)","C. P(A)+P(B)-P(A)P(B)","D. P(A)-P(B)",
        "A. P(A)+P(B)","B. P(A)P(B)","C. P(A|B)+P(B|A)","D. P(A)-P(B)",
        "A. \\sum P(X=xi)","B. \\sum xi^2 P(X=xi)","C. P(X>=0)","D. \\sum xi P(X=xi)",
        "A. (\\sum xi P(X=xi))^2","B. E(X^2)","C. (\\sum xi^2 P(X=xi))","D. E(X^2)-[E(X)]^2",
        "A. p","B. np","C. n(1-p)","D. n+p",
        "A. np^2","B. np(1-p)","C. n^2p","D. n/p",
        "A. P(A và B)/P(B)","B. P(A và B)/P(A)","C. P(A)+P(B)","D. P(A)P(B)",
        "A. P(B|A)P(A)/(\\sum Ai P(B|Ai))","B. \\sum Ai P(Ai)P(B|Ai)","C. P(A)+P(B)","D. P(A)P(B)"
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
        btnSubmit = (Button) findViewById(R.id.btn_submit); // Lấy nút nộp bài
        click2.setEnabled(false);
        btnSubmit.setEnabled(false); // Ban đầu disable nút nộp bài
        txtTimer = (TextView) findViewById(R.id.text_view);
        //lay ra de thi duoc gui tu trang danhsachde thi sang
        int dethi=getIntent().getIntExtra("dethi",0);
        // Thiết lập tiêu đề bộ đề ngay sau khi lấy dethi
        TextView setTitle = findViewById(R.id.set_title);
        // Lấy chỉ số bộ đề từ Intent
        deThiIndex = getIntent().getIntExtra("dethi", 0);
        // Debug log để kiểm tra deThiIndex
        Log.d("De_So_1", "onCreate - deThiIndex: " + deThiIndex);
        Log.d("De_So_1", "onCreate - SET_NAMES[deThiIndex]: " + SET_NAMES[deThiIndex]);
        setTitle.setText(SET_NAMES[deThiIndex]);
        
        // Reset các biến static khi bắt đầu thi mới
        resetStaticVariables();
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
        
        // Hiển thị đáp án đã chọn (nếu có)
        showPreviousAnswer();
        
        // Khởi tạo trạng thái nút
        if (i == 1) {
            click2.setEnabled(false); // Disable nút Previous ở câu đầu
        }
        if (i == 10) {
            click.setEnabled(false); // Disable nút Next ở câu cuối
        }
        // Cho phép nộp bài bất cứ lúc nào
        btnSubmit.setEnabled(true);
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

        // Thêm listener để theo dõi khi người dùng chọn đáp án
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUserSelection = true;
                Log.d("De_So_1", "Người dùng chọn đáp án: " + r1.getText().toString());
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUserSelection = true;
                Log.d("De_So_1", "Người dùng chọn đáp án: " + r2.getText().toString());
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUserSelection = true;
                Log.d("De_So_1", "Người dùng chọn đáp án: " + r3.getText().toString());
            }
        });

        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUserSelection = true;
                Log.d("De_So_1", "Người dùng chọn đáp án: " + r4.getText().toString());
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra xem có chọn đáp án không
                boolean hasAnswered = (radioGroup.getCheckedRadioButtonId() != -1);
                
                Log.d("De_So_1", "Bấm nút Tiếp - Câu hiện tại: " + i + ", Đã chọn đáp án: " + hasAnswered);
                
                // Lưu đáp án hiện tại nếu có chọn
                saveCurrentAnswer();

                if(i>=10) {
                    click.setEnabled(false);
                    return; // Thoát sớm nếu đã đến câu cuối (câu 10)
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
                
                // Hiển thị đáp án đã chọn trước đó (nếu có)
                showPreviousAnswer();
                
                // Cập nhật trạng thái nút
                if (i == 10) {
                    click.setEnabled(false); // Disable nút Next ở câu cuối
                }
            }





        });

        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("De_So_1", "Bấm nút Quay lại - Câu hiện tại: " + i);
                
                // Kiểm tra xem có thực sự chọn đáp án cho câu hiện tại không
                boolean hasAnswered = (radioGroup.getCheckedRadioButtonId() != -1);
                Log.d("De_So_1", "Câu " + i + " có chọn đáp án: " + hasAnswered);
                
                // Lưu đáp án hiện tại trước khi chuyển câu
                saveCurrentAnswer();

                if(i<=1) {
                    click2.setEnabled(false);
                    return; // Thoát sớm nếu đã ở câu đầu (câu 1)
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
                
                // Hiển thị đáp án đã chọn trước đó (nếu có)
                showPreviousAnswer();
                
                // Cập nhật trạng thái nút
                if (i == 1) {
                    click2.setEnabled(false); // Disable nút Previous ở câu đầu
                }
            }



        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSubmitted) {
                    Toast.makeText(De_So_1.this, "Bạn đã nộp bài rồi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // Lưu đáp án hiện tại nếu có chọn
                saveCurrentAnswer();
                
                // Hiển thị dialog xác nhận
                showSubmitConfirmationDialog();
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
                // Lưu đáp án cuối cùng trước khi hết thời gian
                saveCurrentAnswer();
                
                // Tính toán kết quả
                calculateResults();
                
                Intent intent = new Intent(getApplicationContext(),Ket_Qua_Sau_Thi.class);
                Bundle bundle = new Bundle();
                // Lấy tên bộ đề lưu
                String a = SET_NAMES[deThiIndex];
                // Debug log để kiểm tra tên đề khi timer hết
                Log.d("De_So_1", "Timer hết - deThiIndex: " + deThiIndex);
                Log.d("De_So_1", "Timer hết - Tên đề gửi đi: " + a);
                bundle.putInt("correct", correct);
                bundle.putInt("wrong", wrong);
                bundle.putInt("marks", TOTAL_QUESTIONS);
                bundle.putString("de",a);
                intent.putExtras(bundle);
                startActivity(intent);
                finish(); // Đóng activity hiện tại để tránh timer chạy tiếp
                return;
            }

            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(count),
                    TimeUnit.MILLISECONDS.toMinutes(count) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(count)),
                    TimeUnit.MILLISECONDS.toSeconds(count) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(count)));
            txtTimer.setText(hms);
            count -= 1000;
        }
    };
    private void nextPage() {
        // Lưu đáp án hiện tại
        saveCurrentAnswer();
        
        if(flag==0){
            click2.setEnabled(true);
        }
        
        if(i<10){
            // Kiểm tra giới hạn trước khi tăng
            if (flag < 9 && n < 36) { // Cho phép làm đến câu 10 (flag từ 0-9)
                n+=4;
                flag+=1;
                stt.setText("Câu Hỏi Thứ " + (i+=1));
                noiDung.setText(question[flag]);
                r1.setText(option[n]);
                r2.setText(option[n+1]);
                r3.setText(option[n+2]);
                r4.setText(option[n+3]);
                
                // Hiển thị đáp án đã chọn trước đó (nếu có)
                showPreviousAnswer();
                
                // Cập nhật trạng thái nút
                if (i == 10) {
                    click.setEnabled(false); // Disable nút Next ở câu cuối
                }
            }
        }
    }

    private void setDefaultData() {
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r4.setChecked(false);
    }
    
    private void resetStaticVariables() {
        flag = 0;
        correct = 0;
        wrong = 0;
        i = 1;
        n = 0;
        Log.d("De_So_1", "Reset static variables - flag: " + flag + ", correct: " + correct + ", wrong: " + wrong + ", i: " + i + ", n: " + n);
    }
    
    // Phương thức lưu đáp án hiện tại
    private void saveCurrentAnswer() {
        Log.d("De_So_1", "saveCurrentAnswer() - Câu hiện tại: " + (flag + 1));
        Log.d("De_So_1", "isUserSelection: " + isUserSelection);
        
        if (radioGroup.getCheckedRadioButtonId() != -1 && isUserSelection) {
            RadioButton selectedButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            String ansText = selectedButton.getText().toString();
            Log.d("De_So_1", "Đáp án được chọn bởi người dùng: " + ansText);
            
            for(int j=0; j<results.size(); j++){
                if(results.get(j).getQuestionNum() == flag){
                    String currentSavedAnswer = results.get(j).getAnswer();
                    Log.d("De_So_1", "Đáp án đã lưu trước đó: '" + currentSavedAnswer + "'");
                    
                    // Chỉ lưu nếu đáp án thực sự thay đổi
                    if(!ansText.equals(currentSavedAnswer)) {
                        results.get(j).setAnswer(ansText);
                        if(answer[flag].equals(ansText)){
                            results.get(j).setCheck(true);
                        } else {
                            results.get(j).setCheck(false);
                        }
                        Log.d("De_So_1", "Lưu đáp án câu " + (flag + 1) + ": " + ansText);
                    } else {
                        Log.d("De_So_1", "Đáp án không thay đổi, không cần lưu lại");
                    }
                    break;
                }
            }
        } else if (radioGroup.getCheckedRadioButtonId() != -1) {
            Log.d("De_So_1", "Có radio button được chọn nhưng không phải do người dùng chọn");
        } else {
            // Nếu chưa chọn đáp án, ghi log để theo dõi
            Log.d("De_So_1", "Câu " + (flag + 1) + " chưa chọn đáp án");
        }
        
        // Reset biến isUserSelection sau khi lưu
        isUserSelection = false;
    }
    
    // Phương thức kiểm tra xem câu hiện tại đã trả lời chưa
    private boolean isCurrentQuestionAnswered() {
        return radioGroup.getCheckedRadioButtonId() != -1;
    }
    
    // Phương thức debug để kiểm tra trạng thái tất cả câu hỏi
    private void debugAllQuestionsStatus() {
        Log.d("De_So_1", "=== DEBUG TRẠNG THÁI TẤT CẢ CÂU HỎI ===");
        for(int j=0; j<results.size(); j++){
            String answer = results.get(j).getAnswer();
            if(answer != null && !answer.isEmpty() && !answer.equals("")){
                Log.d("De_So_1", "Câu " + (j+1) + ": Đã trả lời - " + answer);
            } else {
                Log.d("De_So_1", "Câu " + (j+1) + ": Chưa trả lời");
            }
        }
        Log.d("De_So_1", "=== KẾT THÚC DEBUG ===");
    }
    
    // Phương thức thay thế để set radio button bằng RadioGroup
    private void setRadioButtonByText(String targetText) {
        Log.d("De_So_1", "setRadioButtonByText() - Tìm text: " + targetText);
        
        String r1Text = r1.getText().toString();
        String r2Text = r2.getText().toString();
        String r3Text = r3.getText().toString();
        String r4Text = r4.getText().toString();
        
        Log.d("De_So_1", "Các text hiện có: r1='" + r1Text + "', r2='" + r2Text + "', r3='" + r3Text + "', r4='" + r4Text + "'");
        
        if(targetText.equals(r1Text)) {
            radioGroup.post(() -> {
                radioGroup.check(R.id.rd1);
                Log.d("De_So_1", "Đã set radioGroup.check(R.id.rd1)");
                // Kiểm tra ngay sau khi set
                Log.d("De_So_1", "radioGroup.getCheckedRadioButtonId() = " + radioGroup.getCheckedRadioButtonId());
                Log.d("De_So_1", "r1.isChecked() = " + r1.isChecked());
            });
        } else if(targetText.equals(r2Text)) {
            radioGroup.post(() -> {
                radioGroup.check(R.id.rd2);
                Log.d("De_So_1", "Đã set radioGroup.check(R.id.rd2)");
                Log.d("De_So_1", "radioGroup.getCheckedRadioButtonId() = " + radioGroup.getCheckedRadioButtonId());
                Log.d("De_So_1", "r2.isChecked() = " + r2.isChecked());
            });
        } else if(targetText.equals(r3Text)) {
            radioGroup.post(() -> {
                radioGroup.check(R.id.rd3);
                Log.d("De_So_1", "Đã set radioGroup.check(R.id.rd3)");
                Log.d("De_So_1", "radioGroup.getCheckedRadioButtonId() = " + radioGroup.getCheckedRadioButtonId());
                Log.d("De_So_1", "r3.isChecked() = " + r3.isChecked());
            });
        } else if(targetText.equals(r4Text)) {
            radioGroup.post(() -> {
                radioGroup.check(R.id.rd4);
                Log.d("De_So_1", "Đã set radioGroup.check(R.id.rd4)");
                Log.d("De_So_1", "radioGroup.getCheckedRadioButtonId() = " + radioGroup.getCheckedRadioButtonId());
                Log.d("De_So_1", "r4.isChecked() = " + r4.isChecked());
            });
        } else {
            Log.d("De_So_1", "KHÔNG TÌM THẤY TEXT PHÙ HỢP: " + targetText);
        }
    }
    
    // Phương thức hiển thị đáp án đã chọn trước đó
    private void showPreviousAnswer() {
        // Lấy đáp án đã lưu cho câu hiện tại
        String savedAnswer = results.get(flag).getAnswer();
        // Xóa lựa chọn cũ
        radioGroup.clearCheck();
        // Tìm radio button tương ứng
        int targetId = -1;
        if (savedAnswer != null && !savedAnswer.isEmpty()) {
            if (savedAnswer.equals(r1.getText().toString())) targetId = R.id.rd1;
            else if (savedAnswer.equals(r2.getText().toString())) targetId = R.id.rd2;
            else if (savedAnswer.equals(r3.getText().toString())) targetId = R.id.rd3;
            else if (savedAnswer.equals(r4.getText().toString())) targetId = R.id.rd4;
        }
        // Chọn radio button nếu tìm thấy
        if (targetId != -1) {
            final int idToCheck = targetId;
            radioGroup.post(() -> radioGroup.check(idToCheck));
        }
    }
    
    // Phương thức hiển thị dialog xác nhận nộp bài
    private void showSubmitConfirmationDialog() {
        // Đếm số câu đã trả lời (chỉ đếm những câu thực sự có đáp án)
        int answeredQuestions = 0;
        for(int j=0; j<results.size(); j++){
            String answer = results.get(j).getAnswer();
            if(answer != null && !answer.isEmpty() && !answer.equals("")){
                answeredQuestions++;
                Log.d("De_So_1", "Câu " + (j+1) + " đã trả lời: " + answer);
            }
        }
        Log.d("De_So_1", "Tổng số câu đã trả lời: " + answeredQuestions);
        
        // Hiển thị chi tiết từng câu
        String detailMessage = "";
        for(int j=0; j<results.size(); j++){
            String answer = results.get(j).getAnswer();
            if(answer != null && !answer.isEmpty() && !answer.equals("")){
                detailMessage += "Câu " + (j+1) + ": ✓\n";
            } else {
                detailMessage += "Câu " + (j+1) + ": ✗\n";
            }
        }
        
        String message = "Bạn đã trả lời " + answeredQuestions + "/10 câu hỏi.\n\n";
        message += "Chi tiết:\n" + detailMessage + "\n";
        if(answeredQuestions < 10) {
            message += "Còn " + (10 - answeredQuestions) + " câu chưa trả lời.\n";
            message += "Bạn có thể nộp bài ngay bây giờ hoặc quay lại trả lời thêm.\n";
            message += "Bạn có chắc chắn muốn nộp bài không?";
        } else {
            message += "Bạn đã hoàn thành tất cả câu hỏi.\n";
            message += "Bạn có chắc chắn muốn nộp bài không?";
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận nộp bài")
               .setMessage(message)
               .setPositiveButton("Nộp bài", (dialog, which) -> {
                   isSubmitted = true;
                   // Tính toán kết quả
                   calculateResults();
                   
                   Intent intent = new Intent(getApplicationContext(),Ket_Qua_Sau_Thi.class);
                   Bundle bundle = new Bundle();
                   // Lấy tên bộ đề lưu
                   String a = SET_NAMES[deThiIndex];
                   // Debug log để kiểm tra tên đề
                   Log.d("De_So_1", "deThiIndex: " + deThiIndex);
                   Log.d("De_So_1", "Tên đề gửi đi: " + a);
                   bundle.putInt("correct", correct);
                   bundle.putInt("wrong", wrong);
                   bundle.putInt("marks", TOTAL_QUESTIONS);
                   bundle.putString("de",a);
                   intent.putExtras(bundle);
                   startActivity(intent);
                   finish(); // Đóng activity hiện tại để tránh timer chạy tiếp
               })
               .setNegativeButton("Tiếp tục làm bài", (dialog, which) -> {
                   dialog.dismiss();
               })
               .setCancelable(false);

        submitDialog = builder.show();
    }
    
    // Phương thức tính toán kết quả
    private void calculateResults() {
        DBHelper dbHelper = new DBHelper(De_So_1.this);
        correct = 0;
        wrong = 0;

        // Xác định lần thi mới (tăng 1 lần so với max hiện tại) và dùng chung cho mọi câu trong lần thi này
        String tenDe = SET_NAMES[deThiIndex];
        int newLanThi = dbHelper.getMaxLanThi(tenDe) + 1;
        
        for(int j=0; j<results.size(); j++){
            String answer = results.get(j).getAnswer();
            if(answer != null && !answer.isEmpty() && !answer.equals("")) {
                if(results.get(j).isCheck() == true){
                    correct += 1;
                    Log.d("De_So_1", "Câu " + (j+1) + " đúng: " + answer);
                } else {
                    wrong += 1;
                    Log.d("De_So_1", "Câu " + (j+1) + " sai: " + answer);
                }
                dbHelper.insertDataChecked(tenDe, results.get(j).getQuestionNum()+1, newLanThi, results.get(j).getAnswer(), De_So_1.answer[results.get(j).getQuestionNum()]);
            } else {
                Log.d("De_So_1", "Câu " + (j+1) + " chưa trả lời");
            }
        }
        Log.d("De_So_1", "Kết quả cuối cùng - Đúng: " + correct + ", Sai: " + wrong);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Tránh rò rỉ window nếu dialog còn mở
        if (submitDialog != null && submitDialog.isShowing()) {
            submitDialog.dismiss();
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}