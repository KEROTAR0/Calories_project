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
import android.view.View;
import android.view.ViewGroup;
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
        popupWindow.showAtLocation(view, Gravity.START, 0, 0);

        TextView menuItem1 = sideMenuView.findViewById(R.id.menu_1);
        TextView menuItem2 = sideMenuView.findViewById(R.id.menu_2);
        //TextView menuItem3 = sideMenuView.findViewById(R.id.menu_3);
        //TextView menuItem4 = sideMenuView.findViewById(R.id.menu_4);
        //TextView menuItem5 = sideMenuView.findViewById(R.id.menu_5);
        TextView menuItem6 = sideMenuView.findViewById(R.id.menu_6);
        TextView menuItem7 = sideMenuView.findViewById(R.id.menu_7);


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
        /*menuItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout4();
                popupWindow.dismiss();
            }
        });*/

        //so nhat ky
        /*menuItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLayout5();
                popupWindow.dismiss();
            }
        });*/

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
    }

    private void navigateToLayout1() {
        // Navigate to layout 1
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new ThongTinCaNhan_ACT());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout2() {
        // Navigate to layout 2
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new CaiDat());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout6() {
        // Navigate to layout 1
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new BuaAn());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLayout7() {
        // Navigate to layout 1
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, new MucTieu_ACT());
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
        alertDialog.show();
    }
}