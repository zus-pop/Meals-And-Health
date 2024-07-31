const quantityValue = document.querySelector('#quantity');
const increaser = document.querySelector('.increase');
const decreaser = document.querySelector('.decrease');
const cartNoti = document.querySelector('#cart-noti');
const addButton = document.querySelector('#add-with-quantity');

increaser.addEventListener('click', _ => {
    const quantity = increaser.previousElementSibling;
    let num = +quantity.value;
    quantity.value = ++num;
});

decreaser.addEventListener('click', _ => {
    const quantity = decreaser.nextElementSibling;
    let num = +quantity.value;
    if (num > 1) {
        quantity.value = --num;
    }
});

addButton.addEventListener('click', _ => {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/meals_and_health/main?route=customer-add-to-cart',
        data: {
            mealId: addButton.value,
            quantity: quantityValue.value
        },
        success: ({ result, size }) => {
            if (result) {
                cartNoti.classList.add('has-item');
                if (size > 0 && size < 10) {
                    cartNoti.innerHTML = size;
                } else {
                    cartNoti.innerHTML = '9+';
                }
                window.alert('Added successfully!');
            } else {
                window.alert('You need to login first to use this feature!');
        }
        }
    });
});