// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.mhs.sempro;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import omgimbot.app.sidangapps.R;

public class SemproMhsActivity_ViewBinding implements Unbinder {
  private SemproMhsActivity target;

  private View view7f0a00d5;

  private View view7f0a010b;

  @UiThread
  public SemproMhsActivity_ViewBinding(SemproMhsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SemproMhsActivity_ViewBinding(final SemproMhsActivity target, View source) {
    this.target = target;

    View view;
    target.mNama = Utils.findRequiredViewAsType(source, R.id.mNama, "field 'mNama'", TextView.class);
    target.mNim = Utils.findRequiredViewAsType(source, R.id.mNim, "field 'mNim'", TextView.class);
    target.mProdi = Utils.findRequiredViewAsType(source, R.id.mProdi, "field 'mProdi'", TextView.class);
    target.mFakultas = Utils.findRequiredViewAsType(source, R.id.mFakultas, "field 'mFakultas'", TextView.class);
    target.mJudul = Utils.findRequiredViewAsType(source, R.id.mJudul, "field 'mJudul'", EditText.class);
    target.mFile = Utils.findRequiredViewAsType(source, R.id.mFile, "field 'mFile'", TextView.class);
    view = Utils.findRequiredView(source, R.id.mChosefile, "field 'mChosefile' and method 'choseFile'");
    target.mChosefile = Utils.castView(view, R.id.mChosefile, "field 'mChosefile'", ImageButton.class);
    view7f0a00d5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.choseFile();
      }
    });
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_default_in, "field 'mToolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.mSubmit, "method 'uploadFile'");
    view7f0a010b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.uploadFile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SemproMhsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mNama = null;
    target.mNim = null;
    target.mProdi = null;
    target.mFakultas = null;
    target.mJudul = null;
    target.mFile = null;
    target.mChosefile = null;
    target.mToolbar = null;

    view7f0a00d5.setOnClickListener(null);
    view7f0a00d5 = null;
    view7f0a010b.setOnClickListener(null);
    view7f0a010b = null;
  }
}
