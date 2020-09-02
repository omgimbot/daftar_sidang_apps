// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.dosen.dashboard;

import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mindorks.placeholderview.PlaceHolderView;
import java.lang.IllegalStateException;
import java.lang.Override;
import omgimbot.app.sidangapps.R;

public class DashboardDosenActivity_ViewBinding implements Unbinder {
  private DashboardDosenActivity target;

  private View view7f0a00bf;

  private View view7f0a00c2;

  private View view7f0a00c0;

  private View view7f0a00c3;

  private View view7f0a0056;

  private View view7f0a0057;

  @UiThread
  public DashboardDosenActivity_ViewBinding(DashboardDosenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DashboardDosenActivity_ViewBinding(final DashboardDosenActivity target, View source) {
    this.target = target;

    View view;
    target.mDrawerView = Utils.findRequiredViewAsType(source, R.id.drawerView, "field 'mDrawerView'", PlaceHolderView.class);
    target.mainMenuDashboard = Utils.findRequiredViewAsType(source, R.id.mainMenu, "field 'mainMenuDashboard'", ImageButton.class);
    target.toolbarMain = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbarMain'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.mCardJudul, "field 'mCardJudul' and method 'goToJudul'");
    target.mCardJudul = Utils.castView(view, R.id.mCardJudul, "field 'mCardJudul'", CardView.class);
    view7f0a00bf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToJudul();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardSempro, "method 'goToSempro'");
    view7f0a00c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToSempro();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardMunaqosah, "method 'goToMunaqosah'");
    view7f0a00c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToMunaqosah();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardkompre, "method 'kompre'");
    view7f0a00c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.kompre();
      }
    });
    view = Utils.findRequiredView(source, R.id.cardInfoBeasiswa, "method 'cardInfoBeasiswa'");
    view7f0a0056 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cardInfoBeasiswa();
      }
    });
    view = Utils.findRequiredView(source, R.id.cardPortalInformasi, "method 'cardPortalInformasi'");
    view7f0a0057 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cardPortalInformasi();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DashboardDosenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDrawerView = null;
    target.mainMenuDashboard = null;
    target.toolbarMain = null;
    target.mCardJudul = null;

    view7f0a00bf.setOnClickListener(null);
    view7f0a00bf = null;
    view7f0a00c2.setOnClickListener(null);
    view7f0a00c2 = null;
    view7f0a00c0.setOnClickListener(null);
    view7f0a00c0 = null;
    view7f0a00c3.setOnClickListener(null);
    view7f0a00c3 = null;
    view7f0a0056.setOnClickListener(null);
    view7f0a0056 = null;
    view7f0a0057.setOnClickListener(null);
    view7f0a0057 = null;
  }
}
