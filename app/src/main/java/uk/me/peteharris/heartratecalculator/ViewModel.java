package uk.me.peteharris.heartratecalculator;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.tool.CompilerChef;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;

/**
 * Created by pharris on 17/06/16.
 */
public class ViewModel extends BaseObservable {
    int maxHr = 200;
    int restingHr = 50;

    double percentage = 0;

    @Bindable
    public String getHeartRate(){
        return String.format("%.0f", ( (maxHr - restingHr) * percentage) + restingHr);
    }
    @Bindable
    public String getPercentage(){
        return String.format("Percentage: %.0f %%", 100 * percentage);
    }

    public SeekBar.OnSeekBarChangeListener getPercentageChangeListener(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentage = 0.01 * i;
                notifyPropertyChanged(uk.me.peteharris.heartratecalculator.BR.percentage);
                notifyPropertyChanged(uk.me.peteharris.heartratecalculator.BR.heartRate);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    public TextWatcher getMaxHrChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    maxHr = Integer.valueOf(editable.toString());
                    notifyPropertyChanged(uk.me.peteharris.heartratecalculator.BR.heartRate);
                } catch (java.lang.NumberFormatException e){

                }
            }
        };
    }
    public TextWatcher getRestingHrChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    restingHr = Integer.valueOf(editable.toString());
                    notifyPropertyChanged(uk.me.peteharris.heartratecalculator.BR.heartRate);
                } catch (java.lang.NumberFormatException e){

                }
            }
        };
    }

}
