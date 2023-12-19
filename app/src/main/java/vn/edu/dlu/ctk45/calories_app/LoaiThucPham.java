package vn.edu.dlu.ctk45.calories_app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class LoaiThucPham extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.loai_thuc_pham, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ListView listView = rootView.findViewById(R.id.lv_food_category);
        Drawable divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider);
        listView.setDivider(divider);
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));

        String[] fcName = new String[]{"Trái cây", "Các loại thịt", "Ngũ cốc", "Hải sản", "Rau củ quả"};
        String[] fcInfo = new String[]{
                "Trái cây là một trong những thực phẩm giàu dinh dưỡng và tốt cho sức khỏe phổ biến nhất thế giới.",
                "Thịt là một nguồn cung cấp thực phẩm cực tốt và giàu dinh dưỡng. Trong khi thịt bò chứa nhiều sắt thì thịt ức gà lại rất giàu protein.",
                "Đây là một trong những loại thực phẩm phổ biến nhất và hiện đang là lương thực chính của hơn một nửa dân số thế giới.",
                "Chúng đặc biệt giàu axit béo omega 3 và i-ốt, hai chất dinh dưỡng mà hầu hết mọi người đều thiếu.",
                "Rau là nguồn cung cấp chất xơ dồi dào nhất trên thế giới. Bên cạnh đó chúng còn cung cấp các chất khoáng và vitamin khác."
        };
        int[] imgs = new int[]{R.drawable.fruit, R.drawable.meat, R.drawable.seed, R.drawable.seafood
                , R.drawable.vegetable};

        ArrayList<FoodCategory> fcList = new ArrayList<>();
        for (int i = 0; i < fcName.length; i++) {
            fcList.add(new FoodCategory(fcName[i], fcInfo[i], imgs[i]));
        }

        FoodCategoryList adapter = new FoodCategoryList(fcList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Fragment fragmentToShow;

                switch (position) {
                    case 0: // Trái cây
                        fragmentToShow = new FruitFragment();
                        break;
                    //case 1: // Các loại thịt
                    //    fragmentToShow = new CacLoaiThit(); // Tạo fragment tương ứng
                    //    break;
                    // Thêm các case khác tương ứng
                    default:
                        fragmentToShow = null; // Fragment mặc định nếu không có trường hợp nào khớp
                        break;
                }

                if (fragmentToShow != null) {
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.ln_main, fragmentToShow) // R.id.fragment_container là ID của layout chứa fragment
                            .addToBackStack(null) // Cho phép quay lại fragment trước đó
                            .commit();
                }
            }
        });

        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }
}