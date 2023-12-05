package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SlideMenu extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.side_menu_layout, container,
                false);


        return rootView;
    }
    public void changeMenuUserName(String name) {
        View view = getView();
        if(view != null) {
            TextView textViewName = view.findViewById(R.id.menu_user_name);
            textViewName.setText(name);
        }
    }
}
