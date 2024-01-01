package vn.edu.dlu.ctk45.calories_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class ThongTinUngDung extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_layout, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        ImageView congThucNauAn = rootView.findViewById(R.id.cong_thuc_nau_an);
        ImageView phuongPhapGiamCan = rootView.findViewById(R.id.phuong_phap_giam_can);
        String link1 = "https://monngonmoingay.com/tim-kiem-mon-ngon/";
        String link2 = "https://suckhoedoisong.vn/cac-phuong-phap-giam-can-khoa-hoc-16922092711175053.htm";

        setClickableImage(congThucNauAn, link1);
        setClickableImage(phuongPhapGiamCan, link2);

        return rootView;
    }

    private void setClickableImage(ImageView imageView, final String link) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Mở đường liên kết: " + link, Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
            }
        });
    }
}