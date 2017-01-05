package com.example.taqtile.onboard_android;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Attr;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 1/4/17.
 */

public class DetailElement extends FrameLayout {

    @Bind(R.id.label)
    TextView label;

    @Bind(R.id.value)
    TextView value;

    public DetailElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    public void init(AttributeSet attrs) {

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DetailElement, 0, 0);
        String labelText = a.getString(R.styleable.DetailElement_label);
        a.recycle();

        LayoutInflater factory = LayoutInflater.from(getContext());
        final View view = factory.inflate(R.layout.detail_element_frame, this, true);

        ButterKnife.bind(this);
        setLabel(labelText);

    }

    public void setLabel(String labelText) {
        label.setText(labelText);
    }

    public void setValue(String valueText) {
        value.setText(valueText);
    }
}
