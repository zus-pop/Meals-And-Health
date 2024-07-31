/* global Intl */

const quantityValues = document.querySelectorAll('.quantity-value');
const increasers = document.querySelectorAll('.increase');
const decreasers = document.querySelectorAll('.decrease');
const formatter = Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
});
const delay = 300;

const checkOutDialog = document.querySelector('#check-out-dialog');
const checkOutBtn = document.querySelector('#checkout-btn');
const cancelBtn = document.querySelector('#cancel-btn');
const removeButtons = document.querySelectorAll('.remove');

const totalCost = document.querySelector('#total-cost');
const cartSize = document.querySelector('#cart-size');
const cartNoti = document.querySelector('#cart-noti');

if (checkOutBtn) {
    checkOutBtn.addEventListener('click', _ => {
        const items = document.querySelectorAll('.cart-row.meal');
        if (!items.length) {
            return window.alert('There is no any meal here to checkout!')
        }
        checkOutDialog.showModal();
    });
}

cancelBtn.addEventListener('click', _ => {
    checkOutDialog.close();
});

let timer;
increasers.forEach(increaser => {
    increaser.addEventListener('click', _ => {
        clearTimeout(timer);
        const bigDaddy = increaser.parentElement.parentElement.parentElement;
        const quantity = increaser.previousElementSibling;
        const totalPrice = bigDaddy.querySelector('.total-price');
        const unitPrice = bigDaddy.querySelector('.unit-price');
        let num = +quantity.value;
        quantity.value = ++num;
        timer = setTimeout(() => {
            $.ajax({
                type: 'POST',
                url: 'main?route=customer-update-cart',
                data: {
                    quantity: quantity.value,
                    mealId: bigDaddy.querySelector('input[name=meal-id]').value
                },
                success: response => {
                    const data = JSON.parse(response);
                    quantity.value = data.quantity;
                    totalPrice.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
                    totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
                },
                error: e => console.log(e)
            });
        }, delay);
    });
});

decreasers.forEach(decreaser => {
    decreaser.addEventListener('click', _ => {
        const quantity = decreaser.nextElementSibling;
        const bigDaddy = decreaser.parentElement.parentElement.parentElement;
        const totalPrice = bigDaddy.querySelector('.total-price');
        const unitPrice = bigDaddy.querySelector('.unit-price');
        let num = +quantity.value;
        if (num > 1) {
            quantity.value = --num;
            clearTimeout(timer);
            timer = setTimeout(() => {
                $.ajax({
                    type: 'POST',
                    url: 'main?route=customer-update-cart',
                    data: {
                        quantity: quantity.value,
                        mealId: bigDaddy.querySelector('input[name=meal-id]').value
                    },
                    success: response => {
                        const data = JSON.parse(response);
                        quantity.value = data.quantity;
                        totalPrice.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
                        totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
                    },
                    error: e => console.log(e)
                });
            }, delay);
        }
    });
});

quantityValues.forEach(quantityValue => {
    quantityValue.addEventListener('change', _ => {
        const bigDaddy = quantityValue.parentElement.parentElement.parentElement;
        const totalPrice = bigDaddy.querySelector('.total-price');
        const unitPrice = bigDaddy.querySelector('.unit-price');
        clearTimeout(timer);
        timer = setTimeout(() => {
            $.ajax({
                type: 'POST',
                url: 'main?route=customer-update-cart',
                data: {
                    quantity: quantityValue.value,
                    mealId: bigDaddy.querySelector('input[name=meal-id]').value
                },
                success: response => {
                    const data = JSON.parse(response);
                    quantityValue.value = data.quantity;
                    totalPrice.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
                    totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
                },
                error: e => console.log(e)
            });
        }, delay);
    });
});

removeButtons.forEach(removeButton => {
    removeButton.addEventListener('click', _ => {
        const bigDaddy = removeButton.parentElement.parentElement;
        const mealId = bigDaddy.querySelector('input[name=meal-id]');
        $.ajax({
            type: 'POST',
            url: 'main?route=customer-remove-item-cart',
            data: {
                mealId: mealId.value
            },
            success: response => {
                const data = JSON.parse(response);
                const result = data.result;
                if (result) {
                    totalCost.innerHTML = formatter.format(data.totalCost);
                    let content;
                    if (data.size > 1)
                        content = `Total (${data.size} items):`;
                    else
                        content = `Total (${data.size} item):`;
                    cartSize.innerHTML = content;
                    bigDaddy.remove();
                    if (data.size <= 0) {
                        cartNoti.classList.remove('has-item');
                    } else if (data.size > 0 && data.size < 10) {
                        cartNoti.innerHTML = data.size;
                    } else {
                        cartNoti.innerHTML = `9+`;
                    }
                }
            },
            error: e => console.log(e)
        });
    });
});