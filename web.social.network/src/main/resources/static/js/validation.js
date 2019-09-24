$(document).ready(function () {
    // registration selectors
    var register_email = ".register_email";
    var register_password = ".register_password";
    var register_password_confirm = ".register_password_confirm";
    var register_submit = ".register_submit";
    var error = "_error";
    var register_email_error = register_email + error;
    var register_password_error = register_password + error;
    var register_password_confirm_error = register_password_confirm + error;
    var $register_email = $(register_email);
    var $register_password = $(register_password);
    var $register_password_confirm = $(register_password_confirm);
    var $register_email_error = $(register_email_error);
    var $register_password_error = $(register_password_error);
    var $register_password_confirm_error = $(register_password_confirm_error);
    var $register_submit = $(register_submit);

    // login selectors
    var $login_email = $(".login_email");
    var $login_password = $(".login_password");
    var $login_submit = $(".login_submit");

    $register_email.on("change", function () {
        showError($register_email, checkEmail($register_email), $register_email_error, $register_submit);
    });
    $register_password.on("change", function () {
        showError($register_password, checkPassword($register_password), $register_password_error, $register_submit);
        showError($register_password_confirm, checkPasswordConf($register_password, $register_password_confirm), $register_password_confirm_error, $register_submit);
    });
    $register_password_confirm.on("change", function () {
        showError($register_password_confirm, checkPasswordConf($register_password, $register_password_confirm), $register_password_confirm_error, $register_submit);
    });
    $register_submit.on("click", function (e) {
        var emailTest = checkEmail($register_email);
        var passwordTest = checkPassword($register_password);
        var passwordConfTest = checkPasswordConf($register_password, $register_password_confirm);
        if(!emailTest || !passwordTest || !passwordConfTest) {
            e.preventDefault();
            showError($register_email, emailTest, $register_email_error, $register_submit);
            showError($register_password, passwordTest, $register_password_error, $register_submit);
            showError($register_password_confirm, passwordConfTest, $register_password_confirm_error, $register_submit);
            $register_submit.prop('disabled', true);
        }
    });

    $login_email.on("change", function () {
        highlightField($login_email, checkEmail($login_email), $login_submit);
    });
    $login_password.on("change", function () {
        highlightField($login_password, checkPassword($login_password), $login_submit);
    });
    $login_submit.on("click", function (e) {
        var emailTest = checkEmail($login_email);
        var passwordTest = checkPassword($login_password);
        if(!emailTest || !passwordTest) {
            e.preventDefault();
            highlightField($login_email, checkEmail($login_email), $login_submit);
            highlightField($login_password, checkPassword($login_password), $login_submit);
            $login_submit.prop('disabled', true);
        }
    });

});
function showError(element, result, error, submit) {
    submit.prop('disabled', !result);
    if (!result) {
        error.removeClass("hidden");
        element.closest("div.form-group").addClass("has-error");
    } else {
        error.addClass("hidden");
        element.closest("div.form-group").removeClass("has-error");
    }
}
function highlightField(element, result, submit) {
    submit.prop('disabled', !result);
    if (!result) {
        element.closest("div.form-group").addClass("has-error");
    } else {
        element.closest("div.form-group").removeClass("has-error");
    }
}
function checkEmail($element) {
    var value = $element.val();
    var emailRegexp = /^[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_]+\.([.]*[a-zA-Z0-9-_]+)+$/;
    return emailRegexp.test(value);
}
function checkPassword($element) {
    var password = $element.val();
    var passwordRegexp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.{6,})/;
    return passwordRegexp.test(password);
}

function checkPasswordConf($password, $confirmation) {
    var conf = $confirmation.val();
    var password = $password.val();
    return conf === password;
}