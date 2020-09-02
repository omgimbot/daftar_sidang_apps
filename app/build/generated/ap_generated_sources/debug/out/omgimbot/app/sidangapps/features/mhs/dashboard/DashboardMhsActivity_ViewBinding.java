// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.mhs.dashboard;

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

public class DashboardMhsActivity_ViewBinding implements Unbinder {
  private DashboardMhsActivity target;

  private View view7f0a00bf;

  private View view7f0a00c2;

  private View view7f0a00c0;

  private View view7f0a00c3;

  private View view7f0a00c1;

  private View view7f0a0056;

  private View view7f0a0057;

  @UiThread
  public DashboardMhsActivity_ViewBinding(DashboardMhsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DashboardMhsActivity_ViewBinding(final DashboardMhsActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.mCardSempro, "field 'mCardSempro' and method 'goToSempro'");
    target.mCardSempro = Utils.castView(view, R.id.mCardSempro, "field 'mCardSempro'", CardView.class);
    view7f0a00c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToSempro();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardMunaqosah, "field 'mCardMunaqosah' and method 'goToMunaqosah'");
    target.mCardMunaqosah = Utils.castView(view, R.id.mCardMunaqosah, "field 'mCardMunaqosah'", CardView.class);
    view7f0a00c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToMunaqosah();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardkompre, "field 'mCardkompre' and method 'goTokompre'");
    target.mCardkompre = Utils.castView(view, R.id.mCardkompre, "field 'mCardkompre'", CardView.class);
    view7f0a00c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goTokompre();
      }
    });
    view = Utils.findRequiredView(source, R.id.mCardPengumuman, "method 'pengumuman'");
    view7f0a00c1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.pengumuman();
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
    DashboardMhsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDrawerView = null;
    target.mainMenuDashboard = null;
    target.toolbarMain = null;
    target.mCardJudul = null;
    target.mCardSempro = null;
    target.mCardMunaqosah = null;
    target.mCardkompre = null;

    view7f0a00bf.setOnClickListener(null);
    view7f0a00bf = null;
    view7f0a00c2.setOnClickListener(null);
    view7f0a00c2 = null;
    view7f0a00c0.setOnClickListener(null);
    view7f0a00c0 = null;
    view7f0a00c3.setOnClickListener(null);
    view7f0a00c3 = null;
    view7f0a00c1.setOnClickListener(null);
    view7f0a00c1 = null;
    view7f0a0056.setOnClickListener(null);
    view7f0a0056 = null;
    view7f0a0057.setOnClickListener(null);
    view7f0a0057 = null;
  }
}
