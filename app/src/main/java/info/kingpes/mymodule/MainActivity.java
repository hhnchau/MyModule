package info.kingpes.mymodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import info.kingpes.mymodule.fragmentcontroller.FragmentController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fragmentController(View view) {
        startActivity(new Intent(this, FragmentController.class));
    }
}
