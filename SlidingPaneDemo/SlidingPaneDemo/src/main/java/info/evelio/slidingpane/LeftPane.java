package info.evelio.slidingpane;

import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.widget.AbsListView.CHOICE_MODE_NONE;
import static android.widget.AbsListView.CHOICE_MODE_SINGLE;

public class LeftPane extends ListFragment {

  private OnItemClickListener mOnItemClickListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    mOnItemClickListener = (OnItemClickListener) activity;
  }

  @Override
  public void onDetach() {
    super.onDetach();

    mOnItemClickListener = null;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    final Resources res = getResources();
    final String[] items = res.getStringArray(R.array.left_pane_items);
    setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, items));


    getListView().setChoiceMode(res.getBoolean(R.bool.dual_pane) ? CHOICE_MODE_SINGLE : CHOICE_MODE_NONE);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);

    informListener(position);
  }

  public void setCurrentItem(int position) {
    setSelection(position);
    getListView().setItemChecked(position, true);
    informListener(position);
  }

  private void informListener(int position) {
    mOnItemClickListener.onItemClick((String) getListAdapter().getItem(position));
  }

  public interface OnItemClickListener {
    void onItemClick(String item);
  }

}
