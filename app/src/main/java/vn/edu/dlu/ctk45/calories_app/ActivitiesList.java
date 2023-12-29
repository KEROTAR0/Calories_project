package vn.edu.dlu.ctk45.calories_app;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.ArrayList;
public class ActivitiesList extends BaseAdapter {
    ArrayList<Activity> list;

    public ActivitiesList(ArrayList<Activity> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.ds_hoat_dong_layout, null);
        Activity hd = list.get(position);
        ImageView img = view.findViewById(R.id.img_activity);
        img.setImageResource(hd.image);
        TextView txtTitle = view.findViewById(R.id.activity_name);
        txtTitle.setText(hd.ten_hoat_dong);
        TextView txtContent = view.findViewById(R.id.activity_calo);
        txtContent.setText(String.valueOf(hd.thong_tin_hd));

        TextView txtAmount = view.findViewById(R.id.time_ammount);
        Button increaseBtn = view.findViewById(R.id.increase);
        Button decreaseBtn = view.findViewById(R.id.decrease);

        final int[] amount = {0};
        txtAmount.setText(String.valueOf(amount[0]));

        increaseBtn.setOnClickListener(v -> {
            amount[0]++;
            txtAmount.setText(String.valueOf(amount[0]));

            // Tính calo và cập nhật vào TextView calo_waste
            int calories = list.get(position).calculateCalories(amount[0]);
            TextView caloWaste = view.findViewById(R.id.calo_waste);
            caloWaste.setText(String.valueOf(calories));

        });

        decreaseBtn.setOnClickListener(v -> {
            if (amount[0] > 0) {
                amount[0]--;
                txtAmount.setText(String.valueOf(amount[0]));

                // Tính calo và cập nhật vào TextView calo_waste
                int calories = list.get(position).calculateCalories(amount[0]);
                TextView caloWaste = view.findViewById(R.id.calo_waste);
                caloWaste.setText(String.valueOf(calories));

            }
        });

        return view;
    }
}

