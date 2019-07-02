package info.kingpes.fragmentcontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentReplace {
    public static final int NEXT = 0;
    public static final int BACK = 1;
    private static FragmentReplace instance = null;
    private FragmentManager frgManager;
    private Fragment frg;
    private boolean backStack;
    private Bundle bundle;
    private int enter, exit, pop_enter, pop_exit;
    private int direction = -1;
    private int frame;

    public static FragmentReplace getInstance() {
        if (instance == null) {
            instance = new FragmentReplace();
        }
        return instance;
    }

    public FragmentReplace bundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public FragmentReplace fragmentManager(FragmentManager frgManager) {
        this.frgManager = frgManager;
        return this;
    }

    public FragmentReplace backStack(boolean backStack) {
        this.backStack = backStack;
        return this;
    }

    public FragmentReplace fragment(Fragment frg) {
        this.frg = frg;
        return this;
    }

    public FragmentReplace frame(int frame) {
        this.frame = frame;
        return this;
    }

    public FragmentReplace direction(int direction) {
        this.direction = direction;
        if (direction == NEXT) {
            enter = R.anim.enter_from_right;
            exit = R.anim.exit_to_left;

            pop_enter = R.anim.enter_from_left;
            pop_exit = R.anim.exit_to_right;
        } else if (direction == BACK) {
            enter = R.anim.enter_from_left;
            exit = R.anim.exit_to_right;

            pop_enter = R.anim.enter_from_right;
            pop_exit = R.anim.exit_to_left;
        }
        return this;
    }


    public void commit() {
        if (frgManager == null || frg == null) return;
        if (bundle != null) frg.setArguments(bundle);
        FragmentTransaction transaction = frgManager.beginTransaction();
        if (backStack) transaction.addToBackStack(frg.getClass().getName());

        if (direction > -1) transaction.setCustomAnimations(enter, exit, pop_enter, pop_exit);
        transaction
                .replace(frame, frg, frg.getClass().getName())
                .commit();

    }
}
