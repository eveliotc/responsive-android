package info.evelio.slidingpane;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements LeftPane.OnItemClickListener {

  private static final int INITIAL_ITEM_POSITION = 0;
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

  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    if (savedInstanceState == null) {
      final LeftPane leftPane = (LeftPane) getFragmentManager().findFragmentById(R.id.left_pane);
      leftPane.setCurrentItem(INITIAL_ITEM_POSITION);
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

  @Override
  public void onItemClick(String item) {
    mPaneLayout.closePane();

    getFragmentManager().beginTransaction()
        .replace(R.id.container, ContentFragment.newInstance(item))
        .commit();
  }

}
