package com.doudou.cn.xuanfukuang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.doudou.cn.testtrainplanassetpro.R;

/**
 * Created by jinliang on 18-3-15.
 */

public class TestXuanFuWindowActivity extends Activity {

    private static final String TAG = TestXuanFuWindowActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView testXuanFuWindow = (TextView) findViewById(R.id.btn_get_data) ;

//        String result = getString(R.string.test_html_txt) ;

        String result = String.format(getResources().getString(R.string.test_html_txt)
                ,"00","30","3000");
        Log.i(TAG," spanableString:"+ result);
        SpannableString msp = new SpannableString(result);


        // 00
        msp.setSpan(new RelativeSizeSpan(1.0f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // min 2-6
        msp.setSpan(new TypefaceSpan("RobotoCondensed-Regular"), 2, 6,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff00ff")), 2,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new AbsoluteSizeSpan(18), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        // 00
        msp.setSpan(new RelativeSizeSpan(1.0f), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // sec
        msp.setSpan(new TypefaceSpan("RobotoCondensed-Regular"), 8, 12,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff00ff")), 8,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new AbsoluteSizeSpan(18), 8, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        msp.setSpan(new RelativeSizeSpan(1.0f), 12, result.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        testXuanFuWindow.setText(Html.fromHtml(msp.toString()));
        testXuanFuWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(new Intent(TestXuanFuWindowActivity.this,WindowService.class));
            }
        });

    }
}
