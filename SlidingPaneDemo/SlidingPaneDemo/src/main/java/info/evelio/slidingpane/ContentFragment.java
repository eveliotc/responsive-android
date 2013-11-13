package info.evelio.slidingpane;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContentFragment extends Fragment {

  private static final String ARG_TEXT = "info.evelio.slidingpane.arg.TEXT";

  public ContentFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_content, container, false);

    final TextView textView = (TextView) rootView.findViewById(R.id.content_text);
    textView.setText(getArguments().getString(ARG_TEXT));
    return rootView;
  }

  public static Fragment newInstance(String item) {
    final Fragment fragment = new ContentFragment();
    fragment.setArguments(args(item));
    return fragment;
  }

  private static Bundle args(String text) {
    Bundle args = new Bundle(1);
    args.putString(ARG_TEXT, text);
    return args;
  }
}