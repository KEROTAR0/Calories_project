package vn.edu.dlu.ctk45.calories_app;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class DSMonAn extends BaseAdapter {
    ArrayList<Food> list;
    private FoodDatabaseHelper dbHelper;

    public DSMonAn(List<Food> foodList, FoodDatabaseHelper dbHelper)  {
        this.list = new ArrayList<>(foodList);
        this.dbHelper = dbHelper;
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
        View view = View.inflate(parent.getContext(), R.layout.ds_mon_layout, null);
        Food food = list.get(position);
        ImageView img = view.findViewById(R.id.food_img);
        img.setImageResource(food.getImage());
        TextView txtTitle = view.findViewById(R.id.food_name);
        txtTitle.setText(food.getTen_mon());
        TextView txtContent = view.findViewById(R.id.food_info);
        txtContent.setText(food.getThong_tin_mon());

        TextView txtAmount = view.findViewById(R.id.food_ammount);
        Button increaseBtn = view.findViewById(R.id.increase);
        Button decreaseBtn = view.findViewById(R.id.decrease);

        final int[] amount = {0};
        txtAmount.setText(String.valueOf(amount[0]));

        increaseBtn.setOnClickListener(v -> {
            amount[0]++;
            txtAmount.setText(String.valueOf(amount[0]));
            dbHelper.updateFoodAmount(food.getId(), amount[0]);
        });
        decreaseBtn.setOnClickListener(v -> {
            if (amount[0] > 0) {
                amount[0]--;
                txtAmount.setText(String.valueOf(amount[0]));
                dbHelper.updateFoodAmount(food.getId(), amount[0]);
            }
        });

        return view;
    }
}
