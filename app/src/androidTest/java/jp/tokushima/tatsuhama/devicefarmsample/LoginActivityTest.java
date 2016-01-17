package jp.tokushima.tatsuhama.devicefarmsample;

/**
 * LoginActivity のテスト（全て成功）
 * Created by tatsuhama on 16/01/17.
 */
public class LoginActivityTest extends UITestBase<LoginActivity> {

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    public void testSuccess() {
        clickSigninButton();
        checkSearchText(R.string.error_field_required);

        setEmail("a");
        clickSigninButton();
        checkSearchText(R.string.error_invalid_email);

        setEmail("foo@example.com");
        clickSigninButton();
        waitProgress(R.id.login_progress);
        checkSearchText(R.string.error_incorrect_password);
    }

    private void setEmail(String email) {
        setText(R.id.email, email);
    }

    private void clickSigninButton() {
        clickView(R.id.email_sign_in_button);
    }
}
