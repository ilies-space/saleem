package com.ahmed.saleem.app.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.saleem.app.R;
import com.ahmed.saleem.app.makeOrder;
import com.ahmed.saleem.app.repairMan;

public class HomeFragment extends Fragment {
    public static ProgressDialog pd2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnViewrepairMan;
        Button makeOrder;
        btnViewrepairMan = root.findViewById(R.id.repairMan);
        makeOrder = root.findViewById(R.id.order);

        btnViewrepairMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), repairMan.class);
                getActivity().startActivity(myIntent);
                loiding();
            }
        });

        // 2

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), makeOrder.class);
                getActivity().startActivity(myIntent);
                loiding();
            }
        });

        return root;
    }


    public void loiding()
    {

        pd2= new ProgressDialog(getActivity());
        pd2.setMessage("جاري تحميل المعلوومات ... ");
        pd2.show();
    }
}
