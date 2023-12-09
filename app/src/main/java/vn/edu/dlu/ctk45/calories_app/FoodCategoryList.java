package vn.edu.dlu.ctk45.calories_app;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.ArrayList;
public class FoodCategoryList extends BaseAdapter {
    ArrayList<FoodCategory> list;

    public FoodCategoryList(ArrayList<FoodCategory> list) {
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
        View view = View.inflate(parent.getContext(), R.layout.ds_loai_thuc_pham, null);
        FoodCategory foodCategory = list.get(position);
        ImageView img = view.findViewById(R.id.food_category_img);
        img.setImageResource(foodCategory.image);
        TextView txtTitle = view.findViewById(R.id.food_category_name);
        txtTitle.setText(foodCategory.ten_thuc_pham);
        TextView txtContent = view.findViewById(R.id.food_category_info);
        txtContent.setText(foodCategory.thong_tin_thuc_pham);

        return view;
    }
}

