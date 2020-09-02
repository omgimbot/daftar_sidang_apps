// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.auth.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import omgimbot.app.sidangapps.R;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f0a013d;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.mBtnRegis = Utils.findRequiredViewAsType(source, R.id.mBtnRegis, "field 'mBtnRegis'", Button.class);
    view = Utils.findRequiredView(source, R.id.signin, "field 'signin' and method 'login'");
    target.signin = Utils.castView(view, R.id.signin, "field 'signin'", Button.class);
    view7f0a013d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    target.mUsername = Utils.findRequiredViewAsType(source, R.id.mUsername, "field 'mUsername'", EditText.class);
    target.mPassword = Utils.findRequiredViewAsType(source, R.id.mPassword, "field 'mPassword'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnRegis = null;
    target.signin = null;
    target.mUsername = null;
    target.mPassword = null;

    view7f0a013d.setOnClickListener(null);
    view7f0a013d = null;
  }
}
