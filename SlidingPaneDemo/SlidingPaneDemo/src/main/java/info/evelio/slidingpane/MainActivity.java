package info.evelio.slidingpane;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

  private SlidingPaneLayout mPaneLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupActionBar(true);

    mPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane);
    mPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
      @Override
      public void onPanelSlide(View view, float v) {
      }

      @Override
      public void onPanelOpened(View view) {
        setupActionBar(false);
      }

      @Override
      public void onPanelClosed(View view) {
        setupActionBar(true);
      }
    });

    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }
  }

  private void setupActionBar(boolean closed) {
    final ActionBar actionBar = getActionBar();
    if (getResources().getBoolean(R.bool.dual_pane)) {
      actionBar.setHomeButtonEnabled(false);
      closed = false;
    }
    actionBar.setDisplayHomeAsUpEnabled(closed);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.action_settings:
        return true;
      case android.R.id.home:
        if (mPaneLayout.isOpen()) {
          mPaneLayout.closePane();
        } else {
          mPaneLayout.openPane();
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

}
