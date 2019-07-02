package info.kingpes.mymodule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament2, container, false);

        TextView txt = view.findViewById(R.id.txt);

        if (getArguments() != null){
            String s = getArguments().getString("DATA");
            txt.setText(s);
        }

        return view;
    }
}
