

/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* 
 
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 1/10/2022      2.0                LinhNT           First Implement
 

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

// validate cho tr?????ng Full name
Validator.isRequired = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            //chi ch???a ch??? c??i v?? 5 t???
            var regex = /^(?![\s.]+$)[a-zA-Z\s.]*$/;
            if (regex.test(value) && value.toString().length > 0) {
                return undefined;
            } else
            {
                return 'Full name kh??ng ch???a k?? t??? ?????c bi???t, t???i ??a l?? 5 t???';
            }
        }
    };

};//validate email
Validator.isEmail = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            //ph???i c?? .com m???i l?? email h???p l???
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})*$/;
            if (regex.test(value)) {
                return undefined;
            } else
            {
                return "Tr?????ng n??y ph???i l?? email vd:abc@gmail.com";
            }
        }
    };

};// validate s??? ??i???n tho???i
Validator.isPhone = function (selector) {
    return{
        selector: selector,
        test: function (value) {
            //ph???i c?? 0 ho???c +84 sau 2 s??? ???? s??? l?? 9 s???
            var regex = /^(0|\+84)(\s|\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\d)(\s|\.)?(\d{3})(\s|\.)?(\d{3})*$/;
            if (regex.test(value)) {
                return undefined;
            } else
            {
                return "S??? ??i???n tho???i ph???i b???t ?????u s??? 0 ho???c 84, v?? 10 ch?? s???";
            }
        }
    };
};
// validate ?????a ch???
Validator.addressMaxLength = function (selector) {
    return{
        selector: selector,
        test: function (value) {
            // ch??? c??i v?? s??? ch??? ch???a 30 k?? t???
            var regex = /^[a-zA-Z0-9 ]*$/;
            //kh??ng v?????t 30 k?? t??? v?? k ????ng validate ho???c ch??? nh???p d???u c??ch ??? d??ud s??? tr??? v??? ????ng
            if (value.toString().length < 30 && regex.test(value)) {
                return undefined;
            } else
            {
                //sai s??? tr??? ra th??ng b??o
                return "?????a ch??? kh??ng ???????c ch???a k?? t??? ????c bi???t v?? ch??? ???????c 40 k?? t???";
            }
        }
    };
};




        