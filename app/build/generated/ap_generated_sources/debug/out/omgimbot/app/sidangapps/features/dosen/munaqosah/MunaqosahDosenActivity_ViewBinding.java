// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.dosen.munaqosah;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import omgimbot.app.sidangapps.R;

public class MunaqosahDosenActivity_ViewBinding implements Unbinder {
  private MunaqosahDosenActivity target;

  @UiThread
  public MunaqosahDosenActivity_ViewBinding(MunaqosahDosenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MunaqosahDosenActivity_ViewBinding(MunaqosahDosenActivity target, View source) {
    this.target = target;

    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_default_in, "field 'mToolbar'", Toolbar.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MunaqosahDosenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mRecyclerView = null;
  }
}
