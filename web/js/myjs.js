/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function onSubmitCreateAccountForm() {
    var email = document.forms["signUpForm"]["email"].value;
    var password = document.forms["signUpForm"]["password"].value;
    var confirm = document.forms["signUpForm"]["confirm"].value;
    var name = document.forms["signUpForm"]["name"].value;
    var phone = document.forms["signUpForm"]["phone"].value;
    var address = document.forms["signUpForm"]["address"].value;
    var check = true;
    if (email.trim().length < 6 || email.trim().length > 50) {
        document.getElementById("emailId").innerHTML = 'Email contain 6 to 50 chars';
        check = false;
    } else {
        document.getElementById("emailId").innerHTML = '';
    }

    if (password.trim().length < 6 || password.trim().length > 50) {
        document.getElementById("passwordId").innerHTML = 'Password contain 6 to 50 chars';
        check = false;
    } else {
        document.getElementById("passwordId").innerHTML = '';
    }

    if (confirm != password) {
        document.getElementById("confirmId").innerHTML = 'Confirm is not matched with Password';
        check = false;
    } else {
        document.getElementById("confirmId").innerHTML = '';
    }

    if (name.trim().length < 2 || name.trim().length > 100) {
        document.getElementById("nameId").innerHTML = 'Name contain 2 to 100 chars';
        check = false;
    } else {
        document.getElementById("nameId").innerHTML = '';
    }

    if (address.trim().length < 6 || address.trim().length > 100) {
        document.getElementById("addressId").innerHTML = 'Address contain 6 to 100 chars';
        check = false;
    } else {
        document.getElementById("addressId").innerHTML = '';
    }
    var phoneFormat = /^\d+$/;
    if (!phoneFormat.test(phone)) {
        document.getElementById("phoneId").innerHTML = 'Phone is not valid';
        check = false;
    } else {
        document.getElementById("phoneId").innerHTML = '';
    }
    if (phone.length < 6 || phone.length > 100) {
        document.getElementById("phoneId").innerHTML = 'Phone is not valid';
        check = false;
    } else {
        document.getElementById("phoneId").innerHTML = '';
    }
    return check;
}
function onSubmitUpdateCarForm() {
    var carName = document.forms["updateCar"]["carName"].value;
    var price = document.forms["updateCar"]["price"].value;
    var color = document.forms["updateCar"]["color"].value;
    var check = true;
    if (carName.trim().length < 2 || carName.trim().length > 50) {
        document.getElementById("carName").innerHTML = 'CarName contain 2 to 50 chars';
        check = false;
    } else {
        document.getElementById("carName").innerHTML = '';
    }
    if (color.trim().length < 2 || color.trim().length > 20) {
        document.getElementById("colorId").innerHTML = 'color contain 2 to 20 chars';
        check = false;
    } else {
        document.getElementById("colorId").innerHTML = '';
    }
    price = parseFloat(price);
    if (isNaN(price)) {
        document.getElementById("priceId").innerHTML = 'Price is not a number';
        check = false;
    } 
    if (price <= 0) {
        document.getElementById("priceId").innerHTML = 'Price is must be greater than 0';
        check = false;
    } else {
        document.getElementById("priceId").innerHTML = '';
    }
    return check;
}
function onSubmitCreateCarForm() {
    var carName = document.forms["creteaNewCar"]["carName"].value;
    var price = document.forms["creteaNewCar"]["price"].value;
    var color = document.forms["creteaNewCar"]["color"].value;
    var check = true;
    if (carName.trim().length < 2 || carName.trim().length > 50) {
        document.getElementById("carNameId").innerHTML = 'CarName contain 2 to 50 chars';
        check = false;
    } else {
        document.getElementById("carNameId").innerHTML = '';
    }
    if (color.trim().length < 2 || color.trim().length > 50) {
        document.getElementById("colorId").innerHTML = 'color contain 2 to 50 chars';
        check = false;
    } else {
        document.getElementById("colorId").innerHTML = '';
    }
    price = parseFloat(price);
    if (isNaN(price)) {
        document.getElementById("priceId").innerHTML = 'Price is not a number';
        check = false;
    } else {
        if (price <= 0) {
            document.getElementById("priceId").innerHTML = 'Price is must be greater than 0';
            check = false;
        } else {
            document.getElementById("priceId").innerHTML = '';
        }
    }
    return check;
}

function onSubmitSearchForm() {
    var dateFrom = document.forms["searchForm"]["dateFrom"].value;
    var dateTo = document.forms["searchForm"]["dateTo"].value;

    if ((dateFrom === '' && dateTo !== '') || (dateFrom !== '' && dateTo === '')) {
        alert('Date From and Date To need to filled together');
        return false;
    } else if (dateFrom !== '' && dateTo !== '') {
        var From = parseDate(dateFrom).getTime();
        var To = parseDate(dateTo).getTime();
        if (From > To) {
            alert('Rental date can not be greater than Return date');
            return false;
        }
    }
    return true;
}
function cannotDecrease() {
    var number = document.forms["changeNumberOfCardForm"]["curNumber"].value;
    if (number <= 1) {
        return false;
    }
    return true;
}
function parseDate(str) {
    var date = str.split('-');
    return new Date(date[0], date[1], date[2]);
}
function onFeedback() {
    var rating = document.forms["sendFeedback"]["rating"].value;
    var description = document.forms["sendFeedback"]["description"].value;
    if (rating.length === 0 && description.length === 0) {
        return false;
    }
    return true;
}
function onSubmitFormDeleteOrderHistory() {
    var status = document.forms["formDeleteHistory"]["status"].value;
    if (status === 'inactive') {
        return false;
    }
    return true;
}