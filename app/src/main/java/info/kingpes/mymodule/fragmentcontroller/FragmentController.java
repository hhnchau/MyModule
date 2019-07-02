package info.kingpes.mymodule.fragmentcontroller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import info.kingpes.fragmentcontroller.FragmentAdd;
import info.kingpes.fragmentcontroller.FragmentReplace;
import info.kingpes.mymodule.Fragment1;
import info.kingpes.mymodule.Fragment2;
import info.kingpes.mymodule.Fragment3;
import info.kingpes.mymodule.R;

public class FragmentController extends AppCompatActivity {
    private Fragment1 frg1;
    private Fragment2 frg2;
    private Fragment3 frg3;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_controller);

        bundle = new Bundle();
        bundle.putString("DATA", "Hello Fragment Controller");

    }

    public void tab1(View view) {
        if (frg1 == null) frg1 = new Fragment1();
        add(frg1);
    }

    public void tab2(View view) {
        if (frg2 == null) frg2 = new Fragment2();
        add(new Fragment2());
    }

    public void tab3(View view) {
        if (frg3 == null) frg3 = new Fragment3();
        add(new Fragment3());
    }

    private void add(Fragment frg) {
        FragmentAdd.getInstance()
                .fragmentManager(getSupportFragmentManager())
                .frame(R.id.frame)
                .add(frg)
                .bundle(bundle)
                //.direction(FragmentAdd.BACK)
                .commit();
    }

    private void replace(Fragment frg) {
        FragmentReplace.getInstance()
                .fragmentManager(getSupportFragmentManager())
                .fragment(frg)
                .bundle(bundle)
                .frame(R.id.frame)
                .backStack(true)
                .direction(FragmentReplace.NEXT)
                .commit();
    }
}
