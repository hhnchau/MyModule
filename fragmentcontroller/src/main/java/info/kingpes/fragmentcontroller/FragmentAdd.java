package info.kingpes.fragmentcontroller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdd {
    public static final int NEXT = 0;
    public static final int BACK = 1;
    private List<Fragment> lstFrg;
    private Fragment frg;
    private boolean exist;
    private FragmentManager frgManager;
    private boolean backStack;
    private Bundle bundle;
    private int enter, exit;
    private int direction = -1;
    private int frame;
    private static FragmentAdd instance = null;

    public static FragmentAdd getInstance() {
        if (instance == null) {
            instance = new FragmentAdd();
        }
        return instance;
    }


    public FragmentAdd bundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public FragmentAdd fragmentManager(FragmentManager frgManager) {
        this.frgManager = frgManager;
        return this;
    }

    public FragmentAdd backStack(boolean backStack) {
        this.backStack = backStack;
        return this;
    }

    public FragmentAdd frame(int frame) {
        this.frame = frame;
        return this;
    }

    public FragmentAdd direction(int direction) {
        this.direction = direction;
        if (direction == NEXT) {
            enter = R.anim.enter_from_right;
            exit = R.anim.exit_to_left;

        } else if (direction == BACK) {
            enter = R.anim.enter_from_left;
            exit = R.anim.exit_to_right;
        }
        return this;
    }


    public FragmentAdd add(@NonNull Fragment frg) {
        this.frg = frg;
        exist = false;
        if (lstFrg == null) lstFrg = new ArrayList<>();
        for (Fragment f : lstFrg) {
            if (frg.getClass().getName().equals(f.getClass().getName())) {
                exist = true;
                break;
            }
        }
        return this;
    }

    public void commit() {
        if (frgManager == null) return;
        FragmentTransaction transaction = frgManager.beginTransaction();
        if (exist) {
            //Exist
            Fragment _frg = null;
            for (Fragment f : lstFrg) {
                transaction.hide(f);
                if (frg.getClass().getName().equals(f.getClass().getName())) _frg = f;
            }
            if (_frg != null) {
                if (direction > -1) transaction.setCustomAnimations(enter, exit);
                transaction.show(_frg)
                        .commit();
            }
        } else {
            //Add
            for (Fragment f : lstFrg) {
                if (!frg.getClass().getName().equals(f.getClass().getName())) transaction.hide(f);
            }

            if (frg != null) {

                if (bundle != null) frg.setArguments(bundle);

                if (backStack) transaction.addToBackStack(frg.getClass().getName());

                if (direction > -1) transaction.setCustomAnimations(enter, exit);
                transaction.add(frame, frg, frg.getClass().getName())
                        .commit();
                lstFrg.add(frg);
            }
        }
    }
}
