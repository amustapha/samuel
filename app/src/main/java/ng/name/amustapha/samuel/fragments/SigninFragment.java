package ng.com.piper.warehouse.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ng.com.piper.warehouse.R;
import ng.com.piper.warehouse.utilities.Constants;
import ng.com.piper.warehouse.utilities.Preferences;


public class SigninFragment extends Fragment implements View.OnClickListener{
    View view;
    String pin = "";
    LinearLayout dots;
    ImageView padlock;
    TextView message;
    boolean reject = false;

    public SigninFragment() {}

    public static SigninFragment newInstance(int target) {

        Bundle args = new Bundle();
        args.putInt("target", target);
        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onClick(View view) {
        if (reject || pin.length() >= 4){
            return;
        }
        switch (view.getId()){
            case R.id.dig_1:
                add(1);
                break;

            case R.id.dig_2:
                add(2);
                break;

            case R.id.dig_3:
                add(3);
                break;

            case R.id.dig_4:
                add(4);
                break;

            case R.id.dig_5:
                add(5);
                break;

            case R.id.dig_6:
                add(6);
                break;

            case R.id.dig_7:
                add(7);
                break;

            case R.id.dig_8:
                add(8);
                break;

            case R.id.dig_9:
                add(9);
                break;

            case R.id.dig_0:
                add(0);
                break;

            default:
                reset();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        setListeners(R.id.dig_0, R.id.dig_1, R.id.dig_2, R.id.dig_3, R.id.dig_4, R.id.dig_5, R.id.dig_6, R.id.dig_7, R.id.dig_8, R.id.dig_9, R.id.dig_0, R.id.clr);
        dots = view.findViewById(R.id.dots);
        padlock = view.findViewById(R.id.padlock);
        message = view.findViewById(R.id.message);
    }

    public void add(int n){
        pin += n;
        markDots();

        if (pin.length() >= 4){
            verify();
        }
    }

    public void reset(){
        if (pin != null && pin.length() > 0 ) {
            pin = pin.substring(0, pin.length() - 1);
        }
        markDots();
    }

    public void verify(){
        reject = true;
        if(pin.equals(Preferences.getInstance(view.getContext()).get("password"))){
            padlock.setImageResource(R.drawable.ic_unlock);
            message.setText(R.string.pin_success);
            message.setTextColor(Color.GREEN);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            Constants.getAction(getArguments().getInt("target"), view.getContext(), getFragmentManager()).run();
        }else{
            Animation anim = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    reject = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            padlock.startAnimation(anim);

            pin = "";
            markDots();
            message.setText(R.string.pin_error);
            message.setTextColor(Color.RED);
        }
    }

    public void markDots(){
        for (int i = 0; i < dots.getChildCount(); i++) {
            ((ImageView) dots.getChildAt(i)).setImageResource(i >= pin.length() ? R.drawable.pin_default : R.drawable.pin_toggle);
        }
    }

    public void setListeners(int... ids){
        for (int id : ids
             ) {
            view.findViewById(id).setOnClickListener(this);
        }
    }
}
