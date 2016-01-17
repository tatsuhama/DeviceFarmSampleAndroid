package jp.tokushima.tatsuhama.devicefarmsample;

/**
 * LoginActivity のテスト（失敗）
 * Created by tatsuhama on 16/01/17.
 */
public class LoginActivityTestFail extends UITestBase<LoginActivity> {

    public LoginActivityTestFail() {
        super(LoginActivity.class);
    }

    public void testFail() {
        setText(R.id.email, "hoge");
        clickSigninButton();
        checkSearchText(R.string.error_field_required); // FAIL
    }

    private void clickSigninButton() {
        clickView(R.id.email_sign_in_button);
    }
}
