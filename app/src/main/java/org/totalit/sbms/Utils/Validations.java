package org.totalit.sbms.Utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

public class Validations {

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
