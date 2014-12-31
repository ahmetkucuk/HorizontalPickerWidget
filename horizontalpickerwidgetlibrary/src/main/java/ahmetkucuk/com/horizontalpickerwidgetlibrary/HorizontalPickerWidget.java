package ahmetkucuk.com.horizontalpickerwidgetlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * Created by ahmetkucuk on 31/12/14.
 */
public class HorizontalPickerWidget extends LinearLayout implements TextView.OnClickListener{

    private TextView leftTextView;
    private TextView rightTextView;
    private TextSwitcher switcher;
    private Context context;
    private String[] values = new String[] {};
    private int currentPosition = 0;

    public HorizontalPickerWidget(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public HorizontalPickerWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public HorizontalPickerWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(context);
    }

    public void init(Context context) {

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.horizontal_picker_layout, this, true);

        leftTextView = (TextView)getChildAt(0);
        rightTextView = (TextView)getChildAt(2);
        switcher = (TextSwitcher)getChildAt(1);

        Animation in = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_in);
        switcher.setInAnimation(in);
//        Animation out = AnimationUtils.loadAnimation(context,
//                android.R.anim.slide_out_right);
        //switcher.setOutAnimation(out);
        switcher.setFactory(mFactory);

        setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeLeft() {
                setCurrentPosition(currentPosition + 1);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                setCurrentPosition(currentPosition - 1);
            }
        });

        leftTextView.setOnClickListener(this);
        rightTextView.setOnClickListener(this);

        setCurrentPosition(0);
    }

    public void setValues(String[] v){
        values = v;
        setCurrentPosition(0);
    }

    public void setCurrentPosition(int position) {
        if(position >= values.length  || position < 0)
            return;
        if(position == 0) {
            leftTextView.setText("");
            if(position < values.length - 1) {
                rightTextView.setText(values[position+1]);
                switcher.setText(values[position]);
            }

        } else if( position == values.length - 1){

            leftTextView.setText(values[position-1]);
            rightTextView.setText("");
            switcher.setText(values[position]);
        } else {
            leftTextView.setText(values[position-1]);
            rightTextView.setText(values[position+1]);
            switcher.setText(values[position]);
        }
        currentPosition = position;
    }

    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(context);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(context, android.R.style.TextAppearance_Large);
            t.setMaxLines(1);
            return t;
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId() == leftTextView.getId()) {
            setCurrentPosition(currentPosition - 1);
        } else if(v.getId() == rightTextView.getId()) {

            setCurrentPosition(currentPosition + 1);
        }
    }
}
