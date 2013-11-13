package info.evelio.slidingpane;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import static android.widget.AbsListView.CHOICE_MODE_NONE;
import static android.widget.AbsListView.CHOICE_MODE_SINGLE;

public class LeftPane extends ListFragment {

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    final Resources res = getResources();
    final String[] items = res.getStringArray(R.array.left_pane_items);
    setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, items));


    getListView().setChoiceMode(res.getBoolean(R.bool.dual_pane) ? CHOICE_MODE_SINGLE : CHOICE_MODE_NONE);
  }
}
