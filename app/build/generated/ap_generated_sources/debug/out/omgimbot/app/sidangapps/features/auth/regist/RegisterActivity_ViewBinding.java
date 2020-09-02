// Generated code from Butter Knife. Do not modify!
package omgimbot.app.sidangapps.features.auth.regist;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import omgimbot.app.sidangapps.R;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.mSpinner = Utils.findRequiredViewAsType(source, R.id.mSpinner, "field 'mSpinner'", Spinner.class);
    target.mFakultas = Utils.findRequiredViewAsType(source, R.id.mFakultas, "field 'mFakultas'", Spinner.class);
    target.mProdi = Utils.findRequiredViewAsType(source, R.id.mProdi, "field 'mProdi'", Spinner.class);
    target.mListDosen = Utils.findRequiredViewAsType(source, R.id.mListDosen, "field 'mListDosen'", Spinner.class);
    target.layoutNIDN = Utils.findRequiredViewAsType(source, R.id.layoutNIDN, "field 'layoutNIDN'", LinearLayout.class);
    target.layoutNPM = Utils.findRequiredViewAsType(source, R.id.layoutNPM, "field 'layoutNPM'", LinearLayout.class);
    target.layoutFakultas = Utils.findRequiredViewAsType(source, R.id.layoutFakultas, "field 'layoutFakultas'", LinearLayout.class);
    target.layoutProdi = Utils.findRequiredViewAsType(source, R.id.layoutProdi, "field 'layoutProdi'", LinearLayout.class);
    target.layoutDosenBimbing = Utils.findRequiredViewAsType(source, R.id.layoutDosenBimbing, "field 'layoutDosenBimbing'", LinearLayout.class);
    target.mSubmit = Utils.findRequiredViewAsType(source, R.id.mSubmit, "field 'mSubmit'", Button.class);
    target.mNama = Utils.findRequiredViewAsType(source, R.id.mNama, "field 'mNama'", EditText.class);
    target.mNpm = Utils.findRequiredViewAsType(source, R.id.mNpm, "field 'mNpm'", EditText.class);
    target.mNidn = Utils.findRequiredViewAsType(source, R.id.mNidn, "field 'mNidn'", EditText.class);
    target.mAlamat = Utils.findRequiredViewAsType(source, R.id.mAlamat, "field 'mAlamat'", EditText.class);
    target.mPassword = Utils.findRequiredViewAsType(source, R.id.mPassword, "field 'mPassword'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSpinner = null;
    target.mFakultas = null;
    target.mProdi = null;
    target.mListDosen = null;
    target.layoutNIDN = null;
    target.layoutNPM = null;
    target.layoutFakultas = null;
    target.layoutProdi = null;
    target.layoutDosenBimbing = null;
    target.mSubmit = null;
    target.mNama = null;
    target.mNpm = null;
    target.mNidn = null;
    target.mAlamat = null;
    target.mPassword = null;
  }
}
