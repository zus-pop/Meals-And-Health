const login = document.querySelector('.login');
const register = document.querySelector('.register');
const toRegister = document.getElementById('to-register');
const toLogin = document.getElementById('to-login');
const password = document.getElementById('password');
const rePassword = document.getElementById('re-password');
const registerButton = document.getElementById('register-btn');

toRegister.onclick = _ => {
    register.classList.add('active');
    login.classList.remove('active');
};

toLogin.onclick = _ => {
    login.classList.add('active');
    register.classList.remove('active');
};

function checkMatching(_) {
    const notMatch = document.querySelectorAll('.not-match');
    if (rePassword.value !== password.value) {
        password.classList.add('invalid');
        rePassword.classList.add('invalid');
        notMatch.forEach(error => error.classList.add('password-not-match'));
        registerButton.disabled = true;
    }
    else {
        password.classList.remove('invalid');
        rePassword.classList.remove('invalid');
        notMatch.forEach(error => error.classList.remove('password-not-match'));
        registerButton.disabled = false;
    }
}

password.oninput = checkMatching
rePassword.oninput = checkMatching