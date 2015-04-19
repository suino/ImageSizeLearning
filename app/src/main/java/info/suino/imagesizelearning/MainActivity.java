package info.suino.imagesizelearning;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image_view);
        assignHeightSpinner();
        assignImageSpinner();
        assignScaleSpinner();
        assignAdjustSpinner();
    }

    private void assignHeightSpinner() {
        String defaultItem = "Change LayoutParams";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(defaultItem);
        adapter.add("WRAP_CONTENT");
        adapter.add("MATCH_PARENT");
        adapter.add("200dp");
        Spinner spinner = (Spinner) findViewById(R.id.height_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String name = (String) spinner.getSelectedItem();

                int height = mImageView.getLayoutParams().height;
                if (name.equals("WRAP_CONTENT")) {
                    height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                } else if (name.equals("MATCH_PARENT")) {
                    height = RelativeLayout.LayoutParams.MATCH_PARENT;
                } else if (name.equals("200dp")) {
                    height = 200;
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        mImageView.getLayoutParams().width,
                        height
                );
                layoutParams.addRule(RelativeLayout.BELOW, R.id.spinner_group);
                mImageView.setLayoutParams(layoutParams);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void assignImageSpinner() {
        final String defaultItem = "Change Image";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(defaultItem);
        adapter.add("縦長");
        adapter.add("横長");
        Spinner spinner = (Spinner) findViewById(R.id.image_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String name = (String) spinner.getSelectedItem();
                if (name.equals(defaultItem)) return;

                if (name.equals("縦長")) {
                    mImageView.setImageResource(R.drawable.tatenaga_large);
                } else if(name.equals("横長")){
                    mImageView.setImageResource(R.drawable.yokonaga_large);
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        mImageView.getLayoutParams().width,
                        mImageView.getLayoutParams().height
                );
                layoutParams.addRule(RelativeLayout.BELOW, R.id.spinner_group);
                mImageView.setLayoutParams(layoutParams);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void assignScaleSpinner() {
        final String defaultItem = "Change ScaleType";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(defaultItem);
        for ( ImageView.ScaleType scaleType : ImageView.ScaleType.values()) {
            adapter.add(scaleType.name());
        }
        Spinner spinner = (Spinner) findViewById(R.id.scale_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String name = (String) spinner.getSelectedItem();
                if (!name.equals(defaultItem)) {
                    mImageView.setScaleType(ImageView.ScaleType.valueOf(name));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void assignAdjustSpinner() {
        final String defaultItem = "Change AdjustViewBounds";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(defaultItem);
        adapter.add("true");
        adapter.add("false");
        Spinner spinner = (Spinner) findViewById(R.id.adjust_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String name = (String) spinner.getSelectedItem();
                if (name.equals(defaultItem)) return;

                if (name.equals("true")) {
                    mImageView.setAdjustViewBounds(true);
                } else {
                    mImageView.setAdjustViewBounds(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
