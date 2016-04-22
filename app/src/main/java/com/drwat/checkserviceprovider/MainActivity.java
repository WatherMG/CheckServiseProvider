package com.drwat.checkserviceprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private PhoneNumberEditTextView editText;
    private TextView providerTextView;
    private TextView regionNameTextView;
    private PhoneNumberEditTextView.OnChangeCurrentRegionListener onChangeCurrentRegionListener = new PhoneNumberEditTextView.OnChangeCurrentRegionListener() {
        @Override
        public void onChange(Region region) {
            if (region != null) {
                regionNameTextView.setText(region.getName());
            } else {
                regionNameTextView.setText("");
            }
        }
    };
    private PhoneNumberEditTextView.OnChangeCurrentServiceProviderListener onChangeCurrentServiceProviderListener = new PhoneNumberEditTextView.OnChangeCurrentServiceProviderListener() {
        @Override
        public void onChange(ServiceProvider provider) {
            if (provider != null) {
                providerTextView.setText(provider.getName());
            } else {
                providerTextView.setText("");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (PhoneNumberEditTextView) findViewById(R.id.editText);
        providerTextView = (TextView) findViewById(R.id.serviceProvider);
        regionNameTextView = (TextView) findViewById(R.id.regionName);
        View v = View.inflate(this, R.layout.header_list_view, null);
        ListView listView = (ListView) findViewById(R.id.hintListView);
        ArrayAdapter<CharSequence> defCodeUkrtelecom = ArrayAdapter.createFromResource(this, R.array.def_code_hint, R.layout.def_code_hint_list);
        assert listView != null;
        listView.setAdapter(defCodeUkrtelecom);
        listView.addHeaderView(v);
        editText.setUseMask(true);
        editText.setChangeCurrentServiceProviderListener(onChangeCurrentServiceProviderListener);
        editText.setOnChangeCurrentRegionListener(onChangeCurrentRegionListener);

    }

    public void clearButton(View view) {
        editText.getText().clear();
        providerTextView.setText("");
        regionNameTextView.setText("");
    }
}
