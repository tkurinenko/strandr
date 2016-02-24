package nimda.preferencessimple2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by tkurinenko on 24.02.2016.
 */
public class PrefActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
