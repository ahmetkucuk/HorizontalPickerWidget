package ahmetkucuk.com.horizontalpickerwidgetlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import ahmetkucuk.com.horizontalpickerwidgetlibrary.adapter.DataSetChangeListener;
import ahmetkucuk.com.horizontalpickerwidgetlibrary.adapter.HorizontalPickerAdapter;

/**
 * Created by ahmetkucuk on 31/12/14.
 */
public class HorizontalPickerWidget extends LinearLayout implements TextView.OnClickListener, DataSetChangeListener {

    private TextView leftTextView;
    private TextView rightTextView;
    private TextSwitcher switcher;
    private Context context;
    private HorizontalPickerAdapter adapter;
    private int currentPosition = 0;
    private OnClickListener onClickListener;

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

    public void setAdapter(HorizontalPickerAdapter adapter) {
        this.adapter = adapter;
        adapter.registerAdapter(this);
        setCurrentPosition(0);
    }

    public HorizontalPickerAdapter getAdapter() {return this.adapter;}

    public Object getSelectedItem() {
        return adapter.getItem(currentPosition);
    }

    public void setOnPickedListener(OnClickListener listener) {
        this.onClickListener = listener;
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
//        switcher.setOnTouchListener(onSwipeTouchListener);

        setOnTouchListener(onSwipeTouchListener);

        leftTextView.setOnClickListener(this);
        rightTextView.setOnClickListener(this);
    }

    OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(context) {
        @Override
        public void onSwipeLeft() {
            setCurrentPosition(currentPosition + 1);
        }

        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
            setCurrentPosition(currentPosition - 1);
        }

        @Override
        public void onClick() {
            super.onClick();
            onClickListener.onClick(switcher);
        }
    };

    public void setCurrentPosition(int position) {
        if(position >= adapter.getCount() || position < 0)
            return;
        switcher.setText(adapter.getItemText(position));
        switcher.setTag(adapter.getItem(position));

        if(position == 0) {
            leftTextView.setText("");
            if(position < adapter.getCount() - 1) {
                rightTextView.setText(adapter.getItemText(position + 1));
            }

        } else if( position == adapter.getCount() - 1){

            leftTextView.setText(adapter.getItemText(position-1));
            rightTextView.setText("");
        } else {
            leftTextView.setText(adapter.getItemText(position-1));
            rightTextView.setText(adapter.getItemText(position + 1));
        }

        currentPosition = position;
    }

    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {

            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView textView = (TextView)mInflater.inflate(R.layout.switcher_textview_layout, null);
            // Create a new TextView
//            TextView t = new TextView(context);
//            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
//            t.setTextAppearance(context, android.R.style.TextAppearance_Large);
//            t.setTextSize(24);
//            t.setMaxLines(1);
//            t.setTextColor(Color.WHITE);
            return textView;
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

    @Override
    public void onDataSetChanged() {
        setCurrentPosition(0);
    }
}
