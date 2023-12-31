package vn.edu.dlu.ctk45.calories_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainScreen extends Fragment{
    private static final String PREF_NAME = "popup_pref";
    private static final String PREF_KEY_POPUP_SHOWN = "popup_shown";
    private TextView dateTextView;
    private Calendar calendar;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_screen, container,
                false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }

        initViews();
        rootView.findViewById(R.id.btn_slide_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSideMenu(v);
            }
        });
        rootView.findViewById(R.id.add_breakfast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaSang());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rootView.findViewById(R.id.add_lunch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaTrua());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rootView.findViewById(R.id.add_dinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaToi());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rootView.findViewById(R.id.add_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new HoatDong());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        dateTextView = rootView.findViewById(R.id.date_picker_actions);
        calendar = Calendar.getInstance();
        dateTextView.setOnClickListener(v -> showDatePickerDialog());
        updateDateInView();

        TextView kcal = rootView.findViewById(R.id.kcal_khuyen_nghi);
        TextView fat = rootView.findViewById(R.id.fat_khuyen_nghi);
        TextView protein = rootView.findViewById(R.id.protein_khuyen_nghi);
        sharedPreferences = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int userKcal = sharedPreferences.getInt("CaloNeeded",0 );
        String userAge = sharedPreferences.getString("Age", "0");
        int age = Integer.parseInt(userAge);
        String userWeight = sharedPreferences.getString("Weight", "0");
        int weight = Integer.parseInt(userWeight);
        String gender = sharedPreferences.getString("Gender", "Nam");
        kcal.setText(String.valueOf(userKcal));
        double fatCal = 0.0;
        double proteinCal =0.0;
        if(gender.equals("Nam")) {
            if(age >= 0 && age <= 65) {
                fatCal = Math.round(userKcal*0.3);
                proteinCal = Math.round(weight*0.85);
            }
            else {
                fatCal = Math.round(userKcal*0.25);
                proteinCal = Math.round(weight*0.9);
            }
        } else {
            if(age >= 0 && age <= 65) {
                fatCal = Math.round(userKcal*0.25);
                proteinCal = Math.round(weight*0.8);
            }
            else {
                fatCal = Math.round(userKcal*0.2);
                proteinCal = Math.round(weight*0.85);
            }
        }
        String fatst = fatCal +"g";
        fat.setText(fatst);
        String proteinst = proteinCal +"g";
        protein.setText(proteinst);

        TextView caloAct = rootView.findViewById(R.id.calo_act);
        String caloWaste = sharedPreferences.getInt("CaloWaste",0 ) + " Calo";
        caloAct.setText(caloWaste);

        int waterNeed = 0;
        TextView waterTextView = rootView.findViewById(R.id.water);
        TextView keepGoal = rootView.findViewById(R.id.keep_goal);
        String kgt = "Bạn cần nạp thêm " +String.valueOf(caloWaste) + " để theo đúng mục tiêu";
        if (sharedPreferences.getInt("CaloWaste",0 ) == 0) {
            keepGoal.setText("Bạn có thể thêm hoạt động ở phía dưới");
            waterNeed = Integer.parseInt(sharedPreferences.getString("Weight", "0"))*30;
        } else {
            keepGoal.setText(kgt);
            waterNeed = Integer.parseInt(sharedPreferences.getString("Weight", "0"))*30 + sharedPreferences.getInt("CaloWaste",0 )*2;
        }

        TextView goal = rootView.findViewById(R.id.goal);
        String userGoal = sharedPreferences.getString("SelectedGoal", "");
        goal.setText(userGoal);

        waterTextView.setText("Bạn cần uống " + waterNeed + "ml mỗi ngày");

        int savedCalories = sharedPreferences.getInt("totalCaloriesMorning", 0) + sharedPreferences.getInt("totalCaloriesLunch", 0) + sharedPreferences.getInt("totalCaloriesDinner", 0);
        float savedFats = sharedPreferences.getFloat("totalFatsMorning", 0.0f) + sharedPreferences.getFloat("totalFatsLunch", 0.0f) + sharedPreferences.getFloat("totalFatsDinner", 0.0f);
        float savedProteins = sharedPreferences.getFloat("totalProteinsMorning", 0.0f) + sharedPreferences.getFloat("totalProteinsLunch", 0.0f) + sharedPreferences.getFloat("totalProteinsDinner", 0.0f);
        TextView caloHT = rootView.findViewById(R.id.kcal_hien_tai);
        TextView fatHT = rootView.findViewById(R.id.fat_hien_tai);
        TextView proteinHT = rootView.findViewById(R.id.protein_hien_tai);
        if (savedCalories != 0 && savedFats != 0.0f && savedProteins != 0.0f) {
            try {
                caloHT.setText(String.valueOf(savedCalories));
                fatHT.setText(String.valueOf(Math.round(savedFats * 10.0) / 10.0));
                proteinHT.setText(String.valueOf(Math.round(savedProteins * 10.0) / 10.0));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Button defaultBtn = rootView.findViewById(R.id.default_btn);
        defaultBtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("CaloWaste", 0);

            editor.putInt("totalCaloriesMorning", 0);
            editor.putInt("totalCaloriesLunch", 0);
            editor.putInt("totalCaloriesDinner", 0);

            editor.putFloat("totalFatsMorning", 0.0f);
            editor.putFloat("totalFatsLunch", 0.0f);
            editor.putFloat("totalFatsDinner", 0.0f);

            editor.putFloat("totalProteinsMorning", 0.0f);
            editor.putFloat("totalProteinsLunch", 0.0f);
            editor.putFloat("totalProteinsDinner", 0.0f);

            editor.apply();

            caloHT.setText("0");
            fatHT.setText("0");
            proteinHT.setText("0");
            caloAct.setText("N Kcal");
            int wn = Integer.parseInt(sharedPreferences.getString("Weight", "0"))*30;
            keepGoal.setText("Bạn có thể thêm hoạt động ở phía dưới");
            waterTextView.setText("Bạn cần uống " + wn + "ml mỗi ngày");

        });

        if(age == 0){
            showUserInputDialog();
        }
        if(age >0 && userGoal.isEmpty()){
            showGoalInputDialog();
        }
        return rootView;
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDateInView();
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void updateDateInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateTextView.setText(sdf.format(calendar.getTime()));
    }
    public void showSideMenu(View view) {
        View sideMenuView = getLayoutInflater().inflate(R.layout.side_menu_layout, null);
        PopupWindow popupWindow = new PopupWindow(sideMenuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        Animation slideInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_left);
        sideMenuView.startAnimation(slideInAnimation);
        popupWindow.showAtLocation(view, Gravity.START, 0, 0);

        TextView menuItem1 = sideMenuView.findViewById(R.id.menu_1);
        TextView menuItem2 = sideMenuView.findViewById(R.id.menu_2);
        TextView menuItem3 = sideMenuView.findViewById(R.id.menu_3);
        TextView menuItem4 = sideMenuView.findViewById(R.id.menu_4);
        TextView menuItem5 = sideMenuView.findViewById(R.id.menu_5);
        TextView menuItem6 = sideMenuView.findViewById(R.id.menu_6);
        TextView menuItem7 = sideMenuView.findViewById(R.id.menu_7);
        TextView menuItem8 = sideMenuView.findViewById(R.id.menu_8);
        TextView menuItem9 = sideMenuView.findViewById(R.id.menu_9);


        //thong tin ca nhan
        menuItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on menu item 1
                // Navigate to layout 1
                navigateToLayout1();
                popupWindow.dismiss();
            }
        });

        //cai dat
        menuItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout2();
                popupWindow.dismiss();
            }
        });

        //thuc an
        /*menuItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout3();
                popupWindow.dismiss();
            }
        });*/

        //loai thuc pham
        menuItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout4();
                popupWindow.dismiss();
            }
        });

        //so nhat ky
        menuItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout5();
                popupWindow.dismiss();
            }
        });

        //bua an
        menuItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout6();
                popupWindow.dismiss();
            }
        });

        //muc tieu
        menuItem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout7();
                popupWindow.dismiss();
            }
        });

        //ve ung dung
        menuItem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout8();
                popupWindow.dismiss();
            }
        });

        //cau hoi thuong gap
        menuItem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout9();
                popupWindow.dismiss();
            }
        });
    }

    private void navigateToLayout1() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new ThongTinCaNhan_ACT());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout2() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new CaiDat());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void navigateToLayout4() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new LoaiThucPham());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void navigateToLayout5() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new NhatKy());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout6() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new BuaAn());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout7() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new MucTieu_ACT());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout8() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new ThongTinUngDung());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout9() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new CauHoiThuongGap());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void initViews() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean popupShown = sharedPreferences.getBoolean(PREF_KEY_POPUP_SHOWN, false);

        if (!popupShown) {
            new Handler().postDelayed(() -> {
                showUserInputDialog();
                markPopupAsShown(sharedPreferences);
            }, 1000);
        }
    }
    private void markPopupAsShown(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_KEY_POPUP_SHOWN, true);
        editor.apply();
    }
    private void showUserInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Vui lòng nhập thông tin ở \"Thông tin cá nhân\" ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.ln_main, new ThongTinCaNhan_ACT());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        dialog.dismiss();
                    }
                });
        // Tạo và hiển thị AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    private void showGoalInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Vui lòng đặt mục tiêu của ban ở \"Mục tiêu\" ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.ln_main, new MucTieu_ACT());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}