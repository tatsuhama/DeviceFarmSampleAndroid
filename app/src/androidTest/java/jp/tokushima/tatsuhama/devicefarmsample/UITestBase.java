package jp.tokushima.tatsuhama.devicefarmsample;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Robotium のテストを書きやすくする基底クラス
 * Created by tatsuhama on 16/01/17.
 */
public class UITestBase<T extends AppCompatActivity> extends ActivityInstrumentationTestCase2<T> {

    private static final int PROGRESS_TIMEOUT = 60000; //60s
    private static final int CHECK_TIME = 100; // 100ms;

    Solo mSolo;
    AppCompatActivity mActivity;

    public UITestBase(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mSolo = new Solo(getInstrumentation(), mActivity);
    }

    /**
     * 指定された文字列の表示状態を確認する
     *
     * @param resId 文字列 ID
     */
    void checkSearchText(int resId) {
        String text = getString(resId);
        String msg = String.format("checkSearchText(%s)", text);
        assertTrue(msg, mSolo.searchText(text));
    }

    /**
     * @param resId 文字列 ID
     * @return リソース文字列
     */
    private String getString(final int resId) {
        return mActivity.getString(resId);
    }

    /**
     * EditTextに文字列を設定する
     *
     * @param editId EditText の ID
     * @param text   文字列
     */
    void setText(final int editId, final String text) {
        EditText editText = (EditText) mSolo.getView(editId);
        mSolo.clearEditText(editText);
        mSolo.enterText(editText, text);
    }

    /**
     * 指定した View をクリックする
     *
     * @param viewId View の ID
     */
    void clickView(final int viewId) {
        mSolo.clickOnView(mSolo.getView(viewId));
    }

    /**
     * プログレスが非表示になるまで待つ
     *
     * @param progressId プログレスの ViewId
     */
    void waitProgress(final int progressId) {
        View progress = mActivity.findViewById(progressId);
        int max = PROGRESS_TIMEOUT / CHECK_TIME;
        for (int i = 0; i < max; i++) {
            sleep(CHECK_TIME);
            if (progress.getVisibility() == View.GONE) {
                break;
            }
        }
        sleep(CHECK_TIME); // 念のため…
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
