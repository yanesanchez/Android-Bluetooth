package com.example.a04_bbmf;

// source: https://data-flair.training/blogs/android-menu/
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_bbmf.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /* --------------------------------------- */
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /////setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ////Button button = (Button)findViewById(R.id.button);
        binding.button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Implement event handling
                        Context ctx =  MainActivity.this;
                        ////Toast.makeText(ctx, "Hello", Toast.LENGTH_LONG).show();
                        binding.textView.setText("Hello Android 426");
                    }
                });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FAB Pressed", Toast.LENGTH_LONG).show();
            }
        });

    }
    /* --------------------------------------- */

    // Overriding onCreateOptionMenu() to make Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating menu by overriding inflate() method of MenuInflater class.
        //Inflating here means parsing layout XML to views.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Overriding onOptionsItemSelected to perform event on menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ////Toast.makeText(this, "You chose : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.first_item:
                binding.textView.setText("First Selected");
                return true;
            case R.id.second_item:
                binding.textView.setText("Second Selected");
                return true;
            case R.id.third_item:
                binding.textView.setText("Third Selected");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}