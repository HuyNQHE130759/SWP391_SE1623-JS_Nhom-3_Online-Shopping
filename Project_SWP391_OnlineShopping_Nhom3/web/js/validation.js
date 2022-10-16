/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var isFlag = 0;
function Validator(options) {
    function  validate(inputElement, rule) {
        var errorMessage = rule.test(inputElement.value);
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');

        } else {
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }

    }
// laays gia tri cuar form can validate
    var formElement = document.querySelector(options.form);
    // console.log(options.rules);
    if (formElement) {
        options.rules.forEach(function (rule) {
            // console.log(rule.selector);
            var inputElement = formElement.querySelector(rule.selector);

            if (inputElement) {
                inputElement.onblur = function () {
                    // console.log('blur' +rule.selector);
                    validate(inputElement, rule);
                    //console.log(inputElement.parentElement.querySelector('.form-message'););
                    var validateFullName = document.getElementById("validateFullName").textContent;
                    var validateAddress = document.getElementById("validateAddress").textContent;
                    var validatePhone = document.getElementById("validatePhone").textContent;
                    var validateEmail = document.getElementById("validateEmail").textContent;
                    if (validateFullName.length === 0 && validateAddress.length === 0
                            && validatePhone.length === 0 && validateEmail.length === 0) {
                        document.getElementById("changeProfile").disabled = false;
                    } else
                    {
                        document.getElementById("changeProfile").disabled = true;
                    }
                };
                inputElement.oninput = function () {
                    //console.log(inputElement.value);
                    errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid');
                };


            }
        });

    }
}


Validator.isRequired = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^[a-zA-Z ]*$/;
            if (regex.test(value) && value.toString().length > 0) {
                return undefined;
            } else
            {
                return 'Full name không chứa kí tự đặc biệt, tối đa là 5 từ';
            }
        }
    };

};
Validator.isEmail = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if (regex.test(value)) {
                return undefined;
            } else
            {
                return "Trường này phải là email";
            }
        }
    };

};
Validator.isPhone = function (selector) {
    return{
        selector: selector,
        test: function (value) {
            var regex = /^(0|\+84)(\s|\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\d)(\s|\.)?(\d{3})(\s|\.)?(\d{3})$/;
            if (regex.test(value)) {
                return undefined;
            } else
            {
                return "Số điện thoại phải bắt đầu số 0 hoặc 84, và 10 chũ số";
            }
        }
    };
};

Validator.addressMaxLength = function (selector) {
    return{
        selector: selector,
        test: function (value) {
            var regex = /^[a-zA-Z0-9 ]*$/;
            if (value.toString().length < 30 && regex.test(value) && value.toString().length > 0) {
                return undefined;
            } else
            {
                return "Địa chỉ không được chứa kí tự đăc biệt và chỉ được 40 kí tự";
            }
        }
    };
};




        