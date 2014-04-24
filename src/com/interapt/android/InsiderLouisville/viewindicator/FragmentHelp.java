package com.interapt.android.InsiderLouisville.viewindicator;



import com.interapt.android.InsiderLouisville.R;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public final class FragmentHelp extends Fragment implements OnClickListener{
	private static final String KEY_HEADING = "TestFragment:Heading";
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static FragmentHelp newInstance(int pos, String heading, String content) {
    	FragmentHelp fragment = new FragmentHelp();
    	fragment.mHeading = heading;
        fragment.mContent = content;//builder.toString();
        fragment.position = pos;

        return fragment;
    }

    private String mHeading = "???";
    private String mContent = "???";
    int position;
   private int[] images = {android.R.drawable.btn_minus,android.R.drawable.btn_plus,
		   android.R.drawable.btn_star_big_off,android.R.drawable.btn_star_big_on }; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
            
        }
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_HEADING)) {
        	mHeading = savedInstanceState.getString(KEY_HEADING);
            
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view;
		view = inflater.inflate(R.layout.fragment_submit_help, null);
		TextView txtMiddle = (TextView)view.findViewById(R.id.txtMiddle);
		TextView txtHeader = (TextView)view.findViewById(R.id.layoutHeader);
		txtMiddle.setText(mContent);
		txtMiddle.setMovementMethod(new ScrollingMovementMethod());
		txtHeader.setText(mHeading);
		return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
        outState.putString(KEY_HEADING, mHeading);
    }

	@Override
	public void onClick(View v) {
		getActivity().finish();
		
	}
}
